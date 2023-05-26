package com.reportportal.services.apache;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import java.io.IOException;

public class PutRequest {

    @Test
    public void putWithJson() throws IOException {

        HttpPut request = new HttpPut("http://httpbin.org/put");

        request.addHeader("Accept", "application/json");
        request.addHeader("Content-type", "application/json");

        String json = "{\r\n" +
                "  \"firstName\": \"John\",\r\n" +
                "  \"lastName\": \"Doe\",\r\n" +
                "  \"emailId\": \"test@gmail.com\"" +
                "}";

        StringEntity stringEntity = new StringEntity(json);
        request.setEntity(stringEntity);
        executeRequest(request);

    }

    private int executeRequest(HttpUriRequest getRequest) throws IOException {
        try (CloseableHttpClient httpclient = HttpClients.createDefault();
             CloseableHttpResponse response = httpclient.execute(getRequest)) {
            System.out.println("Response status:\n" + response.getStatusLine());
            HttpEntity entity = response.getEntity();
            System.out.println("Response content:\n" + EntityUtils.toString(entity));
            EntityUtils.consume(entity);
            return response.getStatusLine().getStatusCode();
        }
    }
}
