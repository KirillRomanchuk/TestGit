package org.testGit.utils.browsers;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.CapabilityType;

import java.util.HashMap;
import java.util.Map;

public class FireFoxBrowser extends BaseBrowser {

    FireFoxBrowser() {
        FirefoxOptions options = setupFireFoxOptions();
        WebDriverManager.firefoxdriver().setup();
        webDriver = new FirefoxDriver(options);
    }

    private FirefoxOptions setupFireFoxOptions() {
        FirefoxOptions options = new FirefoxOptions();

        Map<String, Object> prefs = new HashMap<>();
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);
        prefs.put("profile.default_content_setting_values.notifications", 2);  //1 - allow notification, 2 - block notifications, anyway it hides popup window

        options.setPageLoadStrategy(PageLoadStrategy.NONE);

        options.addArguments("disable-infobars");

        options.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);

        if (System.getProperty("os.name").toLowerCase().contains("linux")) {
            options.addArguments("--headless");
        }

        return options;
    }
}
