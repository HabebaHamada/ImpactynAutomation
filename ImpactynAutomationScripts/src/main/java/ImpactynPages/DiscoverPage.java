package ImpactynPages;

import ImpactynCore.BasePage;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class DiscoverPage extends BasePage {

    private By ReviewToInspire;

    public DiscoverPage(AppiumDriver driver) {
        super(driver);
        initializeLocators();
    }
    private void initializeLocators() {
        if (platform.is(Platform.ANDROID)) {

        } else if (platform.is(Platform.IOS)) {
            ReviewToInspire = AppiumBy.iOSNsPredicateString("type == 'XCUIElementTypeStaticText' AND name == 'Review to Inspire'");
        }
    }
    public UploadReviewPage clickReviewToInspire()  {

        /*click the Review to Inspire Section*/
        WebElement ReviewToInspireButton = wait.until(ExpectedConditions.elementToBeClickable(ReviewToInspire));
        ReviewToInspireButton.click();
        return new UploadReviewPage(driver);
    }

}
