package org.testGit.pages;

import org.testGit.components.BaseComponent;
import org.testGit.utils.Waiters;

public abstract class BasePage extends BaseComponent {
    protected static final int pageLoadTimeout = 10;

    public String getPageUrlPattern() {
        return "";
    }

    public BasePage() {
        this.waitPageIsOpened(pageLoadTimeout);
    }

    public boolean waitPageIsOpened(int timeout) {
        String pattern = getPageUrlPattern();
        boolean result = Waiters.waitUntilCondition(
                () -> driver.getCurrentUrl().matches(pattern),
                timeout,
                String.format("Page '%s' to be loaded", pattern));
        if (!result && timeout > 0) {
            throw new RuntimeException(String.format("%s page is not loaded. \nExpected page URL pattern: \n'%s'" +
                    "\ndoes not match with actual URL \n'%s'", this.getClass(), pattern, driver.getCurrentUrl()));
        }
        return result;
    }
}
