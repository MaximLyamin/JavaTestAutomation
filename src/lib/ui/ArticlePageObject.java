package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class ArticlePageObject extends MainPageObject {

    private static final String
        TITLE = "org.wikipedia:id/view_page_title_text";


    public ArticlePageObject(AppiumDriver driver) {
        super(driver);
    }

    public String getArticleTitle() {
        return this.waitForElementAndGetAttribute(By.id(TITLE), "text", "Cannot find article title on page!", 15);
    }

    public void assertCompareArticles(String expected_text) {
        this.assertElementHasText(By.id(TITLE), expected_text, "We see unexpected title", 15);
    }
}
