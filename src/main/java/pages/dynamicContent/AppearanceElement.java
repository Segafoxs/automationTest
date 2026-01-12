package pages.dynamicContent;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$x;

@Slf4j
public class AppearanceElement {

    private final SelenideElement buttonShowElements = $x("//button[@id='showDelayedBtn']").as("Кнопка \"Показать элементы\"");
    private final SelenideElement dynamicElement = $x("//div[@id='delayedContent']//h4[text() = 'Элемент 2']").as("Элемент 2");

    @Step("Жмем кнопку \"Показать элементы (2 сек)\"")
    public void clickButtonShowElements(){
        buttonShowElements.shouldBe(Condition.clickable, Duration.ofSeconds(10));
        buttonShowElements.click();
    }

    @Step("Проверяем появление элемента 2")
    public String checkElement(){
        dynamicElement.shouldBe(Condition.visible, Duration.ofSeconds(8));
        String text = dynamicElement.text();
        log.info(text);
        return text;
    }
}
