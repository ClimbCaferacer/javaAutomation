package com.reportportal.services.apache;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Base64;

public class PostWithAuth {
    @Test
    public void post() throws URISyntaxException, IOException {
        URI iru = new URIBuilder().setHost("api.instagram.com").
                setScheme("https").
                setPath("/oauth/authorize").
                addParameter("client_id", "7e1cab3540b04452969f4cdca760d66e").
                addParameter("redirect_uri", "http://www.example.com/")
                .addParameter("response_type", "token").build();


        HttpUriRequest httpPost = RequestBuilder.get(iru).build();

        try (
                CloseableHttpClient httpclient = HttpClients.createDefault();
                CloseableHttpResponse response = httpclient.execute(httpPost);
        ) {
            System.out.println("Response status:\n" + response.getStatusLine());
            HttpEntity responseEntity = response.getEntity();
            System.out.println("Response content:\n" + EntityUtils.toString(responseEntity));
            EntityUtils.consume(responseEntity);
        }

    }

    @Test
    public void test() {
        CloseableHttpClient client = HttpClientBuilder.create().build();

        HttpPost request = new HttpPost("https://api.us.onelogin.com/auth/oauth2/v2/token");

        String credentials = String.format("%s:%s", "ONELOGIN CLIENT ID" , "ONELOGIN CLIENT SECRET");
        byte[] encodedAuth = Base64.getEncoder().encode(credentials.getBytes());
        String authHeader = "Basic " + new String(encodedAuth);

        request.setHeader("Authorization", authHeader);
        request.addHeader("Content-Type", "application/json");
        request.setEntity(new StringEntity("{ \"grant_type\": \"client_credentials\" }", "UTF-8"));

        try {
            CloseableHttpResponse reponse = client.execute(request);
            String content = EntityUtils.toString(reponse.getEntity());
            System.out.println(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
