package ImpactynPages;

import ImpactynCore.BasePage;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;


public class FeedPage extends BasePage {


    private final Actions actions;

    private final By ForYouTextLocator = By.xpath("//android.widget.TextView[@text=\"For You\"]");
    private final By FollowingTextLocator = By.xpath("//android.widget.TextView[@text=\"Following\"]");
    private final By EarnButtonLocator = By.xpath("(//android.widget.TextView[@text=\"Earn\"])[1]");

    private final By MentionBrandLocator = By.xpath("//android.widget.EditText");
    private final By AllowRecordingSettingsLocator = By.id("com.android.permissioncontroller:id/permission_allow_foreground_only_button");
    private final By BrandSelectionNameLocator = By.xpath("//android.widget.TextView[@text=\"BRGR\"]");

    private final By ShareButtonLocator = By.xpath("//android.widget.TextView[@text=\"Share\"]");

    String navBarAutomatorString = "new UiSelector().className(\"android.view.View\").instance(16)";
    String progressBarClassName = "android.widget.ProgressBar";
    String brandsSuggestionBarAutomatorString = "new UiSelector().className(\"android.view.View\").instance(8)";
    String brandSelectionAutomatorString = "new UiSelector().className(\"android.view.View\").instance(9)";
    String ratingSliderClassName = "android.widget.SeekBar";
    String flipCameraAutomatorString = "new UiSelector().className(\"android.widget.Button\").instance(0)";

    private final By NavBarLocator= AppiumBy.androidUIAutomator(navBarAutomatorString);
    private final By ProgressBarLocator= AppiumBy.className(progressBarClassName);
    private final By BrandsSuggestionBarLocator=  AppiumBy.androidUIAutomator(brandsSuggestionBarAutomatorString);
    private final By BrandSelectionLocator= AppiumBy.androidUIAutomator(brandSelectionAutomatorString);
    private final By RatingSliderLocator= AppiumBy.className(ratingSliderClassName);
    private final By FlipCameraLocator= AppiumBy.androidUIAutomator(flipCameraAutomatorString);


    public FeedPage(AppiumDriver driver) {
        super(driver);
        this.actions = new Actions(driver);
    }

    public boolean isPageLoaded()
    {
       boolean ForYouValidation=      wait.until(ExpectedConditions.visibilityOfElementLocated(ForYouTextLocator)).isDisplayed();
       boolean FollowingValidation=   wait.until(ExpectedConditions.visibilityOfElementLocated(FollowingTextLocator)).isDisplayed();
       boolean EarnButtonValidation=  wait.until(ExpectedConditions.visibilityOfElementLocated(EarnButtonLocator)).isDisplayed();

       return (ForYouValidation||FollowingValidation||EarnButtonValidation);
    }

    public void clickRecordReview()  {

        /*click the record review button in the Nav Bar*/
        WebElement RecordButton = wait.until(ExpectedConditions.elementToBeClickable(
                NavBarLocator)
        );
        RecordButton.click();

        /*Allow Video and Sound Settings For Recording*/
        WebElement AllowRecording = wait.until(ExpectedConditions.elementToBeClickable(AllowRecordingSettingsLocator));
        AllowRecording.click();
    }

    public void startCameraRecording(long reviewDurationInMillis) throws InterruptedException {
        WebElement ProgressBar =  wait.until(
                ExpectedConditions.visibilityOfElementLocated(ProgressBarLocator)
        );
        ProgressBar.click();

        System.out.println("Recording for " + reviewDurationInMillis + " milliseconds...");
        Thread.sleep(reviewDurationInMillis);

        wait.until(
                ExpectedConditions.visibilityOfElementLocated(ProgressBarLocator)
        ).click();
    }

    public void setMentionBrand(String Brand)
    {
        System.out.println("Entering BRGR Brand...");

        WebElement MentionBrand = wait.until(ExpectedConditions.visibilityOfElementLocated(MentionBrandLocator));

        MentionBrand.click();

        MentionBrand.sendKeys(Brand);

        System.out.println("waiting for brandsSuggestionBar...");


        wait.until(
                ExpectedConditions.visibilityOfElementLocated(BrandsSuggestionBarLocator)
        );
        wait.until(ExpectedConditions.visibilityOfElementLocated(BrandSelectionNameLocator));

        System.out.println("waiting for brandSelection...");

        /*Select the Brand*/
        WebElement brandSelection =  wait.until(
                ExpectedConditions.visibilityOfElementLocated(BrandSelectionLocator)
        );
        brandSelection.click();

    }

    public void setReviewRating()
    {
        WebElement RatingSlider = wait.until(
                ExpectedConditions.visibilityOfElementLocated(RatingSliderLocator)
        );
        // To slide horizontally to the right by 50 pixels
        actions.dragAndDropBy(RatingSlider, 50, 0).perform();
    }

    public void shareReview()
    {
        /*click share button*/
        WebElement ShareButton = wait.until(ExpectedConditions.visibilityOfElementLocated(ShareButtonLocator));
        ShareButton.click();

    }

    public void setFrontCamera()
    {
       /*click flip camera button*/
        WebElement FlipCamera =wait.until(
                ExpectedConditions.visibilityOfElementLocated(FlipCameraLocator)
        );
        FlipCamera.click();
    }
}