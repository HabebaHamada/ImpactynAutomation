package ImpactynTestCases;

import ImpactynPages.*;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;

import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.DesiredCapabilities;

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

        // 1. Read a 'platform' variable from the command line. Defaults to 'android'.
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
                caps.setCapability("appium:deviceName", "iPhone 16 Pro Max");
                caps.setCapability("appium:platformVersion", "18.5");
                /*run "xcrun simctl list devices" to know the udid of the simulator devices*/
                caps.setCapability("appium:udid", "E89DBB85-21DB-42F5-BF3D-E796D7695D32");

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
    /**
     * This is a reusable login method that can be called by any test that needs it.
     * It's not a @Test itself, but a helper utility.
     */
    protected void performLogin() {

        // 1. Initialize the first page object
        LoginOptionsPage loginOptionsPage = new LoginOptionsPage(driver);

        // 2. Choose Google Account to complete login
        LoginWithGooglePage loginWithGoogle=loginOptionsPage.clickLoginWithGoogle();

        // 3. Navigate to the Home Page after login
        HomePage homePage=loginWithGoogle.clickChooseGoogleAccount();

        // Assert that the login was successful as a precondition check
        Assert.assertTrue(homePage.isPageLoaded(), "PRECONDITION FAILED: Could not log in before test.");
        System.out.println("--- PRE-TEST ACTION: Login Successful ---");
    }
}
