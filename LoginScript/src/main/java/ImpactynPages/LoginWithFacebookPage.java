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

    public final By ContinueWithFacebookBtnLocator= By.xpath("//android.widget.Button[@resource-id=\"u_0_0_9A\"]");

    public LoginWithFacebookPage(AppiumDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public FeedPage clickContinueWithFacebook() {
        System.out.println("Clicking the 'ContinueWithFacebook' button.");
        WebElement ContinueWithFacebookButton = wait.until(ExpectedConditions.elementToBeClickable(ContinueWithFacebookBtnLocator));
        ContinueWithFacebookButton.click();
        return new FeedPage(driver);
    }
}
