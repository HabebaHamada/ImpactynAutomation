package ImpactynTestCases;

import ImpactynPages.*;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.DesiredCapabilities;
import io.appium.java_client.ios.IOSDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;


import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;


public abstract class BaseTest {

    protected AppiumDriver driver;
    protected Platform platform;

    @BeforeMethod
    public void setup() throws MalformedURLException {

        // 2. Read a 'platform' variable from the command line. Defaults to 'android'.
        // Example to run for iOS: mvn test -Dplatform=ios
        String platform = System.getProperty("platform", "android").toLowerCase();

        DesiredCapabilities caps = new DesiredCapabilities();
        URL url = new URL("http://127.0.0.1:4723/"); // Appium server URL


        switch (platform) {
            case "android":
                this.platform = Platform.ANDROID;
                caps.setCapability("platformName", "Android");
                caps.setCapability("appium:platformVersion", "14.0");
                caps.setCapability("appium:deviceName", "emulator-5554");
                caps.setCapability("appium:automationName", "UiAutomator2");
                caps.setCapability("appium:avd", "Medium_Phone_API_35");
                caps.setCapability("appium:appPackage", "com.innov8eg.impactyn");
                caps.setCapability("appium:appActivity", "com.innov8eg.impactyn.MainActivity");

                // Ensure the app data is cleared before each run for a clean state
                caps.setCapability("appium:noReset", false);
                caps.setCapability("appium:enforceXPath1", true);
                // Create the specific driver for Android
                driver = new AndroidDriver(url, caps);
                break;

            case "ios":
                this.platform = Platform.IOS;

                caps.setCapability("platformName", "iOS");
                caps.setCapability("appium:automationName", "XCUITest");
                caps.setCapability("appium:deviceName", "iPhone 14");
                caps.setCapability("appium:platformVersion", "18.2");
                caps.setCapability("appium:udid", "F663422D-8B08-4BC7-82F9-54A853CA9E67");

                // For iOS, you typically use 'bundleId' instead of package/activity
                //caps.setCapability("appium:bundleId", "com.innov8.impactyn");
                caps.setCapability("appium:noReset", false);
                caps.setCapability("appium:app", "/Users/mostafa/Documents/ImpactynAutomation/Impactyn.app");
                // Create the specific driver for iOS
                driver = new IOSDriver(url, caps);
                break;

            default:
                throw new IllegalArgumentException("Invalid platform provided! Use 'android' or 'ios'.");
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @AfterMethod
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

    protected void handleInitialPopups() {
        if (this.platform.is(Platform.IOS))
        {
            System.out.println("--- PRE-TEST ACTION: Handling initial pop-ups ---");
            OnBoardingPage onboarding = new OnBoardingPage(driver);
            onboarding.handleSystemAlert_iOS();
        }
        else if (this.platform.is(Platform.ANDROID)) {
            System.out.println("--- PRE-TEST ACTION: Handling initial pop-ups ---");
            OnBoardingPage onboarding = new OnBoardingPage(driver);
            onboarding.handleSystemAlert_Android();
        }

    }


    /**
     * This is a reusable login method that can be called by any test that needs it.
     * It's not a @Test itself, but a helper utility.
     */
    protected void performLogin() {

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
