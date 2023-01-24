package ru.netology.carddelivery;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Locale;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class CardDeliveryTest {
    private LocalDate getDate(int days) {
        return LocalDate.now().plusDays(days);
    }

    private String generateDate(int days) {
        return LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    private String getExpectedDate(int days) {
        var date = getDate(days);
        String month = date.getMonth().getDisplayName(TextStyle.FULL_STANDALONE,
                Locale.getDefault()).toLowerCase();
        month = month.substring(0, 1).toUpperCase() + month.substring(1);
        return month + " " + date.getYear();
    }

    @BeforeEach
    void setupTest() {
        open("http://localhost:9999");
    }

    //    @Test
//    void formSubmission() {
//        $("[data-test-id=city] input").setValue("Вологда").click();
//        $("[data-test-id=date] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
//        $("[data-test-id=date] input").setValue(generateDate(3));
//        $("[data-test-id=name] input").setValue("Ван-бух Иван");
//        $("[data-test-id=phone] input").setValue("+79998888888");
//        $("[data-test-id=agreement]").click();
//        $$("button").find(exactText("Забронировать")).click();
//        $(withText("Успешно!")).shouldBe(visible, Duration.ofMillis(11000));
//        $("[data-test-id=notification] .notification__content").shouldHave(text("Встреча успешно забронирована на " + generateDate(3)));
//    }
//
//    @Test
//    void theSpecifiedCityIsNotFromTheListOfAdministrativeDistricts() {
//        $("[data-test-id=city] input").setValue("Волхов").click();
//        $("[data-test-id=date] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
//        $("[data-test-id=date] input").setValue(generateDate(3));
//        $("[data-test-id=name] input").setValue("Ван-бух Иван");
//        $("[data-test-id=phone] input").setValue("+79998888888");
//        $("[data-test-id=agreement]").click();
//        $$("button").find(exactText("Забронировать")).click();
//        $("[data-test-id=city].input_invalid .input__sub").shouldHave(text("Доставка в выбранный город недоступна"));
//    }
//
//    @Test
//    void dateEarlierThanThreeDaysFromTheCurrentDate() {
//        $("[data-test-id=city] input").setValue("Вологда").click();
//        $("[data-test-id=date] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
//        $("[data-test-id=date] input").setValue(generateDate(1));
//        $("[data-test-id=name] input").setValue("Ван-бух Иван");
//        $("[data-test-id=phone] input").setValue("+79998888888");
//        $("[data-test-id=agreement]").click();
//        $$("button").find(exactText("Забронировать")).click();
//        $("[data-test-id=date] .input_invalid .input__sub").shouldHave(exactText("Заказ на выбранную дату невозможен"));
//    }
//
//    @Test
//    void invalidValuesFieldName() {
//        $("[data-test-id=city] input").setValue("Вологда").click();
//        $("[data-test-id=date] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
//        $("[data-test-id=date] input").setValue(generateDate(3));
//        $("[data-test-id=name] input").setValue("Vasya Pupkin");
//        $("[data-test-id=phone] input").setValue("+79998888888");
//        $("[data-test-id=agreement]").click();
//        $$("button").find(exactText("Забронировать")).click();
//        $("[data-test-id=name].input_invalid .input__sub").shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
//    }
//
//    @Test
//    void validationCheckPhone() {
//        $("[data-test-id=city] input").setValue("Вологда").click();
//        $("[data-test-id=date] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
//        $("[data-test-id=date] input").setValue(generateDate(3));
//        $("[data-test-id=name] input").setValue("Ван-бух Иван");
//        $("[data-test-id=phone] input").setValue("+7999888888");
//        $("[data-test-id=agreement]").click();
//        $$("button").find(exactText("Забронировать")).click();
//        $("[data-test-id=phone].input_invalid .input__sub").shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
//    }
//
//    @Test
//    void noCheckbox() {
//        $("[data-test-id=city] input").setValue("Вологда").click();
//        $("[data-test-id=date] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
//        $("[data-test-id=date] input").setValue(generateDate(3));
//        $("[data-test-id=name] input").setValue("Ван-бух Иван");
//        $("[data-test-id=phone] input").setValue("+79998888888");
//        $$("button").find(exactText("Забронировать")).click();
//        $("[data-test-id=agreement] .checkbox__text").shouldHave(exactText("Я соглашаюсь с условиями обработки и использования моих персональных данных"));
//    }
//
//    @Test
//    void submittingAnEmptyForm() {
//        $("[data-test-id=date] input").setValue(generateDate(3));
//        $$("button").find(exactText("Забронировать")).click();
//        $("[data-test-id=city].input_invalid .input__sub").shouldHave(exactText("Поле обязательно для заполнения"));
//    }
//
//    @Test
//    void emptyFieldCity() {
//        $("[data-test-id=date] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
//        $("[data-test-id=date] input").setValue(generateDate(3));
//        $("[data-test-id=name] input").setValue("Ван-бух Иван");
//        $("[data-test-id=phone] input").setValue("+7999888888");
//        $("[data-test-id=agreement]").click();
//        $$("button").find(exactText("Забронировать")).click();
//        $("[data-test-id=city].input_invalid .input__sub").shouldHave(exactText("Поле обязательно для заполнения"));
//    }
//
//    @Test
//    void emptyFieldPhone() {
//        $("[data-test-id=city] input").setValue("Вологда").click();
//        $("[data-test-id=date] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
//        $("[data-test-id=date] input").setValue(generateDate(3));
//        $("[data-test-id=name] input").setValue("Ван-бух Иван");
//        $("[data-test-id=agreement]").click();
//        $$("button").find(exactText("Забронировать")).click();
//        $("[data-test-id=phone].input_invalid .input__sub").shouldHave(exactText("Поле обязательно для заполнения"));
//    }
//
//    @Test
//    void emptyFieldName() {
//        $("[data-test-id=city] input").setValue("Вологда").click();
//        $("[data-test-id=date] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
//        $("[data-test-id=date] input").setValue(generateDate(3));
//        $("[data-test-id=phone] input").setValue("+79998888888");
//        $("[data-test-id=agreement]").click();
//        $$("button").find(exactText("Забронировать")).click();
//        $("[data-test-id=name].input_invalid .input__sub").shouldHave(exactText("Поле обязательно для заполнения"));
//    }
//
//    @Test
//    void checkCompletionFieldCity() {
//        $("[data-test-id=city] input").setValue("Мо");
//        $$(".menu-item .menu-item__control").find(exactText("Кемерово")).click();
//        $("[data-test-id=city] input").doubleClick().sendKeys(Keys.BACK_SPACE);
//        $("[data-test-id=city] input").setValue("Мо");
//        $$(".menu-item .menu-item__control").find(exactText("Майкоп")).click();
//        $("[data-test-id=city] input").doubleClick().sendKeys(Keys.BACK_SPACE);
//        $("[data-test-id=city] input").setValue("Мо");
//        $$(".menu-item .menu-item__control").find(exactText("Москва")).click();
//        $("[data-test-id=city] input").doubleClick().sendKeys(Keys.BACK_SPACE);
//        $("[data-test-id=city] input").setValue("Мо");
//        $$(".menu-item .menu-item__control").find(exactText("Симферополь")).click();
//        $("[data-test-id=city] input").doubleClick().sendKeys(Keys.BACK_SPACE);
//        $("[data-test-id=city] input").setValue("Мо");
//        $$(".menu-item .menu-item__control").find(exactText("Смоленск")).click();
//        $("[data-test-id=city] input").doubleClick().sendKeys(Keys.BACK_SPACE);
//        $("[data-test-id=city] input").setValue("Мо");
//        $$(".menu-item .menu-item__control").find(exactText("Тамбов")).click();
//    }
//
    @Test
    void selectADateOneWeekAheadOfTheCurrentDate() {
        int days = 10;
        $("[data-test-id=city] input").setValue("Во");
        $$(".menu-item .menu-item__control").find(exactText("Вологда")).click();
        $("[data-test-id=date] input").click();
        $(".calendar-input__calendar-wrapper").shouldBe(visible, Duration.ofSeconds(5));
        Assertions.assertEquals("Январь 2023", $(".calendar__name").getText());
        Assertions.assertEquals("Февраль 2023", getExpectedDate(days));
        while (!$(".calendar__name").getText().equals(getExpectedDate(days))) {
            String current = $(".calendar__name").getText();
            $("div[class*=calendar__arrow_direction_right]:not([class*='calendar__arrow_double'])").click();
            $(".calendar__name").shouldNot(text(current), Duration.ofSeconds(5));
        }
//        $(byText(String.valueOf(LocalDate.now().plusDays(days).getDayOfMonth()))).click();
//        $("[data-test-id=name] input").setValue("Ван-бух Иван");
//        $("[data-test-id=phone] input").setValue("+79998888888");
//        $("[data-test-id=agreement]").click();
//        $$("button").find(exactText("Забронировать")).click();
//        $(withText("Успешно!")).shouldBe(visible, Duration.ofMillis(11000));
//        $("[data-test-id=notification] .notification__content").shouldHave(text("Встреча успешно забронирована на " + generateDate(days)));
    }
}

