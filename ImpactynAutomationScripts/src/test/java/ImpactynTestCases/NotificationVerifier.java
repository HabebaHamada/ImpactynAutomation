package ImpactynTestCases;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class NotificationVerifier {

    private final AppiumDriver driver;
    private final WebDriverWait wait;

    // Constructor to initialize a driver and wait
    public NotificationVerifier(AppiumDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void verifyNotification(String expectedTitle, String expectedText) {

        try {

            // Step 1: Cast the driver to Android Driver
            // Step 2: Open Notifications bar
            // This is the key part: (AndroidDriver) driver
            ((AndroidDriver) driver).openNotifications();


            // Step 3: Wait for the notification elements to be visible
            // Standard Android resource-ids for notification title and text
            String titleLocator = "android:id/title";
            String textLocator = "android:id/text";

            WebElement notificationTitle = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(AppiumBy.id(titleLocator))
            );
            WebElement notificationText = driver.findElement(AppiumBy.id(textLocator));

            // Step 4: Assert that the content is correct
            System.out.println("Found Notification Title: " + notificationTitle.getText());
            System.out.println("Found Notification Text: " + notificationText.getText());

            Assert.assertEquals(notificationTitle.getText(), expectedTitle,"Notification title did not match!");
            Assert.assertTrue(notificationText.getText().contains(expectedText),"Notification text did not contain expected content!");

            System.out.println("Notification verification successful!");

        } catch (Exception e) {
            // Print the page source if the element is not found, for debugging
            System.err.println("Failed to verify notification. Page source:");
            System.err.println(driver.getPageSource());
            // Re-throw the exception to fail the test
            throw new RuntimeException("Could not find or verify the notification.", e);
        } finally {
            // Step 5: Clean up by closing the notification shade
            // Pressing the "Back" button is a reliable way to close it.
            ((AndroidDriver) driver).pressKey(new KeyEvent(AndroidKey.BACK));
            System.out.println("Notification shade closed.");
        }
    }
}
