package com.reportportal.services.api;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class ClientFactory {

    public static RequestSpecification getClient() {
        return RestAssured
                .given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .redirects().follow(false);
    }
}
