import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
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
    void shouldTestNameValidation() {
        open("http://localhost:9999");
        $("[data-test-id=name] input").setValue("Grigoriy Kharitonskiy");
        $("button").click();
        $("[data-test-id=name]").shouldHave(Condition.cssClass("input_invalid"));
    }
}