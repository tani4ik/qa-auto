package tests;

import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class TestWithParameters {

    @BeforeEach
    void openPage() {
        open("https://jobs.dev.by");
        $(".wishes-popup__button-close").click();
    }

    @AfterEach
    void closeBrowser() {
        Selenide.closeWebDriver();
    }

    @ValueSource(strings = {"Python", "QA Manual", "QA Automation"})
    @ParameterizedTest
    void filterResults(String testdata) {
        $(".filter_specialization_title").$(byText(testdata)).click();
        $(".vacancies-list__filter-tag").shouldHave(text(testdata));
    }

    @CsvSource(value = {
            "swift | iOS Developer",
            "react | Front-end Developer"
    }, delimiter = '|')
    @ParameterizedTest
    void searchTechnology(String language, String developer) {
        $("#filter_search").setValue(language);
        $(".search__button").click();
        $(".vacancies-list__body").shouldHave(text(developer));
    }
}
