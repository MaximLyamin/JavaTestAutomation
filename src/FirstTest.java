import lib.CoreTestCase;
import lib.ui.MainPageObject;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.ScreenOrientation;

import java.time.Duration;

public class FirstTest extends CoreTestCase {

    private MainPageObject MainPageObject;

    protected void setUp() throws Exception{
        super.setUp();

        MainPageObject = new MainPageObject(driver);
    }

    @Test
    public void testSearch() {
        MainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find 'Search Wikipedia' input by xpath",
                5);

        MainPageObject.waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search…')]"),
                "Java",
                "Cannot find search input",
                5);

        MainPageObject.waitForElementPresent(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']"),
                "Cannot find 'Object-oriented programming language' for topic searching by 'Java'",
                15);
    }

    @Test
    public void testCancelSearch(){
        MainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find 'Search Wikipedia' input by Id",
                5);

        MainPageObject.waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search…')]"),
                "Java",
                "Cannot find search input",
                5);

        MainPageObject.waitForElementAndClear(
                By.id("org.wikipedia:id/search_src_text"),
                "Cannot find search field by Id",
                5);

        MainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/search_close_btn"),
                "Cannot find 'Cancel Button' by Id",
                5);

        MainPageObject.waitForElementNotPresent(
                By.id("org.wikipedia:id/search_close_btn"),
                "'Cancel Button' by Id still present on page",
                5);
    }

    @Test
    public void testCompareArticles(){
        MainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find 'Search Wikipedia' input by Id",
                5);

        MainPageObject.waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search…')]"),
                "Java",
                "Cannot find search input",
                5);

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']"),
                "Cannot find 'Cancel Button' by Id",
                5);

        MainPageObject.assertElementHasText(
                By.id("org.wikipedia:id/view_page_title_text"),
                "Java (programming language)",
                "Cannot find article title on page",
                15);
    }

    @Test
    public void testFieldInputHasText(){
        MainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find 'Search Wikipedia' input by Id",
                5);

        MainPageObject.assertElementHasText(
                By.id("org.wikipedia:id/search_src_text"),
                "Search…",
                "Cannot find 'Search...' in field input",
                5);
    }

    @Test
    public void testCancelSearchResults(){
        MainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find 'Search Wikipedia' input by Id",
                5);

        MainPageObject.waitForElementAndSendKeys(
                By.id("org.wikipedia:id/search_src_text"),
                "Java",
                "Cannot find search input",
                5);

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
    public void testCheckKeyWordInSearchResults(){
        MainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find 'Search Wikipedia' input by Id",
                5);

        MainPageObject.waitForElementAndSendKeys(
                By.id("org.wikipedia:id/search_src_text"),
                "Java",
                "Cannot find search input",
                5);

        MainPageObject.checkKeyWordInEachResult(
                By.id("org.wikipedia:id/page_list_item_title"),
                "Java",
                "Cannot see keyword in search results",
                15);
    }

    @Test
    public void testSwipeArticle(){
        MainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find 'Search Wikipedia' input by Id",
                5);

        MainPageObject.waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search…')]"),
                "Java",
                "Cannot find search input",
                5);

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']"),
                "Cannot find 'Cancel Button' by Id",
                5);

        MainPageObject.waitForElementPresent(
              By.id("org.wikipedia:id/view_page_title_text"),
              "Cannot find article title",
              15);

        MainPageObject.swipeUpToElement(
                By.id("org.wikipedia:id/page_external_link"),
                "Cannot find the end of the article",
                20);
    }

    @Test
    public void testSaveFirstArticleToMyList(){
        MainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find 'Search Wikipedia' input by Id",
                5);

        MainPageObject.waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search…')]"),
                "Java",
                "Cannot find search input",
                5);

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

        MainPageObject.waitForElementNotPresent(
                By.xpath("//*[@text='Java (programming language)']"),
                "Cannot delete saved article",
                5);
    }

    @Test
    public void testAmountOfNotEmptySearch(){
        MainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find 'Search Wikipedia' input by Id",
                5);

        String search_line = "Linkin Park Discography";
        MainPageObject.waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search…')]"),
                search_line,
                "Cannot put text into articles folder input",
                5);

        String search_result_locator = "//*[@resource-id='org.wikipedia:id/search_results_list']/*[@resource-id='org.wikipedia:id/page_list_item_container']";
        MainPageObject.waitForElementPresent(
                By.xpath(search_result_locator),
                "Cannot find anything by the request " + search_line,
                15);

        int amount_of_search_results = MainPageObject.getAmountOfElements(
                By.xpath(search_result_locator));

        Assert.assertTrue(
                "We found too few results!",
                amount_of_search_results > 0);
    }

    @Test
    public void testAmountOfEmptySearch(){
        MainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find 'Search Wikipedia' input by Id",
                5);

        String search_line = "zxvasdfqwer";
        MainPageObject.waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search…')]"),
                search_line,
                "Cannot put text into articles folder input",
                5);

        String search_result_locator = "//*[@resource-id='org.wikipedia:id/search_results_list']/*[@resource-id='org.wikipedia:id/page_list_item_container']";
        String empty_result_label = "//*[@text='No results found']";
        MainPageObject.waitForElementPresent(
                By.xpath(empty_result_label),
                "Cannot find empty label by the request " + search_line,
                15);

        MainPageObject.assertElementNotPresent(
                By.xpath(search_result_locator),
                "We found some results by request " + search_line);
    }

    @Test
    public void testChangeOrientationOnSearchResults() {
        MainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find 'Search Wikipedia' input by Id",
                5);

        String search_line = "Java";
        MainPageObject.waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search…')]"),
                search_line,
                "Cannot find search input",
                5);

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']"),
                "Cannot find 'Object-oriented programming language' topic searching by " + search_line,
                15);

        String title_before_rotation = MainPageObject.waitForElementAndGetAttribute(
                By.id("org.wikipedia:id/view_page_title_text"),
                "text",
                "",
                15);

        driver.rotate(ScreenOrientation.LANDSCAPE);

        String title_after_rotation = MainPageObject.waitForElementAndGetAttribute(
                By.id("org.wikipedia:id/view_page_title_text"),
                "text",
                "",
                15);

        Assert.assertEquals(
                "Article title have been changed after screen rotation",
                title_before_rotation,
                title_after_rotation);

        driver.rotate(ScreenOrientation.PORTRAIT);

        String title_after_second_rotation = MainPageObject.waitForElementAndGetAttribute(
                By.id("org.wikipedia:id/view_page_title_text"),
                "text",
                "",
                15);

        Assert.assertEquals(
                "Article title have been changed after screen rotation",
                title_before_rotation,
                title_after_second_rotation);
}

    @Test
    public void testCheckSearchArticleInBackground(){
        MainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find 'Search Wikipedia' input by Id",
                5);

        String search_line = "Java";
        MainPageObject.waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search…')]"),
                search_line,
                "Cannot find search input",
                5);

        MainPageObject.waitForElementPresent(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']"),
                "Cannot find 'Object-oriented programming language' topic searching by " + search_line,
                15);

        driver.runAppInBackground(Duration.ofMillis(2000));

        MainPageObject.waitForElementPresent(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']"),
                "Cannot find 'Object-oriented programming language' topic searching by " + search_line + " after returning from background",
                15);
    }

    @Test
    public void testSaveTwoArticlesToMyListThanOneDelete(){
        MainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find 'Search Wikipedia' input by Id",
                5);

        MainPageObject.waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search…')]"),
                "Java",
                "Cannot find search input",
                5);

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

        MainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find 'Search Wikipedia' input by Id",
                5);

        MainPageObject.waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search…')]"),
                "Java",
                "Cannot find search input",
                5);

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
    public void testCheckOpenArticleHasTitle(){
        MainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find 'Search Wikipedia' input by Id",
                5);

        MainPageObject.waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search…')]"),
                "Java",
                "Cannot find search input",
                5);

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']"),
                "Cannot find article with 'Object-oriented programming language'",
                15);

        MainPageObject.assertElementPresent(
                By.id("org.wikipedia:id/view_page_title_text"),
                "Cannot find article title on open page");
    }
}
