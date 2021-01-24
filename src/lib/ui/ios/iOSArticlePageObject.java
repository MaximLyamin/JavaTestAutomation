package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.ArticlePageObject;

public class iOSArticlePageObject extends ArticlePageObject {

    static {
        TITLE = "id:Java (programming language)";
        TITLE_XPATH = "xpath://XCUIElementTypeStaticText[contains(@name,'Java (programming language)') or contains(@name, 'JavaScript')]";
        FOOTER_ELEMENT_ID = "id:View article in browser";
        //OPTIONS_BUTTON_XPATH = "xpath://android.widget.ImageView[@content-desc='More options']";
        OPTIONS_ADD_TO_MY_LIST_BUTTON_XPATH = "id:Save for later";
        //ADD_TO_MY_LIST_OVERLAY_ID = "id:org.wikipedia:id/onboarding_button";
        //MY_LIST_NAME_INPUT_ID = "id:org.wikipedia:id/text_input";
        //MY_LIST_OK_BUTTON_XPATH = "xpath://*[@text='OK']";
        CLOSE_ARTICLE_BUTTON_XPATH = "id:Back";
    }

    public iOSArticlePageObject(AppiumDriver driver) {
        super(driver);
    }
}
