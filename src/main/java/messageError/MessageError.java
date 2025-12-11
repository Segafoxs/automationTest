package messageError;

public enum MessageError {
    USER_NAME_ERROR("Username должен содержать минимум 5 символов"),
    EMAIL_ERROR("Email должен содержать символ @"),
    PASSWORD_ERROR("Password должен содержать минимум 8 символов, включая буквы и цифры"),
    PASSWORD_CONFIRM_ERROR("Пароли не совпадают"),
    INVALID_MESSAGE("Неверное сообщение");
    private final String message;

    MessageError(String message){
        this.message = message;
    }

    public String getMessage(){
        return message;
    }
}
