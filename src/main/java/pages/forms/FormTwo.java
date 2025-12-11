package pages.forms;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import lombok.extern.slf4j.Slf4j;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$x;

@Slf4j
public class FormTwo {
    private final SelenideElement userNameField = $x("//form[@id='validationForm']//input[@id='val-username']").as("имя пользователя");
    private final SelenideElement messageFailEnterUserName = $x("//form[@id='validationForm']//p[@id='username-error']").as("сообщение валидации для поля userName");
    private final SelenideElement emailField = $x("//form[@id='validationForm']//input[@id='val-email']").as("email");
    private final SelenideElement messageFailEnterEmail = $x("//form[@id='validationForm']//p[@id='email-error']").as("сообщение валидации для поля email");
    private final SelenideElement passwordField = $x("//form[@id='validationForm']//input[@id='val-password']").as("пароль");
    private final SelenideElement messageFailEnterPassword = $x("//form[@id='validationForm']//p[@id='password-error']").as("сообщение валидации для поля password");
    private final SelenideElement passwordConfirmField = $x("//form[@id='validationForm']//input[@id='val-confirm-password']").as("повтор пароля");
    private final SelenideElement messageFailEnterConfirmPassword = $x("//form[@id='validationForm']//p[@id='confirm-password-error']").as("сообщение валидации для поля password");
    private final SelenideElement buttonSumbit = $x("//form[@id='validationForm']//button[@id='valSubmitBtn']").as("кнопка отправки формы");
    private final SelenideElement errorMessage = $x("//div[@id='valFormResult']//p[contains(text(), 'Форма содержит ошибки')]");

    public void enterForm(String userName, String email, String password) {
        enterUserName(userName);
        enterEmail(email);
        enterPassword(password);
        enterPasswordConfirm(password);
        clickButton();
    }

    public void enterForm(String userName, String email, String password, String enterPassword) {
        enterUserName(userName);
        enterEmail(email);
        enterPassword(password);
        enterPasswordConfirm(enterPassword);
        clickButton();
    }

    private void enterUserName(String userName){
        userNameField.shouldBe(Condition.visible, Duration.ofSeconds(10));
        userNameField.setValue(userName);
    }

    private void enterEmail(String email){
        emailField.shouldBe(Condition.visible, Duration.ofSeconds(10));
        emailField.setValue(email);
    }

    private void enterPassword(String password){
        passwordField.shouldBe(Condition.visible, Duration.ofSeconds(10));
        passwordField.setValue(password);
    }

    private void enterPasswordConfirm(String password){
        passwordConfirmField.shouldBe(Condition.visible, Duration.ofSeconds(10));
        passwordConfirmField.setValue(password);
    }

    private void clickButton(){
        buttonSumbit.click();
    }

    public String getMessage(String nameElement){
        String message = "";
        switch (nameElement){
            case "userName":
                messageFailEnterUserName.shouldBe(Condition.visible);
                message = messageFailEnterUserName.text();
                break;
            case "email" :
                messageFailEnterEmail.shouldBe(Condition.visible);
                message = messageFailEnterEmail.text();
                break;
            case "password" :
                messageFailEnterPassword.shouldBe(Condition.visible);
                message = messageFailEnterPassword.text();
                break;
            case "confirmPassword" :
                messageFailEnterConfirmPassword.shouldBe(Condition.visible);
                message = messageFailEnterConfirmPassword.text();
                break;
            case "errorMessage":
                errorMessage.shouldBe(Condition.visible);
                message = errorMessage.text();
                break;
        }
        log.info(message);
        return message;
    }

}
