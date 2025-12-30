package messageError;

public enum MessageError {
    USER_NAME_ERROR("Username должен содержать минимум 5 символов"),
    EMAIL_ERROR("Email должен содержать символ @"),
    PASSWORD_ERROR("Password должен содержать минимум 8 символов, включая буквы и цифры"),
    PASSWORD_CONFIRM_ERROR("Пароли не совпадают"),
    INVALID_MESSAGE("Неверное сообщение"),
    ABSENT_COLUMN("Отсутствует столбец: %s"),
    INVALID_NAME("Неверное имя"),
    INVALID_SORT("Неверная сортировка по: %s"),
    INVALID_FIELD("Неверное значение поля: %s"),
    INVALID_COUNT_RECORDS("Неверное количество записей"),
    INVALID_NUMBER_PAGE("Неверный номер страницы"),
    INVALID_COUNT_ELEMENTS("Неверное количество элементов"),
    INVALID_COUNT_TASKS("Неверное количество задач");
    private final String message;

    MessageError(String message){
        this.message = message;
    }

    public String getMessage(){
        return message;
    }
}
