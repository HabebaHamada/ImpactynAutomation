package ImpactynPages;

import ImpactynCore.BasePage;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class HomePage extends BasePage {

    private By homePageIdentifier ;
    private By RecordButtonLocator ;

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
            RecordButtonLocator = AppiumBy.iOSNsPredicateString("type == 'XCUIElementTypeButton' AND name == 'center btn ic'");;}
    }

    public boolean isPageLoaded() {

        return( wait.until(ExpectedConditions.visibilityOfElementLocated(homePageIdentifier)).isDisplayed());
    }
    public UploadReviewPage clickRecordReview()  {

        /*click the record review button in the Nav Bar*/
        WebElement RecordButton = wait.until(ExpectedConditions.elementToBeClickable(RecordButtonLocator));
        RecordButton.click();

        return new UploadReviewPage(driver);
    }
}
