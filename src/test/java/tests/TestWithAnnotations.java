package tests;

import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Feature("Github issues")
public class TestWithAnnotations {
    private static final String REPOSITORY = "eroshenkoam/allure-example";
    private static final int ISSUE_NUMBER = 65;

    @Test
    @Owner("team_facelift")
    @Severity(SeverityLevel.NORMAL)
    @Story("Create new issue")
    @DisplayName("Check that issue with a specific number exists")
    @Tag("smoke")

    @Link(value = "Testing", url = "https://github.com")

    void checkIfIssueExists() {
        Allure.parameter("REPOSITORY", REPOSITORY);
        Allure.parameter("ISSUE_NUMBER", ISSUE_NUMBER);

        GithubSteps steps = new GithubSteps();

        steps.openGithub();
        steps.searchForRepository(REPOSITORY);
        steps.openRepository(REPOSITORY);
        steps.openIssuesTab();
        steps.checkThatIssueExists(ISSUE_NUMBER);
        steps.closeBrowser();
    }
}
