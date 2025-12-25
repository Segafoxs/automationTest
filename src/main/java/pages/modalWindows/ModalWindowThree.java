package pages.modalWindows;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$x;

@Slf4j
public class ModalWindowThree {
    private final SelenideElement buttonSuccessModal = $x("//button[@id='successModalBtn']").as("success modal");
    private final SelenideElement buttonErrorModal = $x("//button[@id='errorModalBtn']").as("error modal");
    private final SelenideElement buttonWarningModal = $x("//button[@id='warningModalBtn']").as("warning modal");
    private final SelenideElement textModal = $x("//p[@id='dynamicModalMessage']").as("текст success modal");
    private final SelenideElement buttonCloseModal = $x("//button[@id='closeDynamicModalBtn']").as("кнопка Закрыть");
    private final SelenideElement titleModal = $x("//h3[@id='dynamicModalTitle']").as("заголовок модального окна");

    @Step("Нажимаем на модальное окно SUCCESS MODAL")
    public void clickModalSuccess(){
        buttonSuccessModal.shouldBe(Condition.clickable, Duration.ofSeconds(10));
        buttonSuccessModal.click();
    }

    @Step("Нажимаем на модальное окно ERROR MODAL")
    public void clickModalError(){
        buttonErrorModal.shouldBe(Condition.clickable, Duration.ofSeconds(10));
        buttonErrorModal.click();
    }

    @Step("Нажимаем на модальное окно WARNING MODAL")
    public void clickModalWarning(){
        buttonWarningModal.shouldBe(Condition.clickable, Duration.ofSeconds(10));
        buttonWarningModal.click();
    }

    @Step("Закрываем модальное окно")
    public void clickCloseModalButton(){
        buttonCloseModal.shouldBe(Condition.visible, Duration.ofSeconds(8));
        buttonCloseModal.click();
    }

    @Step("Извлекаем текст у модального окна")
    public String getTextModalWindow(){
        textModal.shouldBe(Condition.visible, Duration.ofSeconds(8));
        String text = textModal.text();
        log.info("Текст: " + text);
        return text;
    }

    @Step("Извлекаем заголовок у модального окна")
    public String getTitleModalWindow(){
        titleModal.shouldBe(Condition.visible, Duration.ofSeconds(8));
        String text = titleModal.text();
        log.info("Заголовк: " + text);
        return text;
    }
}
