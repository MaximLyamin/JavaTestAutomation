package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ArticlePageObject extends MainPageObject {

    private static final String
        TITLE_ID = "org.wikipedia:id/view_page_title_text",
        FOOTER_ELEMENT_ID = "org.wikipedia:id/page_external_link",
        OPTIONS_BUTTON_XPATH = "//android.widget.ImageView[@content-desc='More options']",
        OPTIONS_ADD_TO_MY_LIST_BUTTON_XPATH = "//android.widget.LinearLayout[3]",
        ADD_TO_MY_LIST_OVERLAY_ID = "org.wikipedia:id/onboarding_button",
        MY_LIST_NAME_INPUT_ID = "org.wikipedia:id/text_input",
        MY_LIST_OK_BUTTON_XPATH = "//*[@text='OK']",
        CLOSE_ARTICLE_BUTTON_XPATH = "//android.widget.ImageButton[@content-desc='Navigate up']";

    public ArticlePageObject(AppiumDriver driver) {
        super(driver);
    }

    public WebElement waitForTitleElement() {
        return this.waitForElementPresent(By.id(TITLE_ID), "Cannot find article title on page!", 15);
    }

    public String getArticleTitle() {
        return this.waitForElementAndGetAttribute(By.id(TITLE_ID), "text", "Cannot find article title on page!", 15);
    }

    public void assertCompareArticles(String expected_text) {
        this.assertElementHasText(By.id(TITLE_ID), expected_text, "We see unexpected title", 15);
    }

    public void swipeToFooter() {
        this.swipeUpToElement(
                By.id(FOOTER_ELEMENT_ID),
                "",
                20);
    }

    public void addArticleToMyList(String name_of_folder) {
        this.waitForElementAndClick(
                By.xpath(OPTIONS_BUTTON_XPATH),
                "Cannot find 'More option' button",
                5);

        this.waitForElementAndClick(
                By.xpath(OPTIONS_ADD_TO_MY_LIST_BUTTON_XPATH),
                "Cannot find 'Add to reading list' button",
                5);

        this.waitForElementAndClick(
                By.id(ADD_TO_MY_LIST_OVERLAY_ID),
                "Cannot find 'Got it' button",
                5);

        this.waitForElementAndClear(
                By.id(MY_LIST_NAME_INPUT_ID),
                "Cannot find input to set name of articles folder",
                5);

        this.waitForElementAndSendKeys(
                By.id(MY_LIST_NAME_INPUT_ID),
                name_of_folder,
                "Cannot put text into articles folder input",
                5);

        this.waitForElementAndClick(
                By.xpath(MY_LIST_OK_BUTTON_XPATH),
                "Cannot press 'OK' button",
                5);
    }

    public void closeArticle() {
        this.waitForElementAndClick(
                By.xpath(CLOSE_ARTICLE_BUTTON_XPATH),
                "Cannot close article, cannot X button",
                5);
    }
}
