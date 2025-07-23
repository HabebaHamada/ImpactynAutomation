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
    public void UploadBackCameraReview()
    {
        // 1. Initialize the first page object
        FeedPage feedPage = new FeedPage(driver);
        feedPage.clickRecordReview();

    }
}
