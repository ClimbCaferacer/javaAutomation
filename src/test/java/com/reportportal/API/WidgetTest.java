package com.reportportal.API;

import com.reportportal.BaseTest;
import com.reportportal.services.widget.WidgetAPI;
import org.assertj.core.api.Assertions;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class WidgetTest extends BaseTest {

    private final WidgetAPI widgetAPI = new WidgetAPI();

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


    public void verifyGetWidget(String widgetNames) {
        var actualOwner = widgetAPI.getWidgetById("superadmin_personal", 6)
                .expectStatusOk().asDto().getOwner();
        Assertions.assertThat(actualOwner).as("Wrong owner in response.")
                .isEqualTo("superadmin");
    }

    @Test(dataProvider = "widgetNames")
    public void verifyCreateWidget(String widgetName) {
        widgetAPI.createWidget("superadmin_personal", widgetName).expectStatusOk();
    }
}
