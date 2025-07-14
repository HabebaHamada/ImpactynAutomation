package ImpactynPages;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginWithFacebookPage {


    private final AppiumDriver driver;
    private final WebDriverWait wait;

    private final By ContinueWithFacebookBtnLocator= By.xpath("//android.widget.Button[starts-with(@text, \"Continue as\")]");
    private final By FacebookLoadingValidationLocator= By.xpath("//android.widget.TextView[@text=\"You previously logged into Impactyn with Facebook.\"]");

    public LoginWithFacebookPage(AppiumDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(50));
    }

    public boolean isPageLoaded()
    {
        System.out.println("Validating that Facebook Login page is Fully Loaded.");
        return wait.until(ExpectedConditions.visibilityOfElementLocated(FacebookLoadingValidationLocator)).isDisplayed();
    }

    public FeedPage clickContinueWithFacebook() {

        if (isPageLoaded()) {
            System.out.println("Clicking the 'Continue as ' button.");
            WebElement ContinueWithFacebookButton = wait.until(ExpectedConditions.elementToBeClickable(ContinueWithFacebookBtnLocator));
            ContinueWithFacebookButton.click();
            return new FeedPage(driver);
        }
        return null;
    }
}
