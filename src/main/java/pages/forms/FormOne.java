package pages.forms;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import lombok.extern.slf4j.Slf4j;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$x;

@Slf4j
public class FormOne {
    private final SelenideElement userNameField = $x("//form[@id='registrationForm']//input[@id='username']").as("имя пользователя");
    private final SelenideElement emailField = $x("//form[@id='registrationForm']//input[@id='email']").as("email");
    private final SelenideElement passwordField = $x("//form[@id='registrationForm']//input[@id='password']").as("пароль");
    private final SelenideElement countryField = $x("//form[@id='registrationForm']//select[@id='country']").as("страна проживания");
    private final SelenideElement terms = $x("//form[@id='registrationForm']//input[@id='terms']").as("чекбокс");
    private final SelenideElement submitButtom = $x("//form[@id='registrationForm']//button[@id='submitBtn']").as("кнопка отправки формы");
    private final SelenideElement successyText = $x("//div[@id='formResult']//p").as("сообщение об успешной отправке");

    public void enterForms(String userName, String email,
                           String password, String country){
        enterUserName(userName);
        enterEmail(email);
        enterPassword(password);
        selectListCountry(country);
        clickTerms();
        clickSubmitButton();
        checkText();
    }

    private void enterUserName(String userName){
        userNameField.shouldBe(Condition.visible, Duration.ofSeconds(8));
        userNameField.setValue(userName);
    }

    private void enterEmail(String email){
        emailField.shouldBe(Condition.visible, Duration.ofSeconds(8));
        emailField.setValue(email);
    }

    private void enterPassword(String password){
        passwordField.shouldBe(Condition.visible, Duration.ofSeconds(8));
        passwordField.setValue(password);
    }

    private void selectListCountry(String country){
        countryField.shouldBe(Condition.visible, Duration.ofSeconds(8));
        countryField.selectOption(country);
    }

    private void clickTerms(){
        terms.click();
    }

    private void clickSubmitButton(){
        submitButtom.click();
    }

    private void checkText(){
        log.info(String.format("Текст сообщения: %s", successyText.text()));
        successyText.shouldHave(Condition.text("Форма успешно отправлена!"));
    }
}
