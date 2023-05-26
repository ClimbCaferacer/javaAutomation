package com.reportportal.API;

import com.reportportal.BaseTest;
import org.junit.Test;

import java.io.IOException;

import static java.lang.System.currentTimeMillis;
import static org.assertj.core.api.Assertions.assertThat;

public class ApacheWidgetTest extends BaseTest {

    @Test
    public void verifyCreateWidgetApache() throws IOException {
            var response = widgetAPI
                    .apacheClientPost(PROPS.defaultProjectName(), "AQAWidget" + currentTimeMillis());

            assertThat(response.has("id")).as("Id should be present").isTrue();
    }

    @Test
    public void verifyGetWidgetApacheWithWrongWidgetId() throws IOException {
        var response = widgetAPI
                .apacheClientGet(String.valueOf(currentTimeMillis()));
        assertThat(response.get("statusCode")).as("Wrong status code").isEqualTo(404);
        assertThat(response.has("errorCode")).as("Error message should be present").isTrue();

    }

    @Test
    public void verifyGetWidgetApache() throws IOException {
        var response = widgetAPI
                .apacheClientGet("6");
        assertThat(response.get("statusCode")).as("Wrong status code").isEqualTo(200);
        assertThat(response.has("name")).as("Name should be present").isTrue();
    }

    @Test
    public void verifyEditWidget() throws IOException {

        var response = widgetAPI
                .apacheClientPost(PROPS.defaultProjectName(), "AQAWidget" + currentTimeMillis());

        var id = response.get("id").toString();
        var putResponse = widgetAPI
                .apacheClientPut(PROPS.defaultProjectName(), id);
        assertThat(putResponse.get("statusCode")).as("Wrong status code").isEqualTo(200);
        assertThat(putResponse.get("message").toString()).as("Name should be present").contains("successfully updated");
    }
}
