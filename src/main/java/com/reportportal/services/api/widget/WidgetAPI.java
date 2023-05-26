package com.reportportal.services.api.widget;

import com.reportportal.services.api.AbstractReportPortalAPI;
import com.reportportal.services.api.AbstractResponseWrapper;
import com.reportportal.services.api.widget.create.request.ContentParameters;
import com.reportportal.services.api.widget.create.request.CreateWidgetRequest;
import com.reportportal.services.api.widget.create.request.Filters;
import com.reportportal.services.api.widget.create.response.PostWidgetResponse;
import com.reportportal.services.api.widget.edit.request.EditWidgetRequest;
import com.reportportal.services.api.widget.edit.request.FiltersItem;
import com.reportportal.services.api.widget.edit.request.WidgetOptions;
import com.reportportal.services.api.widget.edit.response.EditWidgetResponse;
import com.reportportal.services.api.widget.get.response.GetWidgetResponse;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.*;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.hamcrest.MatcherAssert;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;

import static java.lang.String.format;
import static java.lang.System.currentTimeMillis;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;

public class WidgetAPI extends AbstractReportPortalAPI {

    HttpClient apacheClient = HttpClients.createDefault();

    public AbstractResponseWrapper<GetWidgetResponse> getWidgetById(String projectName, int widgetId) {
        return new AbstractResponseWrapper<>(
                getClient()
                        .get(Endpoint.WIDGET, projectName, widgetId)){
        };
    }

    public AbstractResponseWrapper<PostWidgetResponse> createWidget(String projectName, String widgetName) {
        return new AbstractResponseWrapper<>(
                getClient().body(createWidgetRequestBuilder(widgetName))
                        .post(Endpoint.POST_WIDGET, projectName)) {
        };
    }

    public AbstractResponseWrapper<PostWidgetResponse> createWidget(CreateWidgetRequest createWidgetRequest, String projectName, String widgetName) {
        return new AbstractResponseWrapper<>(
                getClient().body(createWidgetRequest)
                        .post(Endpoint.POST_WIDGET, projectName)) {
        };
    }

    public AbstractResponseWrapper<EditWidgetResponse> editWidget(EditWidgetRequest editWidgetRequest, String projectName, String widgetId) {
        return new AbstractResponseWrapper<>(
                getClient().body(editWidgetRequest)
                        .put(Endpoint.UPDATE_WIDGET, projectName, widgetId)){
        };
    }


    public EditWidgetRequest editWidgetRequestBuilder(String widgetName) {
        var widgetOptions = WidgetOptions.builder().build();
        var contentParameters =  com.reportportal.services.api.widget.edit.request.ContentParameters.builder()
                .widgetOptions(widgetOptions)
                .itemsCount(600).build();
        var filtersItem = FiltersItem.builder().name("name").value("value").build();

        return EditWidgetRequest.builder()
                .contentParameters(contentParameters)
                .description("new description")
                .filterIds(List.of("4"))
                .filters(List.of(filtersItem))
                .owner("superadmin")
                .name(widgetName)
                .widgetType("componentHealthCheck")
                .share(true)
                .build();
    }

    public CreateWidgetRequest createWidgetRequestBuilder(String widgetName) {
        return CreateWidgetRequest.builder()
                .filterIds(List.of("4"))
                .widgetType("launchesTable")
                .contentParameters(ContentParameters.builder()
                        .contentFields(List.of("name", "number", "status", "statistics$executions$total", "statistics$executions$passed", "statistics$executions$skipped", "statistics$executions$failed"))
                        .itemsCount(50)
                        .build())
                .name(widgetName + RandomStringUtils.random(5))
                .description("Description")
                .filters(List.of(Filters.builder().name("DEMO_FILTER").value("4").build()))
                .share(true)
                .build();
    }



    public JSONObject apacheClientGet(String widgetId) throws IOException {
        HttpGet httpGet = new HttpGet(PROPS.baseUrl() + format(ApacheEndpoint.WIDGET, PROPS.defaultProjectName(), widgetId));
        httpGet.addHeader("Content-Type", "application/json");
        httpGet.addHeader("Accept", "application/json");
        httpGet.addHeader("Authorization", "Bearer " + bearer);
        return executeRequest(httpGet);
    }

    public JSONObject apacheClientPost(String projectName, String widgetName) throws IOException {
        var body = new JSONObject(CreateWidgetRequest.builder().filterIds(List.of("4"))
                .widgetType("launchesTable")
                .contentParameters(ContentParameters.builder()
                        .contentFields(List.of("name", "number", "status", "statistics$executions$total", "statistics$executions$passed", "statistics$executions$skipped", "statistics$executions$failed"))
                        .itemsCount(50)
                        .build())
                .name(widgetName + RandomStringUtils.random(5))
                .description("Description")
                .filters(List.of(Filters.builder().name("DEMO_FILTER").value("4").build()))
                .share(true).build()).toString();

        StringEntity entity = new StringEntity(body);
        var request = RequestBuilder
                .post(PROPS.baseUrl() + format(ApacheEndpoint.POST_WIDGET, projectName))
                .addHeader("Content-Type", "application/json")
                .addHeader("Accept", "application/json")
                .addHeader("Authorization", "Bearer " + bearer)
                .setEntity(entity).build();
        return executeRequest(request);
    }

    public JSONObject apacheClientPut(String projectName, String widgetId) throws IOException {
        var body = new StringEntity(new JSONObject(editWidgetRequestBuilder("NEW WIDGET NAME" + currentTimeMillis())).toString());

        var request = RequestBuilder
                .put(PROPS.baseUrl() + format(ApacheEndpoint.UPDATE_WIDGET, projectName, widgetId))
                .addHeader("Content-Type", "application/json")
                .addHeader("Accept", "application/json")
                .addHeader("Authorization", "Bearer " + bearer)
                .setEntity(body).build();
        System.out.println(body);
        return executeRequest(request);
    }

    public HttpDelete apacheClientDelete() throws IOException {
        HttpDelete httpDelete = new HttpDelete(PROPS.baseUrl());
        httpDelete.addHeader("Content-Type", "application/json, charset=utf-8");
        httpDelete.addHeader("Accept", "application/json");
        httpDelete.addHeader("Authorization", "Bearer " + bearer);
        return httpDelete;
    }

    private JSONObject executeRequest(HttpUriRequest getRequest) throws IOException {
        try (CloseableHttpClient httpclient = HttpClients.createDefault(); CloseableHttpResponse response = httpclient.execute(getRequest)) {
            System.out.println("Response status from Status Line:\n" + response.getStatusLine());

            int statusCode = response.getStatusLine().getStatusCode();
            //MatcherAssert.assertThat(statusCode, is(lessThan(300)));
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
            return jsonObject.put("statusCode", statusCode);
        }
    }

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
    private static final class ApacheEndpoint {
        public static final String WIDGET = "/api/v1/%s/widget/%s";
        public static final String POST_WIDGET = "/api/v1/%s/widget";
        public static final String UPDATE_WIDGET = "/api/v1/%s/widget/%s";
    }


    private static final class Endpoint {
        public static final String WIDGET = "/api/v1/{projectName}/widget/{widgetId}";
        public static final String POST_WIDGET = "/api/v1/{projectName}/widget";
        public static final String UPDATE_WIDGET = "/api/v1/{projectName}/widget/{widgetId}";
    }
}
