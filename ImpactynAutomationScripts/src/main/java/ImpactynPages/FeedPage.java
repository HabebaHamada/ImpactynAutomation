package ImpactynPages;

import ImpactynCore.BasePage;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
/**
 * FeedPage class represents the feed screen of the application.
 * It provides methods to interact with various elements on the feed page.
 * This class extends BasePage to inherit common functionality.
 */

public class FeedPage extends BasePage {

    private By ForYouTextLocator;
    private By FollowingTextLocator;
    private By EarnButtonLocator ;
    private By RecordButtonLocator;

    public FeedPage(AppiumDriver driver) {
        super(driver);
        initializeLocators();
    }
    /**
     * Initializes locators based on the platform determined in the BasePage. */
    private void initializeLocators() {
        // 'platform' is inherited from BasePage
        if (platform.is(Platform.ANDROID)) {
            String recordButtonAutomatorString = "new UiSelector().className(\"android.view.View\").instance(16)";

            ForYouTextLocator = By.xpath("//android.widget.TextView[@text=\"For You\"]");
            FollowingTextLocator = By.xpath("//android.widget.TextView[@text=\"Following\"]");
            EarnButtonLocator = By.xpath("(//android.widget.TextView[@text=\"Earn\"])[1]");
            RecordButtonLocator = AppiumBy.androidUIAutomator(recordButtonAutomatorString);

        } else if (platform.is(Platform.IOS)) {
            ForYouTextLocator=AppiumBy.accessibilityId("For you");
            FollowingTextLocator=AppiumBy.accessibilityId("Following");
            EarnButtonLocator=By.xpath("//XCUIElementTypeStaticText[@name=\"Earn\"]");
            RecordButtonLocator = AppiumBy.name("center_btn_ic");
        }
    }
    public boolean isPageLoaded()
    {
       boolean ForYouValidation=      wait.until(ExpectedConditions.visibilityOfElementLocated(ForYouTextLocator)).isDisplayed();
       boolean FollowingValidation=   wait.until(ExpectedConditions.visibilityOfElementLocated(FollowingTextLocator)).isDisplayed();
       boolean EarnButtonValidation=  wait.until(ExpectedConditions.visibilityOfElementLocated(EarnButtonLocator)).isDisplayed();

       return (ForYouValidation||FollowingValidation||EarnButtonValidation);
    }

    public UploadReview clickRecordReview()  {

        /*click the record review button in the Nav Bar*/
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                RecordButtonLocator)
        );
        WebElement RecordButton = wait.until(ExpectedConditions.elementToBeClickable(RecordButtonLocator));
        RecordButton.click();

        return new UploadReview(driver);

    }
}