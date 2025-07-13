package ImpactynPages;


import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginWithGooglePage {

    private final AppiumDriver driver;
    private final WebDriverWait wait;

    public final By ChooseGoogleAccountBtnLocator= By.xpath("//android.widget.Button[@resource-id=\"com.android.chrome:id/signin_fre_continue_button\"]");

    public LoginWithGooglePage(AppiumDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void clickChooseGoogleAccount() {
        System.out.println("Clicking the 'Continue as Email' button.");
        WebElement ChooseGoogleAccountButton = wait.until(ExpectedConditions.elementToBeClickable(ChooseGoogleAccountBtnLocator));
        ChooseGoogleAccountButton.click();
    }



}
