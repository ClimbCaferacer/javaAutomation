package com.reportportal.services.apache;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class DeleteRequest {

    @Test
    public void deleteRequest() throws IOException, URISyntaxException {
        URI uri = new URIBuilder()
                .setScheme("http")
                .setHost("httpbin.org")
                .setPath("delete/12345").build();

        HttpDelete httpDelete = new HttpDelete(uri);
        httpDelete.setHeader("Accept-Encoding", "gzip, deflate, br");
        int res = executeRequest(httpDelete);
        Assert.assertEquals(202, res);
    }

    private int executeRequest(HttpUriRequest getRequest) throws IOException {
        try (CloseableHttpClient httpclient = HttpClients.createDefault(); CloseableHttpResponse response = httpclient.execute(getRequest)) {
            HttpEntity entity = response.getEntity();
            System.out.println("Response content:\n" + EntityUtils.toString(entity));
            EntityUtils.consume(entity);
            return response.getStatusLine().getStatusCode();
        }
    }
}
