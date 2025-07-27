package ImpactynPages;


import io.appium.java_client.AppiumDriver;


public class LoginWithGooglePage {

    private final AppiumDriver driver;


    public LoginWithGooglePage(AppiumDriver driver) {
        this.driver = driver;
    }

    public FeedPage clickChooseGoogleAccount() {
        return new FeedPage(driver);
    }



}
