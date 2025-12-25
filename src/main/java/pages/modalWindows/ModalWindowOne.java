package pages.modalWindows;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$x;

@Slf4j
public class ModalWindowOne {
    private final SelenideElement buttonOpenModal = $x("//button[@id='openModalBtn']").as("кнопка открытия модального окна");
    private final SelenideElement buttonModalOk = $x("//div[@id='simpleModal']//button[@id='confirmModalBtn']").as("кнопка ОК в модальном окне");
    private final SelenideElement buttonModalClose = $x("//div[@id='simpleModal']//button[@id='confirmModalBtn']").as("кнопка ЗАКРЫТЬ в модальном окне");
    private final SelenideElement textInModal = $x("//div[@id='simpleModal']//div[@class = 'p-6']/p[contains(text(), 'Это простое модальное окно')]")
            .as("текст в модальном окне");

    @Step("Открываем модальное окно")
    public void clickButtonOpenModal(){
        buttonOpenModal.shouldBe(Condition.clickable, Duration.ofSeconds(8));
        buttonOpenModal.click();
    }

    @Step("Нажимаем кнопку '{nameButton}'")
    public void clickButtonInModal(String nameButton){
        switch (nameButton.toLowerCase()){
            case "ok" :
                buttonModalOk.shouldBe(Condition.clickable, Duration.ofSeconds(8));
                buttonModalOk.click();
                break;
            case "close":
                buttonModalClose.shouldBe(Condition.clickable, Duration.ofSeconds(8));
                buttonModalClose.click();
                break;
        }
    }

    @Step("Извлекаем текст из модального окна")
    public String getTextFromModal(){
        textInModal.shouldBe(Condition.visible, Duration.ofSeconds(8));
        String text = textInModal.text();
        log.info("Текст модального окна 1: " + text);
        return text;
    }
}
