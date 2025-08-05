package ImpactynPages;


import io.appium.java_client.AppiumDriver;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.WebElement;

public class LoginOptionsPage {

    private final AppiumDriver driver;

    public LoginOptionsPage(AppiumDriver driver) {
        this.driver = driver;
    }

    // Public method to perform an action on this page
    public LoginWithPhonePage clickUsePhoneOrEmail() {
        System.out.println("Clicking on 'Use phone or email' button.");
        String usePhoneEmailBtnLocator = "new UiSelector().className(\"android.widget.Button\").instance(0)";
        WebElement phoneEmailButton = driver.findElement(AppiumBy.androidUIAutomator(usePhoneEmailBtnLocator));
        phoneEmailButton.click();

        // Return the next page object to allow for a fluent interface
        return new LoginWithPhonePage(driver);
    }

    public LoginWithFacebookPage clickLoginWithFacebook(){
        System.out.println("Clicking on 'Continue With Facebook' button.");
        String useFacebookBtnLocator = "new UiSelector().className(\"android.widget.Button\").instance(1)";
        WebElement facebookButton = driver.findElement(AppiumBy.androidUIAutomator(useFacebookBtnLocator));
        facebookButton.click();

        // Return the next page object to allow for a fluent interface
        return new LoginWithFacebookPage(driver);
    }

   public LoginWithSnapchatPage clickLoginWithSnapchat(){
        System.out.println("Clicking on 'Continue With Snapchat' button.");
       String useSnapchatBtnLocator = "new UiSelector().className(\"android.widget.Button\").instance(2)";
       WebElement snapchatButton = driver.findElement(AppiumBy.androidUIAutomator(useSnapchatBtnLocator));
        snapchatButton.click();

        // Return the next page object to allow for a fluent interface
        return new LoginWithSnapchatPage(driver);
    }

  public LoginWithGooglePage clickLoginWithGoogle(){
        System.out.println("Clicking on 'Continue With Google' button.");
      String useGoogleBtnLocator = "new UiSelector().className(\"android.widget.Button\").instance(3)";
      WebElement googleButton=driver.findElement(AppiumBy.androidUIAutomator(useGoogleBtnLocator));
       googleButton.click();

       // Return the next page object to allow for a fluent interface
        return new LoginWithGooglePage(driver);
    }
}
