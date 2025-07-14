package ImpactynPages;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class FeedPage {

    private final AppiumDriver driver;
    private final WebDriverWait wait;

    private final By ForYouTextLocator = By.xpath("//android.widget.TextView[@text=\"For You\"]");
    private final By FollowingTextLocator = By.xpath("//android.widget.TextView[@text=\"Following\"]");
    private final By NavBarLocator = By.xpath("//m5.e1/android.view.View/android.view.View/android.view.View[2]/android.view.View/android.view.View[3]/android.view.View[2]");
    private final By EarnButtonLocator = By.xpath("(//android.widget.TextView[@text=\"Earn\"])[1]");


    public FeedPage(AppiumDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(50));
    }

    public boolean isPageLoaded()
    {
       boolean ForYouValidation=  wait.until(ExpectedConditions.visibilityOfElementLocated(ForYouTextLocator)).isDisplayed();
       boolean FollowingValidation=  wait.until(ExpectedConditions.visibilityOfElementLocated(FollowingTextLocator)).isDisplayed();
       boolean NavBarValidation=  wait.until(ExpectedConditions.visibilityOfElementLocated(NavBarLocator)).isDisplayed();
       boolean EarnButtonValidation=  wait.until(ExpectedConditions.visibilityOfElementLocated(EarnButtonLocator)).isDisplayed();

       return (ForYouValidation||FollowingValidation||NavBarValidation||EarnButtonValidation);
    }
}