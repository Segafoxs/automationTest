package org.example.autoTestBrows.forms;

import Utils.TestProperties;
import io.qameta.allure.*;
import messageError.MessageError;
import lombok.extern.slf4j.Slf4j;
import org.example.WebHook;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.forms.FormOne;
import pages.forms.FormThree;
import pages.forms.FormTwo;

import static io.qameta.allure.SeverityLevel.BLOCKER;
import static io.qameta.allure.SeverityLevel.NORMAL;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Slf4j
@Link(name = "WEB Sandbox", url = "https://aqa-proka4.org/sandbox/web")
public class FormsAutoTest extends WebHook {
    private final FormOne formOne = new FormOne();
    private final FormTwo formTwo = new FormTwo();
    private final FormThree formThree = new FormThree();

    @Test
    @DisplayName("Тестирование формы регистрации №1")
    @Description("Данный тест проверяет работу формы №1")
    @Epic("Регистрация")
    @Severity(BLOCKER)
    public void testFormOne(){
        formOne.enterForms(TestProperties.getProperty("user.name"), TestProperties.getProperty("user.email"),
                TestProperties.getProperty("user.password"), TestProperties.getProperty("user.country"));
    }

    @Test
    @DisplayName("Тестирование формы регистрации с валидацией №2")
    @Description("Данный тест проверяет работу формы №2")
    @Epic("Регистрация")
    @Severity(BLOCKER)
    public void testFormTwo(){
        formTwo.enterForm(TestProperties.getProperty("user.name"), TestProperties.getProperty("user.email"),
                TestProperties.getProperty("user.password"));
    }

    @Test
    @DisplayName("Тестирование формы регистрации с валидацией №2")
    @Description("Данный тест проверяет корректность валидации формы №2")
    @Feature("Валидация")
    @Severity(NORMAL)
    public void testFormTwoStepTwo(){
        formTwo.enterForm(TestProperties.getProperty("user.name.error"), TestProperties.getProperty("user.email.error"),
                TestProperties.getProperty("user.password.error"),TestProperties.getProperty("user.password.error")+"23");
        assertEquals(MessageError.USER_NAME_ERROR.getMessage(), formTwo.getMessage("userName"), MessageError.INVALID_MESSAGE.getMessage());
        assertEquals(MessageError.EMAIL_ERROR.getMessage(), formTwo.getMessage("email"), MessageError.INVALID_MESSAGE.getMessage());
        assertEquals(MessageError.PASSWORD_ERROR.getMessage(), formTwo.getMessage("password"), MessageError.INVALID_MESSAGE.getMessage());
        assertEquals(MessageError.PASSWORD_CONFIRM_ERROR.getMessage(), formTwo.getMessage("confirmPassword"), MessageError.INVALID_MESSAGE.getMessage());
    }

    @Test
    @DisplayName("Тестирование динамической формы регистрации №3")
    @Description("Данный тест проверяет работу формы №3 с динамическими полями " +
            "и структуру сообщения об успешной отправке формы")
    @Severity(BLOCKER)
    public void testFormThree(){
        formThree.enterName(TestProperties.getProperty("user.name"));
        formThree.enterEmail(TestProperties.getProperty("user.email"));
        formThree.enterPhone(TestProperties.getProperty("user.phone"));
        formThree.clickButtonSubmit();
        assertEquals("Форма успешно отправлена!", formThree.getTextMessage("successyMessage"));
        assertTrue(formThree.getTextMessage("nameUser").contains("Имя: "));
        assertTrue(formThree.getTextMessage("emailUser").contains("Email"));
        assertTrue(formThree.getTextMessage("phoneUser").contains("Телефоны"));
    }

    @Test
    @DisplayName("Тестирование динамической формы регистрации №3")
    @Description("Данный тест проверяет корректность работы кнопок \"Добавить email\" и кнопки \"Добавить телефон\" формы №3 с динамическими полями " +
            "и структуру сообщения об успешной отправке формы")
    @Feature("Добавить поле")
    @Severity(BLOCKER)
    public void testFormThreeStepTwo(){
        int countEmailField = 2;
        int countPhoneField = 1;
        String phone = "89999999999";
        formThree.enterName(TestProperties.getProperty("user.name"));
        formThree.clickAddButton("email", countEmailField);
        formThree.enterEmail(TestProperties.getProperty("user.email"), "admin@yandex.ru", "user@yandex.ru");
        formThree.clickAddButton("phone", countPhoneField);
        formThree.enterPhone(TestProperties.getProperty("user.phone"), phone);
        formThree.clickButtonSubmit();
        assertEquals("Форма успешно отправлена!", formThree.getTextMessage("successyMessage"), MessageError.INVALID_MESSAGE.getMessage());
        assertEquals(String.format(formThree.getTextMessage("nameUser"), TestProperties.getProperty("user.name")), formThree.getTextMessage("nameUser"), MessageError.INVALID_MESSAGE.getMessage());
        assertEquals(String.format("Email (%d): %s, %s, %s",
                countEmailField+1, TestProperties.getProperty("user.email"),"admin@yandex.ru", "user@yandex.ru"), formThree.getTextMessage("emailUser"), MessageError.INVALID_MESSAGE.getMessage());
        assertEquals(String.format("Телефоны (%d): %s, %s", countPhoneField+1, TestProperties.getProperty("user.phone"), phone),
                formThree.getTextMessage("phoneUser"));
    }

    @Test
    @DisplayName("Тестирование динамической формы регистрации №3")
    @Description("Данный тест проверяет работу кнопки удаления поля формы №3")
    @Flaky
    @Feature("Удаление поля")
    @Severity(BLOCKER)
    public void testFormThreeStepThree() throws Exception {
        int countEmailField = 3;
        int countPhoneField = 1;
        formThree.enterName(TestProperties.getProperty("user.name"));
        formThree.clickAddButton("email", countEmailField);
        formThree.enterEmail(TestProperties.getProperty("user.email"), "admin@yandex.ru", "user@yandex.ru", "user2@yandex.ru");
        formThree.clickAddButton("phone", countPhoneField);
        formThree.enterPhone(TestProperties.getProperty("user.phone"), TestProperties.getProperty("user.phone.two"));
        formThree.clickButtonSubmit();
        int countEmailBefore = formThree.checkCountElement(formThree.getTextMessage("emailUser"));
        int countPhoneBefore = formThree.checkCountElement(formThree.getTextMessage("phoneUser"));
        formThree.clickDeleteButton("email");
        formThree.clickDeleteButton("email");
        formThree.clickDeleteButton("phone");
        formThree.clickButtonSubmit();
        int countEmailAfter = formThree.checkCountElement(formThree.getTextMessage("emailUser"));
        int countPhoneAfter = formThree.checkCountElement(formThree.getTextMessage("phoneUser"));
        assertEquals(countEmailBefore - 2, countEmailAfter, "Количество email не совпадает");
        assertEquals(countPhoneBefore - 1, countPhoneAfter, "Количество phone не совпадает");
    }
}
