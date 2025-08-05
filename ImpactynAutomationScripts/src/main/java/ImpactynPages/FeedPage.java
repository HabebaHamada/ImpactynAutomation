package ImpactynPages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class FeedPage {

    private final WebDriverWait wait;
    private final Actions actions;

    private final By ForYouTextLocator = By.xpath("//android.widget.TextView[@text=\"For You\"]");
    private final By FollowingTextLocator = By.xpath("//android.widget.TextView[@text=\"Following\"]");
    private final By EarnButtonLocator = By.xpath("(//android.widget.TextView[@text=\"Earn\"])[1]");

    private final By MentionBrandLocator = By.xpath("//android.widget.EditText");
    private final By AllowRecordingSettingsLocator = By.id("com.android.permissioncontroller:id/permission_allow_foreground_only_button");
    private final By BrandSelectionNameLocator = By.xpath("//android.widget.TextView[@text=\"BRGR\"]");

    private final By ShareButtonLocator = By.xpath("//android.widget.TextView[@text=\"Share\"]");


    public FeedPage(AppiumDriver driver) {
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(50));
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
        String navBarLocator = "new UiSelector().className(\"android.view.View\").instance(23)";
        WebElement RecordButton = wait.until(ExpectedConditions.elementToBeClickable(
                AppiumBy.androidUIAutomator(navBarLocator))
        );
        RecordButton.click();

        /*Allow Video and Sound Settings For Recording*/
        WebElement AllowRecording = wait.until(ExpectedConditions.elementToBeClickable(AllowRecordingSettingsLocator));
        AllowRecording.click();
    }

    public void startCameraRecording(long reviewDurationInMillis) throws InterruptedException {
        String progressBarLocator = "android.widget.ProgressBar";
        WebElement ProgressBar =  wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        AppiumBy.className(progressBarLocator)
                )
        );
        ProgressBar.click();

        System.out.println("Recording for " + reviewDurationInMillis + " milliseconds...");
        Thread.sleep(reviewDurationInMillis);

        ProgressBar.click();
    }

    public void setMentionBrand(String Brand)
    {
        System.out.println("Entering BRGR Brand...");

        WebElement MentionBrand = wait.until(ExpectedConditions.visibilityOfElementLocated(MentionBrandLocator));

        MentionBrand.click();

        MentionBrand.sendKeys(Brand);

        System.out.println("waiting for brandsSuggestionBar...");


        String brandsSuggestionBarLocator = "new UiSelector().className(\"android.view.View\").instance(7)";
        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        AppiumBy.androidUIAutomator(brandsSuggestionBarLocator)
                )
        );
        wait.until(ExpectedConditions.visibilityOfElementLocated(BrandSelectionNameLocator));

        System.out.println("waiting for brandSelection...");

        /*Select the Brand*/
        String brandSelectionLocator = "new UiSelector().className(\"android.view.View\").instance(9)";

        WebElement brandSelection =  wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        AppiumBy.androidUIAutomator(brandSelectionLocator)
                )
        );
        brandSelection.click();

    }

    public void setReviewRating()
    {
        String ratingSliderLocator = "android.widget.SeekBar";
        WebElement RatingSlider =   wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        AppiumBy.className(ratingSliderLocator)
                )
        );
        // To slide horizontally to the right by 50 pixels
        actions.dragAndDropBy(RatingSlider, 50, 0).perform();
    }

    public void shareReview()
    {
        /*click share button*/
        WebElement ShareButton = wait.until(ExpectedConditions.visibilityOfElementLocated(ShareButtonLocator));
        ShareButton.click();

        /*assert that background Progress bar is shown*/


    }

    public void setFrontCamera()
    {
       /*click flip camera button*/
        String flipCameraLocator = "new UiSelector().className(\"android.widget.Button\").instance(0)";
        WebElement FlipCamera =wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        AppiumBy.androidUIAutomator(flipCameraLocator)
                )
        );
        FlipCamera.click();

    }
}