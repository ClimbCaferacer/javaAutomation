package com.reportportal.services.apache;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.FileEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PostRequest {

    @Test
    public void postWithJson() throws IOException {
        HttpPost httpPost = new HttpPost("http://httpbin.org/post");

        String json = "{\"id\":1,\"name\":\"John\"}";
        StringEntity entity = new StringEntity(json);
        httpPost.setEntity(entity);
        httpPost.setHeader("Accept", "application/json");
        httpPost.setHeader("Content-type", "application/json");

        try (
                CloseableHttpClient httpclient = HttpClients.createDefault();
                CloseableHttpResponse response = httpclient.execute(httpPost);
        ) {
            System.out.println("Response status:\n" + response.getStatusLine());
            System.out.println("Response header:\n" + response.getFirstHeader("Date"));
            HttpEntity responseEntity = response.getEntity();
            System.out.println("Response content:\n" + EntityUtils.toString(responseEntity));
            EntityUtils.consume(responseEntity);
        }
    }

    @Test
    public void postForm() throws IOException {
        List<NameValuePair> form = new ArrayList<>();
        form.add(new BasicNameValuePair("id", "334573"));
        form.add(new BasicNameValuePair("employee", "John Doe"));
        UrlEncodedFormEntity entity = new UrlEncodedFormEntity(form, Consts.UTF_8);

        HttpPost httpPost = new HttpPost("http://httpbin.org/post");
        httpPost.setEntity(entity);

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
    public void postFileMultiPartForm() throws IOException {
        HttpPost httpPost = new HttpPost("http://httpbin.org/post");

        MultipartEntityBuilder builder = MultipartEntityBuilder.create();
        File binaryFile = new File("C:\\Data\\src\\apachehttp\\src\\main\\resources\\google.png");
        builder.addBinaryBody("file", binaryFile, ContentType.IMAGE_PNG, binaryFile.getName())
                .addTextBody("text", "File description", ContentType.DEFAULT_TEXT);
        HttpEntity multipart = builder.build();
        httpPost.setEntity(multipart);
        try (
                CloseableHttpClient httpclient = HttpClients.createDefault();
                CloseableHttpResponse response = httpclient.execute(httpPost);
        ) {
            HttpEntity responseEntity = response.getEntity();
            System.out.println("Response content:\n" + EntityUtils.toString(responseEntity));
            Assert.assertEquals(200, response.getStatusLine().getStatusCode());
            EntityUtils.consume(responseEntity);
        }
    }

    @Test
    public void postFileEntity() throws IOException {
        HttpPost httpPost = new HttpPost("http://httpbin.org/post");
        File fileToUpload = new File("C:\\Data\\src\\apachehttp\\src\\main\\resources\\google.png");
        FileEntity entity = new FileEntity(fileToUpload, ContentType.IMAGE_PNG);
        httpPost.setEntity(entity);
        try (
                CloseableHttpClient httpclient = HttpClients.createDefault();
                CloseableHttpResponse response = httpclient.execute(httpPost);
        ) {
            HttpEntity responseEntity = response.getEntity();
            System.out.println("Response content:\n" + EntityUtils.toString(responseEntity));
            Assert.assertEquals(200, response.getStatusLine().getStatusCode());
            EntityUtils.consume(responseEntity);
        }
    }
}
