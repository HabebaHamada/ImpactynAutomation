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

    public EarnPage(AppiumDriver driver) {
        super(driver);
        initializeLocators();
    }

    private void initializeLocators() {
        // 'platform' is inherited from BasePage
        if (platform.is(Platform.ANDROID)) {
            allowWhileUsingAppLocator = AppiumBy.id("com.android.permissioncontroller:id/permission_allow_foreground_only_button");
        }
    }

    public void allowLocationAccess() {
        if (this.platform.is(Platform.IOS)) {
            System.out.println("Attempting to handle iOS system alert for location access...");
            boolean alertHandled = false;
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

                        System.out.println("Successfully clicked Allow While Using App button");
                        alertHandled = true;
                        break;
                    } catch (Exception alertEx) {
                        // Try alternative method
                        Map<String, String> params = new HashMap<>();
                        params.put("action", "accept");
                        driver.executeScript("mobile: acceptAlert", params);

                        System.out.println("Successfully handled alert using alternative method");
                        alertHandled = true;
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
                System.out.println("Checking for permission pop-up with locator: " + allowWhileUsingAppLocator);
                shortWait.until(ExpectedConditions.elementToBeClickable(allowWhileUsingAppLocator)).click();
                System.out.println("Permission pop-up handled successfully.");
            } catch (TimeoutException e) {
                // This is now EXPECTED and SAFE. It just means the pop-up wasn't there.
                System.out.println("Permission pop-up not found. Continuing...");
            }
        }
    }
}
