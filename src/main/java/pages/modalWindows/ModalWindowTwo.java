package pages.modalWindows;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$x;

@Slf4j
public class ModalWindowTwo {
    private final SelenideElement buttonOpenModalWindow = $x("//button[@id='openNestedModalBtn']").as("кнопка открытия модального окна");
    private final SelenideElement buttonOpenModalTwoWindow = $x("//button[@id='openNestedModal2Btn']").as("кнопка открытия второго модального окна");
    private final SelenideElement buttonOpenModalThreeWindow = $x("//button[@id='openNestedModal3Btn']").as("кнопка открытия третьего модального окна");
    private final SelenideElement buttonCloseModalOne = $x("//button[@id='closeNestedModal1Btn']").as("Кнопка закрытия модального окна первого уровня");
    private final SelenideElement buttonCloseModalTwo = $x("//button[@id='closeNestedModal2Btn']").as("Кнопка закрытия модального окна первого уровня");
    private final SelenideElement buttonCloseModalThree = $x("//button[@id='closeNestedModal3Btn']").as("Кнопка закрытия модального окна третьего уровня");;
    private final SelenideElement textOneLevelModal = $x("//h3[contains(text(), 'Первое модальное окно')]")
            .as("текст модального окна первого уровня");
    private final SelenideElement textTwoLevelModal = $x("//h3[contains(text(), 'Второе модальное окно')]").as("текст модального окна второго уровня");
    private final SelenideElement textThreeLevelModal = $x("//h3[contains(text(), 'Третье модальное окно')]").as("текст модального окна третьего уровня");
    private final SelenideElement textFinalLevel = $x("//div[@id='nestedModal3']//p[contains(@class, 'text-green-800')]").as("текст модального окна третьего уровня");
    private final SelenideElement h3 = $x("//h3[contains(text(), '2. Вложенные модальные окна')]").as("Заголовок третьего уровня");

    @Step("Открываем модальное окно")
    public void clickButtonOpenModalWindow(){
        buttonOpenModalWindow.shouldBe(Condition.clickable, Duration.ofSeconds(8));
        buttonOpenModalWindow.click();
    }

    @Step("Проверяем заголовок модального окна первого уровня")
    public String getTextFromModalLevelOne(){
        textOneLevelModal.shouldBe(Condition.visible, Duration.ofSeconds(8));
        String text = textOneLevelModal.text();
        log.info("Текст модального окна первого уровня: " + text);
        return text;
    }

    @Step("Проверяем заголовок модального окна второго уровня")
    public String getTextFromModalLevelTwo(){
        textTwoLevelModal.shouldBe(Condition.visible, Duration.ofSeconds(8));
        String text = textTwoLevelModal.text();
        log.info("Текст модального окна второго уровня: " + text);
        return text;
    }

    @Step("Проверяем заголовок модального окна первого уровня")
    public String getTextFromModalLevelThree(){
        textThreeLevelModal.shouldBe(Condition.visible, Duration.ofSeconds(8));
        String text = textThreeLevelModal.text();
        log.info("Текст модального окна первого уровня: " + text);
        return text;
    }

    @Step("Открываем модальное окно второго уровня")
    public void clickButtonOpenModalTwo(){
        buttonOpenModalTwoWindow.shouldBe(Condition.clickable, Duration.ofSeconds(8));
        buttonOpenModalTwoWindow.click();
    }

    @Step("Открываем модальное окно третьего уровня")
    public void clickButtonOpenModalThree(){
        buttonOpenModalThreeWindow.shouldBe(Condition.clickable, Duration.ofSeconds(8));
        buttonOpenModalThreeWindow.click();
    }

    @Step("Получаем финальный текст")
    public String getFinalText(){
        textFinalLevel.shouldBe(Condition.visible, Duration.ofSeconds(8));
        String text = textFinalLevel.text();
        log.info("Финальный текст: " + text);
        return text;
    }

    @Step("Нажимаем кнопку ЗАКРЫТЬ в модальном окне первого уровня")
    public void clickButtonCloseModalOne(){
        buttonCloseModalOne.shouldBe(Condition.clickable, Duration.ofSeconds(10));
        buttonCloseModalOne.click();
    }

    @Step("Нажимаем кнопку ЗАКРЫТЬ в модальном окне второго уровня")
    public void clickButtonCloseModalTwo(){
        buttonCloseModalTwo.shouldBe(Condition.clickable, Duration.ofSeconds(10));
        buttonCloseModalTwo.click();
    }

    @Step("Нажимаем кнопку ЗАКРЫТЬ в модальном окне второго уровня")
    public void clickButtonCloseModalThree(){
        buttonCloseModalThree.shouldBe(Condition.clickable, Duration.ofSeconds(10));
        buttonCloseModalThree.click();
    }

    public String getTextFromH3(){
        h3.shouldBe(Condition.visible, Duration.ofSeconds(10));
        return h3.text();
    }

}
