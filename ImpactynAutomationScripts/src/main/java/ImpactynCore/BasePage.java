package ImpactynCore;

import io.appium.java_client.AppiumDriver;

import org.openqa.selenium.Platform;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.logging.Logger;

public abstract class BasePage {
    protected final AppiumDriver driver;
    protected final WebDriverWait wait;
    protected final Platform platform;
    protected final Logger logger;

    protected BasePage(AppiumDriver driver) {
        this.driver = driver;
        this.logger = Logger.getLogger(getClass().getName());

        this.wait = new WebDriverWait(driver, Duration.ofSeconds(25));

        // Determine the platform once and store it
        this.platform = driver.getCapabilities().getPlatformName();
    }
}
