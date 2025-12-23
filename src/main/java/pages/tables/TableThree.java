package pages.tables;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

@Slf4j
public class TableThree {
    private final SelenideElement searchNameLine = $x("//input[@id='filterInput']").as("поиск по имени");
    private final SelenideElement dropListStatus = $x("//select[@id='statusFilter']").as("фильтр по статусу");
    private final SelenideElement contentTable = $x("//table[@id='filterableTable']//tbody[@id='filterableTableBody']").as("содержимое таблицы");
    private final SelenideElement countVisibleRecords = $x("//p[@id='filterCount']//span[@id='visibleRows']").as("количество отображаемых записей");

    @Step("Ищем по имени '{name}'")
    public void searchName(String name){
        searchNameLine.shouldBe(Condition.visible, Duration.ofSeconds(8));
        searchNameLine.setValue(name);
    }

    @Step("Устанавливаем статус '{status}'")
    public void setDropListStatus(String status){
        dropListStatus.shouldBe(Condition.visible, Duration.ofSeconds(8));
        dropListStatus.selectOption(status);
    }

    public List<String> getListFromList(List<String[]> list, int index) {
        if (list.size() > 1 && index <= list.size() - 1) {
            log.info(Arrays.toString(list.get(index)));
            return List.of(list.get(index));
        } else {
            log.info(Arrays.toString(list.get(0)));
            return List.of(list.get(0));
        }
    }

    @Step("Достаем значение из поля '{nameField}'")
    public String getFieldFromList(List<String> listField, String nameField){
        String fieldValue = "";
        switch (nameField.toLowerCase()){
            case "id" :
                fieldValue = listField.get(0);
                break;
            case "name" :
                fieldValue = listField.get(1);
                break;
            case "email":
                fieldValue = listField.get(2);
                break;
            case "status":
                fieldValue = listField.get(3);
                break;
        }
        log.info(String.format("Имя поля: %s. Значение: %s",nameField, fieldValue));
        return fieldValue;
    }

    public List<String[]> getResultColumnSearch() {
        String str = contentTable.getAttribute("innerText");
        List<String[]> records = Arrays.stream(str.split("\n"))
                .map(line -> line.split("\t"))
                .toList();
        return records;
    }

    @Step("Проверяем количество отображаемых записей")
    public int getCountRecord(){
        countVisibleRecords.shouldBe(Condition.visible, Duration.ofSeconds(8));
        log.info(String.format("Количество записей: %s", countVisibleRecords.text()));
        return Integer.parseInt(countVisibleRecords.text());
    }
}
