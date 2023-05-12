package org.testGit.components;

import org.openqa.selenium.By;

public class Header extends BaseComponent {
    private final String BUTTON_SEARCH = ".header-search-button";
    private final String INPUT_SEARCH = "query-builder-test";

    public BaseComponent buttonSearch() {
        return new BaseComponent().init(By.cssSelector(BUTTON_SEARCH), this.webElement);
    }

    public InputComponent inputSearch() {
        return new InputComponent().init(By.id(INPUT_SEARCH), this.webElement);
    }
}
