package ImpactynTestCases;

import ImpactynPages.FeedPage;
import ImpactynPages.ProfilePage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class ImpactynEditProfileTest extends BaseTest {

    @BeforeMethod
    public void loginBeforeReviewTest() {
        handleInitialPopups();
        // Call the reusable login method from our BaseTest
        performLogin();
    }

    @Test(priority = 2, description = "Verify user can Edit his name and bio from Edit profile section")
    public void EditNameAndBio()  {
        SoftAssert softAssertion = new SoftAssert();

        // 1. Initialize the first page object
        FeedPage feedPage = new FeedPage(driver);

        /*Clicking on Profile Icon From nav bar*/
        feedPage.clickProfileButton();
        ProfilePage profilePage = feedPage.clickProfileButton();

        profilePage.clickEditProfileButton();
        profilePage.changeName("TestUser");
        profilePage.changeBio("This is a test bio");
        profilePage.clickSaveButton();
        softAssertion.assertTrue(profilePage.verifySuccessMessage(), "Profile update success message not displayed");
        softAssertion.assertEquals(profilePage.getNameText(), "TestUser", "Name not updated correctly");
        softAssertion.assertEquals(profilePage.getBioText(), "This is a test bio", "Bio not updated correctly");


        softAssertion.assertAll();
    }

    @Test(priority = 1, description = "Verify user can Edit his photo from Edit profile section")
    public void EditProfilePhoto() {
        SoftAssert softAssertion = new SoftAssert();

        // 1. Initialize the first page object
        FeedPage feedPage = new FeedPage(driver);

        /*Clicking on Profile Icon From nav bar*/
        feedPage.clickProfileButton();
        ProfilePage profilePage = feedPage.clickProfileButton();

        profilePage.clickEditProfileButton();
        profilePage.clickPhotoEditing();

        profilePage.allowPhotoAccess();
        profilePage.allowCameraAccess();
        profilePage.selectFromGallery();
        profilePage.choosePhoto();
        profilePage.confirmChosenPhoto();

        profilePage.clickSaveButton();


        softAssertion.assertTrue(profilePage.verifySuccessMessage(), "Profile update success message not displayed");
        softAssertion.assertAll();

    }

}