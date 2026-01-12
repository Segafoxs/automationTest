package pages.dynamicContent;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import io.qameta.allure.Step;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$$x;

public class InfiniteScroll {
    private final ElementsCollection scroll = $$x("//div[@id='infiniteScrollContainer']/div");

    @Step("Прокручиваем до последнего элемента")
    public void scrollElements(){
        scroll.last().shouldBe(Condition.visible, Duration.ofSeconds(8)).scrollIntoView(true);
    }

    @Step("Получаем количество элементов")
    public int getLengthElements(){
        return scroll.size();
    }
}
