package org.testGit.utils.browsers;

import org.openqa.selenium.WebDriver;

public class BaseBrowser {
    protected WebDriver webDriver;

    public WebDriver getDriver() {
        return webDriver;
    }
}
