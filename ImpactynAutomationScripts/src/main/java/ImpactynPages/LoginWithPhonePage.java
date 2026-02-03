package ImpactynPages;

import ImpactynCore.BasePage;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;


public class LoginWithPhonePage extends BasePage {

    private By nextBtnLocator ;
    private By screenTitleLocator ;
    private By phoneNumberInputLocator;

    public LoginWithPhonePage(AppiumDriver driver) {
        super(driver);
        initializeLocators();
    }
    public void initializeLocators()
    {
        if (platform.is(Platform.ANDROID)) {
            String phoneNumberInputAutomatorString = "new UiSelector().className(\"android.widget.EditText\").instance(0)";

            phoneNumberInputLocator=AppiumBy.androidUIAutomator(phoneNumberInputAutomatorString);
            nextBtnLocator = By.xpath("//android.widget.TextView[@text=\"Next\"]");
            screenTitleLocator = By.xpath("//android.widget.TextView[@text=\"What's your number?\"]");
        }
        else if (platform.is(Platform.IOS))
        {
            phoneNumberInputLocator=By.xpath("//XCUIElementTypeTextField[@value=\"phone number\"]");
            nextBtnLocator=By.xpath("//XCUIElementTypeStaticText[@name=\"Next\"]");
            screenTitleLocator=AppiumBy.accessibilityId("What's your number?");
        }
    }
    public void enterPhoneNumber(String phoneNumber) {
        logger.info("Entering phone number: " + phoneNumber);
        WebElement phoneInput = wait.until(ExpectedConditions.visibilityOfElementLocated(phoneNumberInputLocator));
        phoneInput.sendKeys(phoneNumber);
    }

    public OTPPage clickNext() {
        logger.info("Clicking the 'Next' button.");
        WebElement nextButton = wait.until(ExpectedConditions.elementToBeClickable(nextBtnLocator));
        nextButton.click();
        return new OTPPage(driver);
    }

    public boolean isPageLoaded() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(screenTitleLocator)).isDisplayed();
    }

}
