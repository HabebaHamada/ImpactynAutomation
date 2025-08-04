package ImpactynTestCases;

import ImpactynPages.FeedPage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class ImpactynUploadReviewTest extends BaseTest
{

    @BeforeMethod
    public void loginBeforeReviewTest() {
        // Call the reusable login method from our BaseTest
        performLogin();
    }

    @Test (priority = 1 , description = "Verify user can upload a new review from Back camera")
    public void UploadBackCameraReview() throws InterruptedException {
        // 1. Initialize the first page object
        FeedPage feedPage = new FeedPage(driver);

        /*Clicking on Plus Icon From nav bar*/
        feedPage.clickRecordReview();

        /*Camera Recording for 30 seconds*/
        feedPage.startCameraRecording(3000);

        /*mention Brand*/
        feedPage.setMentionBrand("BRGR");

        /*set rating for the Review*/
        feedPage.setReviewRating();

        /*Uploading the Review*/
        feedPage.shareReview();
    }

    @Test (priority = 2 , description = "Verify user can upload a new review from Front camera")
    public void UploadFrontCameraReview() throws InterruptedException {
        // 1. Initialize the first page object
        FeedPage feedPage = new FeedPage(driver);

        /*Clicking on Plus Icon From nav bar*/
        feedPage.clickRecordReview();

        /*Flipping Camera to Front Camera*/
        feedPage.setFrontCamera();

        /*Camera Recording for 30 seconds*/
        feedPage.startCameraRecording(3000);

        /*mention Brand*/
        feedPage.setMentionBrand("BRGR");

        /*set rating for the Review*/
        feedPage.setReviewRating();

        /*Uploading the Review*/
        feedPage.shareReview();
    }


}
