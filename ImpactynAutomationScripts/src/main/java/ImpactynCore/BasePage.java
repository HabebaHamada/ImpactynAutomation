package ImpactynCore;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.Platform;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class BasePage {
    protected final AppiumDriver driver;
    protected final WebDriverWait wait;
    protected final Platform platform;

    public BasePage(AppiumDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(25));

        // Determine the platform once and store it
        this.platform = driver.getCapabilities().getPlatformName();
        assert this.platform != null;
        System.out.println("BasePage initialized for platform: " + this.platform.name());
    }
}
