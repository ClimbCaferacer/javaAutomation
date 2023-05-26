package com.reportportal.services.apache;

import org.apache.http.HeaderIterator;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.hamcrest.MatcherAssert;
import org.hamcrest.core.IsEqual;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;

public class HttpClientCommon {

    @Test
    public void test() throws IOException {
        HttpGet httpGet = new HttpGet("https://samples.openweathermap.org/data/2.5/weather?q=London,uk");
        Assert.assertNotNull(executeRequest(httpGet));
    }

    @Test
    public void requestBuilder() throws IOException {
        /**
         *         RequestBuilder.get();
         *         RequestBuilder.post();
         *         RequestBuilder.put();
         *         RequestBuilder.patch();
         *         RequestBuilder.delete();
         *         RequestBuilder.options();
         */

        HttpUriRequest getRequest = RequestBuilder.get("https://samples.openweathermap.org/data/2.5/weather")
                .addHeader("Accept-Encoding", "gzip, deflate, br")
                .addHeader("Cookie", "any=cookie")
                .addParameter("q", "London,uk")
                .addParameter("appid", "b6907d289e10d714a6e88b30761fae22").build();

        executeRequest(getRequest);
    }

    @Test
    public void responseHandling() throws IOException {
        HttpUriRequest getRequest = RequestBuilder.get("https://samples.openweathermap.org/data/2.5/weather")
                .addHeader("Accept-Encoding", "gzip, deflate, br")
                .addHeader("Cookie", "any=cookie")
                .addParameter("q", "London,uk")
                .addParameter("appid", "b6907d289e10d714a6e88b30761fae22").build();

        // Create a custom response handler
        final ResponseHandler<String> responseHandler = response -> {
            int status = response.getStatusLine().getStatusCode();
            if (status >= 200 && status < 300) {
                HttpEntity entity = response.getEntity();
                return entity != null ? response.getStatusLine() + EntityUtils.toString(entity) : null;
            } else {
                throw new ClientProtocolException("Unexpected response status: " + status);
            }
        };
        //Create client with custom config
        CloseableHttpClient httpclient = HttpClientBuilder.create().setDefaultRequestConfig(getCommonRequestConfig()).build();
        String responseBody = httpclient.execute(getRequest, responseHandler);
        System.out.println("Response data:\n" + responseBody);
    }

    private RequestConfig getCommonRequestConfig() {
        return RequestConfig.custom().
                setConnectionRequestTimeout(1000).setConnectTimeout(1000).setSocketTimeout(1000).build();
    }

    @Test
    public void requestConfig() throws IOException {
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectionRequestTimeout(1000)
                .setConnectTimeout(1000)
                .setSocketTimeout(1000).build();
        HttpGet request = new HttpGet("SAMPLE_URL");
        request.setConfig(requestConfig);
        CloseableHttpClient httpclient = HttpClients.createDefault();
        httpclient.execute(request);
    }

    @Test
    public void headerIterator() throws IOException {
        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("q", "Minsk,BY"));
        params.add(new BasicNameValuePair("appid", "b6907d289e10d714a6e88b30761fae22"));
        String paramString = URLEncodedUtils.format(params, "UTF-8");

        HttpGet httpGet =
                new HttpGet("https://samples.openweathermap.org/data/2.5/weather?" + paramString);

        try (
                CloseableHttpClient httpclient = HttpClients.createDefault();
                CloseableHttpResponse response = httpclient.execute(httpGet);
        ) {
            HeaderIterator it = response.headerIterator();
            while (it.hasNext()) {
                System.out.println(it.next());
            }
        }
    }

    private JSONObject executeRequest(HttpUriRequest getRequest) throws IOException {
        try (CloseableHttpClient httpclient = HttpClients.createDefault(); CloseableHttpResponse response = httpclient.execute(getRequest)) {
            System.out.println("Response status from Status Line:\n" + response.getStatusLine());

            int statusCode = response.getStatusLine().getStatusCode();
            MatcherAssert.assertThat(statusCode, IsEqual.equalTo(HttpStatus.SC_OK));
            JSONObject jsonObject = null;
            HttpEntity entity = response.getEntity();

            if (entity != null) {
                //You can deserialize response to any instance here, JSONObject is just example
                try (InputStream instream = entity.getContent()) {
                    BufferedReader reader = new BufferedReader(new InputStreamReader(instream, StandardCharsets.ISO_8859_1));
                    StringBuilder sb = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null)
                        sb.append(line + "\n");
                    jsonObject = new JSONObject(sb.toString());
                }
            }
            MatcherAssert.assertThat(jsonObject.toString(), is(notNullValue()));
            System.out.println("Response content from JSONObject:\n" + jsonObject.toString());
            return jsonObject;
        }
    }
}
