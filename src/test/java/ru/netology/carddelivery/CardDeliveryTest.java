package ru.netology.carddelivery;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;

public class CardDeliveryTest {
    String generateDate(int days) {
        return LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    ;

    @BeforeEach
    void setupTest() {
        open("http://localhost:9999");
    }

    @Test
    void formSubmission() {
        $("[data-test-id=city] input").setValue("Вологда").click();
        $("[data-test-id=date] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[data-test-id=date] input").setValue(generateDate(3));
        $("[data-test-id=name] input").setValue("Ван-бух Иван");
        $("[data-test-id=phone] input").setValue("+79998888888");
        $("[data-test-id=agreement]").click();
        $$("button").find(exactText("Забронировать")).click();
        $(withText("Успешно!")).shouldBe(visible, Duration.ofMillis(11000));
        $("[data-test-id=notification] .notification__content").shouldHave(text("Встреча успешно забронирована на " + generateDate(3)));
    }

    @Test
    void theSpecifiedCityIsNotFromTheListOfAdministrativeDistricts() {
        $("[data-test-id=city] input").setValue("Волхов").click();
        $("[data-test-id=date] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[data-test-id=date] input").setValue(generateDate(3));
        $("[data-test-id=name] input").setValue("Ван-бух Иван");
        $("[data-test-id=phone] input").setValue("+79998888888");
        $("[data-test-id=agreement]").click();
        $$("button").find(exactText("Забронировать")).click();
        $("[data-test-id=city].input_invalid .input__sub").shouldHave(text("Доставка в выбранный город недоступна"));
    }

    @Test
    void dateEarlierThanThreeDaysFromTheCurrentDate() {
        $("[data-test-id=city] input").setValue("Вологда").click();
        $("[data-test-id=date] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[data-test-id=date] input").setValue(generateDate(1));
        $("[data-test-id=name] input").setValue("Ван-бух Иван");
        $("[data-test-id=phone] input").setValue("+79998888888");
        $("[data-test-id=agreement]").click();
        $$("button").find(exactText("Забронировать")).click();
        $("[data-test-id=date] .input_invalid .input__sub").shouldHave(exactText("Заказ на выбранную дату невозможен"));
    }

    @Test
    void invalidValuesFieldName() {
        $("[data-test-id=city] input").setValue("Вологда").click();
        $("[data-test-id=date] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[data-test-id=date] input").setValue(generateDate(3));
        $("[data-test-id=name] input").setValue("Vasya Pupkin");
        $("[data-test-id=phone] input").setValue("+79998888888");
        $("[data-test-id=agreement]").click();
        $$("button").find(exactText("Забронировать")).click();
        $("[data-test-id=name].input_invalid .input__sub").shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    void validationCheckPhone() {
        $("[data-test-id=city] input").setValue("Вологда").click();
        $("[data-test-id=date] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[data-test-id=date] input").setValue(generateDate(3));
        $("[data-test-id=name] input").setValue("Ван-бух Иван");
        $("[data-test-id=phone] input").setValue("+7999888888");
        $("[data-test-id=agreement]").click();
        $$("button").find(exactText("Забронировать")).click();
        $("[data-test-id=phone].input_invalid .input__sub").shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    void noCheckbox() {
        $("[data-test-id=city] input").setValue("Вологда").click();
        $("[data-test-id=date] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[data-test-id=date] input").setValue(generateDate(3));
        $("[data-test-id=name] input").setValue("Ван-бух Иван");
        $("[data-test-id=phone] input").setValue("+79998888888");
        $$("button").find(exactText("Забронировать")).click();
        $("[data-test-id=agreement] .checkbox__text").shouldHave(exactText("Я соглашаюсь с условиями обработки и использования моих персональных данных"));
    }

    @Test
    void submittingAnEmptyForm() {
        $("[data-test-id=date] input").setValue(generateDate(3));
        $$("button").find(exactText("Забронировать")).click();
        $("[data-test-id=city].input_invalid .input__sub").shouldHave(exactText("Поле обязательно для заполнения"));
    }

    @Test
    void emptyFieldCity() {
        $("[data-test-id=date] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[data-test-id=date] input").setValue(generateDate(3));
        $("[data-test-id=name] input").setValue("Ван-бух Иван");
        $("[data-test-id=phone] input").setValue("+7999888888");
        $("[data-test-id=agreement]").click();
        $$("button").find(exactText("Забронировать")).click();
        $("[data-test-id=city].input_invalid .input__sub").shouldHave(exactText("Поле обязательно для заполнения"));
    }

    @Test
    void emptyFieldPhone() {
        $("[data-test-id=city] input").setValue("Вологда").click();
        $("[data-test-id=date] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[data-test-id=date] input").setValue(generateDate(3));
        $("[data-test-id=name] input").setValue("Ван-бух Иван");
        $("[data-test-id=agreement]").click();
        $$("button").find(exactText("Забронировать")).click();
        $("[data-test-id=phone].input_invalid .input__sub").shouldHave(exactText("Поле обязательно для заполнения"));
    }

    @Test
    void emptyFieldName() {
        $("[data-test-id=city] input").setValue("Вологда").click();
        $("[data-test-id=date] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[data-test-id=date] input").setValue(generateDate(3));
        $("[data-test-id=phone] input").setValue("+79998888888");
        $("[data-test-id=agreement]").click();
        $$("button").find(exactText("Забронировать")).click();
        $("[data-test-id=name].input_invalid .input__sub").shouldHave(exactText("Поле обязательно для заполнения"));
    }
}
