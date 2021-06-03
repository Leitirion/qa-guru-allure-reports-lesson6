import com.codeborne.selenide.Condition;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.*;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;

public class IssueFindTests {
    String URL = "https://github.com",
     searchText = "allure",
     section1 = "Issues",
     repositoryName = "allure-framework/allure2";
    int issueNumber = 1274;

    private final WebStepsForIssueFindTests steps = new WebStepsForIssueFindTests();

    @Test
    public void testIssueFind() {

        SelenideLogger.addListener("allure", new AllureSelenide());

        open(URL);
        $(".header-search-input").setValue(searchText).pressEnter();
        $(By.linkText(repositoryName)).click();
        $(byText(section1)).click();
        $(withText("#" + issueNumber)).shouldBe(Condition.visible);

    }

    @Test
    @Feature("Issues")
    @Story("Find Issue with number")
    @Severity(SeverityLevel.BLOCKER)
    @Link(value = "Github", url = "https://github.com")
    @DisplayName("Find Issue")
    public void testIssueFindWithStep() {

        step("Open page", (s) -> {
            s.parameter("URL", URL);
            open(URL);
        });
        step("Find repo with name " + searchText, (s) -> {
            s.parameter("repository", searchText);
            $(".header-search-input").setValue(searchText).pressEnter();
        });
        step("Search for the repository " + repositoryName, (s) -> {
            s.parameter("repository", repositoryName);
            $(By.linkText(repositoryName)).click();
        });
        step("Find and go to the " + section1 +" section", () ->
                $(withText(section1)).click()
        );
        step("Find the issue #" + issueNumber + " and check it", (s) -> {
            s.parameter("number", issueNumber);
            $(withText("#" + issueNumber)).shouldBe(Condition.visible);
        });

    }

    @Test
    @Feature("Issues")
    @Story("Find Issue with number")
    @Severity(SeverityLevel.BLOCKER)
    @Link(value = "Github", url = "https://github.com")
    @DisplayName("Find Issue")
    public void testIssueFindWithWebSteps() {
        steps.openURL(URL);
        steps.searchRepository(searchText);
        steps.goToRepository(repositoryName);
        steps.openIssueSection(section1);
        steps.findIssue(issueNumber);
        steps.makeScreenshot();
    }

}