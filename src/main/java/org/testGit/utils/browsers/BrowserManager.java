package org.testGit.utils.browsers;

import org.apache.logging.log4j.Logger;
import org.testGit.core.config.Config;
import org.testGit.core.logger.MainLogger;

import java.util.HashMap;
import java.util.Map;

public class BrowserManager {
    protected static final Logger logger = MainLogger.logger;
    private static BaseBrowser browser;

    public static BaseBrowser getBrowser() {
        if (browser == null) {
            new BrowserManager().initDriver();
        }
        return browser;
    }

    private void initDriver() {
        String browserName = Config.browser();
        if (browserName.equalsIgnoreCase("Chrome")) {
            browser = new ChromeBrowser();
        } else if (browserName.equalsIgnoreCase("Firefox")) {
            browser = new FireFoxBrowser();
        } else {
            browser = new ChromeBrowser();
        }
    }

    public static void quitSession() {
        getBrowser().getDriver().quit();
        browser = null;
    }


}
