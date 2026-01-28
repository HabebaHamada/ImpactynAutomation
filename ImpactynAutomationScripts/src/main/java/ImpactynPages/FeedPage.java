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
    private By ChallengeIconLocator;
    private By EarnButtonLocator;
    private By RecordButtonLocator;
    private By ProfileButtonLocator ;

    public FeedPage(AppiumDriver driver) {
        super(driver);
        initializeLocators();
    }
    /**
     * Initializes locators based on the platform determined in the BasePage. */
    private void initializeLocators() {
        // 'platform' is inherited from BasePage
        if (platform.is(Platform.ANDROID)) {
            ForYouTextLocator = By.xpath("//android.widget.TextView[@text=\"For You\"]");
            FollowingTextLocator = By.xpath("//android.widget.TextView[@text=\"Following\"]");
            ChallengeIconLocator = By.xpath("(//android.widget.TextView[@text=\"Earn\"])[1]");

        } else if (platform.is(Platform.IOS)) {
            ForYouTextLocator=AppiumBy.accessibilityId("For you");
            FollowingTextLocator=AppiumBy.accessibilityId("Following");
            ChallengeIconLocator =By.xpath("//XCUIElementTypeStaticText[@name=\"Earn\"]");
            EarnButtonLocator = By.xpath("//XCUIElementTypeButton[@name=\"Earn\"]");
            ProfileButtonLocator = By.xpath("//XCUIElementTypeButton[@name=\"Profile\"]");
        }
    }
    public boolean isPageLoaded()
    {
       boolean ForYouValidation        = wait.until(ExpectedConditions.visibilityOfElementLocated(ForYouTextLocator)).isDisplayed();
       boolean FollowingValidation     = wait.until(ExpectedConditions.visibilityOfElementLocated(FollowingTextLocator)).isDisplayed();
       boolean ChallengeIconValidation = wait.until(ExpectedConditions.visibilityOfElementLocated(ChallengeIconLocator)).isDisplayed();
       boolean EarnButtonValidation    = wait.until(ExpectedConditions.visibilityOfElementLocated(EarnButtonLocator)).isDisplayed();
       return (ForYouValidation||FollowingValidation|| ChallengeIconValidation||EarnButtonValidation);
    }


    public EarnPage clickEarnButton()  {

        /*click the Earn button in the Nav Bar*/
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                EarnButtonLocator)
        );
        WebElement EarnButton = wait.until(ExpectedConditions.elementToBeClickable(EarnButtonLocator));
        EarnButton.click();

        return new EarnPage(driver);

    }
    public ProfilePage clickProfileButton()  {

        /*click the Profile button in the Nav Bar*/
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                ProfileButtonLocator)
        );
        WebElement ProfileButton = wait.until(ExpectedConditions.elementToBeClickable(ProfileButtonLocator));
        ProfileButton.click();

        return new ProfilePage(driver);

    }
}