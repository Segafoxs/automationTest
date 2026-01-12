package org.example.autoTestBrows.dynamicContentAutoTest;

import io.qameta.allure.Description;
import lombok.extern.slf4j.Slf4j;
import messageError.MessageError;
import org.example.WebHook;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pages.dynamicContent.AppearanceElement;
import pages.dynamicContent.InfiniteScroll;
import pages.dynamicContent.LoadData;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
public class DynamicContentAutoTest extends WebHook {
    private final AppearanceElement appearanceElement = new AppearanceElement();
    private final LoadData loadData = new LoadData();
    private final InfiniteScroll infiniteScroll = new InfiniteScroll();

    @Test
    @Tag("dynamicContent")
    @DisplayName("1. Появление элементов")
    @Description("Элементы появляются через 2 секунды после нажатия кнопки")
    public void appearanceElementTest(){
        appearanceElement.clickButtonShowElements();
        assertEquals("Элемент 2", appearanceElement.checkElement(), "Элемент не появился");
    }

    @Test
    @Tag("dynamicContent")
    @DisplayName("2. AJAX загрузка данных")
    @Description("Загрузка данных имитирует запрос к API (3 секунды). Проверка структуры таблицы")
    public void LoadDataCheckHeadInTableTest(){
        loadData.clickButtonLoadData();
        List<String> listHeadTable = loadData.getNameColumn();
        assertEquals("ID", listHeadTable.get(0), String.format(MessageError.INVALID_NAME.getMessage(), "ID"));
        assertEquals("Name", listHeadTable.get(1), String.format(MessageError.INVALID_NAME.getMessage(), "Name"));
        assertEquals("Status", listHeadTable.get(2), String.format(MessageError.INVALID_NAME.getMessage(), "Status"));
    }

    @Test
    @Tag("dynamicContent")
    @DisplayName("2. AJAX загрузка данных")
    @Description("Загрузка данных имитирует запрос к API (3 секунды). Проверка значений у таблицы")
    public void LoadDataCheckLineFromTableTest(){
        loadData.clickButtonLoadData();
        List<String> listItem = loadData.getLineFromTable(1);
        assertEquals("1", listItem.get(0), String.format(MessageError.INVALID_FIELD.getMessage(), "id"));
        assertEquals("User Alpha", listItem.get(1), String.format(MessageError.INVALID_FIELD.getMessage(), "name"));
        assertEquals("Active", listItem.get(2), String.format(MessageError.INVALID_FIELD.getMessage(), "status"));
    }

    @Test
    @Tag("dynamicContent")
    @DisplayName("3. Infinite Scroll")
    @Description("Прокрутите вниз для загрузки новых элементов")
    public void infiniteScrollTest(){
        int lenBefore = infiniteScroll.getLengthElements();
        infiniteScroll.scrollElements();
        int lenAfter = infiniteScroll.getLengthElements();
        Assertions.assertTrue(lenAfter > lenBefore);
    }
}
