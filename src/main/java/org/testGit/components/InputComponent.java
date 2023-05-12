package org.testGit.components;

import org.openqa.selenium.Keys;

import java.util.Arrays;

public class InputComponent extends BaseComponent {
    public void sendKeys(CharSequence... keysToSend) {
        logger.debug("Entering value in input " + Arrays.toString(keysToSend));
        this.clear();
        this.webElement.sendKeys(keysToSend);
    }

    public void append(String value) {
        this.webElement.sendKeys(value);
    }

    public void clear() {
        this.click();
        webElement.clear();
    }

    public String getValue() {
        String value = this.webElement.getAttribute("value");
        logger.debug("Retrieved input entered value is '" + value + "'");
        return value;
    }

    public void pressEnter() {
        String shiftTab = Keys.chord(Keys.ENTER);
        this.webElement.sendKeys(shiftTab);
    }

    public void clearViaBackspace() {
        this.click();
        String value = this.getValue();
        if (!value.isEmpty()) {
            for (int i = 0; i < value.length(); i++) {
                this.webElement.sendKeys(Keys.BACK_SPACE);
            }
        }
    }
}
