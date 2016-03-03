package jft.murinov.addressbook.appmanager;

import jft.murinov.addressbook.model.ContactData;
import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Created by Dima on 28.02.2016.
 */
public class ContactHelper extends HelperBase{

    public ContactHelper(FirefoxDriver wd) {
        super(wd);
    }

    public void clickAddNewContact() {
        click(By.linkText("add new"));
    }

    public void fillContactForm(ContactData contactData) {
        typeInfoBox(By.name("firstname"), contactData.getFirstName());
        typeInfoBox(By.name("middlename"), contactData.getMiddleName());
        typeInfoBox(By.name("lastname"), contactData.getLastName());
        typeInfoBox(By.name("nickname"), contactData.getNickname());
        typeInfoBox(By.name("address"), contactData.getFirstAddress());
        typeInfoBox(By.name("home"), contactData.getHomePhoneString());
        typeInfoBox(By.name("mobile"), contactData.getMobilePhoneString());
        typeInfoBox(By.name("email"), contactData.getFirstEmail());
    }

    public void selectContact(int rowNumber) {
        click(By.xpath("//tr[" + (1 + rowNumber) + "]/td[1]/input[@type='checkbox']"));
    }

    public void waitForAutoRedirectToContactsList(String firstEmail) {
        waitToBeClickable(By.linkText(firstEmail));
    }

    public void waitForAutoRedirectToContactsListAfterDelete(String tableHeaderLocator) {
        waitToBeVisible(By.cssSelector(tableHeaderLocator));
    }

    public void submitContactForm() {
        click(By.xpath("//div[@id='content']/form/input[21]"));
    }

    public void deleteSelectedContacts() { click(By.xpath("//div[@id='content']/form[2]/div[2]/input"));
    }

    public void acceptAlert() { wd.switchTo().alert().accept();
    }

    public void clickModifyContact() {
        click(By.xpath("//table[@id='maintable']/tbody/tr[2]/td[8]/a/img"));
    }

    public void submitContactModify() { click(By.xpath("//div[@id='content']/form[1]/input[22]"));
    }
}
