package ImpactynPages;

import ImpactynCore.BasePage;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class HomePage extends BasePage {

    private By homePageIdentifier ;
    public HomePage(AppiumDriver driver) {
        super(driver);
        initializeLocators();
    }

    private void initializeLocators() {
        // 'platform' is inherited from BasePage
    if (platform.is(Platform.ANDROID)) {
            homePageIdentifier = By.id("com.innov8eg.impactyn:id/home_page_element_id"); // Replace with actual Android locator
        } else if (platform.is(Platform.IOS)) {
            homePageIdentifier = AppiumBy.accessibilityId("smallLogo");
        }
    }
    public boolean isPageLoaded() {

        boolean homePageLoadingValidation  = wait.until(ExpectedConditions.visibilityOfElementLocated(homePageIdentifier)).isDisplayed();

        return (homePageLoadingValidation);
    }
}
