package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;

public class ArticlePageObject extends MainPageObject {

    private static final String
        TITLE_ID = "id:org.wikipedia:id/view_page_title_text",
        FOOTER_ELEMENT_ID = "id:org.wikipedia:id/page_external_link",
        OPTIONS_BUTTON_XPATH = "xpath://android.widget.ImageView[@content-desc='More options']",
        OPTIONS_ADD_TO_MY_LIST_BUTTON_XPATH = "xpath://android.widget.LinearLayout[3]",
        ADD_TO_MY_LIST_OVERLAY_ID = "id:org.wikipedia:id/onboarding_button",
        MY_LIST_NAME_INPUT_ID = "id:org.wikipedia:id/text_input",
        MY_LIST_OK_BUTTON_XPATH = "xpath://*[@text='OK']",
        CLOSE_ARTICLE_BUTTON_XPATH = "xpath://android.widget.ImageButton[@content-desc='Navigate up']";

    public ArticlePageObject(AppiumDriver driver) {
        super(driver);
    }

    public WebElement waitForTitleElement() {
        return this.waitForElementPresent(TITLE_ID, "Cannot find article title on page!", 15);
    }

    public String getArticleTitle() {
        return this.waitForElementAndGetAttribute(TITLE_ID, "text", "Cannot find article title on page!", 15);
    }

    public void assertCompareArticles(String expected_text) {
        this.assertElementHasText(TITLE_ID, expected_text, "We see unexpected title", 15);
    }

    public void swipeToFooter() {
        this.swipeUpToElement(
                FOOTER_ELEMENT_ID,
                "",
                20);
    }

    public void addArticleToMyListFirstTime(String name_of_folder) {
        this.waitForElementAndClick(
                OPTIONS_BUTTON_XPATH,
                "Cannot find 'More option' button",
                5);

        this.waitForElementAndClick(
                OPTIONS_ADD_TO_MY_LIST_BUTTON_XPATH,
                "Cannot find 'Add to reading list' button",
                5);

        this.waitForElementAndClick(
                ADD_TO_MY_LIST_OVERLAY_ID,
                "Cannot find 'Got it' button",
                5);

        this.waitForElementAndClear(
                MY_LIST_NAME_INPUT_ID,
                "Cannot find input to set name of articles folder",
                5);

        this.waitForElementAndSendKeys(
                MY_LIST_NAME_INPUT_ID,
                name_of_folder,
                "Cannot put text into articles folder input",
                5);

        this.waitForElementAndClick(
                MY_LIST_OK_BUTTON_XPATH,
                "Cannot press 'OK' button",
                5);
    }

    public void closeArticle() {
        this.waitForElementAndClick(
                CLOSE_ARTICLE_BUTTON_XPATH,
                "Cannot close article, cannot X button",
                5);
    }

    public void assertTitleByIdIsPresentOnOpenArticle() {
        this.assertElementPresent(
                TITLE_ID,
                "Cannot find article title on open page by id " + TITLE_ID);
    }

    public void addArticleToMyCreatedList(String name_of_folder) {
        this.waitForElementAndClick(
                OPTIONS_BUTTON_XPATH,
                "Cannot find 'More option' button",
                5);

        this.waitForElementAndClick(
                OPTIONS_ADD_TO_MY_LIST_BUTTON_XPATH,
                "Cannot find 'Add to reading list' button",
                5);

        this.waitForElementAndClick(
                "xpath://*[@text='" + name_of_folder + "']",
                "Cannot find created folder",
                5);
    }
}
