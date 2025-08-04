package ImpactynPages;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class FeedPage {

    private final AppiumDriver driver;
    private final WebDriverWait wait;
    private final Actions actions;

    private final By ForYouTextLocator = By.xpath("//android.widget.TextView[@text=\"For You\"]");
    private final By FollowingTextLocator = By.xpath("//android.widget.TextView[@text=\"Following\"]");
    private final By NavBarLocator = By.xpath("//m5.e1/android.view.View/android.view.View/android.view.View[2]/android.view.View/android.view.View[3]/android.view.View[2]");
    private final By EarnButtonLocator = By.xpath("(//android.widget.TextView[@text=\"Earn\"])[1]");

    private final By ProgressBarLocator = By.xpath("//android.widget.ProgressBar[@text=\"0.05\"]");
    private final By MentionBrandLocator = By.xpath("//android.widget.TextView[@text=\"mention the brand\"]");
    private final By AllowRecordingSettingsLocator = By.id("com.android.permissioncontroller:id/permission_allow_foreground_only_button");
    private final By AllowVoiceSettingsLocator = By.id("com.android.permissioncontroller:id/permission_allow_foreground_only_button");
    private final By BrandsSuggestionBarLocator = By.xpath("//m5.e1/android.view.View/android.view.View/android.view.View/android.view.View[3]");
    private final By BrandSelectionNameLocator = By.xpath("//android.widget.TextView[@text=\"BRGR\"]");

    private final By RatingSliderLocator = By.id("");
    private final By ShareButtonLocator = By.id("");
    private final By FlipCameraLocator = By.id("");


    public FeedPage(AppiumDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(50));
        this.actions = new Actions(driver);

    }

    public boolean isPageLoaded()
    {
       boolean ForYouValidation=      wait.until(ExpectedConditions.visibilityOfElementLocated(ForYouTextLocator)).isDisplayed();
       boolean FollowingValidation=   wait.until(ExpectedConditions.visibilityOfElementLocated(FollowingTextLocator)).isDisplayed();
       boolean NavBarValidation=      wait.until(ExpectedConditions.visibilityOfElementLocated(NavBarLocator)).isDisplayed();
       boolean EarnButtonValidation=  wait.until(ExpectedConditions.visibilityOfElementLocated(EarnButtonLocator)).isDisplayed();

       return (ForYouValidation||FollowingValidation||NavBarValidation||EarnButtonValidation);
    }

    public void clickRecordReview()  {

        /*click the record review button in the Nav Bar*/
        WebElement RecordButton = wait.until(ExpectedConditions.elementToBeClickable(NavBarLocator));
        RecordButton.click();

        /*Allow Video and Sound Settings For Recording*/
        WebElement AllowRecording = wait.until(ExpectedConditions.elementToBeClickable(AllowRecordingSettingsLocator));
        AllowRecording.click();

        WebElement AllowVoice = wait.until(ExpectedConditions.elementToBeClickable(AllowVoiceSettingsLocator));
        AllowVoice.click();
    }

    public void startCameraRecording(long reviewDuration) throws InterruptedException {
        WebElement ProgressBar = wait.until(ExpectedConditions.elementToBeClickable(ProgressBarLocator));
        ProgressBar.click();

        wait(reviewDuration);

        ProgressBar.click();
    }

    public void setMentionBrand(String Brand)
    {
        WebElement MentionBrand = wait.until(ExpectedConditions.visibilityOfElementLocated(MentionBrandLocator));

        MentionBrand.click();

        MentionBrand.sendKeys(Brand);

        wait.until(ExpectedConditions.visibilityOfElementLocated(BrandsSuggestionBarLocator));
        wait.until(ExpectedConditions.visibilityOfElementLocated(BrandSelectionNameLocator));
    }

    public void setReviewRating()
    {
        WebElement RatingSlider = wait.until(ExpectedConditions.visibilityOfElementLocated(RatingSliderLocator));
        // To slide horizontally to the right by 50 pixels
        actions.dragAndDropBy(RatingSlider, 50, 0).perform();
    }

    public void shareReview()
    {
        /*click share button*/
        WebElement ShareButton = wait.until(ExpectedConditions.elementToBeClickable(ShareButtonLocator));
        ShareButton.click();

        /*assert that background Progress bar is shown*/

        /*assert that video is fully uploaded*/

    }

    public void setFrontCamera()
    {
       /*click flip camera button*/
        WebElement FlipCamera = wait.until(ExpectedConditions.elementToBeClickable(FlipCameraLocator));
        FlipCamera.click();

    }
}