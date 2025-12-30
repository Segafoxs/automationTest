package org.example.autoTestBrows.modalWindows;

import io.qameta.allure.Description;
import io.qameta.allure.Link;
import org.example.WebHook;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pages.modalWindows.ModalWindowOne;
import pages.modalWindows.ModalWindowThree;
import pages.modalWindows.ModalWindowTwo;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Link(name = "WEB Sandbox", url = "https://aqa-proka4.org/sandbox/web")
public class ModalWindowsAutoTest extends WebHook {
    private final ModalWindowOne modalWindowOne = new ModalWindowOne();
    private final ModalWindowTwo modalWindowTwo = new ModalWindowTwo();
    private final ModalWindowThree modalWindowThree = new ModalWindowThree();

    @Test
    @Tag("modalWindow")
    @DisplayName("Взаимодействие с модальным окном №1")
    @Description("Нажатие клавиши ОК в модальном окне")
    public void testModalWindowClickButtonOk(){
        modalWindowOne.clickButtonOpenModal();
        assertEquals("Это простое модальное окно для практики автоматизации.", modalWindowOne.getTextFromModal());
        modalWindowOne.clickButtonInModal("ok");
    }

    @Test
    @Tag("modalWindow")
    @DisplayName("Взаимодействие с модальным окном №1")
    @Description("Нажатие клавиши Закрыть в модальном окне")
    public void testModalWindowClickButtonClose(){
        modalWindowOne.clickButtonOpenModal();
        assertEquals("Это простое модальное окно для практики автоматизации.", modalWindowOne.getTextFromModal());
        modalWindowOne.clickButtonInModal("close");
    }

    @Test
    @Tag("modalWindow")
    @DisplayName("Взаимодействие с модальным окном №2")
    @Description("Тестирование вложенных модальных окон")
    public void testModalTwo(){
        modalWindowTwo.clickButtonOpenModalWindow();
        assertEquals("Первое модальное окно", modalWindowTwo.getTextFromModalLevelOne());
        modalWindowTwo.clickButtonOpenModalTwo();
        assertEquals("Второе модальное окно", modalWindowTwo.getTextFromModalLevelTwo());
        modalWindowTwo.clickButtonOpenModalThree();
        assertEquals("Третье модальное окно", modalWindowTwo.getTextFromModalLevelThree());
        assertEquals("Вы достигли максимального уровня вложенности!", modalWindowTwo.getFinalText());
    }

    @Test
    @Tag("modalWindow")
    @DisplayName("Взаимодействие с модальным окном №2")
    @Description("Тестирование кнопки ЗАКРЫТЬ в модальном окне")
    public void testModalTwoClose(){
        modalWindowTwo.clickButtonOpenModalWindow();
        modalWindowTwo.clickButtonOpenModalTwo();
        modalWindowTwo.clickButtonOpenModalThree();
        assertEquals("Вы достигли максимального уровня вложенности!", modalWindowTwo.getFinalText());
        modalWindowTwo.clickButtonCloseModalThree();
        assertEquals("Второе модальное окно", modalWindowTwo.getTextFromModalLevelTwo());
        modalWindowTwo.clickButtonCloseModalTwo();
        assertEquals("Первое модальное окно", modalWindowTwo.getTextFromModalLevelOne());
        modalWindowTwo.clickButtonCloseModalOne();
        assertEquals("2. Вложенные модальные окна", modalWindowTwo.getTextFromH3());
    }

    @Test
    @Tag("modalWindow")
    @DisplayName("Взаимодействие с модальным окном SUCCESS MODAL №3")
    @Description("Открытие и проверка содержимого модального окна SUCCESS MODAL")
    public void testModalThreeSuccessModal(){
        modalWindowThree.clickModalSuccess();
        assertEquals("Операция выполнена", modalWindowThree.getTitleModalWindow());
        assertEquals("Данные успешно сохранены!", modalWindowThree.getTextModalWindow());
        modalWindowThree.clickCloseModalButton();
    }

    @Test
    @Tag("modalWindow")
    @DisplayName("Взаимодействие с модальным окном ERROR MODAL №3")
    @Description("Открытие и проверка содержимого модального окна ERROR MODAL")
    public void testModalThreeErrorModal(){
        modalWindowThree.clickModalError();
        assertEquals("Ошибка!", modalWindowThree.getTitleModalWindow());
        assertEquals("Произошла ошибка при обработке запроса.", modalWindowThree.getTextModalWindow());
        modalWindowThree.clickCloseModalButton();
    }

    @Test
    @Tag("modalWindow")
    @DisplayName("Взаимодействие с модальным окном WARNING MODAL №3")
    @Description("Открытие и проверка содержимого модального окна ERROR MODAL")
    public void testModalThreeWarningModal(){
        modalWindowThree.clickModalWarning();
        assertEquals("Внимание", modalWindowThree.getTitleModalWindow());
        assertEquals("Эта операция необратима. Продолжить?", modalWindowThree.getTextModalWindow());
        modalWindowThree.clickCloseModalButton();
    }
}
