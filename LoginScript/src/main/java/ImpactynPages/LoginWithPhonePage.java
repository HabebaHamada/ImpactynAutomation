package ImpactynPages;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginWithPhonePage {

    private final AppiumDriver driver;
    private final WebDriverWait wait;

    public final By phoneNumberInputLocator = By.xpath("//m5.e1/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View[2]/android.widget.EditText[1]");
    public final By nextBtnLocator = By.xpath("//android.widget.TextView[@text=\"Next\"]");
    public final By screenTitleLocator = By.xpath("//android.widget.TextView[@text=\"What's your number?\"]");


    public LoginWithPhonePage(AppiumDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
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
