package ImpactynPages;

import ImpactynCore.BasePage;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginOptionsPage extends BasePage {

    By usePhoneEmailBtnLocator;
    By useFacebookBtnLocator;
    By useSnapchatBtnLocator;
    By useGoogleBtnLocator;

    public LoginOptionsPage(AppiumDriver driver) {
        super(driver);
        initializeLocators();
    }

    public void initializeLocators()
    {
        // 'platform' is inherited from BasePage
        if (platform.is(Platform.ANDROID)) {
            String   usePhoneEmailBtnAutomatorString = "new UiSelector().className(\"android.widget.Button\").instance(0)";
            String   useFacebookBtnAutomatorString = "new UiSelector().className(\"android.widget.Button\").instance(1)";
            String   useSnapchatBtnAutomatorString = "new UiSelector().className(\"android.widget.Button\").instance(2)";
            String   useGoogleBtnAutomatorString = "new UiSelector().className(\"android.widget.Button\").instance(3)";

             usePhoneEmailBtnLocator=AppiumBy.androidUIAutomator(usePhoneEmailBtnAutomatorString);
             useFacebookBtnLocator=AppiumBy.androidUIAutomator(useFacebookBtnAutomatorString);
             useSnapchatBtnLocator=AppiumBy.androidUIAutomator(useSnapchatBtnAutomatorString);
             useGoogleBtnLocator=AppiumBy.androidUIAutomator(useGoogleBtnAutomatorString);

        } else if (platform.is(Platform.IOS)) {
        }

    }
    // Public method to perform an action on this page
    public LoginWithPhonePage clickUsePhoneOrEmail() {
        System.out.println("Clicking on 'Use phone or email' button.");
        WebElement phoneEmailButton =  wait.until(ExpectedConditions.elementToBeClickable(usePhoneEmailBtnLocator));
        phoneEmailButton.click();

        // Return the next page object to allow for a fluent interface
        return new LoginWithPhonePage(driver);
    }

    public LoginWithFacebookPage clickLoginWithFacebook(){
        System.out.println("Clicking on 'Continue With Facebook' button.");
        WebElement facebookButton = wait.until(ExpectedConditions.elementToBeClickable(useFacebookBtnLocator));
        facebookButton.click();

        // Return the next page object to allow for a fluent interface
        return new LoginWithFacebookPage(driver);
    }

   public LoginWithSnapchatPage clickLoginWithSnapchat(){
        System.out.println("Clicking on 'Continue With Snapchat' button.");
        WebElement snapchatButton = wait.until(ExpectedConditions.elementToBeClickable(useSnapchatBtnLocator));
        snapchatButton.click();

        // Return the next page object to allow for a fluent interface
        return new LoginWithSnapchatPage(driver);
    }

  public LoginWithGooglePage clickLoginWithGoogle(){
        System.out.println("Clicking on 'Continue With Google' button.");
        WebElement googleButton= wait.until(ExpectedConditions.elementToBeClickable(useGoogleBtnLocator));
        googleButton.click();

       // Return the next page object to allow for a fluent interface
        return new LoginWithGooglePage(driver);
    }
}
