package pages.dragAndDrop;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.codeborne.selenide.Selenide.*;

@Slf4j
public class DragAndDropBetweenContainers {
    private final SelenideElement containerToDo = $x("//div[@id='todoContainer']").as("Контейнер to do");
    private final ElementsCollection toDoItems = $$x("//div[@id='todoContainer']/div").as("Количество элементов контейнера to do");
    private final SelenideElement containerInProgress = $x("//div[@id='progressContainer']").as("Контейнер in progress");
    private final ElementsCollection inProgressItems = $$x("//div[@id='progressContainer']/div").as("Количество элементов контейнера In Progress");
    private final SelenideElement containerDone = $x("//div[@id='doneContainer']").as("Контейнер done");
    private final ElementsCollection doneItems = $$x("//div[@id='doneContainer']/div").as("Количество элементов контейнера Done");
    private final SelenideElement taskTestWrite = $x("//div[@id='todoContainer']//div[@data-task='task2']").as("Задача \"Написать тесты\"");
    private final SelenideElement taskWriteAPI = $x("//div[@id='progressContainer']//div[@data-task='task4']").as("Задача \"Разработатка API\"");
    private final ElementsCollection taskTitleDoneContainer = $$x("//div[@id='doneContainer']//h5").as("заголовки задачи контейнера done");
    private final ElementsCollection taskTextDoneContainer = $$x("//div[@id='doneContainer']//p").as("текст задачи контейнера done");
    private final ElementsCollection taskTitleToDoContainer = $$x("//div[@id='todoContainer']//h5").as("заголовки задачи контейнера to do");
    private final ElementsCollection taskTextToDoContainer = $$x("//div[@id='todoContainer']//p").as("текст задачи контейнера to do");
    private final ElementsCollection taskTitleInProgressContainer = $$x("//div[@id='progressContainer']//h5").as("заголовки задачи контейнера in progress");
    private final ElementsCollection taskTextInProgressContainer = $$x("//div[@id='progressContainer']//p").as("текст задачи контейнера in progress");

    @Step("Считаем количество элементов в контейнере '{nameContainer}'")
    public int countItem(String nameContainer){
        int count = 0;
        switch (nameContainer.toLowerCase()){
            case "to do":
                count = toDoItems.size();
                break;
            case "in progress":
                count = inProgressItems.size();
                break;
            case "done":
                count = doneItems.size();
                break;
        }
        log.info(String.valueOf(count));
        return count;
    }

    @Step("Перетаскиваем в контейнер done")
    public void dragTaskBetweenContainer(){
        actions().dragAndDrop(taskTestWrite, containerInProgress).perform();
        actions().dragAndDrop(taskWriteAPI, containerDone).perform();
    }

    @Step("Достаем задачи из контейнера done")
    public List<String> getTaskFromDoneContainer(){
        Map<String, String> mapTask = getMapTaskFromDoneContainer();
        List<String> listTask = mapTask.keySet().stream().toList();
        log.info(String.format("Выполненые задачи: %s", listTask.toString()));
        return listTask;
    }

    private Map<String,String> getMapTaskFromDoneContainer(){
        Map<String, String> mapTask = new HashMap<>();
        for (int i = 0; i < taskTitleDoneContainer.size(); i++) {
            if (taskTitleDoneContainer.size() == taskTextDoneContainer.size()) {
                mapTask.put(taskTitleDoneContainer.get(i).text(), taskTextDoneContainer.get(i).text());
            }
        }
        int countTask = countItem("done");
        if (mapTask.size() == countTask) {
            log.info(String.format("%s", mapTask.toString()));
            return mapTask;
        }
        else {
            log.info(String.format("Пустая map: %s", mapTask.toString()));
            return mapTask;
        }
    }
}
