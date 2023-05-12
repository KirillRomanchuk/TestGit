package org.testGit.utils;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;
import org.testGit.core.logger.MainLogger;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

public class Waiters {
    protected static final Logger logger = MainLogger.logger;
    public static final int period = 500;
    public static final int timeout = 30;

    public static final int WAIT_SMALL_TIMEOUNT = 5;
    public static final int WAIT_MEDIUM_TIMEOUT = 10;

    public static boolean waitUntilCondition(Callable<Boolean> condition,
                                             int timeout,
                                             int period,
                                             String message) {
        String msg = message.isEmpty() ? "Condition is not true" : String.format("Condition '%s' is not true", message);
        long timeStart = System.currentTimeMillis();
        long timeExit = timeStart + timeout * 1000;
        logger.debug("Start waiting for the action '" + message + "'");
        do {
            try {
                boolean result = condition.call();
                if (timeout == 0)
                    return result;
                if (!result) {
                    throw new Exception(msg);
                }
                return result;
            } catch (Exception e) {
                logger.debug(msg);
            }
            try {
                if (timeout > 0)
                    TimeUnit.MILLISECONDS.sleep(period);
            } catch (InterruptedException e) {
                logger.warn(e.getMessage());
            }
        }
        while (System.currentTimeMillis() < timeExit);

        return false;
    }

    public static boolean waitUntilCondition(Callable<Boolean> condition, int timeout, String message) {
        return waitUntilCondition(condition, timeout, period, message);
    }

    public static WebElement findElement(By by,
                                         SearchContext context,
                                         int timeout,
                                         int period) {
        WebElement webElement = null;
        long timeStart = System.currentTimeMillis();
        long timeExit = timeStart + timeout * 1000L;
        logger.debug("Start looking for the element using strategy '" + by.toString() + "' " +
                "with timeout '" + timeout + "' and period '" + period + "'");
        do {
            try {
                webElement = context.findElement(by);
                logger.debug("Found element using strategy '" + by + "'");
                return webElement;
            } catch (Exception e) {
                logger.info("Cannot find element using strategy '" + by + "'");
                if (timeout == 0)
                    return null;
            }
            try {
                TimeUnit.MILLISECONDS.sleep(period);
            } catch (InterruptedException e) {
                logger.warn(e.getMessage());
            }
        }
        while (System.currentTimeMillis() < timeExit);

        return webElement;
    }

    public static List<WebElement> findElements(By by,
                                                SearchContext context,
                                                int timeout,
                                                int period) {
        List<WebElement> webElements = null;
        long timeStart = System.currentTimeMillis();
        long timeExit = timeStart + timeout * 1000;
        logger.debug("Start looking for the elements using strategy '" + by.toString() + "'");
        do {
            webElements = context.findElements(by);
            if (!webElements.isEmpty()) return webElements;
            else logger.debug("Cannot find elements using strategy " + by.toString());
            try {
                TimeUnit.MILLISECONDS.sleep(period);
            } catch (InterruptedException e) {
                logger.warn(e.getMessage());
            }
        }
        while (System.currentTimeMillis() < timeExit);

        return webElements;
    }

    public static List<WebElement> findElements(By by, SearchContext context, int timeout) {
        return findElements(by, context, timeout, period);
    }
}
