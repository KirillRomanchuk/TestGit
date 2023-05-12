package org.testGit.pages;

import org.openqa.selenium.By;
import org.testGit.components.BaseComponent;
import org.testGit.components.InputComponent;

public class LoginPage extends BasePage {
    protected final String LOGIN = "login_field";
    protected final String PASSWORD = "password";
    protected final String BUTTON_SIGN_IN = ".//input[@value='Sign in']";

    public String getPageUrlPattern() {
        return "^https?:\\/\\/.+\\/login";
    }

    public InputComponent inputLogin() {
        return new InputComponent().init(By.id(LOGIN), this.webElement);
    }

    public InputComponent inputPassword() {
        return new InputComponent().init(By.id(PASSWORD), this.webElement);
    }

    public BaseComponent buttonSignIn() {
        return new BaseComponent().init(By.xpath(BUTTON_SIGN_IN), this.webElement);
    }
}
