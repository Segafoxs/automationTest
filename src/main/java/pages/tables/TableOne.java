package pages.tables;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

@Slf4j
public class TableOne {
    private final SelenideElement idTableColumn = $x("//table[@id='usersTable']//th[text() = 'ID']").as("колонка ID");
    private final SelenideElement nameTableColumn = $x("//table[@id='usersTable']//th[text() = 'Name']").as("колонка name");
    private final SelenideElement emailTableColumn = $x("//table[@id='usersTable']//th[text() = 'Email']").as("колонка email");
    private final SelenideElement statusTableColumn = $x("//table[@id='usersTable']//th[text() = 'Status']").as("колонка status");
    private final SelenideElement actionsTableColumn = $x("//table[@id='usersTable']//th[text() = 'Actions']").as("колонка acactions");
    private final ElementsCollection contentTable = $$x("//table[@id='usersTable']//tbody//tr").as("содержимое таблицы");

    @Step("Получаем запись из таблицы под номером '{number}'")
    public String getRecordFromTable(int number){
        log.info(contentTable.get(number-1).text());
        return contentTable.get(number-1).text();
    }

    @Step("Получаем запись значение столбца '{nameColumn}'")
    public String getTextColumn(String nameColumn){
        String textColumn = "";
        switch (nameColumn.toLowerCase()){
            case ("id") :
                idTableColumn.shouldBe(Condition.visible, Duration.ofSeconds(8));
                textColumn = idTableColumn.text();
                break;
            case ("name") :
                nameTableColumn.shouldBe(Condition.visible, Duration.ofSeconds(8));
                textColumn = nameTableColumn.text();
                break;
            case ("email"):
                emailTableColumn.shouldBe(Condition.visible, Duration.ofSeconds(8));
                textColumn = emailTableColumn.text();
                break;
            case ("status"):
                statusTableColumn.shouldBe(Condition.visible, Duration.ofSeconds(8));
                textColumn = statusTableColumn.text();
                break;
            case ("actions"):
                actionsTableColumn.shouldBe(Condition.visible, Duration.ofSeconds(8));
                textColumn = actionsTableColumn.text();
                break;
        }
        log.info(textColumn);
        return textColumn;
    }
}

