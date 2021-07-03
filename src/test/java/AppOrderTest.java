import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

class AppOrderTest {

    @Test
    void shouldTestSomething() throws InterruptedException {
        open("http://localhost:9999");
        $("[data-test-id=name] input").setValue("Григорий Харитонский");
        $("[data-test-id=phone] input").setValue("+70000000000");
        $("[data-test-id=agreement] span").click();
        $("button").click();
        $("[data-test-id=order-success]").shouldHave(Condition.exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }
}