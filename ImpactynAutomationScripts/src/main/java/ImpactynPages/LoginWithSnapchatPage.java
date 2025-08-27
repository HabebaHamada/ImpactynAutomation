package ImpactynPages;


import ImpactynCore.BasePage;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginWithSnapchatPage extends BasePage {

    private By ContinueBtnLocator;
    private By SnapchatLoadingValidationLocator;

    public LoginWithSnapchatPage(AppiumDriver driver) {
        super(driver);
        initializeLocators();
    }
    /** * Initializes locators based on the platform determined in the BasePage.
     */private void initializeLocators() {
        // 'platform' is inherited from BasePage
        if (platform.is(Platform.ANDROID)) {
            ContinueBtnLocator= By.xpath("//android.widget.Button[@text=\"Continue\"]");
            SnapchatLoadingValidationLocator = By.xpath("//android.widget.TextView[@text=\"Connect to Impactyn (https://www.impactyn.io)?\"]");
        } else if (platform.is(Platform.IOS)) {
            ContinueBtnLocator = AppiumBy.accessibilityId("Continue");
            SnapchatLoadingValidationLocator = AppiumBy.accessibilityId("Connect to Impactyn (https://www.impactyn.io)?");
        }
    }

    public boolean isPageLoaded()
    {
        System.out.println("Validating that Snapchat Login page is Fully Loaded.");
        return wait.until(ExpectedConditions.visibilityOfElementLocated(SnapchatLoadingValidationLocator)).isDisplayed();
    }

    public FeedPage clickContinueWithSnapchat() {

        if (isPageLoaded()) {
            System.out.println("Clicking the 'Continue' button.");
            WebElement ContinueWithFacebookButton = wait.until(ExpectedConditions.elementToBeClickable(ContinueBtnLocator));
            ContinueWithFacebookButton.click();
            return new FeedPage(driver);
        }
        return null;
    }
}
