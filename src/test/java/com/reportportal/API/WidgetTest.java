package com.reportportal.API;

import com.reportportal.BaseTest;
import org.assertj.core.api.Assertions;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Objects;

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
        var actualOwner = widgetAPI.getWidgetById("superadmin_personal", 6)
                .expectStatusOk().asDto().getOwner();
        Assertions.assertThat(actualOwner).as("Wrong owner in response.")
                .isEqualTo("superadmin");
    }

    @Test(dataProvider = "widgetNames")
    public void verifyCreateWidget(String widgetName) {
        widgetAPI.createWidget("superadmin_personal", widgetName).expectStatusOk();
    }

    @Test
    public void verifyDeleteWidget() {
        var widgetId = dashboardAPI.getDashboardById("superadmin_personal", 17).asDto()
                .getWidgets().stream()
                .filter(widget -> Objects.equals(widget.getWidgetName(), "NEW WIDGET"))
                .findFirst().get().getWidgetId();
        dashboardAPI.deleteWidget("superadmin_personal", 17, widgetId);
    }
}
