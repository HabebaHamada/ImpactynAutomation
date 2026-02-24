package ImpactynPages;

import ImpactynCore.BasePage;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.*;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class SystemAlertsPage extends BasePage {

    private By allowWhileUsingAppLocator ;
    private By allowButtonLocator;
    private By AllowRecordingSettingsLocator;
    private By NextAccessibilityLocator ;

    public SystemAlertsPage(AppiumDriver driver) {
        super(driver);
        initializeLocators();
    }

    private void initializeLocators() {
        if (platform.is(Platform.ANDROID)) {
                allowWhileUsingAppLocator = By.xpath("//android.widget.Button[@resource-id=\"com.android.permissioncontroller:id/permission_allow_foreground_only_button\"]");
                allowButtonLocator = By.xpath("//android.widget.Button[@resource-id=\"com.android.permissioncontroller:id/permission_allow_button\"]");
                AllowRecordingSettingsLocator = By.id("com.android.permissioncontroller:id/permission_allow_foreground_only_button");
        } else if (platform.is(Platform.IOS)) {
                NextAccessibilityLocator = AppiumBy.accessibilityId("AccessibilityIdentifiers.coachMarkNext");
        }
    }


    private void handle_initialSystemAlert_Android() {

        handlePermissionPopup(allowWhileUsingAppLocator); // Handles Media/Location
        handlePermissionPopup(allowButtonLocator);        // Handles Notifications
        handlePermissionPopup(allowButtonLocator);
        logger.info("Onboarding screen found and done.");

    }

    private void handlePermissionPopup(By locator) {
        try {
            // This wait is specific to this action.
            WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(5));
            logger.info("Checking for permission pop-up with locator: " + locator);
            shortWait.until(ExpectedConditions.elementToBeClickable(locator)).click();
            logger.info("Permission pop-up handled successfully.");
        } catch (TimeoutException e) {
            // This is now EXPECTED and SAFE. It just means the pop-up wasn't there.
            logger.warning("Permission pop-up not found. Continuing...");
        }
    }

    /*this method is used only for iOS system alert handling */
    private void handle_initialSystemAlert_iOS() {
        logger.info("Handling iOS system alert to handle Notification ...");

        try {
            // Step 1: Wait for the alert to be present on the screen.
            // This is a crucial step to handle any small delays.
            wait.until(ExpectedConditions.alertIsPresent());

            // Step 2: Switch the driver's focus to the alert.
            Alert systemAlert = driver.switchTo().alert();

            // Step 3: You can get the text for verification (optional but good for debugging)
            String alertText = systemAlert.getText();
            logger.info("Found system alert with text: " + alertText);

            // Step 4: Accept the alert. This will click the default "Continue" button.
            systemAlert.accept();

            logger.info("System alert accepted.");

        } catch (Exception e) {
            logger.warning("Failed to handle the system alert.");
            // Optionally take a screenshot here for debugging
            throw e;
        }
        logger.info("System alert confirmed.");
    }

    public void handleInitialPopups() {
        if (this.platform.is(Platform.IOS))
        {
            logger.info("--- PRE-TEST ACTION: Handling initial pop-ups ---");
            handle_initialSystemAlert_iOS();
        }
        else if (this.platform.is(Platform.ANDROID)) {
            logger.info("--- PRE-TEST ACTION: Handling initial pop-ups ---");
            handle_initialSystemAlert_Android();
        }

    }

    /*this method is used only for iOS system alert handling */
    public void confirmSignIn() {
        logger.info("Handling iOS system alert to confirm sign-in...");

        try {
            // Step 1: Wait for the alert to be present on the screen.
            // This is a crucial step to handle any small delays.
            wait.until(ExpectedConditions.alertIsPresent());

            // Step 2: Switch the driver's focus to the alert.
            Alert systemAlert = driver.switchTo().alert();

            // Step 3: You can get the text for verification (optional but good for debugging)
            String alertText = systemAlert.getText();
            logger.info("Found system alert with text: " + alertText);

            // Step 4: Accept the alert. This will click the default "Continue" button.
            systemAlert.accept();

            logger.info("System alert accepted.");

        } catch (Exception e) {
            logger.warning("Failed to handle the system alert.");
            throw e;
        }
        logger.info("System alert confirmed.");
    }

    public void allowLocationAccess() {
        if (this.platform.is(Platform.IOS)) {
            logger.info("Attempting to handle iOS system alert for location access...");
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

                        logger.info("Successfully clicked Allow While Using App button");
                        alertHandled = true;
                        break;
                    } catch (Exception alertEx) {
                        // Try alternative method
                        Map<String, String> params = new HashMap<>();
                        params.put("action", "accept");
                        driver.executeScript("mobile: acceptAlert", params);

                        logger.info("Successfully handled alert using alternative method");
                        alertHandled = true;
                    }
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public void allowGalleryAccess() {
        if (this.platform.is(Platform.IOS)) {
            logger.info("Attempting to handle iOS system alert for gallery access...");
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
                        params.put("buttonLabel", "Allow Full Access");
                        driver.executeScript("mobile:alert", params);

                        logger.info("Successfully clicked Allow Full Access button");
                        alertHandled = true;
                        break;
                    } catch (Exception alertEx) {
                        // Try alternative method
                        Map<String, String> params = new HashMap<>();
                        params.put("action", "accept");
                        driver.executeScript("mobile: acceptAlert", params);

                        logger.info("Successfully handled alert using alternative method");
                        alertHandled = true;
                    }
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    /*Profile Page related system alerts handling */

    public void allowCameraAccess()
    {
        logger.info("Handling iOS system alert to Allow Camera...");

        try {
            // Step 1: Wait for the alert to be present on the screen.
            // This is a crucial step to handle any small delays.
            wait.until(ExpectedConditions.alertIsPresent());

            // Step 2: Switch the driver's focus to the alert.
            Alert systemAlert = driver.switchTo().alert();

            // Step 3: You can get the text for verification (optional but good for debugging)
            String alertText = systemAlert.getText();
            logger.info("Found system alert with text: " + alertText);

            // Step 4: Accept the alert. This will click the default "Continue" button.
            systemAlert.accept();

            logger.info("System alert accepted.");

        } catch (Exception e) {
            logger.warning("Failed to handle the system alert.");
            throw e;
        }
        logger.info("System alert confirmed.");
    }

    /* upload review Page related system alerts handling */

    public void allowRecordingSettings() {
        if (this.platform.is(Platform.ANDROID)) {
            /*Allow Video and Sound Settings For Recording*/
            WebElement AllowRecording = wait.until(ExpectedConditions.elementToBeClickable(AllowRecordingSettingsLocator));
            AllowRecording.click();
        } else if (this.platform.is(Platform.IOS)) {
            /*Handle Coach Marks if present*/
            try {
                WebElement NextButton = wait.until(ExpectedConditions.visibilityOfElementLocated(NextAccessibilityLocator));
                while (NextButton.isDisplayed()) {
                    NextButton.click();
                    // Re-locate the Next button after clicking
                    NextButton = wait.until(ExpectedConditions.visibilityOfElementLocated(NextAccessibilityLocator));
                }
            } catch (Exception e) {
                // If the Next button is not found, we assume there are no coach marks to handle
                logger.warning("No more coach marks to handle.");
            }
        }
    }

}
