package ImpactynTestCases;

import ImpactynPages.EarnPage;
import ImpactynPages.FeedPage;
import ImpactynPages.UploadReviewPage;
import org.openqa.selenium.Platform;
import org.testng.asserts.SoftAssert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class ImpactynUploadReviewTest extends BaseTest
{

    @BeforeMethod
    public void loginBeforeReviewTest() {
        handleInitialPopups();
        // Call the reusable login method from our BaseTest
        performLogin();
    }

    @Test (priority = 4 , description = "Verify user can upload a new review from Back camera")
    public void UploadBackCameraReview() throws InterruptedException {

        SoftAssert softAssertion = new SoftAssert();

        // 1. Initialize the first page object
        FeedPage feedPage = new FeedPage(driver);

        /*Clicking on Plus Icon From nav bar*/
        UploadReviewPage uploadReview=feedPage.clickRecordReview();

        uploadReview.allowRecordingSettings();

        /*Camera Recording for 20 seconds*/
        uploadReview.startCameraRecording(20);

        /*mention Brand*/
        uploadReview.mentionBrand("BRGR");

        /*set rating for the Review*/
        uploadReview.setReviewRating();

        /*Uploading the Review*/
        uploadReview.shareReview();

        /*soft Assertion Navigating to the 'Feed' page after Uploading*/
        softAssertion.assertTrue(feedPage.isPageLoaded(),"Did not navigate to the 'Feed' page after Uploading");

        if(this.platform == Platform.ANDROID) {
            /*initialize a Notification Verification object */
            NotificationVerifier notificationVerifier = new NotificationVerifier(driver);
            notificationVerifier.verifyNotification("Reel", "\uD83C\uDFAC Preparing your video... Just a moment");
        } else if (this.platform == Platform.IOS) {
            softAssertion.assertTrue(uploadReview.verifyUploadingMessages(),"Video uploading messages not displayed as expected on iOS");
        }

        softAssertion.assertAll();
    }

    @Test (priority = 3 , description = "Verify user can upload a new review from Front camera")
    public void UploadFrontCameraReview() throws InterruptedException {
        SoftAssert softAssertion = new SoftAssert();

        // 1. Initialize the first page object
        FeedPage feedPage = new FeedPage(driver);

        /*Clicking on Plus Icon From nav bar*/
        UploadReviewPage uploadReview= feedPage.clickRecordReview();

        uploadReview.allowRecordingSettings();

        /*Flipping Camera to Front Camera*/
        uploadReview.flipCamera();

        /*Camera Recording for 20 seconds*/
        uploadReview.startCameraRecording(20);

        /*mention Brand*/
        uploadReview.mentionBrand("BRGR");

        /*set rating for the Review*/
        uploadReview.setReviewRating();

       /*Uploading the Review*/
        uploadReview.shareReview();

        /*soft Assertion Navigating to the 'Feed' page after Uploading*/
        softAssertion.assertTrue(feedPage.isPageLoaded(),"Did not navigate to the 'Feed' page after Uploading");

        if(this.platform == Platform.ANDROID) {
            /*initialize a Notification Verification object */
            NotificationVerifier notificationVerifier = new NotificationVerifier(driver);

            notificationVerifier.verifyNotification("Reel", "\uD83C\uDFAC Preparing your video... Just a moment");
        } else if (this.platform == Platform.IOS) {
            softAssertion.assertTrue(uploadReview.verifyUploadingMessages(),"Video uploading messages not displayed as expected on iOS");
        }
        softAssertion.assertAll();
    }

    @Test (priority = 2 , description = "Verify user can upload a new review from Gallery")
    public void UploadGalleryReview() throws InterruptedException {
        SoftAssert softAssertion = new SoftAssert();

        // 1. Initialize the first page object
        FeedPage feedPage = new FeedPage(driver);

        UploadReviewPage uploadReview= feedPage.clickRecordReview();

        uploadReview.allowRecordingSettings();

        /*Choosing from Gallery*/
        uploadReview.chooseFromGallery();

        /*mention Brand*/
        uploadReview.mentionBrand("BRGR");

        /*set rating for the Review*/
        uploadReview.setReviewRating();

        /*Uploading the Review*/
        uploadReview.shareReview();

        /*soft Assertion Navigating to the 'Feed' page after Uploading*/
        softAssertion.assertTrue(feedPage.isPageLoaded(),"Did not navigate to the 'Feed' page after Uploading");

        if(this.platform == Platform.ANDROID) {
            /*initialize a Notification Verification object */
            NotificationVerifier notificationVerifier = new NotificationVerifier(driver);

            notificationVerifier.verifyNotification("Reel", "\uD83C\uDFAC Preparing your video... Just a moment");
        } else if (this.platform == Platform.IOS) {
            softAssertion.assertTrue(uploadReview.verifyUploadingMessages(),"Video uploading messages not displayed as expected on iOS");
        }
        softAssertion.assertAll();
    }

    @Test (priority = 1 , description = "Verify user can upload a new review from Earn page")
    public void UploadFromEarnPage()
    {
        SoftAssert softAssertion = new SoftAssert();

        // 1. Initialize the first page object
        FeedPage feedPage = new FeedPage(driver);

        // 2. Clicking on Earn Button From nav bar
        EarnPage earnPage= feedPage.clickEarnButton();

        earnPage.allowLocationAccess();




    }

}
