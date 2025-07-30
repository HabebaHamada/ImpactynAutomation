package ImpactynTestCases;

import ImpactynPages.*;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;


import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;


public class BaseTest {

    public AppiumDriver driver;
    public WebDriverWait wait;

    private static DesiredCapabilities getDesiredCapabilities() {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("platformName", "Android");
        caps.setCapability("appium:platformVersion", "14.0");

        caps.setCapability("appium:deviceName", "emulator-5554");
        caps.setCapability("appium:automationName", "UiAutomator2");
        caps.setCapability("appium:avd", "Medium_Phone");


        caps.setCapability("appium:appPackage", "com.innov8eg.impactyn");
        caps.setCapability("appium:appActivity", "com.innov8eg.impactyn.MainActivity");

        // Ensure the app data is cleared before each run for a clean state
        caps.setCapability("appium:noReset", false);
        return caps;
    }

    @BeforeMethod
    public void setup() {
        DesiredCapabilities caps = getDesiredCapabilities();

        URL url;
        try {
            url = new URL("http://127.0.0.1:4723/");
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }

        driver = new AndroidDriver(url, caps);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @AfterMethod
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

    /**
     * This is a reusable login method that can be called by any test that needs it.
     * It's not a @Test itself, but a helper utility.
     */
    protected void performLogin() {
        System.out.println("--- PRE-TEST ACTION: Performing login ---");
        OnBoardingPage onboarding = new OnBoardingPage(driver);
        onboarding.handleOnboardingFlow();

        // 1. Initialize the first page object
        LoginOptionsPage loginOptionsPage = new LoginOptionsPage(driver);

        // 2. Perform actions using the page object methods
        // This line clicks the button
        LoginWithGooglePage loginWithGoogle=loginOptionsPage.clickLoginWithGoogle();

        FeedPage feedPage=loginWithGoogle.clickChooseGoogleAccount();
        // Assert that the login was successful as a precondition check
        Assert.assertTrue(feedPage.isPageLoaded(), "PRECONDITION FAILED: Could not log in before test.");
        System.out.println("--- PRE-TEST ACTION: Login Successful ---");
    }


}
