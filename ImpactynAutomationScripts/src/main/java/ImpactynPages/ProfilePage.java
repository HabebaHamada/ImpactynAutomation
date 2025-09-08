package ImpactynPages;

import ImpactynCore.BasePage;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.util.HashMap;
import java.util.Map;

public class ProfilePage extends BasePage {

    private By EditProfileButtonLocator;
    private By NameEditingTextFieldLocator;
    private By BioEditingTextFieldLocator;
    private By SaveButtonLocator;
    private By CloseButtonLocator;
    private By PhotoEditingLocator;
    private By SelectGalleryLocator;
    private By PhotoSelectedLocator;
    private By CropButtonLocator;
    private By ConfirmChosenPhotoLocator;
    private By NameTextLocator;
    private By BioTextLocator;
    private By SuccessSavingMessageLocator;

    public ProfilePage(AppiumDriver driver) {
        super(driver);
        initializeLocators();
    }

    void initializeLocators()
    {
        if (platform.is(Platform.ANDROID)) {

            EditProfileButtonLocator = By.xpath("//android.widget.TextView[@text=\"Edit Profile\"]");
            NameEditingTextFieldLocator = AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.EditText\").instance(0)");
            BioEditingTextFieldLocator = AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.EditText\").instance(1)");
            SaveButtonLocator = AppiumBy.androidUIAutomator("new UiSelector().text(\"Save\")");
            CloseButtonLocator = AppiumBy.className("android.widget.Button");
            PhotoEditingLocator = AppiumBy.androidUIAutomator("new UiSelector().className(\"android.view.View\").instance(3)");
            SelectGalleryLocator = AppiumBy.androidUIAutomator("new UiSelector().text(\"Gallery\")");
            PhotoSelectedLocator = AppiumBy.id("com.google.android.providers.media.module:id/icon_thumbnail");
            CropButtonLocator = By.xpath("//android.widget.Button[@resource-id=\"com.innov8eg.impactyn:id/crop_image_menu_crop\"]");
            NameTextLocator = By.xpath("//android.widget.TextView[contains(@text, 'Followers')]/preceding-sibling::android.widget.TextView[2]");
            BioTextLocator = By.xpath("//android.widget.TextView[contains(@text, 'Followers')]/following-sibling::android.widget.TextView[1]");
            SuccessSavingMessageLocator = By.xpath("//android.widget.Toast[1]");

        } else if (platform.is(Platform.IOS)) {

            EditProfileButtonLocator = AppiumBy.accessibilityId("Edit Profile");
            NameEditingTextFieldLocator = By.xpath("//XCUIElementTypeOther/XCUIElementTypeTextField[1]");
            BioEditingTextFieldLocator = By.xpath("//XCUIElementTypeOther/XCUIElementTypeTextField[2]");
            SaveButtonLocator = AppiumBy.accessibilityId("Save");
            CloseButtonLocator = By.xpath("//XCUIElementTypeButton[@name=\"Close\"]");
            PhotoEditingLocator = By.xpath("(//XCUIElementTypeImage[@name=\"edit\"])[2]");
            SelectGalleryLocator = By.xpath("//XCUIElementTypeButton[@name=\"PHOTOS\"]");
            PhotoSelectedLocator = By.xpath("//XCUIElementTypeScrollView/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeOther/XCUIElementTypeCollectionView/XCUIElementTypeCell[1]/XCUIElementTypeOther/XCUIElementTypeImage");
            ConfirmChosenPhotoLocator = By.xpath("(//XCUIElementTypeStaticText[@name=\"Done\"])[1]");
            NameTextLocator = By.xpath("//XCUIElementTypeStaticText[contains(@name, 'Followers')]/preceding-sibling::XCUIElementTypeStaticText[1]");
            BioTextLocator = By.xpath("//XCUIElementTypeOther/XCUIElementTypeStaticText[3]"); // or //XCUIElementTypeStaticText[contains(@name, 'Followers')]/following-sibling::XCUIElementTypeStaticText[1]
            SuccessSavingMessageLocator = AppiumBy.accessibilityId(", Profile updated successfully!");
        }

    }

    public void clickEditProfileButton() {
        wait.until(ExpectedConditions.elementToBeClickable(EditProfileButtonLocator)).click();
    }

    public void changeName(String newName) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(NameEditingTextFieldLocator)).clear();
        wait.until(ExpectedConditions.visibilityOfElementLocated(NameEditingTextFieldLocator)).sendKeys(newName);
    }

    public String getNameText() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(NameTextLocator)).getText();
    }

    public void changeBio(String newBio) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(BioEditingTextFieldLocator)).clear();
        wait.until(ExpectedConditions.visibilityOfElementLocated(BioEditingTextFieldLocator)).sendKeys(newBio);
    }

    public String getBioText() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(BioTextLocator)).getText();
    }

    public void clickSaveButton() {
        wait.until(ExpectedConditions.elementToBeClickable(SaveButtonLocator)).click();
    }
    public void clickCloseButton() {
        wait.until(ExpectedConditions.elementToBeClickable(CloseButtonLocator)).click();
    }

    public boolean verifySuccessMessage()
    {
        if (this.platform.is(Platform.IOS)) {
            System.out.println("Waiting for the success toast message to appear...");

            return wait.until(ExpectedConditions.visibilityOfElementLocated(SuccessSavingMessageLocator)).isDisplayed();
        } else {
            System.out.println("Waiting for the success toast message to appear...");

            // For Android Toasts, we wait for PRESENCE in the DOM.
            WebElement toastElement = wait.until(ExpectedConditions.presenceOfElementLocated(SuccessSavingMessageLocator));

            // Optional but highly recommended: Log the toast text for better debugging.
            String toastText = toastElement.getText();
            System.out.println("Found toast message with text: " + toastText);

            // If the line above was successful without throwing an exception,
            // it means the element was found. We can now confidently return true.
            return true;
        }

    }

    public void clickPhotoEditing() {
        wait.until(ExpectedConditions.elementToBeClickable(PhotoEditingLocator)).click();
    }

    public void allowPhotoAccess() {
        if (this.platform.is(Platform.IOS)) {
            System.out.println("Attempting to handle iOS system alert for photo access...");
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
                        java.util.Map<String, String> params = new java.util.HashMap<>();
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

    public void allowCameraAccess()
    {
        if (this.platform.is(Platform.IOS)) {
            System.out.println("Handling iOS system alert to Allow Camera...");

            try {
                // Step 1: Wait for the alert to be present on the screen.
                // This is a crucial step to handle any small delays.
                wait.until(ExpectedConditions.alertIsPresent());

                // Step 2: Switch the driver's focus to the alert.
                Alert systemAlert = driver.switchTo().alert();

                // Step 3: You can get the text for verification (optional but good for debugging)
                String alertText = systemAlert.getText();
                System.out.println("Found system alert with text: " + alertText);

                // Step 4: Accept the alert. This will click the default "Continue" button.
                systemAlert.accept();

                System.out.println("System alert accepted.");

            } catch (Exception e) {
                System.err.println("Failed to handle the system alert.");
                throw e;
            }
            System.out.println("System alert confirmed.");
        }
    }

    public void selectFromGallery() {
        wait.until(ExpectedConditions.elementToBeClickable(SelectGalleryLocator)).click();
    }
    public void choosePhoto() {
        wait.until(ExpectedConditions.elementToBeClickable(PhotoSelectedLocator)).click();
    }
    public void confirmChosenPhoto() {
        if (this.platform.is(Platform.IOS)) {
            wait.until(ExpectedConditions.elementToBeClickable(ConfirmChosenPhotoLocator)).click();
        }
        else if (this.platform.is(Platform.ANDROID))
        {
            wait.until(ExpectedConditions.elementToBeClickable(CropButtonLocator)).click();
        }
    }

}
