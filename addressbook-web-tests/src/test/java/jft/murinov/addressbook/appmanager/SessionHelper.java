package jft.murinov.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Created by Dima on 28.02.2016.
 */
public class SessionHelper extends HelperBase{

    public SessionHelper(WebDriver wd) {
        super(wd);
    }

    public void login(String userName, String password) {
        typeInfoBox(By.name("user"), userName);
        typeInfoBox(By.name("pass"), password);
        click(By.xpath("//form[@id='LoginForm']/input[3]"));
    }

}
