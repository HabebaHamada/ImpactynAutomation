package ImpactynPages;


import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class onBoardingPage {

    private final AppiumDriver driver;

    private final By mediaSettingsAllowLocator = By.xpath("//android.widget.Button[@resource-id=\"com.android.permissioncontroller:id/permission_allow_foreground_only_button\"]");
    private final By notificationSettingsAllowLocator = By.xpath("//android.widget.Button[@resource-id=\"com.android.permissioncontroller:id/permission_allow_button\"]");
    private final By phoneCallSettingsAllowLocator = By.xpath("//android.widget.Button[@resource-id=\"com.android.permissioncontroller:id/permission_allow_button\"]");

    public onBoardingPage(AppiumDriver driver) {
        this.driver = driver;
    }


    public void handleOnboardingFlow() {
        try {
            WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(5));

            System.out.println("Checking for onboarding/settings screen...");
            shortWait.until(ExpectedConditions.elementToBeClickable(mediaSettingsAllowLocator)).click();
            shortWait.until(ExpectedConditions.elementToBeClickable(notificationSettingsAllowLocator)).click();
            shortWait.until(ExpectedConditions.elementToBeClickable(phoneCallSettingsAllowLocator)).click();
            System.out.println("Onboarding screen found and done.");


        } catch (TimeoutException e) {
            // This is expected if the onboarding screen is not present.
            System.out.println("Onboarding screen not found. Proceeding...");
        }
    }


}
