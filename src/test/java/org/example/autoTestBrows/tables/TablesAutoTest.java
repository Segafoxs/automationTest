package org.example.autoTestBrows.tables;

import io.qameta.allure.*;
import lombok.extern.slf4j.Slf4j;
import messageError.MessageError;
import org.example.WebHook;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pages.tables.TableFour;
import pages.tables.TableOne;
import pages.tables.TableThree;
import pages.tables.TableTwo;

import java.util.List;

import static io.qameta.allure.SeverityLevel.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Slf4j
@Link(name = "WEB Sandbox", url = "https://aqa-proka4.org/sandbox/web")
public class TablesAutoTest extends WebHook {
    private final TableOne tableOne = new TableOne();
    private final TableTwo tableTwo = new TableTwo();
    private final TableThree tableThree = new TableThree();
    private final TableFour tableFour = new TableFour();

    @Test
    @Tag("tables")
    @DisplayName("Проверка структуры таблицы №1")
    @Description("Этот тест проверяет структуры таблицы №1")
    @Severity(MINOR)
    public void testStructureTable(){
        assertEquals("ID",tableOne.getTextColumn("id"), String.format(MessageError.ABSENT_COLUMN.getMessage(), "ID"));
        assertEquals("Name", tableOne.getTextColumn("name"), String.format(MessageError.ABSENT_COLUMN.getMessage(), "Name"));
        assertEquals("Email", tableOne.getTextColumn("email"), String.format(MessageError.ABSENT_COLUMN.getMessage(), "Email"));
        assertEquals("Status", tableOne.getTextColumn("status"), String.format(MessageError.ABSENT_COLUMN.getMessage(), "Status"));
        assertEquals("Actions", tableOne.getTextColumn("actions"), String.format(MessageError.ABSENT_COLUMN.getMessage(), "Actions"));
    }

    @Test
    @Tag("tables")
    @DisplayName("Проверка содержимого таблицы №1")
    @Description("Этот тест проверяет содержимое таблицы №1")
    @Severity(NORMAL)
    public void testContentTable(){
        String tableRecordOne = tableOne.getRecordFromTable(1);
        String tableRecordTwo = tableOne.getRecordFromTable(2);
        String tableRecordThree = tableOne.getRecordFromTable(3);
        assertTrue(tableRecordOne.contains("1"));
        assertTrue(tableRecordOne.contains("John Doe"));
        assertTrue(tableRecordOne.contains("john@example.com"));
        assertTrue(tableRecordOne.contains("Active"));
        assertTrue(tableRecordTwo.contains("2"));
        assertTrue(tableRecordTwo.contains("Pending"));
        assertTrue(tableRecordThree.contains("3"));
        assertTrue(tableRecordThree.contains("Bob Johnson"));
        assertTrue(tableRecordThree.contains("Inactive"));
    }

    @Test
    @Tag("tables")
    @DisplayName("Проверка таблицы с сортировкой №2")
    @Description("Этот тест проверяет сортировку по возрастанию таблицы №2. Столбец id")
    @Feature("Сортировка столбца")
    @Severity(NORMAL)
    public void testSortUpIdColumn(){
        tableTwo.clickSortColumn("id");
        List<String> listId = tableTwo.getListValue("id");
        assertEquals("1", listId.get(0), String.format(MessageError.INVALID_SORT.getMessage(), "ID"));
        assertEquals("3", listId.get(2), String.format(MessageError.INVALID_SORT.getMessage(), "ID"));
        assertEquals("5", listId.get(4), String.format(MessageError.INVALID_SORT.getMessage(), "ID"));
    }

    @Test
    @Tag("tables")
    @DisplayName("Проверка таблицы с сортировкой №2")
    @Description("Этот тест проверяет сортировку по убыванию таблицы №2. Столбец id")
    @Feature("Сортировка столбца")
    @Severity(NORMAL)
    public void testSortDownIdColumn(){
        tableTwo.clickSortColumn("id");
        tableTwo.clickSortColumn("id");
        List<String> listId = tableTwo.getListValue("id");
        assertEquals("5", listId.get(0), String.format(MessageError.INVALID_SORT.getMessage(), "ID"));
        assertEquals("3", listId.get(2), String.format(MessageError.INVALID_SORT.getMessage(), "ID"));
        assertEquals("1", listId.get(4), String.format(MessageError.INVALID_SORT.getMessage(), "ID"));
    }

    @Test
    @Tag("tables")
    @DisplayName("Проверка таблицы с сортировкой №2")
    @Description("Этот тест проверяет сортировку по возрастанию таблицы №2. Столбец Name")
    @Feature("Сортировка столбца")
    @Severity(NORMAL)
    public void testSortUpNameColumn(){
        tableTwo.clickSortColumn("name");
        String firstLineTable = tableTwo.getTextInLineTable(1);
        String lastLineTable = tableTwo.getTextInLineTable(5);
        List<String> listName = tableTwo.getListValue("name");
        assertEquals("Alice Johnson", listName.get(0), MessageError.INVALID_NAME.getMessage());
        assertEquals("Charlie Brown", listName.get(2));
        assertEquals("Evan Davis", listName.get(4), MessageError.INVALID_NAME.getMessage());
        assertTrue(firstLineTable.contains(listName.get(0)));
        assertTrue(lastLineTable.contains(listName.get(4)));
    }

    @Test
    @Tag("tables")
    @DisplayName("Проверка таблицы с сортировкой №2")
    @Description("Этот тест проверяет сортировку по убыванию таблицы №2. Столбец Name")
    @Feature("Сортировка столбца")
    @Severity(NORMAL)
    public void testSortDownNameColumn(){
        tableTwo.clickSortColumn("name");
        tableTwo.clickSortColumn("name");
        String firstLineTable = tableTwo.getTextInLineTable(1);
        String lastLineTable = tableTwo.getTextInLineTable(5);
        List<String> listName = tableTwo.getListValue("name");
        assertEquals("Evan Davis", listName.get(0), MessageError.INVALID_NAME.getMessage());
        assertEquals("Charlie Brown", listName.get(2), MessageError.INVALID_NAME.getMessage());
        assertEquals("Alice Johnson", listName.get(4), MessageError.INVALID_NAME.getMessage());
        assertTrue(firstLineTable.contains(listName.get(0)));
        assertTrue(lastLineTable.contains(listName.get(4)));
    }

    @Test
    @Tag("tables")
    @DisplayName("Проверка таблицы с сортировкой №2")
    @Description("Этот тест проверяет сортировку по возрастанию таблицы №2. Столбец age")
    @Feature("Сортировка столбца")
    @Severity(NORMAL)
    public void testSortUpAgeColumn(){
        tableTwo.clickSortColumn("age");
        List<String> listAge = tableTwo.getListValue("age");
        assertEquals("25", listAge.get(0), String.format(MessageError.INVALID_SORT.getMessage(), "Age"));
        assertEquals("31", listAge.get(2), String.format(MessageError.INVALID_SORT.getMessage(), "Age"));
        assertEquals("42", listAge.get(4), String.format(MessageError.INVALID_SORT.getMessage(), "Age"));
    }

    @Test
    @Tag("tables")
    @DisplayName("Проверка таблицы с сортировкой №2")
    @Description("Этот тест проверяет сортировку по убыванию таблицы №2. Столбец age")
    @Feature("Сортировка столбца")
    @Severity(NORMAL)
    public void testSortDownAgeColumn(){
        tableTwo.clickSortColumn("age");
        tableTwo.clickSortColumn("age");
        List<String> listAge = tableTwo.getListValue("age");
        assertEquals("42", listAge.get(0), String.format(MessageError.INVALID_SORT.getMessage(), "Age"));
        assertEquals("31", listAge.get(2), String.format(MessageError.INVALID_SORT.getMessage(), "Age"));
        assertEquals("25", listAge.get(4), String.format(MessageError.INVALID_SORT.getMessage(), "Age"));
    }

    @Test
    @Tag("tables")
    @DisplayName("Проверка таблицы с сортировкой №2")
    @Description("Этот тест проверяет сортировку по возрастанию таблицы №2. Столбец Salary")
    @Feature("Сортировка столбца")
    @Severity(NORMAL)
    public void testSortUpSalaryColumn(){
        tableTwo.clickSortColumn("salary");
        List<String> listSalary = tableTwo.getListValue("salary");
        assertEquals("$65,000", listSalary.get(0), String.format(MessageError.INVALID_SORT.getMessage(), "Salary"));
        assertEquals("$88,000", listSalary.get(2), String.format(MessageError.INVALID_SORT.getMessage(), "Salary"));
        assertEquals("$105,000", listSalary.get(4), String.format(MessageError.INVALID_SORT.getMessage(), "Salary"));
    }

    @Test
    @Tag("tables")
    @DisplayName("Проверка таблицы с сортировкой №2")
    @Description("Этот тест проверяет сортировку по возрастанию таблицы №2. Столбец Salary")
    @Feature("Сортировка столбца")
    @Severity(NORMAL)
    public void testSortDownSalaryColumn(){
        tableTwo.clickSortColumn("salary");
        tableTwo.clickSortColumn("salary");
        List<String> listSalary = tableTwo.getListValue("salary");
        assertEquals("$105,000", listSalary.get(0), String.format(MessageError.INVALID_SORT.getMessage(), "Salary"));
        assertEquals("$88,000", listSalary.get(2), String.format(MessageError.INVALID_SORT.getMessage(), "Salary"));
        assertEquals("$65,000", listSalary.get(4), String.format(MessageError.INVALID_SORT.getMessage(), "Salary"));
    }

    @Test
    @Tag("tables")
    @DisplayName("Проверка таблицы №3 с фильтрацией")
    @Description("Этот тест проверяет фильтрацию таблицы №3 по имени")
    @Feature("Фильтрация по имени")
    @Severity(CRITICAL)
    public void testFilterTableForName(){
        tableThree.searchName("D");
        List<String[]> listSearchResult = tableThree.getResultColumnSearch();
        List<String> resultOne = tableThree.getListFromList(listSearchResult, 0);
        List<String> resultTwo = tableThree.getListFromList(listSearchResult, 1);
        assertEquals(listSearchResult.size(), tableThree.getCountRecord(), MessageError.INVALID_COUNT_RECORDS.getMessage());
        assertEquals("3", tableThree.getFieldFromList(resultOne, "id"), String.format(MessageError.INVALID_FIELD.getMessage(), "id"));
        assertEquals("Charlie Adams", tableThree.getFieldFromList(resultOne, "name"), MessageError.INVALID_NAME.getMessage());
        assertEquals("charlie@example.com", tableThree.getFieldFromList(resultOne, "email"), String.format(MessageError.INVALID_FIELD.getMessage(), "email"));
        assertEquals("Active", tableThree.getFieldFromList(resultOne, "status"), MessageError.INVALID_NAME.getMessage());
        assertEquals("4", tableThree.getFieldFromList(resultTwo, "id"), String.format(MessageError.INVALID_FIELD.getMessage(), "id"));
        assertEquals("Diana Moore", tableThree.getFieldFromList(resultTwo, "name"), MessageError.INVALID_NAME.getMessage());
        assertEquals("diana@example.com", tableThree.getFieldFromList(resultTwo, "email"), String.format(MessageError.INVALID_FIELD.getMessage(), "email"));
        assertEquals("Pending", tableThree.getFieldFromList(resultTwo, "status"), MessageError.INVALID_NAME.getMessage());
    }

    @Test
    @Tag("tables")
    @DisplayName("Проверка таблицы №3 с фильтрацией")
    @Description("Этот тест проверяет фильтрацию таблицы №3 по имени")
    @Feature("Фильтрация по имени")
    @Severity(CRITICAL)
    public void testFilterTableForName1(){
        tableThree.searchName("Diana");
        List<String[]> listSearchResult = tableThree.getResultColumnSearch();
        List<String> resultOne = tableThree.getListFromList(listSearchResult, 0);
        assertEquals(listSearchResult.size(), tableThree.getCountRecord(), MessageError.INVALID_COUNT_RECORDS.getMessage());
        assertEquals("4", tableThree.getFieldFromList(resultOne, "id"), String.format(MessageError.INVALID_FIELD.getMessage(), "id"));
        assertEquals("Diana Moore", tableThree.getFieldFromList(resultOne, "name"), MessageError.INVALID_NAME.getMessage());
        assertEquals("diana@example.com", tableThree.getFieldFromList(resultOne, "email"), String.format(MessageError.INVALID_FIELD.getMessage(), "email"));
        assertEquals("Pending", tableThree.getFieldFromList(resultOne, "status"), MessageError.INVALID_NAME.getMessage());
    }

    @Test
    @Tag("tables")
    @DisplayName("Проверка таблицы №3 с фильтрацией")
    @Description("Этот тест проверяет фильтрацию таблицы №3 по статусу \"Active\"")
    @Feature("Фильтрация по статусу")
    @Severity(NORMAL)
    public void testFilterTableForStatusActive(){
        tableThree.setDropListStatus("Active");
        List<String[]> listSearchResult = tableThree.getResultColumnSearch();
        checkFirstRecordStatusActive(tableThree.getListFromList(listSearchResult, 0));
        checkTwoRecordStatusActive(tableThree.getListFromList(listSearchResult, 1));
        checkThreeRecordStatusActive(tableThree.getListFromList(listSearchResult, 2));
        assertEquals(listSearchResult.size(), tableThree.getCountRecord(), MessageError.INVALID_COUNT_RECORDS.getMessage());
    }

    private void checkFirstRecordStatusActive(List<String> list){
        assertEquals("1", list.get(0), String.format(MessageError.INVALID_FIELD.getMessage(), "id"));
        assertEquals("Alice Cooper", list.get(1), MessageError.INVALID_NAME.getMessage());
        assertEquals("alice@example.com", list.get(2), String.format(MessageError.INVALID_FIELD.getMessage(), "email"));
        assertEquals("Active", list.get(3), MessageError.INVALID_NAME.getMessage());
    }

    private void checkTwoRecordStatusActive(List<String> list){
        assertEquals("3", list.get(0), String.format(MessageError.INVALID_FIELD.getMessage(), "id"));
        assertEquals("Charlie Adams", list.get(1), MessageError.INVALID_NAME.getMessage());
        assertEquals("charlie@example.com", list.get(2), String.format(MessageError.INVALID_FIELD.getMessage(), "email"));
        assertEquals("Active", list.get(3), MessageError.INVALID_NAME.getMessage());
    }

    private void checkThreeRecordStatusActive(List<String> list){
        assertEquals("5", list.get(0), String.format(MessageError.INVALID_FIELD.getMessage(), "id"));
        assertEquals("Eve Martinez", list.get(1), MessageError.INVALID_NAME.getMessage());
        assertEquals("eve@example.com", list.get(2), String.format(MessageError.INVALID_FIELD.getMessage(), "email"));
        assertEquals("Active", list.get(3), MessageError.INVALID_NAME.getMessage());
    }

    @Test
    @Tag("tables")
    @DisplayName("Проверка таблицы №3 с фильтрацией")
    @Description("Этот тест проверяет фильтрацию таблицы №3 по статусу \"Inactive\"")
    @Feature("Фильтрация по статусу")
    @Severity(NORMAL)
    public void testFilterTableForStatusInactive(){
        tableThree.setDropListStatus("Inactive");
        List<String[]> listSearchResult = tableThree.getResultColumnSearch();
        checkFirstRecordStatusInactive(tableThree.getListFromList(listSearchResult, 0));
        checkTwoRecordStatusInactive(tableThree.getListFromList(listSearchResult, 1));
        assertEquals(listSearchResult.size(), tableThree.getCountRecord(), MessageError.INVALID_COUNT_RECORDS.getMessage());
    }

    private void checkFirstRecordStatusInactive(List<String> list){
        assertEquals("2", list.get(0), String.format(MessageError.INVALID_FIELD.getMessage(), "id"));
        assertEquals("Bob Wilson", list.get(1), MessageError.INVALID_NAME.getMessage());
        assertEquals("bob@example.com", list.get(2), String.format(MessageError.INVALID_FIELD.getMessage(), "email"));
        assertEquals("Inactive", list.get(3), MessageError.INVALID_NAME.getMessage());
    }

    private void checkTwoRecordStatusInactive(List<String> list){
        assertEquals("6", list.get(0), String.format(MessageError.INVALID_FIELD.getMessage(), "id"));
        assertEquals("Frank Lopez", list.get(1), MessageError.INVALID_NAME.getMessage());
        assertEquals("frank@example.com", list.get(2), String.format(MessageError.INVALID_FIELD.getMessage(), "email"));
        assertEquals("Inactive", list.get(3), MessageError.INVALID_NAME.getMessage());
    }

    @Test
    @Tag("tables")
    @DisplayName("Проверка таблицы №3 с фильтрацией")
    @Description("Этот тест проверяет фильтрацию таблицы №3 по статусу \"Pending\"")
    @Feature("Фильтрация по статусу")
    @Severity(NORMAL)
    public void testFilterTableForStatusPending(){
        tableThree.setDropListStatus("Pending");
        List<String[]> listSearchResult = tableThree.getResultColumnSearch();
        checkFirstRecordStatusPending(tableThree.getListFromList(listSearchResult, 0));
        assertEquals(listSearchResult.size(), tableThree.getCountRecord(), MessageError.INVALID_COUNT_RECORDS.getMessage());
    }

    private void checkFirstRecordStatusPending(List<String> list){
        assertEquals("4", list.get(0), String.format(MessageError.INVALID_FIELD.getMessage(), "id"));
        assertEquals("Diana Moore", list.get(1), MessageError.INVALID_NAME.getMessage());
        assertEquals("diana@example.com", list.get(2), String.format(MessageError.INVALID_FIELD.getMessage(), "email"));
        assertEquals("Pending", list.get(3), MessageError.INVALID_NAME.getMessage());
    }

    @Test
    @Tag("tables")
    @DisplayName("Проверка таблицы №4 с пагинацией")
    @Description("Тест проверяет корректность работы количества отображаемых записей")
    @Feature("Увеличение количества отображаемых записей")
    @Severity(NORMAL)
    public void testTablePagination(){
        int recordCountBefore = tableFour.getCountRecordInTable();
        tableFour.setCountVisibleRecords(10);
        int recordCountAfter = tableFour.getCountRecordInTable();
        assertEquals(5, recordCountBefore, MessageError.INVALID_COUNT_RECORDS.getMessage());
        assertEquals(10, recordCountAfter, MessageError.INVALID_COUNT_RECORDS.getMessage());
    }

    @Test
    @Tag("tables")
    @DisplayName("Проверка таблицы №4 с пагинацией")
    @Description("Тест проверяет корректность работы кнопки \"Следующая страница\"")
    @Severity(BLOCKER)
    public void testTableButtonNextPage(){
        int numberCurrentPage = tableFour.getNumberCurrentPage();
        tableFour.clickNextPageButton();
        int numberNextPage = tableFour.getNumberCurrentPage();
        assertEquals(1, numberCurrentPage, MessageError.INVALID_NUMBER_PAGE.getMessage());
        assertEquals(2, numberNextPage, MessageError.INVALID_NUMBER_PAGE.getMessage());
    }

    @Test
    @Tag("tables")
    @DisplayName("Проверка таблицы №4 с пагинацией")
    @Description("Тест проверяет корректность работы кнопки \"Последняя страница\"")
    @Severity(BLOCKER)
    public void testTableButtonLastPage(){
        int numberCurrentPage = tableFour.getNumberCurrentPage();
        tableFour.clickLastPageButton();
        int numberNextPage = tableFour.getNumberCurrentPage();
        assertEquals(1, numberCurrentPage, MessageError.INVALID_NUMBER_PAGE.getMessage());
        assertEquals(4, numberNextPage, MessageError.INVALID_NUMBER_PAGE.getMessage());
    }

    @Test
    @Tag("tables")
    @DisplayName("Проверка таблицы №4 с пагинацией")
    @Description("Тест проверяет корректность работы кнопки \"Первая страница\"")
    @Severity(BLOCKER)
    public void testTableButtonFirstPage(){
        tableFour.clickLastPageButton();
        int numberCurrentPage = tableFour.getNumberCurrentPage();
        tableFour.clickFirstPageButton();
        int numberFirstPage = tableFour.getNumberCurrentPage();
        assertEquals(4, numberCurrentPage, MessageError.INVALID_NUMBER_PAGE.getMessage());
        assertEquals(1, numberFirstPage, MessageError.INVALID_NUMBER_PAGE.getMessage());
    }

    @Test
    @Tag("tables")
    @DisplayName("Проверка таблицы №4 с пагинацией")
    @Description("Тест проверяет корректность работы кнопки \"Предыдущая страница\"")
    @Severity(BLOCKER)
    public void testTablePreviousFirstPage(){
        tableFour.clickLastPageButton();
        int numberCurrentPage = tableFour.getNumberCurrentPage();
        tableFour.clickPreviousPageButton();
        int numberFirstPage = tableFour.getNumberCurrentPage();
        assertEquals(4, numberCurrentPage, MessageError.INVALID_NUMBER_PAGE.getMessage());
        assertEquals(3, numberFirstPage, MessageError.INVALID_NUMBER_PAGE.getMessage());
    }
}
