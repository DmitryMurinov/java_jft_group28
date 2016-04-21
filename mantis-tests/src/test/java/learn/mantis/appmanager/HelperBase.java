package learn.mantis.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;

/**
 * Created by Dima on 28.02.2016.
 */
public class HelperBase {

    protected ApplicationManager app;
    protected WebDriver wd;

    public HelperBase(ApplicationManager app) {
        this.app = app;
        this.wd = app.getWebDriver();
    }

    protected void click(By locator) {
        wd.findElement(locator).click();
    }

    protected void typeInfoBox(By locator, String textToEnter) {
        click(locator);
        if (textToEnter != null){
            String existingText = wd.findElement(locator).getAttribute("value");
            if(!textToEnter.equals(existingText)) {
                wd.findElement(locator).clear();
                wd.findElement(locator).sendKeys(textToEnter);
            }
        }
    }

    protected void attachFile(By locator, File file) {
        if(file != null){
            wd.findElement(locator).sendKeys(file.getAbsolutePath());}
    }

    public void openStartPage() {
        click(By.xpath(".//*[@id='nav']/ul/li[1]/a"));
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

    protected boolean isElementPresent(By locator) {
        try{
            wd.findElement(locator);
            return true;
        }catch (NoSuchElementException e){
            return false;
        }
    }
}
