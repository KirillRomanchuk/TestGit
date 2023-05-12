package org.testGit.components;

import org.openqa.selenium.By;

public class SearchResultComponent extends BaseComponent {
    private final String TITLE = ".//div[contains(@class, 'search-title')]";

    public BaseComponent title() {
        return new BaseComponent().init(By.xpath(TITLE), this.webElement);
    }


}
