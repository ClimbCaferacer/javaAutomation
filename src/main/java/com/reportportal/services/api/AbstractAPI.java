package com.reportportal.services.api;

import com.reportportal.configuration.CommonProps;
import io.restassured.specification.RequestSpecification;
import org.aeonbits.owner.ConfigFactory;

public abstract class AbstractAPI {
    public static final CommonProps PROPS = ConfigFactory.create(CommonProps.class);
    protected abstract RequestSpecification getClient();
}
