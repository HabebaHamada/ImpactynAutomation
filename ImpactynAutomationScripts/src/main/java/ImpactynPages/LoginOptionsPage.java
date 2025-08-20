package ImpactynPages;

import ImpactynCore.BasePage;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebElement;

public class LoginOptionsPage extends BasePage {

    String usePhoneEmailBtnLocator;
    String useFacebookBtnLocator;
    String useSnapchatBtnLocator;
    String useGoogleBtnLocator;

    public LoginOptionsPage(AppiumDriver driver) {
        super(driver);
        initializeLocators();
    }

    public void initializeLocators()
    {
        // 'platform' is inherited from BasePage
        if (platform.is(Platform.ANDROID)) {
            usePhoneEmailBtnLocator = "new UiSelector().className(\"android.widget.Button\").instance(0)";
            useFacebookBtnLocator = "new UiSelector().className(\"android.widget.Button\").instance(1)";
            useSnapchatBtnLocator = "new UiSelector().className(\"android.widget.Button\").instance(2)";
            useGoogleBtnLocator = "new UiSelector().className(\"android.widget.Button\").instance(3)";
        } else if (platform.is(Platform.IOS)) {
        }

    }
    // Public method to perform an action on this page
    public LoginWithPhonePage clickUsePhoneOrEmail() {
        System.out.println("Clicking on 'Use phone or email' button.");
        WebElement phoneEmailButton = driver.findElement(AppiumBy.androidUIAutomator(usePhoneEmailBtnLocator));
        phoneEmailButton.click();

        // Return the next page object to allow for a fluent interface
        return new LoginWithPhonePage(driver);
    }

    public LoginWithFacebookPage clickLoginWithFacebook(){
        System.out.println("Clicking on 'Continue With Facebook' button.");
        WebElement facebookButton = driver.findElement(AppiumBy.androidUIAutomator(useFacebookBtnLocator));
        facebookButton.click();

        // Return the next page object to allow for a fluent interface
        return new LoginWithFacebookPage(driver);
    }

   public LoginWithSnapchatPage clickLoginWithSnapchat(){
        System.out.println("Clicking on 'Continue With Snapchat' button.");
        WebElement snapchatButton = driver.findElement(AppiumBy.androidUIAutomator(useSnapchatBtnLocator));
        snapchatButton.click();

        // Return the next page object to allow for a fluent interface
        return new LoginWithSnapchatPage(driver);
    }

  public LoginWithGooglePage clickLoginWithGoogle(){
        System.out.println("Clicking on 'Continue With Google' button.");
        WebElement googleButton=driver.findElement(AppiumBy.androidUIAutomator(useGoogleBtnLocator));
        googleButton.click();

       // Return the next page object to allow for a fluent interface
        return new LoginWithGooglePage(driver);
    }
}
