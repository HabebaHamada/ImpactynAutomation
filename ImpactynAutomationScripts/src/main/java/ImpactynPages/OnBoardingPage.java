package ImpactynPages;

import ImpactynCore.BasePage;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class OnBoardingPage extends BasePage{

    private  By allowWhileUsingAppLocator ;
    private  By allowButtonLocator;

    public OnBoardingPage(AppiumDriver driver) {
        super(driver);
        initializeLocators();
    }
    /** * Initializes locators based on the platform determined in the BasePage.
     */
    private void initializeLocators() {
        // 'platform' is inherited from BasePage
        if (platform.is(Platform.ANDROID)) {
        allowWhileUsingAppLocator = By.xpath("//android.widget.Button[@resource-id=\"com.android.permissioncontroller:id/permission_allow_foreground_only_button\"]");
        allowButtonLocator = By.xpath("//android.widget.Button[@resource-id=\"com.android.permissioncontroller:id/permission_allow_button\"]");
        }
    }

    public void handleOnboardingFlow() {

        handlePermissionPopup(allowWhileUsingAppLocator); // Handles Media/Location
        handlePermissionPopup(allowButtonLocator);        // Handles Notifications
        handlePermissionPopup(allowButtonLocator);
        System.out.println("Onboarding screen found and done.");

    }

    private void handlePermissionPopup(By locator) {
        try {
            // This wait is specific to this action.
            WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(5));
            System.out.println("Checking for permission pop-up with locator: " + locator);
            shortWait.until(ExpectedConditions.elementToBeClickable(locator)).click();
            System.out.println("Permission pop-up handled successfully.");
        } catch (TimeoutException e) {
            // This is now EXPECTED and SAFE. It just means the pop-up wasn't there.
            System.out.println("Permission pop-up not found. Continuing...");
        }
    }


}
