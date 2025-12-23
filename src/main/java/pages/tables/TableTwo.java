package pages.tables;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Link;
import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

@Slf4j
public class TableTwo {
    private final SelenideElement idColumnTable = $x("//table[@id='sortableTable']//thead//th[contains(text(), 'ID')]").as("колонка айди");
    private final SelenideElement nameColumnTable = $x("//table[@id='sortableTable']//thead//th[contains(text(), 'Name')]").as("колонка name");
    private final SelenideElement ageColumnTable = $x("//table[@id='sortableTable']//thead//th[contains(text(), 'Age')]").as("колонка age");
    private final SelenideElement salaryColumnTable = $x("//table[@id='sortableTable']//thead//th[contains(text(), 'Salary')]").as("колонка salary");
    private final ElementsCollection contentTable = $$x("//table[@id='sortableTable']//tbody[@id='sortableTableBody']//tr").as("содержимое таблицы");
    private final ElementsCollection idValuesTable = $$x("//table[@id='sortableTable']//tbody[@id='sortableTableBody']//tr//td[1]").as("значения столбка id");
    private final ElementsCollection nameValuesTable = $$x("//table[@id='sortableTable']//tbody[@id='sortableTableBody']//tr//td[2]").as("значения столбца name");
    private final ElementsCollection ageValuesTable = $$x("//table[@id='sortableTable']//tbody[@id='sortableTableBody']//tr//td[3]").as("значения столбца age");
    private final ElementsCollection salaryValuesTable = $$x("//table[@id='sortableTable']//tbody[@id='sortableTableBody']//tr//td[4]").as("значение столбца salary");

    @Step("Сортируем столбец '{nameColumn}'")
    public void clickSortColumn(String nameColumn){
        switch (nameColumn.toLowerCase()){
            case "id":
                idColumnTable.shouldBe(Condition.visible, Condition.clickable);
                idColumnTable.click();
                break;
            case "name":
                nameColumnTable.shouldBe(Condition.visible, Condition.clickable);
                nameColumnTable.click();
                break;
            case "age":
                ageColumnTable.shouldBe(Condition.visible, Condition.clickable);
                ageColumnTable.click();
                break;
            case "salary":
                salaryColumnTable.shouldBe(Condition.visible, Condition.clickable);
                salaryColumnTable.click();
                break;
        }
    }

    @Step("Получаем значения из столбца '{nameColumn}'")
    public List<String> getListValue(String nameColumn){
        List<String> listValue = new ArrayList<>();
        ElementsCollection valueTable = switch (nameColumn.toLowerCase()) {
            case "id" -> idValuesTable;
            case "name" -> nameValuesTable;
            case "age" -> ageValuesTable;
            case "salary" -> salaryValuesTable;
            default -> null;
        };

        for (int i = 0; i < contentTable.size(); i++){
            if (valueTable != null) {
                listValue.add(valueTable.get(i).shouldBe(Condition.visible,Duration.ofSeconds(8)).text());
            }
        }
        log.info(listValue.toString());
        return listValue;
    }

    @Step("Получаем строку под номером '{numberLine}'")
    public String getTextInLineTable(int numberLine){
        String text = "";
        if (numberLine > 0 && numberLine < 6) {
            text = contentTable.get(numberLine - 1).shouldBe(Condition.visible,
                    Duration.ofSeconds(8)).text();
        }
        log.info(text);
        return text;
    }
}
