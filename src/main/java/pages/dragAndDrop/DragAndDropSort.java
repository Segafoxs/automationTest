package pages.dragAndDrop;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

import static com.codeborne.selenide.Selenide.*;

@Slf4j
public class DragAndDropSort {
    private final SelenideElement dragAndDropSort = $x("//div[@id='sortableList']");
    private final ElementsCollection items = $$x("//div[@id='sortableList']/div");
    private final SelenideElement itemOne = $x("//div[@id='sortableList']//div[@data-position='1']");
    private final SelenideElement itemTwo = $x("//div[@id='sortableList']//div[@data-position='2']");
    private final SelenideElement itemThree = $x("//div[@id='sortableList']//div[@data-position='3']");
    private final SelenideElement itemFour = $x("//div[@id='sortableList']//div[@data-position='4']");
    private final SelenideElement itemFive = $x("//div[@id='sortableList']//div[@data-position='5']");

    public void getItems(){
        for (int i = 0; i < items.size(); i++){
            log.info(items.get(i).text());
        }
    }

    @Step("Меняем порядок элементов")
    public void reverseItemDragAndDrop(){
        actions().dragAndDrop(itemFive, itemOne).perform();
        actions().dragAndDrop(itemFour, itemOne).perform();
        actions().dragAndDrop(itemThree, itemOne).perform();
        actions().dragAndDrop(itemTwo, itemOne).perform();
    }

    @Step("Возвращаем список элементов")
    public List<String> getListItem(){
        return items.stream().map(SelenideElement::text).toList();
    }
}
