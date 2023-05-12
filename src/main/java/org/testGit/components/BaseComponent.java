package org.testGit.components;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testGit.core.logger.MainLogger;
import org.testGit.utils.Waiters;
import org.testGit.utils.browsers.BrowserManager;

import static org.testGit.utils.Waiters.findElement;

public class BaseComponent {
    protected static final Logger logger = MainLogger.logger;
    protected WebDriver driver = BrowserManager.getBrowser().getDriver();
    protected final int period = Waiters.period;
    protected final int timeout = Waiters.timeout;
    protected WebElement webElement;
    protected By by;
    protected SearchContext context;

    public <C extends BaseComponent> C init(By by, SearchContext context, int timeout, int period) {
        this.by = by;
        this.context = context;
        this.webElement = findElement(by, context, timeout, period);
        return (C) this;
    }

    public <C extends BaseComponent> C init(By by) {
        return init(by, driver, timeout, period);
    }

    public <C extends BaseComponent> C init(By by, int timeout) {
        return init(by, driver, timeout, period);
    }

    public <C extends BaseComponent> C init(By by, int timeout, int period) {
        return init(by, driver, timeout, period);
    }

    public <C extends BaseComponent> C init(By by, SearchContext context, int timeout) {
        return init(by, context, timeout, period);
    }

    public <C extends BaseComponent> C init(By by, SearchContext context) {
        return init(by, context, timeout, period);
    }

    public <C extends BaseComponent> C init(WebElement webElement, SearchContext context) {
        this.context = context;
        this.webElement = webElement;
        return (C) this;
    }

    public String getText() {
        String text = webElement.getText();
        logger.debug("Web element text is '" + text + "'");
        return text;
    }

    public void click() {
        logger.info("Click on component " + this.getClass());
        webElement.click();
    }

    public WebElement getWebElement() {
        return webElement;
    }
}
