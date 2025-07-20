package ImpactynTestCases;

import ImpactynPages.*;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;



public class ImpactynLoginTest extends BaseTest  {

    @BeforeMethod
    public void initializeSettings()
    {

        OnBoardingPage onboardingPage = new OnBoardingPage(driver);
        onboardingPage.handleOnboardingFlow();
    }


   @Test(priority = 4)
    public void loginWithPhoneNumberTest() {

        System.out.println("Login with Phone Number Testcase Started : ");

        // 1. Initialize the first page object
        LoginOptionsPage loginOptionsPage = new LoginOptionsPage(driver);

        // 2. Perform actions using the page object methods
        // This line clicks the button and returns the next page object
        LoginWithPhonePage loginWithPhonePage = loginOptionsPage.clickUsePhoneOrEmail();

        // 3. Verify that we landed on the correct page
        Assert.assertTrue(loginWithPhonePage.isPageLoaded(), "Did not navigate to the 'Get Started' page.");

        loginWithPhonePage.enterPhoneNumber("1558689803");

        // 4. Perform actions on the new page
        OTPPage OTPPAGE= loginWithPhonePage.clickNext();

        // 5. Verify that we landed on the correct page "OTP Page"
        Assert.assertTrue(OTPPAGE.isPageLoaded(), "Did not navigate to the 'OTP Page' page.");

        // 6. Perform actions on the new page
        FeedPage feedPage= OTPPAGE.waitForManualOtpAndProceed();

        // 7. Verify that Feed Screen appears by verifying that Nav Bar appears
        Assert.assertTrue(feedPage.isPageLoaded(),"Did not navigate to the 'Feed' page.");

        System.out.println("Test execution completed successfully.");

    }


    @Test(priority = 1)
    public void loginWithFacebookTest() {

        System.out.println("Login with Facebook Testcase Started : ");

        // 1. Initialize the first page object
        LoginOptionsPage loginOptionsPage = new LoginOptionsPage(driver);

        // 2. Perform actions using the page object methods
        // This line clicks the button and returns the next page object
        LoginWithFacebookPage loginWithFacebook = loginOptionsPage.clickLoginWithFacebook();

        //3.Click continue with facebook
        FeedPage feedPage=loginWithFacebook.clickContinueWithFacebook();

        Assert.assertTrue(feedPage.isPageLoaded(),"Did not navigate to the 'Feed' page.");

        System.out.println("Test execution completed successfully.");

    }

    @Test(priority = 2)
    public void loginWithSnapchatTest() {

        System.out.println("Login with Snapchat Testcase Started : ");

        // 1. Initialize the first page object
        LoginOptionsPage loginOptionsPage = new LoginOptionsPage(driver);

        // 2. Perform actions using the page object methods
        // This line clicks the button and returns the next page object
        LoginWithSnapchatPage loginWithSnapchat = loginOptionsPage.clickLoginWithSnapchat();

        //3.Click continue with facebook
        FeedPage feedPage=loginWithSnapchat.clickContinueWithSnapchat();

        Assert.assertTrue(feedPage.isPageLoaded(),"Did not navigate to the 'Feed' page.");

        System.out.println("Test execution completed successfully.");

    }

    @Test(priority = 3)
    public void loginWithGoogleTest() {

        System.out.println("Login with Google Testcase Started : ");

        // 1. Initialize the first page object
        LoginOptionsPage loginOptionsPage = new LoginOptionsPage(driver);

        // 2. Perform actions using the page object methods
        // This line clicks the button
        LoginWithGooglePage loginWithGoogle=loginOptionsPage.clickLoginWithGoogle();

        FeedPage feedPage=loginWithGoogle.clickChooseGoogleAccount();

        Assert.assertTrue(feedPage.isPageLoaded(),"Did not navigate to the 'Feed' page.");

        System.out.println("Test execution completed successfully.");

    }
}