package org.example.autoTestBrows.dragAndDrop;

import io.qameta.allure.Description;
import io.qameta.allure.Link;
import messageError.MessageError;
import org.example.WebHook;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pages.dragAndDrop.DragAndDrop;
import pages.dragAndDrop.DragAndDropBetweenContainers;
import pages.dragAndDrop.DragAndDropSort;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Link(name = "WEB Sandbox", url = "https://aqa-proka4.org/sandbox/web")
public class DragAndDropAutoTest extends WebHook {
    private final DragAndDrop dragAndDrop = new DragAndDrop();
    private final DragAndDropSort dragAndDropSort = new DragAndDropSort();
    private final DragAndDropBetweenContainers dragAndDropBetweenContainers = new DragAndDropBetweenContainers();

    @Test
    @Tag("dragAndDrop")
    @DisplayName("Простой Drag & Drop")
    @Description("Перетаскивание из списка \"Доступные\" в \"Выбранные\"")
    public void testDragAndDrop(){
        dragAndDrop.addSelectItems();
        assertEquals(4, dragAndDrop.getSizeSelectedItems(), MessageError.INVALID_COUNT_ELEMENTS.getMessage());
    }

    @Test
    @Tag("dragAndDrop")
    @DisplayName("Сортировка списка")
    @Description("Перетаскивание элемента для изменения порядка")
    public void testDragAndDropSort(){
        dragAndDropSort.reverseItemDragAndDrop();
        List<String> listItem = dragAndDropSort.getListItem();
        assertEquals("Позиция 5", listItem.get(0));
        assertEquals("Позиция 4", listItem.get(1));
        assertEquals("Позиция 3", listItem.get(2));
        assertEquals("Позиция 2", listItem.get(3));
        assertEquals("Позиция 1", listItem.get(4));
    }

    @Test
    @Tag("dragAndDrop")
    @DisplayName("Перетаскивание между контейнерами")
    @Description("Организуйте задачи по статусам: To Do, In Progress, Done")
    public void testCountTaskFromContainers(){
        int countItemsBefore = dragAndDropBetweenContainers.countItem("done");
        dragAndDropBetweenContainers.dragTaskBetweenContainer();
        int countItemsAfter = dragAndDropBetweenContainers.countItem("done");
        assertEquals(countItemsBefore+1, countItemsAfter, MessageError.INVALID_COUNT_TASKS.getMessage());
    }

    @Test
    @Tag("dragAndDrop")
    @DisplayName("Перетаскивание между контейнерами")
    @Description("Организуйте задачи по статусам: To Do, In Progress, Done")
    public void testDragAndDropBetweenContainers(){
        dragAndDropBetweenContainers.dragTaskBetweenContainer();
        List<String> listTask = dragAndDropBetweenContainers.getTaskFromDoneContainer();
        assertEquals(2, listTask.size(), MessageError.INVALID_COUNT_TASKS.getMessage());
        assertEquals("База данных", listTask.get(0));
        assertEquals("Разработка API", listTask.get(1));
    }

}
