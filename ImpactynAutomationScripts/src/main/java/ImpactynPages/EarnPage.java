package ImpactynPages;

import ImpactynCore.BasePage;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.Platform;

import java.util.HashMap;
import java.util.Map;

public class EarnPage extends BasePage {

    public EarnPage(AppiumDriver driver) {
        super(driver);
        initializeLocators();
    }
    private void initializeLocators() {
        // 'platform' is inherited from BasePage
        if (platform.is(Platform.ANDROID)) {

        } else if (platform.is(Platform.IOS)) {

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
    }
}
