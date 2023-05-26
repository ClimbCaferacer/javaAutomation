package com.reportportal.services.apache;

import org.apache.http.HttpVersion;
import org.apache.http.NameValuePair;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.junit.Test;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class FluentHC {

    @Test
    public void getExample() throws IOException, URISyntaxException {
        URI uri = new URIBuilder().setScheme("http").setHost("api.geonames.org").setPath("postalCodeSearch")
                .addParameter("postalcode", "9011")
                .addParameter("maxRows", "10")
                .addParameter("username", "demo").build();

        String response = Request.Get(uri)
                .addHeader("Accept", "Accept: text/html,application/xhtml+xml")
                .connectTimeout(1000)
                .socketTimeout(1000)
                .execute()
                .returnContent()
                .asString();

        System.out.println(response);
    }

    @Test
    public void postExample() throws IOException {
        List<NameValuePair> form = new ArrayList<>();
        form.add(new BasicNameValuePair("id", "334573"));
        form.add(new BasicNameValuePair("employee", "John Doe"));

        String response = Request.Post("http://httpbin.org/post")
                .useExpectContinue()
                .version(HttpVersion.HTTP_1_1)
                .bodyForm(form)
                .execute().returnContent().asString();

        System.out.println(response);
    }

    @Test
    public void responseHandling() throws IOException {
        JSONObject response = Request.Get("https://restcountries-v1.p.rapidapi.com/capital/tallinn")
                .addHeader("X-RapidAPI-Host", "restcountries-v1.p.rapidapi.com")
                .addHeader("X-RapidAPI-Key", "SIGN-UP-FOR-KEY") //need a registration to get a key
                .connectTimeout(1000)
                .socketTimeout(1000)
                .execute()
                .handleResponse(responseToHandle -> new JSONObject(EntityUtils.toString(responseToHandle.getEntity())));
    }




}
