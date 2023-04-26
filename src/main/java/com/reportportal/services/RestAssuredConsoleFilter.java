package com.reportportal.services;

import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.impl.EnglishReasonPhraseCatalog;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Locale;
import java.util.StringJoiner;

import static java.lang.String.format;
import static org.apache.commons.lang3.StringUtils.abbreviate;

@Slf4j
public class RestAssuredConsoleFilter implements Filter {

    private static final int MAX_RESPONSE_BODY_SYMBOLS = 200;
    private static final int RESPONSE_CODE_500 = 500;

    @Override
    public Response filter(FilterableRequestSpecification requestSpec,
                           FilterableResponseSpecification responseSpec, FilterContext ctx) {
        final Response response = ctx.next(requestSpec, responseSpec);
        String msg = parseToMessage(requestSpec, response);
        final int statusCode = response.statusCode();
        if (statusCode < RESPONSE_CODE_500) {
            log.debug("{}", msg);
        } else {
            log.error("{}", msg);
            return response;
        }
        return response;
    }

    private String parseToMessage(FilterableRequestSpecification requestSpec,
                                  Response response) {
        final String method = requestSpec.getMethod();
        StringJoiner msg = new StringJoiner(System.lineSeparator());
        String statusReason = EnglishReasonPhraseCatalog.INSTANCE
                .getReason(response.statusCode(), Locale.ENGLISH);

        msg.add(format("%s %s %s %s", method, requestSpec.getURI(), response.getStatusCode(),
                statusReason));
        if (!requestSpec.getMultiPartParams().isEmpty()) {
            try {
                ByteArrayInputStream data = (ByteArrayInputStream) requestSpec.getMultiPartParams()
                        .get(0).getContent();
                data.reset();
                msg.add("Request File: " + requestSpec.getMultiPartParams().get(0).getFileName());
            } catch (ClassCastException e) {
                msg.add("can't parse request file data");
                log.error("can't parse file data", e);
            }
        }
        msg.add("Response body: " + abbreviate(response.getBody().asString(),
                MAX_RESPONSE_BODY_SYMBOLS));
        return msg.toString();
    }
}
