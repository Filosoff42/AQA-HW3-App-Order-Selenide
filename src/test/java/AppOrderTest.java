import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.*;

class AppOrderTest {

    @Test
    void shouldTestSubmitSuccess() {
        open("http://localhost:9999");
        $("[data-test-id=name] input").setValue("Григорий Харитонский");
        $("[data-test-id=phone] input").setValue("+70000000000");
        $("[data-test-id=agreement] span").click();
        $("button").click();
        $("[data-test-id=order-success]").shouldHave(Condition.exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }

    @Test
    void shouldTestAllFieldsEmpty() {
        open("http://localhost:9999");
        $("button").click();
        $("[data-test-id=name]").shouldHave(Condition.cssClass("input_invalid"));
        $("[data-test-id=name] span.input__sub").shouldHave(Condition.exactText("Поле обязательно для заполнения"));
    }

    @Test
    void shouldTestNameFieldEmpty() {
        open("http://localhost:9999");
        $("[data-test-id=phone] input").setValue("+70000000000");
        $("[data-test-id=agreement] span").click();
        $("button").click();
        $("[data-test-id=name]").shouldHave(Condition.cssClass("input_invalid"));
        $("[data-test-id=name] span.input__sub").shouldHave(Condition.exactText("Поле обязательно для заполнения"));
    }

    @Test
    void shouldTestPhoneFieldEmpty() {
        open("http://localhost:9999");
        $("[data-test-id=name] input").setValue("Григорий Харитонский");
        $("[data-test-id=agreement] span").click();
        $("button").click();
        $("[data-test-id=phone]").shouldHave(Condition.cssClass("input_invalid"));
        $("[data-test-id=phone] span.input__sub").shouldHave(Condition.exactText("Поле обязательно для заполнения"));
    }

    @Test
    void shouldTestNoAgreementCheck() {
        open("http://localhost:9999");
        $("[data-test-id=name] input").setValue("Григорий Харитонский");
        $("[data-test-id=phone] input").setValue("+70000000000");
        $("button").click();
        $("[data-test-id=agreement]").shouldHave(Condition.cssClass("input_invalid"));
    }

    @Test
    void shouldTestNameValidationFail() {
        open("http://localhost:9999");
        $("[data-test-id=name] input").setValue("Grigoriy Kharitonskiy");
        $("[data-test-id=phone] input").setValue("+70000000000");
        $("[data-test-id=agreement] span").click();
        $("button").click();
        $("[data-test-id=name]").shouldHave(Condition.cssClass("input_invalid"));
        $("[data-test-id=name] span.input__sub").shouldHave(Condition.exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    void shouldTestPhoneValidationFail() {
        open("http://localhost:9999");
        $("[data-test-id=name] input").setValue("Григорий Харитонский");
        $("[data-test-id=phone] input").setValue("123");
        $("[data-test-id=agreement] span").click();
        $("button").click();
        $("[data-test-id=phone]").shouldHave(Condition.cssClass("input_invalid"));
        $("[data-test-id=phone] span.input__sub").shouldHave(Condition.exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }
}