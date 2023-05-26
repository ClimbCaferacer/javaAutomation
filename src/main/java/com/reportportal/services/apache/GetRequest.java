package com.reportportal.services.apache;

import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.hamcrest.MatcherAssert;
import org.hamcrest.core.IsEqual;
import org.junit.Test;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class GetRequest {

    @Test
    public void getWidgetById(String projectName, String widgetId) {

    }

    @Test
    public void getWithNameValuePairParams() throws IOException {
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
            System.out.println("Protocol version:" + response.getProtocolVersion());
            System.out.println("Response status line:" + response.getStatusLine());
            System.out.println("Response status code:" + response.getStatusLine().getStatusCode());
            System.out.println("Response reason phrase:" + response.getStatusLine().getReasonPhrase());

            HttpEntity entity = response.getEntity();
            System.out.println("Response content:\n" + EntityUtils.toString(entity));
            EntityUtils.consume(entity);
        }
    }

    @Test
    public void getWithUrlParams() throws IOException, URISyntaxException {
        URI uri = new URIBuilder()
                .setScheme("https")
                .setHost("samples.openweathermap.org")
                .setPath("/data/2.5/weather")
                .setParameter("q", "Minsk,BY")
                .setParameter("appid", "b6907d289e10d714a6e88b30761fae22")
                .build();

        HttpGet httpGet = new HttpGet(uri);

        try (
                CloseableHttpClient httpclient = HttpClients.createDefault();
                CloseableHttpResponse response = httpclient.execute(httpGet);
        ) {
            int statusCode = response.getStatusLine().getStatusCode();
            MatcherAssert.assertThat(statusCode, IsEqual.equalTo(HttpStatus.SC_OK));
            HttpEntity entity = response.getEntity();
            System.out.println("Response content:\n" + EntityUtils.toString(entity));
            EntityUtils.consume(entity);
        }
    }
}

