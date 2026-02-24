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


import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Base64;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.IOUtils;


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

    /**
     * Pushes a local file from the project resources to the iOS Simulator's media library.
     */
    protected void addFileToSimulatorPhotos(String resourcePath) {
        System.out.println("Adding file '" + resourcePath + "' to Simulator using 'mobile: pushFile'...");

        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(resourcePath)) {

            if (inputStream == null) {
                throw new RuntimeException("File not found in resources. Ensure the path is correct: " + resourcePath);
            }

            // Step 1: Read the file into a byte array and Base64 encode it.
            // This command expects the payload to be a Base64 string.
            byte[] fileBytes = IOUtils.toByteArray(inputStream);
            String base64String = Base64.getEncoder().encodeToString(fileBytes);

            // Step 2: Construct the remote path using the correct Photos app bundle ID.
            String fileName = new File(resourcePath).getName();
            // --- STEP 2: PUSH THE FILE TO THE APP'S WRITABLE CONTAINER ---
            // The 'Documents' folder is a standard, writable location inside any app's sandbox.
            String appContainerPath = "Documents/" + fileName;

            Map<String, Object> pushArgs = new HashMap<>();
            // We don't use the '@' prefix here. The path is relative to the app's root.
            pushArgs.put("remotePath", appContainerPath);
            pushArgs.put("payload", base64String);

            System.out.println("Executing 'mobile: pushFile' to app container: " + appContainerPath);
            driver.executeScript("mobile: pushFile", pushArgs);

//            // --- STEP 3: SAVE THE PUSHED FILE TO THE CAMERA ROLL ---
//            // This command tells the system to take the file we just saved
//            // and officially import it into the Photos app.
//            Map<String, Object> saveArgs = new HashMap<>();
//            // The path here is the full path inside the app container, which Appium knows how to find.
//            saveArgs.put("filePath", appContainerPath);
//
//            System.out.println("Executing 'mobile: saveFileToCameraRoll'...");
//            driver.executeScript("mobile: saveFileToCameraRoll", saveArgs);

            System.out.println("Successfully saved file to the simulator's camera roll.");

        } catch (IOException e) {
            throw new RuntimeException("Error reading or adding file to simulator: " + e.getMessage(), e);
        }
    }
}
