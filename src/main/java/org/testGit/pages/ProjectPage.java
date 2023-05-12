package org.testGit.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testGit.components.FileComponent;
import org.testGit.utils.Waiters;

import java.util.LinkedHashMap;
import java.util.List;

import static org.testGit.core.config.Config.gitUserName;
import static org.testGit.core.config.Config.repositoryName;

public class ProjectPage extends BasePage {
    protected final String FILES = ".//div[@role='grid']/div[not(@class='sr-only') and (@role='row')]";

    public String getPageUrlPattern() {
        return String.format("^https?:\\/\\/.+\\/%s\\/%s", gitUserName(), repositoryName());
    }

    public LinkedHashMap<String, FileComponent> filesMap() {
        LinkedHashMap<String, FileComponent> items = new LinkedHashMap<>();
        List<WebElement> webElements = Waiters.findElements(By.xpath(FILES), this.webElement, this.timeout);
        for (WebElement we : webElements) {
            FileComponent item = new FileComponent().init(we, this.webElement);
            items.put(item.title().getText(), item);
        }
        return items;
    }


}
