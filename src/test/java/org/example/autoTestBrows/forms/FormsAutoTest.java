package org.example.autoTestBrows.forms;

import Utils.TestProperties;
import generators.GeneratorData;
import io.qameta.allure.*;
import messageError.MessageError;
import lombok.extern.slf4j.Slf4j;
import org.example.WebHook;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
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
    private final String userName = GeneratorData.generateUserName();
    private final String userEmal = GeneratorData.generateUserEmail();
    private final String userPassword = GeneratorData.generatePassword();
    private final String userPhone = GeneratorData.generateNumberPhone();


    @Test
    @Tag("forms")
    @DisplayName("Тестирование формы регистрации №1")
    @Description("Данный тест проверяет работу формы №1")
    @Epic("Регистрация")
    @Severity(BLOCKER)
    public void testFormOne(){
        formOne.enterForms(userName, userEmal,
                userPassword, "Russia");
    }

    @Test
    @Tag("forms")
    @DisplayName("Тестирование формы регистрации с валидацией №2")
    @Description("Данный тест проверяет работу формы №2")
    @Epic("Регистрация")
    @Severity(BLOCKER)
    public void testFormTwo(){
        formTwo.enterForm(userName, userEmal,
                userPassword);
    }

    @Test
    @Tag("forms")
    @DisplayName("Тестирование формы регистрации с валидацией №2")
    @Description("Данный тест проверяет корректность валидации формы №2")
    @Feature("Валидация")
    @Severity(NORMAL)
    public void testFormTwoStepTwo(){
        String noValidUserName = GeneratorData.generateRandomString(1);
        String noValidEmail = GeneratorData.generateRandomString(1);
        String noValidPassword = GeneratorData.generateRandomString(1);

        formTwo.enterForm(noValidUserName, noValidEmail, noValidPassword
                , noValidPassword + "1");
        assertEquals(MessageError.USER_NAME_ERROR.getMessage(), formTwo.getMessage("userName"), MessageError.INVALID_MESSAGE.getMessage());
        assertEquals(MessageError.EMAIL_ERROR.getMessage(), formTwo.getMessage("email"), MessageError.INVALID_MESSAGE.getMessage());
        assertEquals(MessageError.PASSWORD_ERROR.getMessage(), formTwo.getMessage("password"), MessageError.INVALID_MESSAGE.getMessage());
        assertEquals(MessageError.PASSWORD_CONFIRM_ERROR.getMessage(), formTwo.getMessage("confirmPassword"), MessageError.INVALID_MESSAGE.getMessage());
    }

    @Test
    @Tag("forms")
    @DisplayName("Тестирование динамической формы регистрации №3")
    @Description("Данный тест проверяет работу формы №3 с динамическими полями " +
            "и структуру сообщения об успешной отправке формы")
    @Severity(BLOCKER)
    public void testFormThree(){
        formThree.enterName(userName);
        formThree.enterEmail(userEmal);
        formThree.enterPhone(userPhone);
        formThree.clickButtonSubmit();
        assertEquals("Форма успешно отправлена!", formThree.getTextMessage("successyMessage"));
        assertTrue(formThree.getTextMessage("nameUser").contains("Имя: "));
        assertTrue(formThree.getTextMessage("emailUser").contains("Email"));
        assertTrue(formThree.getTextMessage("phoneUser").contains("Телефоны"));
    }

    @Test
    @Tag("forms")
    @DisplayName("Тестирование динамической формы регистрации №3")
    @Description("Данный тест проверяет корректность работы кнопок \"Добавить email\" и кнопки \"Добавить телефон\" формы №3 с динамическими полями " +
            "и структуру сообщения об успешной отправке формы")
    @Feature("Добавить поле")
    @Severity(BLOCKER)
    public void testFormThreeStepTwo(){
        int countEmailField = 2;
        int countPhoneField = 1;
        String phone = GeneratorData.generateNumberPhone();
        String email1 = GeneratorData.generateUserEmail();
        String email2 = GeneratorData.generateUserEmail();

        formThree.enterName(userName);
        formThree.clickAddButton("email", countEmailField);
        formThree.enterEmail(userEmal, email1 , email2);
        formThree.clickAddButton("phone", countPhoneField);
        formThree.enterPhone(userPhone, phone);
        formThree.clickButtonSubmit();
        assertEquals("Форма успешно отправлена!", formThree.getTextMessage("successyMessage"), MessageError.INVALID_MESSAGE.getMessage());
        assertEquals(String.format(formThree.getTextMessage("nameUser"), userName), formThree.getTextMessage("nameUser"), MessageError.INVALID_MESSAGE.getMessage());
        assertEquals(String.format("Email (%d): %s, %s, %s",
                countEmailField+1, userEmal, email1, email2), formThree.getTextMessage("emailUser"), MessageError.INVALID_MESSAGE.getMessage());
        assertEquals(String.format("Телефоны (%d): %s, %s", countPhoneField+1, userPhone, phone),
                formThree.getTextMessage("phoneUser"));
    }

    @Test
    @Tag("forms")
    @DisplayName("Тестирование динамической формы регистрации №3")
    @Description("Данный тест проверяет работу кнопки удаления поля формы №3")
    @Flaky
    @Feature("Удаление поля")
    @Severity(BLOCKER)
    public void testFormThreeStepThree() throws Exception {
        int countEmailField = 3;
        int countPhoneField = 1;
        String email1 = GeneratorData.generateUserEmail();
        String email2 = GeneratorData.generateUserEmail();
        String email3 = GeneratorData.generateUserEmail();
        String userPhone2 = GeneratorData.generateNumberPhone();

        formThree.enterName(userName);
        formThree.clickAddButton("email", countEmailField);
        formThree.enterEmail(userEmal, email1, email2, email3);
        formThree.clickAddButton("phone", countPhoneField);
        formThree.enterPhone(userPhone, userPhone2);
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
