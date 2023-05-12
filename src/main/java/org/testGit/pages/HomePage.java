package org.testGit.pages;

import org.openqa.selenium.By;
import org.testGit.components.Header;

public class HomePage extends BasePage {

//    protected final String HEADER = ".Header";
    protected final String HEADER = ".//header[contains(@class,'Header')]";

    public String getPageUrlPattern() {
        return "^https?:\\/\\/.+";
    }

    public Header header() {
        return new Header().init(By.xpath(HEADER), this.webElement);
    }
}
