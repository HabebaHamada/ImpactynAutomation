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


}
