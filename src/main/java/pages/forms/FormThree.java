package pages.forms;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.extern.slf4j.Slf4j;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

@Slf4j
public class FormThree {
    private final SelenideElement nameField = $x("//form[@id='dynamicForm']//input[@id='dyn-name']").as("поле имя пользователя");
    private final ElementsCollection emailField = $$x("//form[@id='dynamicForm']//input[@name='email[]']").as("поле email");
    private final ElementsCollection phoneField = $$x("//form[@id='dynamicForm']//input[@name='phone[]']").as("поле номера телефонов");
    private final SelenideElement buttonSubmit = $x("//form[@id='dynamicForm']//button[@id='dynSubmitBtn']").as("кнопка отправки формы");
    private final SelenideElement succesyMessage = $x("//div[@id='dynFormResult']//p[contains(@class, 'text-green-800')]").as("Сообщение об успешной отправки формы");
    private final SelenideElement successyMessageName = $x("//div[@id='dynFormResult']//strong[contains(text(), 'Имя')]/ancestor::p").as("имя в сообщении");
    private final SelenideElement successyMessageEmail = $x("//div[@id='dynFormResult']//strong[contains(text(), 'Email')]/ancestor::p").as("email пользователя");
    private final SelenideElement successyMessagePhone = $x("//div[@id='dynFormResult']//strong[contains(text(), 'Телефоны')]/ancestor::p").as("Телефон пользователя");
    private final SelenideElement buttonAddEmail = $x("//form[@id='dynamicForm']//button[@id='addEmailBtn']").as("кнопка добавить email");
    private final SelenideElement buttonAddPhone = $x("//form[@id='dynamicForm']//button[@id='addPhoneBtn']").as("кнопка добавить телефон");
    private final ElementsCollection buttonDeleteEmailField = $$x("//button[@onclick='removeEmailField(this)']").as("кнопка удаления поля email");
    private final ElementsCollection buttonDeletePhoneField = $$x("//button[@onclick='removePhoneField(this)']").as("кнопка удаления телефона");

    public void enterName(String name) {
        nameField.shouldBe(Condition.visible, Duration.ofSeconds(10));
        nameField.setValue(name);
    }

    public void enterEmail(String... email) {
        List<String> listPhone = Arrays.asList(email);
        for (int i = 0; i < emailField.size(); i++) {
            emailField.get(i).shouldBe(Condition.visible).setValue(listPhone.get(i));
        }
    }

    public void enterPhone(String... phone) {
        List<String> listPhone = Arrays.asList(phone);
        for (int i = 0; i < phoneField.size(); i++) {
            phoneField.get(i).shouldBe(Condition.visible).setValue(listPhone.get(i));
        }
    }


    public void clickButtonSubmit() {
        buttonSubmit.click();
    }

    public String getTextMessage(String nameElement) {
        String message = "";
        switch (nameElement) {
            case "nameUser":
                successyMessageName.shouldBe(Condition.visible);
                message = successyMessageName.text();
                break;
            case "emailUser":
                successyMessageEmail.shouldBe(Condition.visible);
                message = successyMessageEmail.text();
                break;
            case "phoneUser":
                successyMessagePhone.shouldBe(Condition.visible);
                message = successyMessagePhone.text();
                break;
            case "successyMessage":
                succesyMessage.shouldBe(Condition.visible);
                message = succesyMessage.text();
                break;
        }
        log.info(message);
        return message;
    }

    public void clickAddButton(String nameField, int count) {
        for (int i = 0; i < count; i++) {
            if (nameField.equals("email")) {
                buttonAddEmail.shouldBe(Condition.clickable);
                buttonAddEmail.click();
            } else if (nameField.equals("phone")) {
                buttonAddPhone.shouldBe(Condition.clickable);
                buttonAddPhone.click();
            }
        }
    }

    public void clickDeleteButton(String field) {
        SelenideElement buttonDelete = null;
        switch (field) {
            case "email":
                if (buttonDeleteEmailField.size() >= 1) {
                    buttonDelete = buttonDeleteEmailField.get(1);
                }
                else {
                    buttonDelete = buttonDeleteEmailField.get(0);
                }
                break;
            case "phone":
                buttonDelete = buttonDeletePhoneField.get(0);
                break;
        }
        if (buttonDelete != null) {
            buttonDelete.shouldBe(Condition.clickable);
            buttonDelete.click();
        }
    }

    public int checkCountElement(String message) throws Exception {
        int startIndex = message.indexOf("(");
        int endIndex = message.indexOf(")");
        int countElement = 0;
        try {
            countElement =  Integer.parseInt(message.substring(startIndex+1, endIndex));
        }catch (Exception exception){
            log.info(String.valueOf(exception));
            throw new Exception();
        }
        log.info(String.format("Количество элементов: %s", countElement));
        return countElement;
    }
}
