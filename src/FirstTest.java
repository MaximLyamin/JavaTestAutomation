import lib.CoreTestCase;
import lib.ui.*;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;

public class FirstTest extends CoreTestCase {

    private MainPageObject MainPageObject;

    protected void setUp() throws Exception {
        super.setUp();

        MainPageObject = new MainPageObject(driver);
    }

    @Test
    public void testFieldInputHasText() {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        String expected_text = "Search…";

        SearchPageObject.initSearchInput();
        SearchPageObject.assertCompareSearchInputText(expected_text);
    }

    @Test
    public void testCancelSearchResults() {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");

        MainPageObject.searchResultHasSomeArticles(
                By.id("org.wikipedia:id/page_list_item_title"),
                "Cannot see some articles",
                15);

        MainPageObject.waitForElementAndClear(
                By.id("org.wikipedia:id/search_src_text"),
                "Cannot find search field by Id",
                5);

        MainPageObject.waitForElementPresent(
                By.id("org.wikipedia:id/search_empty_message"),
                "Search page does not clear",
                15);
    }

    @Test
    public void testCheckKeyWordInSearchResults() {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");

        MainPageObject.checkKeyWordInEachResult(
                By.id("org.wikipedia:id/page_list_item_title"),
                "Java",
                "Cannot see keyword in search results",
                15);
    }

    @Test
    public void testSaveTwoArticlesToMyListThanOneDelete() {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']"),
                "Cannot find article with 'Object-oriented programming language'",
                15);

        MainPageObject.waitForElementPresent(
                By.id("org.wikipedia:id/view_page_title_text"),
                "Cannot find article title on page",
                15);

        MainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.ImageView[@content-desc='More options']"),
                "Cannot find 'More option' button",
                5);

        MainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.LinearLayout[3]"),
                "Cannot find 'Add to reading list' button",
                5);

        MainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/onboarding_button"),
                "Cannot find 'Got it' button",
                5);

        MainPageObject.waitForElementAndClear(
                By.id("org.wikipedia:id/text_input"),
                "Cannot find input to set name of articles folder",
                5);

        String name_of_folder = "Learning programming";

        MainPageObject.waitForElementAndSendKeys(
                By.id("org.wikipedia:id/text_input"),
                name_of_folder,
                "Cannot put text into articles folder input",
                5);

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@text='OK']"),
                "Cannot press 'OK' button",
                5);

        MainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
                "Cannot close article, cannot X button",
                5);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Programming language']"),
                "Cannot find article with 'Object-oriented programming language'",
                15);

        MainPageObject.waitForElementPresent(
                By.id("org.wikipedia:id/view_page_title_text"),
                "Cannot find article title on page",
                15);

        MainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.ImageView[@content-desc='More options']"),
                "Cannot find 'More option' button",
                5);

        MainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.LinearLayout[3]"),
                "Cannot find 'Add to reading list' button",
                5);

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@text='" + name_of_folder + "']"),
                "Cannot find created list",
                15);

        MainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
                "Cannot close article, cannot X button",
                5);

        MainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.FrameLayout[@content-desc='My lists']"),
                "Cannot find 'My lists' button",
                10);

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@text='" + name_of_folder + "']"),
                "Cannot find created folder",
                5);

        MainPageObject.swipeElementToLeft(
                By.xpath("//*[@text='Java (programming language)']"),
                "Cannot find saved article");

        MainPageObject.waitForElementPresent(
                By.xpath("//*[@text='JavaScript']"),
                "Cannot find second saved article",
                5);

        String title_in_list = MainPageObject.waitForElementAndGetAttribute(
                By.xpath("//*[@text='JavaScript']"),
                "text",
                "Cannot find article with title 'JavaScript'",
                5);

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@text='JavaScript']"),
                "",
                15);

        String title_in_open_article = MainPageObject.waitForElementAndGetAttribute(
                By.xpath("//*[@text='JavaScript']"),
                "text",
                "Cannot find article with title 'JavaScript'",
                5);

        Assert.assertEquals(
                "Article have been changed after opening",
                title_in_open_article,
                title_in_list);
    }

    @Test
    public void testCheckOpenArticleHasTitle() {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']"),
                "Cannot find article with 'Object-oriented programming language'",
                15);

        MainPageObject.assertElementPresent(
                By.id("org.wikipedia:id/view_page_title_text"),
                "Cannot find article title on open page");
    }
}
