package org.testGit.pages;

import org.openqa.selenium.By;
import org.testGit.components.BaseComponent;

import static org.testGit.core.config.Config.gitUserName;
import static org.testGit.core.config.Config.repositoryName;

public class FilePage extends BasePage {

    protected final String TEXT = ".//div/textarea";
    private static String fileName;

    public static void setFileName(String name) {
        fileName = name;
    }

    public String getFileName() {
        return fileName;
    }

    public String getPageUrlPattern() {
        return String.format("^https?:\\/\\/.+\\/%s\\/%s\\/.+\\/%s", gitUserName(), repositoryName(), fileName);
    }

    public BaseComponent fileText() {
        return new BaseComponent().init(By.xpath(TEXT), this.webElement);
    }

}
