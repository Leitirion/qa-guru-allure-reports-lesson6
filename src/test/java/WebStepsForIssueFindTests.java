
import com.codeborne.selenide.Condition;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;

public class WebStepsForIssueFindTests {

    @Step("Open page")
    public void openURL(String URL) {
        open(URL);
    }

    @Step("Find repo with name {searchText}")
    public void searchRepository(String searchText) {
        $(".header-search-input").setValue(searchText).pressEnter();
    }

    @Step("Search for the repository {repositoryName}")
    public void goToRepository(String repositoryName) {
        $(By.linkText(repositoryName)).click();
    }

    @Step("Find and go to the {section1}")
    public void openIssueSection(String section1) {
        $(withText(section1)).click();
    }

    @Step("Find the issue #{issueNumber} and check it")
    public void findIssue(int issueNumber) {
        $(withText("#" + issueNumber)).shouldBe(Condition.visible);
    }

    @Attachment(value = "Screenshot", type = "image/png")
    public byte[] makeScreenshot() {
        return screenshot(OutputType.BYTES);
    }

}
