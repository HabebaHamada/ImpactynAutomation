package ImpactynPages;

import ImpactynCore.BasePage;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class UploadReviewPage extends BasePage {

    private By MentionBrandLocator;
    private By ShareButtonLocator;
    private By ProgressBarLocator;
    private By BrandsSuggestionBarLocator;
    private By BrandSelectionLocator;
    private By RatingSliderLocator;
    private By FlipCameraLocator;
    private By ChooseFromGalleryLocator;
    private By ChooseVideoLocator;
    private By ConfirmChosenVideoLocator;
    private By PreparationReviewNotificationLocator;
    private By FinishingReviewNotificationLocator;

    public UploadReviewPage(AppiumDriver driver) {
        super(driver);
        initializeLocators();
    }

    /**
     * Initializes locators based on the platform determined in the BasePage.
     */
    private void initializeLocators() {
        // 'platform' is inherited from BasePage
        if (platform.is(Platform.ANDROID)) {

            MentionBrandLocator = By.xpath("//android.widget.EditText");
            ShareButtonLocator = By.xpath("//android.widget.TextView[@text=\"Share\"]");

            String progressBarClassName = "android.widget.ProgressBar";
            String brandsSuggestionBarAutomatorString = "new UiSelector().className(\"android.view.View\").instance(8)";
            String brandSelectionAutomatorString = "new UiSelector().className(\"android.view.View\").instance(9)";
            String ratingSliderClassName = "android.widget.SeekBar";
            String flipCameraAutomatorString = "new UiSelector().className(\"android.widget.Button\").instance(0)";

            ProgressBarLocator = AppiumBy.className(progressBarClassName);
            BrandsSuggestionBarLocator = AppiumBy.androidUIAutomator(brandsSuggestionBarAutomatorString);
            BrandSelectionLocator = AppiumBy.androidUIAutomator(brandSelectionAutomatorString);
            RatingSliderLocator = AppiumBy.className(ratingSliderClassName);
            FlipCameraLocator = AppiumBy.androidUIAutomator(flipCameraAutomatorString);

        } else if (platform.is(Platform.IOS)) {

            ProgressBarLocator = By.xpath("//XCUIElementTypeApplication[@name=\"Impactyn\"]/XCUIElementTypeWindow[1]/XCUIElementTypeOther[4]/XCUIElementTypeOther/XCUIElementTypeButton[2]");
            MentionBrandLocator = By.xpath("//XCUIElementTypeTextField[@value=\"mention the brand\"]");
            BrandsSuggestionBarLocator = By.xpath("//XCUIElementTypeCollectionView");
            BrandSelectionLocator = By.xpath("//XCUIElementTypeCollectionView/XCUIElementTypeCell[1]/XCUIElementTypeOther/XCUIElementTypeImage");
            RatingSliderLocator = AppiumBy.className("XCUIElementTypeSlider");
            ShareButtonLocator = AppiumBy.iOSNsPredicateString("type == 'XCUIElementTypeButton' AND name == 'Share'");
            FlipCameraLocator = AppiumBy.accessibilityId("flip");

            ChooseFromGalleryLocator = By.xpath("//XCUIElementTypeImage[@name='feed_selected']/..");
            ChooseVideoLocator = By.xpath("//XCUIElementTypeCell/XCUIElementTypeOther[1]/XCUIElementTypeImage");
            ConfirmChosenVideoLocator = By.xpath("//XCUIElementTypeStaticText[@name=\"Done\"]");
            PreparationReviewNotificationLocator = AppiumBy.accessibilityId("Preparing your video...");
            FinishingReviewNotificationLocator = AppiumBy.accessibilityId("Keep Impactyn open to finish posting..");
        }
    }

    public void startCameraRecording(int reviewDurationInSeconds) throws InterruptedException {
        /*click the progress bar to start recording*/
        WebElement ProgressBar = wait.until(
                ExpectedConditions.visibilityOfElementLocated(ProgressBarLocator)
        );
        ProgressBar.click();

        System.out.println("Recording for " + reviewDurationInSeconds * 1000 + " milliseconds...");
        Thread.sleep(reviewDurationInSeconds * 1000L);

        /*click the progress bar again to stop recording*/
        wait.until(
                ExpectedConditions.visibilityOfElementLocated(ProgressBarLocator)
        ).click();
    }

    public void mentionBrand(String Brand) throws InterruptedException {
        System.out.println("mentioning Brand...");

        WebElement mentionBrandTextField = wait.until(ExpectedConditions.visibilityOfElementLocated(MentionBrandLocator));

        mentionBrandTextField.click();
        mentionBrandTextField.clear(); // Ensure field is empty
        Thread.sleep(5000); // Wait for 5 seconds to ensure the field is ready
        for (char c : (Brand).toCharArray()) {
            mentionBrandTextField.sendKeys(Character.toString(c));
            Thread.sleep(500); // Adjust delay as needed
        }
        Thread.sleep(5000); // Wait for 5 seconds to ensure the field is ready

        System.out.println("waiting for brandSelection...");

        /*Select the Brand*/
        WebElement brandSelection = wait.until(
                ExpectedConditions.visibilityOfElementLocated(BrandSelectionLocator)
        );
        brandSelection.click();
    }

    public void setReviewRating(String ratingValue) {
        WebElement RatingSlider = wait.until(
                ExpectedConditions.visibilityOfElementLocated(RatingSliderLocator)
        );
            RatingSlider.sendKeys(ratingValue);
    }

    public FeedPage shareReview() {
        /*click share button*/
        WebElement ShareButton = wait.until(ExpectedConditions.elementToBeClickable(ShareButtonLocator));
        System.out.println("Clicking Share Button...");
        ShareButton.click();
        return new FeedPage(driver);

    }

    public void flipCamera() {
        /*click flip camera button*/
        WebElement FlipCamera = wait.until(
                ExpectedConditions.visibilityOfElementLocated(FlipCameraLocator)
        );
        FlipCamera.click();
    }

    public void chooseFromGallery() throws InterruptedException {
        WebElement ChooseFromGallery = wait.until(
                ExpectedConditions.visibilityOfElementLocated(ChooseFromGalleryLocator)
        );
        ChooseFromGallery.click();

        SystemAlertsPage alerts= new SystemAlertsPage(driver);
        alerts.allowGalleryAccess();

        WebElement ChooseVideo = wait.until(
                ExpectedConditions.visibilityOfElementLocated(ChooseVideoLocator)
        );
        ChooseVideo.click();

        Thread.sleep(3000);
        WebElement ConfirmChosenVideo = wait.until(
                ExpectedConditions.elementToBeClickable(ConfirmChosenVideoLocator)
        );
        ConfirmChosenVideo.click();
    }

    public boolean verifyUploadingMessages() {
        try {
            // Wait for the "Preparing your video..." notification
            WebElement preparingNotification = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(PreparationReviewNotificationLocator)
            );
            boolean isPreparingVisible = preparingNotification.isDisplayed();
            System.out.println("\"Preparing your video...\" notification is visible: " + isPreparingVisible);

            // Wait for the "Keep Impactyn open to finish posting.." notification
            WebElement finishingNotification = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(FinishingReviewNotificationLocator)
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
