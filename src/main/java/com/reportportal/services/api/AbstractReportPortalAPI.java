package com.reportportal.services.api;

import io.restassured.specification.RequestSpecification;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class AbstractReportPortalAPI extends AbstractAPI {

    protected static String bearer;

    static {
        try {
            bearer = getBearer();
        } catch (IOException e) {
            System.out.println("Can't read from file orfile doesn't exist.");
        }
    }

    protected static String getBearer() throws IOException {
        String file ="src/main/java/com/reportportal/bearer.txt";

        BufferedReader reader = new BufferedReader(new FileReader(file));
        String currentLine = reader.readLine();
        reader.close();
        return currentLine;
    }


    @Override
    protected RequestSpecification getClient() {
            return ClientFactory.getClient()
                .baseUri(PROPS.baseUrl())
                .header("Authorization",  "Bearer " + bearer);
    }

}
