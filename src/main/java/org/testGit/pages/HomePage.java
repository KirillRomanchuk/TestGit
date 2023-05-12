package org.testGit.pages;

import org.openqa.selenium.By;
import org.testGit.components.Header;

public class HomePage extends BasePage {

    protected final String HEADER = ".Header";

    public String getPageUrlPattern() {
        return "^https?:\\/\\/.+";
    }

    public Header header() {
        return new Header().init(By.cssSelector(HEADER), this.webElement);
    }
}
