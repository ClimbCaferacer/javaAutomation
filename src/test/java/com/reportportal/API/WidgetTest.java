package com.reportportal.API;

import com.reportportal.BaseTest;
import com.reportportal.services.api.widget.create.request.CreateWidgetRequest;
import com.reportportal.services.api.widget.edit.request.EditWidgetRequest;
import org.assertj.core.api.Assertions;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static java.lang.Integer.parseInt;
import static java.lang.System.currentTimeMillis;

public class WidgetTest extends BaseTest {



    @DataProvider
    public Object[][] widgetNames() {

        return new Object[][]{
                {"Widget1"},
                {"Widget2"},
                {"Widget3"},
                {"Widget4"},
                {"Widget5"},
        };
    }

    @Test
    public void verifyGetWidget() {
        var actualOwner = widgetAPI.getWidgetById(PROPS.defaultProjectName(), 6)
                .expectStatusOk().asDto().getOwner();
        Assertions.assertThat(actualOwner).as("Wrong owner in response.")
                .isEqualTo("superadmin");
    }

    @Test
    public void verifyGetWidgetNegative() {
        var actualOwner = widgetAPI.getWidgetById(PROPS.defaultProjectName(), (int) currentTimeMillis())
                .expectStatusNotFoundRequest().response().extract().body().jsonPath().get("errorCode");
        Assertions.assertThat(actualOwner).as("Wrong error code.")
                .isEqualTo(40420);
    }

    @Test(dataProvider = "widgetNames")
    public void verifyCreateWidget(String widgetName) {
        widgetAPI.createWidget(PROPS.defaultProjectName(), widgetName).expectStatusOk();
    }

    @Test
    public void verifyCreateWidgetWithIncorrectBody() {
        var errorCode = widgetAPI.createWidget(CreateWidgetRequest.builder().build(),PROPS.defaultProjectName(), "widgetName" + currentTimeMillis())
                .expectStatusBadRequest().response().extract().body().jsonPath().get("errorCode");
        Assertions.assertThat(errorCode).as("Wrong error code.")
                .isEqualTo(40420);
    }

    @Test
    public void verifyCreateWidgetWithIncorrectProjectName() {
        var errorCode = widgetAPI.createWidget("incorrect_project", "widgetName" + currentTimeMillis())
                .expectStatusForbidden().response().extract().body().jsonPath().get("message");
        Assertions.assertThat(errorCode).as("Wrong error code.")
                .isEqualTo("You do not have enough permissions. Please check the list of your available projects.");
    }

    @Test
    public void verifyDeleteWidget() {
        var widgetId = dashboardAPI.getDashboardById(PROPS.defaultProjectName(), 17).asDto()
                .getWidgets().stream()
                .findFirst().get().getWidgetId();
        dashboardAPI.deleteWidget(PROPS.defaultProjectName(), 17, widgetId);
    }

    @Test
    public void verifyEndToEndWidgetScenario() {
        var widgetId = widgetAPI.createWidget(PROPS.defaultProjectName(), "aqaWidget" + currentTimeMillis())
                .expectStatusOk().asDto().getId();
        widgetAPI.editWidget(widgetAPI.editWidgetRequestBuilder("NEW WIDGET NAME" + currentTimeMillis()), PROPS.defaultProjectName(), widgetId).expectStatusOk();
        dashboardAPI.deleteWidget(PROPS.defaultProjectName(), 17, parseInt(widgetId)).expectStatusOk();
    }

    @Test
    public void verifyWidgetScenarioWithIncorrectBody() {
        var widgetId = widgetAPI.createWidget(PROPS.defaultProjectName(), "aqaWidget" + currentTimeMillis())
                .expectStatusOk().asDto().getId();
        widgetAPI.editWidget(EditWidgetRequest.builder().build(), PROPS.defaultProjectName(), widgetId).expectStatusBadRequest();
    }

    @Test
    public void verifyWidgetScenarioWithIncorrectProject() {
        var widgetId = widgetAPI.createWidget(PROPS.defaultProjectName(), "aqaWidget" + currentTimeMillis())
                .expectStatusOk().asDto().getId();
        var message = widgetAPI.editWidget(widgetAPI.editWidgetRequestBuilder("NEW WIDGET NAME"), "incorrect_project", widgetId)
                .expectStatusForbidden().response().extract().body().jsonPath().get("message");
        Assertions.assertThat(message).as("Incorrect message")
                .isEqualTo("You do not have enough permissions. Please check the list of your available projects.");
    }
}
