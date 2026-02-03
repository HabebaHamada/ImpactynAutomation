package ImpactynPages;

import ImpactynCore.BasePage;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;


public class LoginWithFacebookPage extends BasePage {

    private By ContinueWithFacebookBtnLocator;
    private By FacebookLoadingValidationLocator;

    public LoginWithFacebookPage(AppiumDriver driver) {
        super(driver);
        initializeLocators();
    }

    private void initializeLocators(){
        if (platform.is(Platform.ANDROID)) {
        ContinueWithFacebookBtnLocator= By.xpath("//android.widget.Button[starts-with(@text, \"Continue as\")]");
        FacebookLoadingValidationLocator= By.xpath("//android.widget.TextView[@text=\"You previously logged into Impactyn with Facebook.\"]");
    } else if (platform.is(Platform.IOS)) {
        ContinueWithFacebookBtnLocator= By.xpath("//android.widget.Button[starts-with(@text, \"Continue as\")]");
        FacebookLoadingValidationLocator= By.xpath("//android.widget.TextView[@text=\"You previously logged into Impactyn with Facebook.\"]");
    }
}

    public boolean isPageLoaded()
    {
        logger.info("Validating that Facebook Login page is Fully Loaded.");
        return wait.until(ExpectedConditions.visibilityOfElementLocated(FacebookLoadingValidationLocator)).isDisplayed();
    }

    public HomePage clickContinueWithFacebook() {

        if (isPageLoaded()) {
            logger.info("Clicking the 'Continue as ' button.");
            WebElement ContinueWithFacebookButton = wait.until(ExpectedConditions.elementToBeClickable(ContinueWithFacebookBtnLocator));
            ContinueWithFacebookButton.click();
            return new HomePage(driver);
        }
        return null;
    }
}
