package ImpactynPages;

import ImpactynCore.BasePage;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ProfilePage extends BasePage {

    private By EditProfileButtonLocator;
    private By EditProfileTextLocator;
    private By NameEditingTextFieldLocator;
    private By BioEditingTextFieldLocator;
    private By SaveButtonLocator;
    private By CloseButtonLocator;
    private By PhotoEditingLocator;
    private By SelectGalleryLocator;
    private By PhotoSelectedLocator;
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
        // 'platform' is inherited from BasePage
        if (platform.is(Platform.ANDROID)) {


        } else if (platform.is(Platform.IOS)) {

            EditProfileButtonLocator = AppiumBy.accessibilityId("Edit Profile");
            EditProfileTextLocator = By.xpath("(//XCUIElementTypeStaticText[@name=\"Edit Profile\"])[2]");
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
        return wait.until(ExpectedConditions.visibilityOfElementLocated(SuccessSavingMessageLocator)).isDisplayed();
    }
    public void clickPhotoEditing() {
        wait.until(ExpectedConditions.elementToBeClickable(PhotoEditingLocator)).click();
    }
    public void selectFromGallery() {
        wait.until(ExpectedConditions.elementToBeClickable(SelectGalleryLocator)).click();
    }
    public void choosePhoto() {
        wait.until(ExpectedConditions.elementToBeClickable(PhotoSelectedLocator)).click();
    }
    public void confirmChosenPhoto() {
        wait.until(ExpectedConditions.elementToBeClickable(ConfirmChosenPhotoLocator)).click();
    }

}
