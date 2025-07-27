package ImpactynPages;


import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class LoginWithSnapchatPage {

    private final AppiumDriver driver;
    private final WebDriverWait wait;

    private final By ContinueBtnLocator= By.xpath("//android.widget.Button[@text=\"Continue\"]");
    private final By SnapchatLoadingValidationLocator = By.xpath("//android.widget.TextView[@text=\"Connect to Impactyn (https://www.impactyn.io)?\"]");

    public LoginWithSnapchatPage(AppiumDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(50));
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
