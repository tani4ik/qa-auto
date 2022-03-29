package tests;

import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.addAttachment;
import static io.qameta.allure.Allure.step;

public class TestWithLambdaSteps {
    private static final String REPOSITORY = "eroshenkoam/allure-example";
    private static final int ISSUE_NUMBER = 65;

    @Test
    void checkIfIssueExists() {
        SelenideLogger.addListener("allure", new AllureSelenide());

        step("Open Github page", () -> {
            open("https://github.com");
        });

        step("Search for repository " + REPOSITORY, () -> {
            $(".header-search-input").click();
            $(".header-search-input").setValue(REPOSITORY);
            $(".header-search-input").submit();
        });

        step("Open repository " + REPOSITORY, () -> {
            $(By.linkText("eroshenkoam/allure-example")).click();
        });

        step("Open Issue tab", () -> {
            $(By.partialLinkText("Issues")).click();
            addAttachment("Page Source", "text/html", WebDriverRunner.source(), "html");
        });

        step("Check that issue with number " + ISSUE_NUMBER + " exists", () -> {
            $(withText("#65")).should(exist);
        });

        step("Close page", () -> {
            closeWebDriver();
        });
    }
}
