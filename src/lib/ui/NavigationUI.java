package lib.ui;

import io.appium.java_client.AppiumDriver;

abstract public class NavigationUI extends MainPageObject {

    protected static String
            MY_LISTS_LINK_XPATH;

    public NavigationUI(AppiumDriver driver) {
        super(driver);
    }

    public void clickMyList() {
        this.waitForElementAndClick(
                MY_LISTS_LINK_XPATH,
                "Cannot find 'My lists' button",
                10);
    }
}
