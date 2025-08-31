package ImpactynTestCases;

import ImpactynPages.FeedPage;
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

    @Test (priority = 2 , description = "Verify user can upload a new review from Back camera")
    public void UploadBackCameraReview() throws InterruptedException {

        SoftAssert softAssertion = new SoftAssert();

        // 1. Initialize the first page object
        FeedPage feedPage = new FeedPage(driver);

        /*Clicking on Plus Icon From nav bar*/
        feedPage.clickRecordReview();

        /*Camera Recording for 20 seconds*/
        feedPage.startCameraRecording(20000);

        /*mention Brand*/
        feedPage.mentionBrand("BRGR");

        /*set rating for the Review*/
        feedPage.setReviewRating();

        /*Uploading the Review*/
        feedPage.shareReview();

        /*soft Assertion Navigating to the 'Feed' page after Uploading*/
        softAssertion.assertTrue(feedPage.isPageLoaded(),"Did not navigate to the 'Feed' page after Uploading");

        if(this.platform == Platform.ANDROID) {
            /*initialize a Notification Verification object */
            NotificationVerifier notificationVerifier = new NotificationVerifier(driver);

            notificationVerifier.verifyNotification("Reel", "\uD83C\uDFAC Preparing your video... Just a moment");
        } else if (this.platform == Platform.IOS) {
            softAssertion.assertTrue(feedPage.verifyUploadingMessages(),"Video uploading messages not displayed as expected on iOS");
        }

        softAssertion.assertAll();
    }

    @Test (priority = 3 , description = "Verify user can upload a new review from Front camera")
    public void UploadFrontCameraReview() throws InterruptedException {
        SoftAssert softAssertion = new SoftAssert();

        // 1. Initialize the first page object
        FeedPage feedPage = new FeedPage(driver);

        /*Clicking on Plus Icon From nav bar*/
        feedPage.clickRecordReview();

        /*Flipping Camera to Front Camera*/
        feedPage.flipCamera();

        /*Camera Recording for 20 seconds*/
        feedPage.startCameraRecording(20000);

        /*mention Brand*/
        feedPage.mentionBrand("BRGR");

        /*set rating for the Review*/
        feedPage.setReviewRating();

       /*Uploading the Review*/
        feedPage.shareReview();

        /*soft Assertion Navigating to the 'Feed' page after Uploading*/
        softAssertion.assertTrue(feedPage.isPageLoaded(),"Did not navigate to the 'Feed' page after Uploading");

        if(this.platform == Platform.ANDROID) {
            /*initialize a Notification Verification object */
            NotificationVerifier notificationVerifier = new NotificationVerifier(driver);

            notificationVerifier.verifyNotification("Reel", "\uD83C\uDFAC Preparing your video... Just a moment");
        } else if (this.platform == Platform.IOS) {
            softAssertion.assertTrue(feedPage.verifyUploadingMessages(),"Video uploading messages not displayed as expected on iOS");
        }
        softAssertion.assertAll();
    }

    @Test (priority = 1 , description = "Verify user can upload a new review from Gallery")
    public void UploadGalleryReview() throws InterruptedException {
        SoftAssert softAssertion = new SoftAssert();

        // 1. Initialize the first page object
        FeedPage feedPage = new FeedPage(driver);

        feedPage.clickRecordReview();

        /*Choosing from Gallery*/
        feedPage.chooseFromGallery();

        /*mention Brand*/
        feedPage.mentionBrand("BRGR");

        /*set rating for the Review*/
        feedPage.setReviewRating();

        /*Uploading the Review*/
        feedPage.shareReview();

        /*soft Assertion Navigating to the 'Feed' page after Uploading*/
        softAssertion.assertTrue(feedPage.isPageLoaded(),"Did not navigate to the 'Feed' page after Uploading");

        if(this.platform == Platform.ANDROID) {
            /*initialize a Notification Verification object */
            NotificationVerifier notificationVerifier = new NotificationVerifier(driver);

            notificationVerifier.verifyNotification("Reel", "\uD83C\uDFAC Preparing your video... Just a moment");
        } else if (this.platform == Platform.IOS) {
            softAssertion.assertTrue(feedPage.verifyUploadingMessages(),"Video uploading messages not displayed as expected on iOS");
        }
        softAssertion.assertAll();
    }


}
