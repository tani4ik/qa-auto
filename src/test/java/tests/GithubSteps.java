package tests;

import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;

public class GithubSteps {
    @Step
    public void openGithub() {
        open("https://github.com");
    }

    @Step
    public void searchForRepository(String repository) {
        $(".header-search-input").click();
        $(".header-search-input").setValue(repository);
        $(".header-search-input").submit();
    }

    @Step
    public void openRepository(String repo) {
        $(By.linkText(repo)).click();
    }

    @Step
    public void openIssuesTab() {
        $(By.partialLinkText("Issues")).click();
    }

    @Step
    public void checkThatIssueExists(Integer number) {
        $(withText(number.toString())).should(exist);
    }

    @Step
    public void closeBrowser() {
        closeWebDriver();
    }


}
