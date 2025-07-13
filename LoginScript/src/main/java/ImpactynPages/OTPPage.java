package ImpactynPages;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class OTPPage {

    private final AppiumDriver driver;
    private final WebDriverWait wait;

    private final By screenTitleLocator = By.xpath("//android.widget.TextView[@text=\"Enter the code we just texted you\"]");
    private final By OTPInputLocator = By.xpath("//android.widget.EditText");
    public final By nextBtnLocator = By.xpath("//android.widget.TextView[@text=\"Next\"]");


    public OTPPage(AppiumDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    /*not used as this part is manually entered*/
    public void enterOTP(String OTP) {
        System.out.println("Entering OTP: " + OTP);
        WebElement phoneInput = wait.until(ExpectedConditions.visibilityOfElementLocated(OTPInputLocator));
        phoneInput.sendKeys(OTP);
    }

    public FeedPage clickNext() {
        System.out.println("Clicking the 'Next' button.");
        WebElement nextButton = wait.until(ExpectedConditions.elementToBeClickable(nextBtnLocator));
        nextButton.click();
        return new FeedPage(driver);
    }

    public boolean isPageLoaded() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(screenTitleLocator)).isDisplayed();
    }
}
