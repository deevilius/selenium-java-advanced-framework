package com.letskodeit.base;

import java.time.Duration;

import com.letskodeit.utilities.Constants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.CapabilityType;

public class WebDriverFactory {
    public static final Logger log = LogManager.getLogger(WebDriverFactory.class.getName());

    // Singleton
    // Only one instance of the class can exist at a time
    private static final WebDriverFactory instance = new WebDriverFactory();

    private WebDriverFactory() {
    }

    public static WebDriverFactory getInstance() {
        return instance;
    }

    private static ThreadLocal<WebDriver> threadedDriver = new ThreadLocal<WebDriver>();
    private static ThreadLocal<String> threadedBrowser = new ThreadLocal<String>();

    /***
     * Get driver instance based on the browser type
     * @param browser
     * @return
     */
    public WebDriver getDriver(String browser) {
        WebDriver driver = null;
        setDriver(browser);
        threadedBrowser.set(browser);
        if (threadedDriver.get() == null) {
            try {
                if (browser.equalsIgnoreCase(Constants.FIREFOX)) {
                    FirefoxOptions ffOptions = setFirefoxOptions();
                    driver = new FirefoxDriver(ffOptions);
                    threadedDriver.set(driver);
                }
                if (browser.equalsIgnoreCase(Constants.CHROME)) {
                    ChromeOptions chromeOptions = setChromeOptions();
                    driver = new ChromeDriver(chromeOptions);
                    threadedDriver.set(driver);
                }
                if (browser.equalsIgnoreCase(Constants.IE)) {
                    InternetExplorerOptions ieOptions = setIEOptions();
                    driver = new InternetExplorerDriver(ieOptions);
                    threadedDriver.set(driver);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            threadedDriver.get().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            threadedDriver.get().manage().window().maximize();
        }
        return threadedDriver.get();
    }

    public String getBrowser() {
        return threadedBrowser.get();
    }

    public void quitDriver() {
        threadedDriver.get().quit();
        threadedDriver.set(null);
    }

    private void setDriver(String browser) {
        String driverPath = "";
        String os = Constants.OS_NAME.toLowerCase().substring(0, 3);
        log.info("OS Name from system property :: " + os);
        String directory = Constants.USER_DIRECTORY + Constants.DRIVERS_DIRECTORY;
        String driverKey = "";
        String driverValue = "";

        if (browser.equalsIgnoreCase(Constants.FIREFOX)) {
            driverKey = Constants.GECKO_DRIVER_KEY;
            driverValue = Constants.GECKO_DRIVER_VAlUE;
        } else if(browser.equalsIgnoreCase(Constants.CHROME)) {
            driverKey = Constants.CHROME_DRIVER_KEY;
            driverValue = Constants.CHROME_DRIVER_VALUE;
        } else if(browser.equalsIgnoreCase(Constants.IE)) {
            driverKey = Constants.IE_DRIVER_KEY;
            driverValue = Constants.IE_DRIVER_VALUE;
        } else {
            log.info("Browser type not supported");
        }

        driverPath = directory + driverValue + (os.equals("win") ? ".exe" : "") ;
        log.info("Driver Binary :: " + driverPath);
        System.setProperty(driverKey, driverPath);
    }

    private ChromeOptions setChromeOptions() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("disable-infobars");
        return options;
    }

    private FirefoxOptions setFirefoxOptions() {
        FirefoxOptions options = new FirefoxOptions();
        options.setCapability(CapabilityType.HAS_NATIVE_EVENTS, false);
        return options;
    }

    private InternetExplorerOptions setIEOptions() {
        InternetExplorerOptions options = new InternetExplorerOptions();
        options.setCapability(InternetExplorerDriver.NATIVE_EVENTS, false);
        options.setCapability(InternetExplorerDriver.ENABLE_PERSISTENT_HOVERING, false);
        options.setCapability(InternetExplorerDriver.REQUIRE_WINDOW_FOCUS, false);
        options.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);
        options.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING, true);
        options.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
        return options;
    }
}
