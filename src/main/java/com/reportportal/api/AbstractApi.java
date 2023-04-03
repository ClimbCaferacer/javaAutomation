package com.reportportal.api;

import com.reportportal.configuration.CommonProps;
import io.restassured.specification.RequestSpecification;
import org.aeonbits.owner.ConfigFactory;

public abstract class AbstractApi {
    public static final CommonProps PROPS = ConfigFactory.create(CommonProps.class);
    protected abstract RequestSpecification getClient();
}
