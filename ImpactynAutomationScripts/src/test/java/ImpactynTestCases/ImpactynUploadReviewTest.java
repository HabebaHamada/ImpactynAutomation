package ImpactynTestCases;

import ImpactynPages.SystemAlertsPage;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import ImpactynPages.FeedPage;
import ImpactynPages.HomePage;
import ImpactynPages.UploadReviewPage;

import org.openqa.selenium.Platform;

import org.testng.annotations.DataProvider;
import org.testng.asserts.SoftAssert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class ImpactynUploadReviewTest extends BaseTest
{

    @BeforeMethod
    public void loginBeforeReviewTest() {
        SystemAlertsPage alertsPage= new SystemAlertsPage(driver);
        alertsPage.handleInitialPopups();

        // Call the reusable login method from our BaseTest
        performLogin();
    }

    @DataProvider(name = "uploadReviewData")
    public Object[][] provideSearchDataFromJson() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        List<Map<String, String>> data = mapper.readValue(
                new File("src/test/resources/testdata.json"),
                List.class
        );

        Object[][] testData = new Object[data.size()][3];
        for (int i = 0; i < data.size(); i++) {
            testData[i][0] = data.get(i).get("brandName");
            testData[i][1] = data.get(i).get("recordingDuration");
            testData[i][2] = data.get(i).get("ratingValue");
        }
        return testData;
    }

    @Test (priority = 1 , dataProvider = "uploadReviewData", description = "Verify user can upload a new review from Nav Bar")
    public void UploadFromNavBar(String brandName , String recordingDuration , String ratingValue) throws InterruptedException {

        SoftAssert softAssertion = new SoftAssert();

        // 1. Initialize the first page object
        HomePage homePage = new HomePage(driver);

        /*Clicking on Plus Icon From nav bar*/
        UploadReviewPage uploadReview=homePage.clickRecordReview();

        SystemAlertsPage systemAlertsPage= new SystemAlertsPage(driver);
        systemAlertsPage.allowRecordingSettings();

        /*Camera Recording for 20 seconds*/
        uploadReview.startCameraRecording(Integer.parseInt(recordingDuration));

        /*mention Brand*/
        uploadReview.mentionBrand(brandName);

        /*set rating for the Review*/
        uploadReview.setReviewRating(ratingValue);

        /*Uploading the Review*/
        FeedPage feedPage= uploadReview.shareReview();

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

//    @Test (priority = 3 , description = "Verify user can upload a new review from Front camera")
//    public void UploadFrontCameraReview() throws InterruptedException {
//        SoftAssert softAssertion = new SoftAssert();
//
//        // 1. Initialize the first page object
//        FeedPage feedPage = new FeedPage(driver);
//
//        /*Clicking on Plus Icon From nav bar*/
//        UploadReviewPage uploadReview= feedPage.clickRecordReview();
//
//
//        SystemAlertsPage systemAlertsPage= new SystemAlertsPage(driver);
//        systemAlertsPage.allowRecordingSettings();
//
//        /*Flipping Camera to Front Camera*/
//        uploadReview.flipCamera();
//
//        /*Camera Recording for 20 seconds*/
//        uploadReview.startCameraRecording(20);
//
//        /*mention Brand*/
//        uploadReview.mentionBrand("BRGR");
//
//        /*set rating for the Review*/
//        uploadReview.setReviewRating();
//
//       /*Uploading the Review*/
//        uploadReview.shareReview();
//
//        /*soft Assertion Navigating to the 'Feed' page after Uploading*/
//        softAssertion.assertTrue(feedPage.isPageLoaded(),"Did not navigate to the 'Feed' page after Uploading");
//
//        if(this.platform == Platform.ANDROID) {
//            /*initialize a Notification Verification object */
//            NotificationVerifier notificationVerifier = new NotificationVerifier(driver);
//
//            notificationVerifier.verifyNotification("Reel", "\uD83C\uDFAC Preparing your video... Just a moment");
//        } else if (this.platform == Platform.IOS) {
//            softAssertion.assertTrue(uploadReview.verifyUploadingMessages(),"Video uploading messages not displayed as expected on iOS");
//        }
//        softAssertion.assertAll();
//    }
//
//    @Test (priority = 2 , description = "Verify user can upload a new review from Gallery")
//    public void UploadGalleryReview() throws InterruptedException {
//        SoftAssert softAssertion = new SoftAssert();
//
//        // 1. Initialize the first page object
//        FeedPage feedPage = new FeedPage(driver);
//
//        UploadReviewPage uploadReview= feedPage.clickRecordReview();
//
//
//        SystemAlertsPage systemAlertsPage= new SystemAlertsPage(driver);
//        systemAlertsPage.allowRecordingSettings();
//
//        /*Choosing from Gallery*/
//        uploadReview.chooseFromGallery();
//
//        /*mention Brand*/
//        uploadReview.mentionBrand("BRGR");
//
//        /*set rating for the Review*/
//        uploadReview.setReviewRating();
//
//        /*Uploading the Review*/
//        uploadReview.shareReview();
//
//        /*soft Assertion Navigating to the 'Feed' page after Uploading*/
//        softAssertion.assertTrue(feedPage.isPageLoaded(),"Did not navigate to the 'Feed' page after Uploading");
//
//        if(this.platform == Platform.ANDROID) {
//            /*initialize a Notification Verification object */
//            NotificationVerifier notificationVerifier = new NotificationVerifier(driver);
//
//            notificationVerifier.verifyNotification("Reel", "\uD83C\uDFAC Preparing your video... Just a moment");
//        } else if (this.platform == Platform.IOS) {
//            softAssertion.assertTrue(uploadReview.verifyUploadingMessages(),"Video uploading messages not displayed as expected on iOS");
//        }
//        softAssertion.assertAll();
//    }

//    @Test (priority = 1 , description = "Verify user can upload a new review from Earn page")
//    public void UploadFromEarnPage()
//    {
//        SoftAssert softAssertion = new SoftAssert();
//
//        // 1. Initialize the first page object
//        FeedPage feedPage = new FeedPage(driver);
//
//        // 2. Clicking on Earn Button From nav bar
//        EarnPage earnPage= feedPage.clickEarnButton();
//
//
//        SystemAlertsPage systemAlertsPage= new SystemAlertsPage(driver);
//        systemAlertsPage.allowLocationAccess();
//
//
//
//
//    }

}
