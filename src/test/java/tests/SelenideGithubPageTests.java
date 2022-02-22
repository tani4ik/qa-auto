package tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selectors.byText;

public class SelenideGithubPageTests {

    @BeforeAll
    static void beforeAll() {
        Configuration.baseUrl = "https://github.com";
        Configuration.browserSize = "1920x1080";
    }

    @Test
    void wikiContainsJUnit5Example() {
        open("/selenide/selenide");
        $("h1").shouldHave(text("selenide / selenide"));
        $("#wiki-tab").click();
        $("#wiki-body").$("h1").shouldHave(text("Welcome to the selenide wiki!"));
        $(".wiki-rightbar").$(".js-wiki-more-pages-link").click();
        $(".wiki-rightbar").$(byText("SoftAssertions")).click();
        $("#wiki-wrapper").$("h1").shouldHave(text("SoftAssertions"));
        $(".markdown-body").shouldHave(text("Using JUnit5 extend test class:"));
    }
}
