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
    private By MentionBrandLocator;
    private By AllowRecordingSettingsLocator;
    private By ShareButtonLocator;
    private By ProgressBarLocator;
    private By BrandsSuggestionBarLocator;
    private By BrandSelectionLocator;
    private By RatingSliderLocator;
    private By FlipCameraLocator;
    private By NextAccessibilityLocator;
    private By recordButtonLocator;
    private By chooseFromGalleryLocator;
    private By chooseVideoLocator;
    private By confirmChoosenVideoLocator;
    private By preprationReviewNotificationLocator;
    private By finshingReviewNotificationLocator;

    public FeedPage(AppiumDriver driver) {
        super(driver);
        initializeLocators();
    }
    /**
     * Initializes locators based on the platform determined in the BasePage. */
    private void initializeLocators() {    // 'platform' is inherited from BasePage
        if (platform.is(Platform.ANDROID)) {

            ForYouTextLocator = By.xpath("//android.widget.TextView[@text=\"For You\"]");
            FollowingTextLocator = By.xpath("//android.widget.TextView[@text=\"Following\"]");
            EarnButtonLocator = By.xpath("(//android.widget.TextView[@text=\"Earn\"])[1]");
            MentionBrandLocator = By.xpath("//android.widget.EditText");
            AllowRecordingSettingsLocator = By.id("com.android.permissioncontroller:id/permission_allow_foreground_only_button");
            ShareButtonLocator = By.xpath("//android.widget.TextView[@text=\"Share\"]");

            String recordButtonAutomatorString = "new UiSelector().className(\"android.view.View\").instance(16)";
            String progressBarClassName = "android.widget.ProgressBar";
            String brandsSuggestionBarAutomatorString = "new UiSelector().className(\"android.view.View\").instance(8)";
            String brandSelectionAutomatorString = "new UiSelector().className(\"android.view.View\").instance(9)";
            String ratingSliderClassName = "android.widget.SeekBar";
            String flipCameraAutomatorString = "new UiSelector().className(\"android.widget.Button\").instance(0)";

            recordButtonLocator= AppiumBy.androidUIAutomator(recordButtonAutomatorString);
            ProgressBarLocator= AppiumBy.className(progressBarClassName);
            BrandsSuggestionBarLocator=  AppiumBy.androidUIAutomator(brandsSuggestionBarAutomatorString);
            BrandSelectionLocator= AppiumBy.androidUIAutomator(brandSelectionAutomatorString);
            RatingSliderLocator= AppiumBy.className(ratingSliderClassName);
            FlipCameraLocator= AppiumBy.androidUIAutomator(flipCameraAutomatorString);

        } else if (platform.is(Platform.IOS)) {
            ForYouTextLocator=AppiumBy.accessibilityId("For you");
            FollowingTextLocator=AppiumBy.accessibilityId("Following");
            EarnButtonLocator=By.xpath("//XCUIElementTypeStaticText[@name=\"Earn\"]");

            ProgressBarLocator=By.xpath("//XCUIElementTypeApplication[@name=\"Impactyn\"]/XCUIElementTypeWindow[1]/XCUIElementTypeOther[4]/XCUIElementTypeOther/XCUIElementTypeButton[2]");
            MentionBrandLocator=By.xpath("//XCUIElementTypeTextField[@value=\"mention the brand\"]");
            BrandsSuggestionBarLocator=By.xpath("(//XCUIElementTypeOther[@name=\"Horizontal scroll bar, 3 pages\"])[2]");
            BrandSelectionLocator=By.xpath("//XCUIElementTypeCollectionView/XCUIElementTypeCell[1]/XCUIElementTypeOther/XCUIElementTypeImage");
            RatingSliderLocator=AppiumBy.className("XCUIElementTypeSlider");
            ShareButtonLocator=By.xpath("//XCUIElementTypeButton[@name=\"Share\"]");
            FlipCameraLocator=AppiumBy.accessibilityId("flip");
            NextAccessibilityLocator =AppiumBy.accessibilityId("AccessibilityIdentifiers.coachMarkNext");

            recordButtonLocator = AppiumBy.name("center_btn_ic");
            chooseFromGalleryLocator = By.xpath("//XCUIElementTypeImage[@name='feed_selected']/..");
            chooseVideoLocator = By.xpath("//XCUIElementTypeCell/XCUIElementTypeOther[1]/XCUIElementTypeImage");
            confirmChoosenVideoLocator = By.xpath("//XCUIElementTypeStaticText[@name=\"Done\"]");
            preprationReviewNotificationLocator=AppiumBy.accessibilityId("Preparing your video...");
            finshingReviewNotificationLocator=AppiumBy.accessibilityId("Keep Impactyn open to finish posting..");
        }
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
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                recordButtonLocator)
        );
        WebElement RecordButton = wait.until(ExpectedConditions.elementToBeClickable(recordButtonLocator));
        RecordButton.click();

        if (this.platform.is(Platform.ANDROID)) {
            /*Allow Video and Sound Settings For Recording*/
            WebElement AllowRecording = wait.until(ExpectedConditions.elementToBeClickable(AllowRecordingSettingsLocator));
            AllowRecording.click();
        }
        else if (this.platform.is(Platform.IOS)){
            /*Handle Coach Marks if present*/
            try {
                WebElement NextButton = wait.until(ExpectedConditions.visibilityOfElementLocated(NextAccessibilityLocator));
                while (NextButton.isDisplayed()) {
                    NextButton.click();
                    // Re-locate the Next button after clicking
                    NextButton = wait.until(ExpectedConditions.visibilityOfElementLocated(NextAccessibilityLocator));
                }
            } catch (Exception e) {
                // If the Next button is not found, we assume there are no coach marks to handle
                System.out.println("No coach marks to handle.");
            }
        }
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

    public void mentionBrand(String Brand)
    {
        System.out.println("Entering BRGR Brand...");

        WebElement mentionBrandTextField = wait.until(ExpectedConditions.visibilityOfElementLocated(MentionBrandLocator));

        mentionBrandTextField.click();
        mentionBrandTextField.clear(); // Ensure field is empty
        mentionBrandTextField.sendKeys(Brand);

        System.out.println("waiting for brandsSuggestionBar...");


        wait.until(
                ExpectedConditions.visibilityOfElementLocated(BrandsSuggestionBarLocator)
        );

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
        if (platform.is(Platform.IOS)) {
            // Set the slider value using JavaScript for XCUIElementTypeSlider
            RatingSlider.sendKeys("0.5"); // Set to 50%, adjust as needed
        } else {
            // For Android, use dragAndDropBy
            RatingSlider.sendKeys("5.5"); // Set to 50%, adjust as needed
        }
    }

    public void shareReview()
    {
        /*click share button*/
        WebElement ShareButton = wait.until(ExpectedConditions.visibilityOfElementLocated(ShareButtonLocator));
        ShareButton.click();

    }

    public void flipCamera()
    {
       /*click flip camera button*/
        WebElement FlipCamera =wait.until(
                ExpectedConditions.visibilityOfElementLocated(FlipCameraLocator)
        );
        FlipCamera.click();
    }

    public void chooseFromGallery()
    {
        WebElement ChooseFromGallery= wait.until(
                ExpectedConditions.visibilityOfElementLocated(chooseFromGalleryLocator)
        );
        ChooseFromGallery.click();

        WebElement ChooseVideo= wait.until(
                ExpectedConditions.visibilityOfElementLocated(chooseVideoLocator)
        );
        ChooseVideo.click();

        WebElement ConfirmChoosenVideo= wait.until(
                ExpectedConditions.visibilityOfElementLocated(confirmChoosenVideoLocator)
        );
        ConfirmChoosenVideo.click();
    }

    public boolean verifyUploadingMessages() {
        try {
            // Wait for the "Preparing your video..." notification
            WebElement preparingNotification = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(preprationReviewNotificationLocator)
            );
            boolean isPreparingVisible = preparingNotification.isDisplayed();
            System.out.println("\"Preparing your video...\" notification is visible: " + isPreparingVisible);

            // Wait for the "Keep Impactyn open to finish posting.." notification
            WebElement finishingNotification = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(finshingReviewNotificationLocator)
            );
            boolean isFinishingVisible = finishingNotification.isDisplayed();
            System.out.println("\"Keep Impactyn open to finish posting..\" notification is visible: " + isFinishingVisible);

            return isPreparingVisible && isFinishingVisible;
        } catch (Exception e) {
            System.out.println("An error occurred while verifying uploading messages: " + e.getMessage());
            return false;
        }
    }
}