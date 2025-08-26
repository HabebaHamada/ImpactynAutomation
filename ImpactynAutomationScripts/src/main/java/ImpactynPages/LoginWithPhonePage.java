package ImpactynPages;

import ImpactynCore.BasePage;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;


public class LoginWithPhonePage extends BasePage {


    String phoneNumberInputAutomatorString = "new UiSelector().className(\"android.widget.EditText\").instance(0)";
    private final By phoneNumberInputLocator=AppiumBy.androidUIAutomator(phoneNumberInputAutomatorString);
    private final By nextBtnLocator = By.xpath("//android.widget.TextView[@text=\"Next\"]");
    private final By screenTitleLocator = By.xpath("//android.widget.TextView[@text=\"What's your number?\"]");

    public LoginWithPhonePage(AppiumDriver driver) {
        super(driver);
    }

    public void enterPhoneNumber(String phoneNumber) {
        System.out.println("Entering phone number: " + phoneNumber);
        WebElement phoneInput = wait.until(ExpectedConditions.visibilityOfElementLocated(phoneNumberInputLocator));
        phoneInput.sendKeys(phoneNumber);
    }

    public OTPPage clickNext() {
        System.out.println("Clicking the 'Next' button.");
        WebElement nextButton = wait.until(ExpectedConditions.elementToBeClickable(nextBtnLocator));
        nextButton.click();
        return new OTPPage(driver);
    }

    public boolean isPageLoaded() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(screenTitleLocator)).isDisplayed();
    }

}
