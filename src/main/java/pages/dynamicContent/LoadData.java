package pages.dynamicContent;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

@Slf4j
public class LoadData {
    private final SelenideElement buttonLoadData = $x("//button[@id='loadAjaxBtn']").as("Кнопка \"Загрузить данные\"");
    private final ElementsCollection tableHead = $$x("//div[@id='ajaxContent']//table/thead/tr/th").as("Колонки таблицы");
    private final ElementsCollection tableLine = $$x("//div[@id='ajaxContent']//table/tbody/tr").as("Строки таблицы");

    @Step("Жмем кнопку \"Загрузить данные\"")
    public void clickButtonLoadData(){
        buttonLoadData.shouldBe(Condition.clickable, Duration.ofSeconds(8));
        buttonLoadData.click();
    }

    @Step("Проверяем названия колонки таблицы")
    public List<String> getNameColumn(){
        return tableHead.texts();
    }

    @Step("Получаем строку из таблицы с ID {id}")
    public List<String> getLineFromTable(int id){
        String line = tableLine.get(id-1).shouldBe(Condition.visible, Duration.ofSeconds(10)).text();
        log.info("Строка: " + line);
        return getListFromString(line);
    }

    private List<String> getListFromString(String line){
        String[] parts = line.trim().split("\\s+");
        List<String> resultList = new ArrayList<>();
        if (parts.length == 4) {
            resultList = Stream.of(
                    parts[0],
                    parts[1] + " " + parts[2],
                    parts[3]
            ).toList();
        }
        log.info(resultList.toString());
        return resultList;
    }
}
