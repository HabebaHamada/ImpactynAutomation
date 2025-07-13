package ImpactynPages;


import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginOptionsPage {

    private final AppiumDriver driver;
    private final WebDriverWait wait;

    private final By usePhoneEmailBtnLocator = By.xpath("//m5.e1/android.view.View/android.view.View/android.view.View/android.view.View[1]/android.widget.Button");
    private final By useFacebookBtnLocator = By.xpath("//m5.e1/android.view.View/android.view.View/android.view.View/android.view.View[2]/android.widget.Button");
    private final By useSnapchatBtnLocator = By.xpath("//m5.e1/android.view.View/android.view.View/android.view.View/android.view.View[3]/android.widget.Button");
    private final By useGoogleBtnLocator = By.xpath("//m5.e1/android.view.View/android.view.View/android.view.View/android.view.View[4]/android.widget.Button");

    public LoginOptionsPage(AppiumDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Public method to perform an action on this page
    public LoginWithPhonePage clickUsePhoneOrEmail() {
        System.out.println("Clicking on 'Use phone or email' button.");
        WebElement phoneEmailButton = wait.until(ExpectedConditions.elementToBeClickable(usePhoneEmailBtnLocator));
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

   /* public LoginWithSnapchatPage LoginWithSnapchat(){
        System.out.println("Clicking on 'Continue With Snapchat' button.");
        WebElement snapchatButton = wait.until(ExpectedConditions.elementToBeClickable(useSnapchatBtnLocator));
        snapchatButton.click();
        // Return the next page object to allow for a fluent interface
        return new LoginWithSnapchatPage(driver);

    }*/

   /* public LoginWithGooglePage LoginWithGoogle(){
        System.out.println("Clicking on 'Continue With Google' button.");
        WebElement googleButton = wait.until(ExpectedConditions.elementToBeClickable(useGoogleBtnLocator));
        googleButton.click();
        // Return the next page object to allow for a fluent interface
        return new LoginWithGooglePage(driver);

    }*/
}
