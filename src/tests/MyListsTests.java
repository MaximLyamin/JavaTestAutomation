package tests;

import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.MyListsPageObject;
import lib.ui.NavigationUI;
import lib.ui.SearchPageObject;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.MyListsPageObjectFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;

public class MyListsTests extends CoreTestCase {
    @Test
    public void testSaveFirstArticleToMyList() {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);;
        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        NavigationUI NavigationUI = new NavigationUI(driver);
        MyListsPageObject MyListsPageObject = MyListsPageObjectFactory.get(driver);
        String search_line = "Java";
        String article_title = "Java (programming language)";
        String substring = "Object-oriented programming language";
        String name_of_folder = "Learning programming";

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(search_line);
        SearchPageObject.clickByArticleWithSubstring(substring);
        ArticlePageObject.assertCompareArticles(article_title);
        ArticlePageObject.addArticleToMyListFirstTime(name_of_folder);
        ArticlePageObject.closeArticle();
        NavigationUI.clickMyList();
        MyListsPageObject.openFolderByName(name_of_folder);
        MyListsPageObject.swipeByArticleToDelete(article_title);
    }

    @Test
    public void testSaveTwoArticlesToMyListThanOneDelete() {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);;
        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        NavigationUI NavigationUI = new NavigationUI(driver);
        MyListsPageObject MyListsPageObject = MyListsPageObjectFactory.get(driver);
        String search_line = "Java";
        String first_article_title = "Java (programming language)";
        String substring_first_article = "Object-oriented programming language";
        String name_of_folder = "Learning programming";
        String second_article_title = "JavaScript";
        String substring_second_article = "Programming language";

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(search_line);
        SearchPageObject.clickByArticleWithSubstring(substring_first_article);
        ArticlePageObject.assertCompareArticles(first_article_title);
        ArticlePageObject.addArticleToMyListFirstTime(name_of_folder);
        ArticlePageObject.closeArticle();
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(search_line);
        SearchPageObject.clickByArticleWithSubstring(substring_second_article);
        ArticlePageObject.assertCompareArticles(second_article_title);
        ArticlePageObject.addArticleToMyCreatedList(name_of_folder);
        ArticlePageObject.closeArticle();
        NavigationUI.clickMyList();
        MyListsPageObject.openFolderByName(name_of_folder);
        MyListsPageObject.swipeByArticleToDelete(first_article_title);
        MyListsPageObject.waitForArticleToAppearByTitle(second_article_title);
        String title_in_list = MyListsPageObject.getArticleByTitleXpathInSavedList(second_article_title);
        MyListsPageObject.openArticleByTitleInSavedList(second_article_title);
        String title_in_open_article = MyListsPageObject.getArticleByTitleXpathInSavedList(second_article_title);

        assertEquals(
                "Article have been changed after opening",
                title_in_open_article,
                title_in_list);
    }
}

