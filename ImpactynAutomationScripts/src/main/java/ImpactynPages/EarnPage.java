package ImpactynPages;

import ImpactynCore.BasePage;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class EarnPage extends BasePage {

    private By allowWhileUsingAppLocator;
    private By reviewToInspireButtonLocator;

    public EarnPage(AppiumDriver driver) {
        super(driver);
        initializeLocators();
    }

    private void initializeLocators() {
        // 'platform' is inherited from BasePage
        if (platform.is(Platform.ANDROID)) {
            allowWhileUsingAppLocator = AppiumBy.id("com.android.permissioncontroller:id/permission_allow_foreground_only_button");
            reviewToInspireButtonLocator = AppiumBy.androidUIAutomator("new UiSelector().text(\"Review to Inspire\").instance(0)");
        }
    }

    public void allowAccess() {
        if (this.platform.is(Platform.IOS)) {
            logger.info("Attempting to handle iOS system alert for access...");
            for (int i = 0; i < 5; i++) {
                try {
                    // Wait briefly before each attempt
                    Thread.sleep(1000);

                    try {
                        // Check if alert exists first
                        driver.switchTo().alert();

                        // Use different approach for iOS alert
                        Map<String, Object> params = new HashMap<>();
                        params.put("action", "accept");
                        params.put("buttonLabel", "Allow While Using App");
                        driver.executeScript("mobile:alert", params);

                        logger.info("Successfully clicked Allow While Using App button");
                        break;
                    } catch (Exception _) {
                        // Try alternative method
                        Map<String, String> params = new HashMap<>();
                        params.put("action", "accept");
                        driver.executeScript("mobile: acceptAlert", params);

                        logger.info("Successfully handled alert using alternative method");
                    }
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        else if (this.platform.is(Platform.ANDROID))
        {
            try {
                // This wait is specific to this action.
                WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(5));
                logger.info("Checking for permission pop-up with locator: " + allowWhileUsingAppLocator);
                shortWait.until(ExpectedConditions.elementToBeClickable(allowWhileUsingAppLocator)).click();
                logger.info("Permission pop-up handled successfully.");
            } catch (TimeoutException _) {
                // This is now EXPECTED and SAFE. It just means the pop-up wasn't there.
                logger.warning("Permission pop-up not found. Continuing...");
            }
        }
    }

    public UploadReviewPage clickReviewToInspire()
    {
        logger.info("Clicking Review To Inspire button");
        wait.until(ExpectedConditions.visibilityOfElementLocated(reviewToInspireButtonLocator));
        wait.until(ExpectedConditions.elementToBeClickable(reviewToInspireButtonLocator)).click();
        return new UploadReviewPage(driver);
    }
}
