package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class SearchPageObject extends MainPageObject {

    private static final String
        SEARCH_INIT_ELEMENT = "//*[contains(@text,'Search Wikipedia')]",
        SEARCH_INPUT = "//*[contains(@text,'Search…')]",
        SEARCH_INPUT_ID = "org.wikipedia:id/search_src_text",
        SEARCH_CANCEL_BUTTON = "org.wikipedia:id/search_close_btn",
        SEARCH_RESULT_BY_SUBSTRING_TPL = "//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='{SUBSTRING}']",
        SEARCH_RESULT_BY_TITLE_AND_SUBSTRING_TPL = "//*[@text='{TITLE}']/following-sibling::*[@text='{SUBSTRING}']",
        SEARCH_RESULT_ELEMENT_XPATH = "//*[@resource-id='org.wikipedia:id/search_results_list']/*[@resource-id='org.wikipedia:id/page_list_item_container']",
        SEARCH_EMPTY_RESULT_ELEMENT_XPATH = "//*[@text='No results found']",
        SEARCH_RESULT_TITLE_ARTICLE_ID = "org.wikipedia:id/page_list_item_title",
        SEARCH_RESULT_AFTER_CANCEL_ID = "org.wikipedia:id/search_empty_message";

    public SearchPageObject(AppiumDriver driver){
        super(driver);
    }
    /* TEMPLATES METHODS */
    private static String getResultSearchElement(String substring) {
        return SEARCH_RESULT_BY_SUBSTRING_TPL.replace("{SUBSTRING}", substring);
    }

    private static String getResultSearchElementByTitleAndSubstring(String title, String substring) {
       return SEARCH_RESULT_BY_TITLE_AND_SUBSTRING_TPL.replace("{TITLE}", title).replace("{SUBSTRING}", substring);
    }
    /* TEMPLATES METHODS */

    public void initSearchInput() {
        this.waitForElementAndClick(
                By.xpath(SEARCH_INIT_ELEMENT),
                "Cannot find search and click init element by " + SEARCH_INIT_ELEMENT,
                5);

        this.waitForElementPresent(
                By.xpath(SEARCH_INIT_ELEMENT),
                "Cannot find search input after clicking by " + SEARCH_INIT_ELEMENT,
                5);
    }

    public void waitForCancelButtonToAppear() {
        this.waitForElementPresent(
                By.id(SEARCH_CANCEL_BUTTON),
                "Cannot find search cancel button",
                5);
    }

    public void waitForCancelButtonToDisappear() {
        this.waitForElementNotPresent(
                By.id(SEARCH_CANCEL_BUTTON),
                "Search cancel button is still present",
                5);
    }

    public void clickCancelSearch() {
        this.waitForElementAndClick(
                By.id(SEARCH_CANCEL_BUTTON),
                "Cannot find and click search cancel button",
                5);
    }

    public void typeSearchLine(String search_line){
        this.waitForElementAndSendKeys(
                By.xpath(SEARCH_INPUT),
                search_line,
                "Cannot find and type into search input",
                5);
    }

    public void waitForSearchResult(String substring) {
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementPresent(
                By.xpath(search_result_xpath),
                "Cannot find search result with substring " + substring);
    }

    public void clickByArticleWithSubstring(String substring) {
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementAndClick(
                By.xpath(search_result_xpath),
                "Cannot find and click search result with substring " + substring,
                5);
    }

    public void assertCompareSearchInputText(String expected_text) {
        this.assertElementHasText(By.id(SEARCH_INPUT_ID), expected_text, "We see unexpected title", 15);
    }

    public int getAmountOfFoundArticles() {
        this.waitForElementPresent(
                By.xpath(SEARCH_RESULT_ELEMENT_XPATH),
                "Cannot find anything by the request",
                15);

        return this.getAmountOfElements(By.xpath(SEARCH_RESULT_ELEMENT_XPATH));
    }

    public void waitForEmptyResultsLabel() {
        this.waitForElementPresent(
                By.xpath(SEARCH_EMPTY_RESULT_ELEMENT_XPATH),
                "Cannot find empty label by the request",
                15);
    }
    public void assertThereIsNoResultOfSearch() {
        this.assertElementNotPresent(
                By.xpath(SEARCH_RESULT_ELEMENT_XPATH),
                "We found some results by request");
    }

    public void checkKeyWordInVisibleSearchResults(String search_line) {
        this.checkKeyWordInEachVisibleResult(
                By.id(SEARCH_RESULT_TITLE_ARTICLE_ID),
                search_line,
                "Cannot see keyword " + search_line + " in search results",
                15);
    }

    public void assertThereIsSomeResultOfSearch() {
        this.searchResultHasSomeArticles(
                By.id(SEARCH_RESULT_TITLE_ARTICLE_ID),
                "Cannot see some articles",
                15);
    }

    public void assertThereUsNoResultOfSearchAfterCancel() {
        this.waitForElementPresent(
                By.id(SEARCH_RESULT_AFTER_CANCEL_ID),
                "Search page does not clear",
                15);
    }

    public void waitForElementByTitleAndDescription(String title, String description) {
        String article_title_and_subscription_xpath = getResultSearchElementByTitleAndSubstring(title, description);
        this.waitForElementPresent(
                By.xpath(article_title_and_subscription_xpath),
                "Cannot find article with title " + title + " and description " + description + " by xpath " + article_title_and_subscription_xpath,
                15);
    }
}
