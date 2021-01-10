package tests;

import lib.CoreTestCase;
import lib.ui.SearchPageObject;
import org.junit.Test;

public class SearchTests extends CoreTestCase {
    @Test
    public void testSearch() {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.waitForSearchResult("Object-oriented programming language");
    }

    @Test
    public void testCancelSearch() {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.waitForCancelButtonToAppear();
        SearchPageObject.clickCancelSearch();
        SearchPageObject.waitForCancelButtonToDisappear();
    }
    @Test
    public void testAmountOfNotEmptySearch() {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        String search_line = "Linkin Park Discography";

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(search_line);
        int amount_of_search_results = SearchPageObject.getAmountOfFoundArticles();

        assertTrue(
                "We found too few results!",
                amount_of_search_results > 0);
    }

    @Test
    public void testAmountOfEmptySearch() {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        String search_line = "zxvasdfqwer";

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(search_line);
        SearchPageObject.waitForEmptyResultsLabel();
        SearchPageObject.assertThereIsNoResultOfSearch();
    }

    @Test
    public void testFieldInputHasText() {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        String expected_text = "Search…";

        SearchPageObject.initSearchInput();
        SearchPageObject.assertCompareSearchInputText(expected_text);
    }

    @Test
    public void testCheckKeyWordInSearchResults() {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        String search_line = "Java";

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(search_line);
        SearchPageObject.checkKeyWordInVisibleSearchResults(search_line);
    }

    @Test
    public void testCancelSearchResults() {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        String search_line = "Java";

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(search_line);
        SearchPageObject.assertThereIsSomeResultOfSearch();
        SearchPageObject.clickCancelSearch();
        SearchPageObject.assertThereUsNoResultOfSearchAfterCancel();
    }

    @Test
    public void testSearchArticleWithTitleAndDescription() {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        String search_line = "Java";
        String substring = "Object-oriented programming language";
        String title = "Java (programming language)";

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(search_line);
        SearchPageObject.waitForElementByTitleAndDescription(title, substring);
        int articles_in_search_result = SearchPageObject.getAmountOfFoundArticles();

        assertTrue("We found too few articles!", articles_in_search_result >= 3);
    }
}
