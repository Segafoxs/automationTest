package pages.tables;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

@Slf4j
public class TableFour {
    private final SelenideElement countVisibleRecords = $x("//select[@id='pageSize']").as("количество отображаемых записей");
    private final ElementsCollection contentTable = $$x("//table[@id='paginatedTable']//tbody[@id='paginatedTableBody']/tr").as("содержимое таблицы");
    private final SelenideElement buttonCurrentPage = $x("//div[@id='pageNumbers']//button[contains(@class,'bg-blue-600')]").as("кнопка текущей страницы в таблице");
    private final SelenideElement buttonLastPage = $x("//button[@id='lastPageBtn']").as("кнопка последней страницы");
    private final SelenideElement buttonNextPage = $x("//button[@id='nextPageBtn']").as("кнопка следующей страницы");
    private final SelenideElement buttonFirstPage = $x("//button[@id='firstPageBtn']").as("кнопка первая страница");
    private final SelenideElement buttonPreviousPage = $x("//button[@id='prevPageBtn']").as("кнопка предыдущая страница");

    List<String[]> contentTableList = new ArrayList<>();

    private void getContentFromTable(){
        for (int i = 0; i < contentTable.size(); i++){
            String item = contentTable.get(i).shouldBe(Condition.visible, Duration.ofSeconds(8)).text();
            contentTableList.add(item.split("\\s+"));
        }
    }

    @Step("Достаем запись из таблицы с id: '{id}'")
    public String[] getRecordFromTableForId(int id){
        getContentFromTable();
        return contentTableList.get(id-1);
    }

    @Step("Достаем значение столбца: '{nameColumn}' у строки с id: '{id}'")
    public String getValueFromTable(int id, String nameColumn){
        getContentFromTable();
        int index = 0;
        switch (nameColumn.toLowerCase()){
            case "id":
                break;
            case "product":
                index = 1;
                break;
            case "category":
                index = 2;
                break;
            case "price":
                index = 3;
                break;
        }
        log.info(contentTableList.get(id-1)[index]);
        return contentTableList.get(id-1)[index];
    }

    @Step("Получаем количество отображаемых записей из таблицы")
    public int getCountRecordInTable(){
        log.info("Количество отображаемых записей: " + contentTable.size());
        return contentTable.size();
    }

    @Step("Меняем количество отображаемых записей у таблицы: '{count}'")
    public void setCountVisibleRecords(int count){
        countVisibleRecords.shouldBe(Condition.visible, Duration.ofSeconds(8));
        countVisibleRecords.selectOptionByValue(String.valueOf(count));
    }

    @Step("Проверяем текущий номер страницы")
    public int getNumberCurrentPage(){
        buttonCurrentPage.shouldBe(Condition.visible, Duration.ofSeconds(8));
        int numberPage = Integer.parseInt(buttonCurrentPage.text());
        log.info(String.format("Номер текущей страницы: %s", numberPage));
        return numberPage;
    }

    @Step("Нажимаем кнопку \"Следующая страница\"")
    public void clickNextPageButton(){
        buttonNextPage.shouldBe(Condition.clickable, Duration.ofSeconds(8));
        buttonNextPage.click();
    }

    @Step("Нажимаем кнопку \"Последняя страница\"")
    public void clickLastPageButton(){
        buttonLastPage.shouldBe(Condition.clickable, Duration.ofSeconds(8));
        buttonLastPage.click();
    }

    @Step("Нажимаем кнопку \"Первая страница\"")
    public void clickFirstPageButton(){
        buttonFirstPage.shouldBe(Condition.clickable, Duration.ofSeconds(8));
        buttonFirstPage.click();
    }

    @Step("Нажимаем кнопку \"Предыдущая страница\"")
    public void clickPreviousPageButton(){
        buttonPreviousPage.shouldBe(Condition.clickable, Duration.ofSeconds(8));
        buttonPreviousPage.click();
    }
}
