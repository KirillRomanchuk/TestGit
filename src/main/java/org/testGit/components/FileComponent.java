package org.testGit.components;

import org.openqa.selenium.By;

public class FileComponent extends BaseComponent {
    private final String TITLE = "[role='rowheader']";
    private final String FILE_LINK = ".//div[@role='rowheader']//a";

    public BaseComponent title() {
        return new BaseComponent().init(By.cssSelector(TITLE), this.webElement);
    }

    public BaseComponent fileLink() {
        return new BaseComponent().init(By.xpath(FILE_LINK), this.webElement);
    }
}
