package org.example;

import Utils.TestProperties;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.PageLoadStrategy;

public class WebHook {

    @BeforeEach
    public void initBrowser() {
        Configuration.pageLoadStrategy = PageLoadStrategy.EAGER.toString();
        Configuration.timeout = 10000;
        Selenide.open(TestProperties.getProperty("baseUrl"));
        WebDriverRunner.getWebDriver().manage().window().maximize();
    }
}
