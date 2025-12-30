package pages.dragAndDrop;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.*;

@Slf4j
public class DragAndDrop {
    private final ElementsCollection availableItems = $$x("//div[@id='availableItems']/div").as("Доступные элементы");
    private final ElementsCollection selectedItems = $$x("//div[@id='selectedItems']/div").as("Выбранные элементы");
    private final SelenideElement selectedItemsContainer = $x("//div[@id='selectedItems']").as("Контейнер выбранных элементов");

    @Step("Перетаскиваем элементы в контейнер \"Выбранные элементы\"")
    public void addSelectItems(){
        int size = availableItems.size();
        for (int i = 0; i < size; i++){
            SelenideElement item = availableItems.get(0);
            actions().dragAndDrop(item, selectedItemsContainer).perform();
        }
    }

    @Step("Проверяем количество элементов")
    public int getSizeSelectedItems(){
        int countItem = selectedItems.size();
        log.info(String.format("Количество выбранных элементов: %d", countItem));
        return countItem;
    }
}
