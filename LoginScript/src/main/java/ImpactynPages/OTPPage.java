package ImpactynPages;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class OTPPage {

    private final AppiumDriver driver;
    private final WebDriverWait wait;

    private final By screenTitleLocator = By.xpath("//android.widget.TextView[@text=\"Enter the code we just texted you\"]");
    private final By OTPInputLocator = By.xpath("//android.widget.EditText");
    private final By nextBtnLocator = By.xpath("//android.widget.TextView[@text=\"Next\"]");

    private final By feedPageElement = By.xpath("//m5.e1/android.view.View/android.view.View/android.view.View[2]/android.view.View/android.view.View[3]/android.view.View[2]");


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

    public void clickNext(){
        System.out.println("Clicking the 'Next' button.");
        WebElement nextButton = wait.until(ExpectedConditions.elementToBeClickable(nextBtnLocator));
        nextButton.click();
    }

    public boolean isPageLoaded() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(screenTitleLocator)).isDisplayed();
    }

    public FeedPage waitForManualOtpAndProceed() {
        System.out.println(">>> WAITING FOR 65 SECONDS FOR MANUAL OTP ENTRY <<<");
        System.out.println(">>> Please enter the OTP on the device now... <<<");

        // Create a special WebDriverWait with a long timeout just for this step.
        WebDriverWait longWait = new WebDriverWait(driver, Duration.ofSeconds(65)); // 60s + 5s buffer

        try {
            // The script will pause here and wait for the feed page element to become visible.
            // This only happens after the OTP is entered and the app navigates.
            longWait.until(ExpectedConditions.visibilityOfElementLocated(feedPageElement));

            System.out.println("OTP entry detected. Proceeding to the feed page.");
            clickNext();
            return new FeedPage(driver);

        } catch (TimeoutException e) {
            // This block will be executed if you fail to enter the OTP within 65 seconds.
            System.err.println("Timeout! OTP was not entered in time.");
            // We re-throw the exception to make sure the test fails clearly.
            throw new TimeoutException("Test failed because manual OTP entry timed out.", e);
        }
    }
}
