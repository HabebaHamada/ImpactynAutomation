package ImpactynPages;

import ImpactynCore.BasePage;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginOptionsPage extends BasePage {

    private By usePhoneEmailBtnLocator;
    private By useFacebookBtnLocator;
    private By useSnapchatBtnLocator;
    private By useGoogleBtnLocator;

    public LoginOptionsPage(AppiumDriver driver) {
        super(driver);
        initializeLocators();
    }
    public void initializeLocators()
    {
        if (platform.is(Platform.ANDROID)) {
            String usePhoneEmailBtnAutomatorString = "new UiSelector().className(\"android.widget.Button\").instance(0)";
            String useFacebookBtnAutomatorString = "new UiSelector().className(\"android.widget.Button\").instance(1)";
            String useSnapchatBtnAutomatorString = "new UiSelector().className(\"android.widget.Button\").instance(2)";
            String useGoogleBtnAutomatorString = "new UiSelector().className(\"android.widget.Button\").instance(3)";

            usePhoneEmailBtnLocator=AppiumBy.androidUIAutomator(usePhoneEmailBtnAutomatorString);
            useFacebookBtnLocator=AppiumBy.androidUIAutomator(useFacebookBtnAutomatorString);
            useSnapchatBtnLocator=AppiumBy.androidUIAutomator(useSnapchatBtnAutomatorString);
            useGoogleBtnLocator=AppiumBy.androidUIAutomator(useGoogleBtnAutomatorString);

        } else if (platform.is(Platform.IOS)) {
            usePhoneEmailBtnLocator=AppiumBy.accessibilityId("Use phone or email");
            useFacebookBtnLocator=AppiumBy.accessibilityId("Continue with Facebook");
            useSnapchatBtnLocator=AppiumBy.accessibilityId("Continue with Snapchat");
            useGoogleBtnLocator=AppiumBy.accessibilityId("Continue with Google");
        }
    }

    public LoginWithPhonePage clickUsePhoneOrEmail() {
        logger.info("Clicking on 'Use phone or email' button.");
        WebElement phoneEmailButton =  wait.until(ExpectedConditions.elementToBeClickable(usePhoneEmailBtnLocator));
        phoneEmailButton.click();

        // Return the next page object to allow for a fluent interface
        return new LoginWithPhonePage(driver);
    }

    public LoginWithFacebookPage clickLoginWithFacebook(){
        logger.info("Clicking on 'Continue With Facebook' button.");
        WebElement facebookButton = wait.until(ExpectedConditions.elementToBeClickable(useFacebookBtnLocator));
        facebookButton.click();

        if (this.platform.is(Platform.IOS))
        {
            confirmSignIn();
        }

        // Return the next page object to allow for a fluent interface
        return new LoginWithFacebookPage(driver);
    }

   public LoginWithSnapchatPage clickLoginWithSnapchat(){
        logger.info("Clicking on 'Continue With Snapchat' button.");
        WebElement snapchatButton = wait.until(ExpectedConditions.elementToBeClickable(useSnapchatBtnLocator));
        snapchatButton.click();

       if (this.platform.is(Platform.IOS))
       {
           confirmSignIn();
       }

        // Return the next page object to allow for a fluent interface
        return new LoginWithSnapchatPage(driver);
    }

  public LoginWithGooglePage clickLoginWithGoogle(){
        logger.info("Clicking on 'Continue With Google' button.");
        WebElement googleButton= wait.until(ExpectedConditions.elementToBeClickable(useGoogleBtnLocator));
        googleButton.click();

        if (this.platform.is(Platform.IOS))
        {
            confirmSignIn();
        }

       // Return the next page object to allow for a fluent interface
        return new LoginWithGooglePage(driver);
    }

    /*this method is used only for iOS system alert handling */
    private void confirmSignIn() {
        logger.info("Handling iOS system alert to confirm sign-in...");

        try {
            // Step 1: Wait for the alert to be present on the screen.
            // This is a crucial step to handle any small delays.
            wait.until(ExpectedConditions.alertIsPresent());

            // Step 2: Switch the driver's focus to the alert.
            Alert systemAlert = driver.switchTo().alert();

            // Step 3: You can get the text for verification (optional but good for debugging)
            String alertText = systemAlert.getText();
            logger.info("Found system alert with text: " + alertText);

            // Step 4: Accept the alert. This will click the default "Continue" button.
            systemAlert.accept();

            logger.info("System alert accepted.");

        } catch (Exception e) {
            logger.warning("Failed to handle the system alert.");
            throw e;
        }
    }
}
