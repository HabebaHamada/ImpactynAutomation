package ImpactynPages;

import ImpactynCore.BasePage;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.HashMap;
import java.util.Map;

public class UploadReviewPage extends BasePage {

    private By MentionBrandLocator;
    private By AllowRecordingSettingsLocator;
    private By ShareButtonLocator;
    private By ProgressBarLocator;
    private By BrandsSuggestionBarLocator;
    private By BrandSelectionLocator;
    private By RatingSliderLocator;
    private By FlipCameraLocator;
    private By NextAccessibilityLocator;
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
            AllowRecordingSettingsLocator = By.id("com.android.permissioncontroller:id/permission_allow_foreground_only_button");
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
            BrandsSuggestionBarLocator = By.xpath("(//XCUIElementTypeOther[@name=\"Horizontal scroll bar, 3 pages\"])[2]");
            BrandSelectionLocator = By.xpath("//XCUIElementTypeCollectionView/XCUIElementTypeCell[1]/XCUIElementTypeOther/XCUIElementTypeImage");
            RatingSliderLocator = AppiumBy.className("XCUIElementTypeSlider");
            ShareButtonLocator = By.xpath("//XCUIElementTypeButton[@name=\"Share\"]");
            FlipCameraLocator = AppiumBy.accessibilityId("flip");
            NextAccessibilityLocator = AppiumBy.accessibilityId("AccessibilityIdentifiers.coachMarkNext");

            ChooseFromGalleryLocator = By.xpath("//XCUIElementTypeImage[@name='feed_selected']/..");
            ChooseVideoLocator = By.xpath("//XCUIElementTypeCell/XCUIElementTypeOther[1]/XCUIElementTypeImage");
            ConfirmChosenVideoLocator = By.xpath("//XCUIElementTypeStaticText[@name=\"Done\"]");
            PreparationReviewNotificationLocator = AppiumBy.accessibilityId("Preparing your video...");
            FinishingReviewNotificationLocator = AppiumBy.accessibilityId("Keep Impactyn open to finish posting..");
        }
    }

    public void allowRecordingSettings() {
        if (this.platform.is(Platform.ANDROID)) {
            /*Allow Video and Sound Settings For Recording*/
            WebElement AllowRecording = wait.until(ExpectedConditions.elementToBeClickable(AllowRecordingSettingsLocator));
            AllowRecording.click();
        } else if (this.platform.is(Platform.IOS)) {
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
                System.out.println("No more coach marks to handle.");
            }
        }
    }

    public void startCameraRecording(int reviewDurationInSeconds) throws InterruptedException {
        /*click the record button to start recording*/
        WebElement ProgressBar = wait.until(
                ExpectedConditions.visibilityOfElementLocated(ProgressBarLocator)
        );
        ProgressBar.click();

        System.out.println("Recording for " + reviewDurationInSeconds * 1000 + " milliseconds...");
        Thread.sleep(reviewDurationInSeconds * 1000L);

        /*click the progress bar to stop recording*/
        wait.until(
                ExpectedConditions.visibilityOfElementLocated(ProgressBarLocator)
        ).click();
    }

    public void mentionBrand(String Brand) throws InterruptedException {
        System.out.println("Entering BRGR Brand...");

        WebElement mentionBrandTextField = wait.until(ExpectedConditions.visibilityOfElementLocated(MentionBrandLocator));

        mentionBrandTextField.click();
        mentionBrandTextField.clear(); // Ensure field is empty
        Thread.sleep(5000); // Wait for 5 seconds to ensure the field is ready
        for (char c : (Brand).toCharArray()) {
            mentionBrandTextField.sendKeys(Character.toString(c));
            Thread.sleep(500); // Adjust delay as needed
        }
        Thread.sleep(5000); // Wait for 5 seconds to ensure the field is ready

        System.out.println("waiting for brandsSuggestionBar...");


        wait.until(
                ExpectedConditions.visibilityOfElementLocated(BrandsSuggestionBarLocator)
        );

        System.out.println("waiting for brandSelection...");

        /*Select the Brand*/
        WebElement brandSelection = wait.until(
                ExpectedConditions.visibilityOfElementLocated(BrandSelectionLocator)
        );
        brandSelection.click();

    }

    public void setReviewRating() {
        WebElement RatingSlider = wait.until(
                ExpectedConditions.visibilityOfElementLocated(RatingSliderLocator)
        );
        if (platform.is(Platform.IOS)) {
            // Set the slider value using JavaScript for XCUIElementTypeSlider
            RatingSlider.sendKeys("0.5"); // Set to 50%, adjust as needed
        } else {
            // For Android SeekBar, set the value using sendKeys
            RatingSlider.sendKeys("5.5"); // Set to 50%, adjust as needed
        }
    }

    public void shareReview() {
        /*click share button*/
        WebElement ShareButton = wait.until(ExpectedConditions.visibilityOfElementLocated(ShareButtonLocator));
        ShareButton.click();

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

        allowGalleryAccess();

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

    private void allowGalleryAccess() {
        if (this.platform.is(Platform.IOS)) {
            System.out.println("Attempting to handle iOS system alert for gallery access...");
            boolean alertHandled = false;
            for (int i = 0; i < 5; i++) {
                try {
                    // Wait briefly before each attempt
                    Thread.sleep(1000);

                    try {
                        // Check if alert exists first
                        driver.switchTo().alert();

                        // Use different approach for iOS alert
                        Map<String, Object> params = new HashMap<>();
                        params.put("action", "accept");
                        params.put("buttonLabel", "Allow Full Access");
                        driver.executeScript("mobile:alert", params);

                        System.out.println("Successfully clicked Allow Full Access button");
                        alertHandled = true;
                        break;
                    } catch (Exception alertEx) {
                        // Try alternative method
                        Map<String, String> params = new HashMap<>();
                        params.put("action", "accept");
                        driver.executeScript("mobile: acceptAlert", params);

                        System.out.println("Successfully handled alert using alternative method");
                        alertHandled = true;
                    }
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
