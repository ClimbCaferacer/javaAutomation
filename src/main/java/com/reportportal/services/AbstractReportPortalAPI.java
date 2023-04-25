package com.reportportal.services;

import io.restassured.specification.RequestSpecification;

public class AbstractReportPortalAPI extends AbstractAPI {


    @Override
    protected RequestSpecification getClient() {
            return ClientFactory.getClient()
                .baseUri(PROPS.baseUrl())
                .header("Authorization",  "Bearer " + "e7778433-04d9-41e0-9fa2-630f6bf6c96c");
    }
}
