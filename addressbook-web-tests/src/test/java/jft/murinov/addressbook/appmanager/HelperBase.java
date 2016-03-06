package jft.murinov.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by Dima on 28.02.2016.
 */
public class HelperBase {
    protected WebDriver wd;

    public HelperBase(WebDriver wd) {
        this.wd = wd;
    }

    protected void click(By locator) {
        wd.findElement(locator).click();
    }

    protected void typeInfoBox(By locator, String textToEnter) {
        click(locator);
        if (textToEnter != null) {
            wd.findElement(locator).clear();
            wd.findElement(locator).sendKeys(textToEnter);
        }
    }

    public boolean isAlertPresent() {
        try {
            wd.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }

    protected void waitToBeClickable(By locator) {
        new WebDriverWait(wd, 60, 200).until(
                ExpectedConditions.elementToBeClickable(locator)
        );
    }

    protected void waitToBeVisible(By locator) {
        new WebDriverWait(wd, 60, 200).until(
                ExpectedConditions.presenceOfElementLocated(locator)
        );
    }

}
