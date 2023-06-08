package com.reportportal.services.api;

import io.restassured.response.Response;
import io.restassured.response.ResponseBodyData;
import io.restassured.response.ValidatableResponse;
import lombok.Getter;
import lombok.SneakyThrows;
import org.apache.http.HttpException;
import org.hamcrest.Matcher;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Optional;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;

public abstract class AbstractResponseWrapper<T> {
    private static final String DEFAULT_STATUS_CODE_ERROR = "Wrong status code!";
    private static final int RESPONSE_CODE_300 = 300;
    private static final int RESPONSE_CODE_400 = 400;
    private static final int RESPONSE_CODE_404 = 404;
    private static final int RESPONSE_CODE_403 = 403;
    private static final int RESPONSE_CODE_500 = 500;

    private final ValidatableResponse response;

    @SuppressWarnings("OptionalUsedAsFieldOrParameterType")
    private Optional<Type> type;

    @Getter
    private static ValidatableResponse latestResponse;

    protected AbstractResponseWrapper(Response response) {
        this.response = response.then();
        latestResponse = this.response;
        setTypeRef();
    }

    public static <T> T getLatestResponse(Class<T> bodyType) {
        return latestResponse.extract().body().as(bodyType);
    }

    private void setTypeRef() {
        Type superClass = this.getClass().getGenericSuperclass();
        if (superClass instanceof Class) {
            this.type = Optional.empty();
        } else {
            this.type = Optional
                    .ofNullable(((ParameterizedType) superClass).getActualTypeArguments()[0]);
        }
    }

    /**
     * Use {@link ResponseBodyData#asString()} in case of string response.
     */
    public T asDto() {
        expectStatusOk();
        return response.extract().body().as(type
                .orElseThrow(
                        () -> new NullPointerException("Return type not defined for ResponseWrapper")));
    }

    public AbstractResponseWrapper<T> expectStatusOk() {
        return expectStatusOk(DEFAULT_STATUS_CODE_ERROR);
    }
    @SneakyThrows
    public AbstractResponseWrapper<T> expectStatusOk(String errorMessage) {
        validateResponseCode(errorMessage, lessThan(RESPONSE_CODE_300));
        return this;
    }
    @SneakyThrows
    public AbstractResponseWrapper<T> expectStatusBadRequest() {
        validateResponseCode(equalTo(RESPONSE_CODE_400));
        return this;
    }

    @SneakyThrows
    public AbstractResponseWrapper<T> expectStatusNotFoundRequest() {
        validateResponseCode(equalTo(RESPONSE_CODE_404));
        return this;
    }

    @SneakyThrows
    private void validateResponseCode(Matcher<? super Integer> expectedStatusCode) {
        validateResponseCode(DEFAULT_STATUS_CODE_ERROR, expectedStatusCode);
    }

    private void validateResponseCode(String errorMessage,
                                      Matcher<? super Integer> expectedStatusCode) throws HttpException {
        failOnServerError(response);
        try {
            response.statusCode(expectedStatusCode);
        } catch (AssertionError error) {
            throw new HttpException(errorMessage, error);
        }
    }
    @SneakyThrows
    private static void failOnServerError(ValidatableResponse response) {
        if (response.extract().statusCode() >= RESPONSE_CODE_500) {
            throw new HttpException("Failed with server error" + response.extract().body().asString());
        }
    }

    public ValidatableResponse response() {
        return response;
    }

    public AbstractResponseWrapper<T> expectStatusForbidden() {
        validateResponseCode(equalTo(RESPONSE_CODE_403));
        assertEquals(Optional.ofNullable((Integer) this.response.extract().body().jsonPath().get("errorCode")), 4003);
        return this;
    }
}
