package org.example.autoTestBrows.alerts;

import io.qameta.allure.Description;
import io.qameta.allure.Link;
import org.example.WebHook;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pages.alerts.Alerts;

@Link(name = "WEB Sandbox", url = "https://aqa-proka4.org/sandbox/web")
public class AlertsAutoTest extends WebHook {
    private final Alerts alerts = new Alerts();

    @Test
    @Tag("alert")
    @DisplayName("Взаимодействие с простым alert")
    @Description("Открытие и закрытие простого alert")
    public void testAlert(){
        alerts.clickAlertButton();
        alerts.checkTextAndCloseAlert();
    }

    @Test
    @Tag("alert")
    @DisplayName("Взаимодействие с confirm alert")
    @Description("Открытие и закрытие confirm alert")
    public void testConfirmAlert(){
        alerts.clickAlertConfirmButton();
        alerts.checkTextAndCloseAlertConfirm();
    }

    @Test
    @Tag("alert")
    @DisplayName("Взаимодействие с prompt alert")
    @Description("Открытие и закрытие prompt alert")
    public void testPromptAlert(){
        alerts.clickAlertPromptButton();
        alerts.setTextPromptAlert("Sergey");
        alerts.checkTextAndCloseAlertPrompt();
    }
}
