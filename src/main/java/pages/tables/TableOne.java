package pages.tables;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class TableOne {
    private final SelenideElement idColumn = $x("//table[@id='usersTable']//th[text() = 'ID']").as("колонка ID");
    private final SelenideElement nameColumn = $x("//table[@id='usersTable']//th[text() = 'Name']").as("колонка name");

}
