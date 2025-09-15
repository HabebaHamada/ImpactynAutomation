package ImpactynTestCases;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.Collections;

public class NotificationVerifier {

    private final AppiumDriver driver;
    private final WebDriverWait wait;

    public NotificationVerifier(AppiumDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    public void verifyNotification(String expectedTitle, String expectedText) {

        try {

            // Step 1: Cast the driver to Android Driver
            // Step 2: Open Notifications bar
            ((AndroidDriver) driver).openNotifications();

            // Step 3: Wait for the notification elements to be visible
            // Standard Android resource-ids for notification title and text
            String titleLocator = "android:id/title";
            String standardTextLocator = "android:id/text";

            WebElement notificationTitle = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(AppiumBy.id(titleLocator))
            );

            // Step 4: Assert that the content is correct
            System.out.println("Found Notification Title: " + notificationTitle.getText());
            Assert.assertEquals(notificationTitle.getText(), expectedTitle,"Notification title did not match!");


            try {
                // Use a very short wait to check for existence without wasting time.
                new WebDriverWait(driver, Duration.ofSeconds(1)).until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.id(standardTextLocator)));
                System.out.println("Notification was already expanded.");
            } catch (Exception _) {
                // 3. If it's not expanded, expand it now.
                System.out.println("Notification is collapsed. Performing swipe to expand...");
                expandNotification(notificationTitle); // Swipe on the title element to pull it down.
            }

            WebElement notificationTextElement =   wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.id(standardTextLocator)));

            // Step 5: Assert on the text of the element we found
            System.out.println("Found Notification Text: " + notificationTextElement.getText());
            Assert.assertTrue(notificationTextElement.getText().contains(expectedText),"Notification text did not contain expected content!");

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

    private void expandNotification(WebElement element) {
        // Get the element's location and size
        int startX = element.getRect().getX() + (element.getRect().getWidth() / 2);
        int startY = element.getRect().getY() + (element.getRect().getHeight() / 2);
        // Swipe down by the height of the element
        int endY = startY + (element.getRect().getHeight());

        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence swipe = new Sequence(finger, 1);
        swipe.addAction(finger.createPointerMove(Duration.ofMillis(0), PointerInput.Origin.viewport(), startX, startY));
        swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        // The duration of the move is what makes it a swipe
        swipe.addAction(finger.createPointerMove(Duration.ofMillis(300), PointerInput.Origin.viewport(), startX, endY));
        swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        driver.perform(Collections.singletonList(swipe));
    }
}
