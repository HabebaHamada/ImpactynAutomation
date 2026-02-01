package ImpactynTestCases;

import ImpactynPages.*;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ImpactynLoginTest extends BaseTest  {

    @Test(priority = 2, description = "Verify login with Snapchat.")
    public void loginWithSnapchatTest() {

        System.out.println("Login with Snapchat Testcase Started : ");

        SystemAlertsPage alertsPage= new SystemAlertsPage(driver);
        alertsPage.handleInitialPopups();

        // 1. Initialize the first page object
        LoginOptionsPage loginOptionsPage = new LoginOptionsPage(driver);

        // 2. Perform actions using the page object methods
        // This line clicks the button and returns the next page object
        LoginWithSnapchatPage loginWithSnapchat = loginOptionsPage.clickLoginWithSnapchat();

        //3.Click continue with facebook
        HomePage homePage=loginWithSnapchat.clickContinueWithSnapchat();

        Assert.assertTrue(homePage.isPageLoaded(),"Home Page did not load successfully after Snapchat login.");

        System.out.println("Test execution completed successfully.");

    }

    @Test(priority = 1, description = "Verify login with Google.")
    public void loginWithGoogleTest() {

        System.out.println("Login with Google Testcase Started : ");

        SystemAlertsPage alertsPage= new SystemAlertsPage(driver);
        alertsPage.handleInitialPopups();

        // 1. Initialize the first page object
        LoginOptionsPage loginOptionsPage = new LoginOptionsPage(driver);

        // 2. Perform actions using the page object methods
        LoginWithGooglePage loginWithGoogle=loginOptionsPage.clickLoginWithGoogle();

        HomePage homePage=loginWithGoogle.clickChooseGoogleAccount();

        Assert.assertTrue(homePage.isPageLoaded(),"Home Page did not load successfully after Google login.");

        System.out.println("Test execution completed successfully.");

    }

//   @Test(priority = 3 , description = "Verify login with a valid phone number.")
//    public void loginWithPhoneNumberTest() {
//
//        System.out.println("Login with Phone Number Testcase Started : ");
//
//        SystemAlertsPage alertsPage= new SystemAlertsPage(driver);
//        alertsPage.handleInitialPopups();
//
//       // 1. Initialize the first page object
//        LoginOptionsPage loginOptionsPage = new LoginOptionsPage(driver);
//
//        // 2. Perform actions using the page object methods
//        // This line clicks the button and returns the next page object
//        LoginWithPhonePage loginWithPhonePage = loginOptionsPage.clickUsePhoneOrEmail();
//
//        // 3. Verify that we landed on the correct page
//        Assert.assertTrue(loginWithPhonePage.isPageLoaded(), "Did not navigate to the 'Get Started' page.");
//
//        loginWithPhonePage.enterPhoneNumber("1558689803");
//
//        // 4. Perform actions on the new page
//        OTPPage OTPPAGE= loginWithPhonePage.clickNext();
//
//        // 5. Verify that we landed on the correct page "OTP Page"
//        Assert.assertTrue(OTPPAGE.isPageLoaded(), "Did not navigate to the 'OTP Page' page.");
//
//        // 6. Perform actions on the new page
//        HomePage homePage= OTPPAGE.waitForManualOtpAndProceed();
//
//        // 7. Verify that Feed Screen appears by verifying that Nav Bar appears
//        Assert.assertTrue(homePage.isPageLoaded(),"Did not navigate to the 'Feed' page.");
//
//        System.out.println("Test execution completed successfully.");
//
//    }


//    @Test(priority = 3 , description = "Verify login with Facebook.")
//    public void loginWithFacebookTest() {
//
//        System.out.println("Login with Facebook Testcase Started : ");
//
//        SystemAlertsPage alertsPage= new SystemAlertsPage(driver);
//        alertsPage.handleInitialPopups();
//
//        // 1. Initialize the first page object
//        LoginOptionsPage loginOptionsPage = new LoginOptionsPage(driver);
//
//        // 2. Perform actions using the page object methods
//        // This line clicks the button and returns the next page object
//        LoginWithFacebookPage loginWithFacebook = loginOptionsPage.clickLoginWithFacebook();
//
//        //3.Click continue with facebook
//        HomePage homePage =loginWithFacebook.clickContinueWithFacebook();
//
//        Assert.assertTrue(homePage.isPageLoaded(),"Home Page did not load successfully after Facebook login.");
//
//        System.out.println("Test execution completed successfully.");
//
//    }
}