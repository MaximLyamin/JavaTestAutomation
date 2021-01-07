package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ArticlePageObject extends MainPageObject {

    private static final String
        TITLE_ID = "org.wikipedia:id/view_page_title_text",
        FOOTER_ELEMENT_ID = "org.wikipedia:id/page_external_link";


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
}
