package generators;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;

import java.time.LocalDate;

/** Класс для генерации тестовых данных пользователя */
@Slf4j
public class GeneratorData {

    public static String generateUserName(){
        StringBuilder userName = new StringBuilder("userAutoTest")
                .append(RandomStringUtils.insecure().nextNumeric(5))
                .append(LocalDate.now());
        log.info(String.format("Сгенерировано имя пользователя: %s", userName));
        return userName.toString();
    }

    public static String generatePassword(){
        return RandomStringUtils.insecure().nextAlphanumeric(10);
    }

    public static String generatePassword(int length){
        return RandomStringUtils.insecure().nextAlphanumeric(length);
    }

    public static String generateUserEmail(){
        StringBuilder email = new StringBuilder("userEmail")
                .append(RandomStringUtils.insecure().nextNumeric(5))
                .append(LocalDate.now())
                .append("@email.ru");
        log.info(String.format("Сгенерирован email: %s", email));
        return email.toString();
    }

    public static String generateNumberPhone(){
        StringBuilder phone = new StringBuilder("89")
                .append(RandomStringUtils.insecure().nextNumeric(9));
        log.info(String.format("Сгенерирован номер телефона: %s", phone));
        return phone.toString();
    }

    public static String generateRandomString(int length){
        return RandomStringUtils.insecure().randomAlphabetic(length);
    }
}
