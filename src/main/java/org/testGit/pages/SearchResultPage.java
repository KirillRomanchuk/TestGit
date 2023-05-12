package org.testGit.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testGit.components.SearchResultComponent;
import org.testGit.utils.Waiters;

import java.util.LinkedHashMap;
import java.util.List;

public class SearchResultPage extends BasePage {
    protected final String SEARCH_RESULT = ".//div[@data-testid='results-list']/div";

    public String getPageUrlPattern() {
        return "^https?:\\/\\/.+\\/search.+";
    }

    public LinkedHashMap<String, SearchResultComponent> searchResultMap() {
        LinkedHashMap<String, SearchResultComponent> items = new LinkedHashMap<>();
        List<WebElement> webElements = Waiters.findElements(By.xpath(SEARCH_RESULT), this.webElement, this.timeout);
        for (WebElement we : webElements) {
            SearchResultComponent item = new SearchResultComponent().init(we, this.webElement);
            items.put(item.title().getText(), item);
        }
        return items;
    }

}
