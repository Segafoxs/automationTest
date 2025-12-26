package pages.alerts;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$x;

public class Alerts {

    private final SelenideElement alertButton = $x("//button[@id='alertBtn']").as("простой алерт");
    private final SelenideElement alertConfirmButton = $x("//button[@id='confirmBtn']").as("confirm");
    private final SelenideElement alertPromtButton = $x("//button[@id='promptBtn']").as("prompt");
    private String text = "";

    @Step("Открываем алерт")
    public void clickAlertButton(){
        alertButton.shouldBe(Condition.clickable, Duration.ofSeconds(8));
        alertButton.click();
    }

    @Step("Открываем confirm alert")
    public void clickAlertConfirmButton(){
        alertConfirmButton.shouldBe(Condition.clickable, Duration.ofSeconds(8));
        alertConfirmButton.click();
    }

    @Step("Открываем prompt alert")
    public void clickAlertPromptButton(){
        alertPromtButton.shouldBe(Condition.clickable, Duration.ofSeconds(8));
        alertPromtButton.click();
    }

    @Step("Проверяем текст у алерта и закрываем его")
    public void checkTextAndCloseAlert(){
        Selenide.confirm();
    }

    @Step("Проверяем текст у Confirm Alert и закрываем его")
    public void checkTextAndCloseAlertConfirm(){
        Selenide.confirm("Вы уверены?");
    }

    @Step("Проверяем текст у Prompt Alert и закрываем его")
    public void checkTextAndCloseAlertPrompt(){
        Selenide.confirm(String.format("Привет, %s!", text));
    }

    @Step("Вводим текст '{text}'")
    public void setTextPromptAlert(String text){
        this.text = text;
        Selenide.prompt(text);
    }
}
