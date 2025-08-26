package ImpactynPages;


import ImpactynCore.BasePage;
import io.appium.java_client.AppiumDriver;


public class LoginWithGooglePage extends BasePage {



    public LoginWithGooglePage(AppiumDriver driver) {
        super(driver);
    }

    public FeedPage clickChooseGoogleAccount() {
        return new FeedPage(driver);
    }



}
