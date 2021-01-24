package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.MyListsPageObject;

public class iOSMyListsPageObject extends MyListsPageObject {

    static {
        FOLDER_BY_NAME_XPATH_TPL = "xpath://*[@text='{FOLDER_NAME}']";
        ARTICLE_BY_TITLE_XPATH_TPL = "xpath://*[@text='{TITLE}']";
    }

    public iOSMyListsPageObject(AppiumDriver driver) {
        super(driver);
    }
}
