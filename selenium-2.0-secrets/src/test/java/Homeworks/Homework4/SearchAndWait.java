package Homeworks.Homework4;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;

import com.google.common.base.Predicate;

import static Homeworks.Homework4.Predicates.fixed;

/**
 * Created by Dima on 12.09.2016.
 */
public class SearchAndWait {

    @Test
    public void SearchAndWaitPositive(){

        WebDriver wd = new FirefoxDriver();

        String URL = "http://demos.telerik.com/aspnet-ajax/webmail/default.aspx";

        wd.get(URL);

        System.out.println("Click first link");

        wd.findElement(By.xpath(".//*[@id='ctl00_FolderContent_FolderNavigationControl_rtvFolders']/ul/li[6]/div")).click();

        System.out.println("Wait until table stop moving");

        WebElement table = wd.findElement(By.xpath(".//*[@id='ctl00_MainContent_messages']"));

        new WebElementWait(table, 60).until(fixed());

        System.out.println("Expand list");

        wd.findElement(By.xpath(".//*[@id='ctl00_FolderContent_FolderNavigationControl_rtvFolders']/ul/li[6]/div/span[2]")).click();

        System.out.println("Wait until list not moving");

        WebElement list = wd.findElement(By.xpath(".//*[@id='ctl00_FolderContent_FolderNavigationControl_rtvFolders']/ul/li[6]/ul"));

        new WebElementWait(list, 60).until(fixed());

        List<WebElement> inList = list.findElements(By.tagName("li"));

        System.out.println("Click on items in list");

        for(WebElement item : inList){
            item.click();
            new WebElementWait(table, 60).until(fixed());

        }

        wd.close();
    }

}
