package jft.murinov.addressbook.appmanager;

import jft.murinov.addressbook.model.ContactData;
import jft.murinov.addressbook.model.GroupData;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.pagefactory.ElementLocator;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dima on 28.02.2016.
 */
public class ContactHelper extends HelperBase{

    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void clickAddNewContact() {
        click(By.linkText("add new"));
    }

    public void fillContactForm(ContactData contactData, boolean creation) {
        typeInfoBox(By.name("firstname"), contactData.getFirstName());
        typeInfoBox(By.name("middlename"), contactData.getMiddleName());
        typeInfoBox(By.name("lastname"), contactData.getLastName());
        typeInfoBox(By.name("nickname"), contactData.getNickname());
        typeInfoBox(By.name("address"), contactData.getFirstAddress());
        typeInfoBox(By.name("home"), contactData.getHomePhoneString());
        typeInfoBox(By.name("mobile"), contactData.getMobilePhoneString());
        typeInfoBox(By.name("email"), contactData.getFirstEmail());

        if(creation){
            new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
        } else {
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }
    }

    public void selectContact(int rowNumber) {
        click(By.xpath("//tr[" + (2 + rowNumber) + "]/td[1]/input[@type='checkbox']"));
    }

    public void waitForAutoRedirectToContactsList() {
        waitToBeClickable(By.id("maintable"));
    }

    public void waitForAutoRedirectToContactsListAfterDelete() {
        waitToBeVisible(By.cssSelector(".fdTableSortTrigger"));
    }

    public void submitContactForm() {
        click(By.xpath("//div[@id='content']/form/input[21]"));
    }

    public void deleteSelectedContacts() { click(By.xpath("//div[@id='content']/form[2]/div[2]/input"));
    }

    public void acceptAlert() { wd.switchTo().alert().accept();
    }

    public void clickModifyContact(int index) {
        click(By.xpath("//table[@id='maintable']/tbody/tr[" + (2 + index) + "]/td[8]/a/img"));
    }

    public void submitContactModify() { click(By.xpath("//div[@id='content']/form[1]/input[22]"));
    }

    public void createContact(ContactData contactData, boolean isCreation) {
        clickAddNewContact();
        fillContactForm(contactData, isCreation);
        submitContactForm();
        waitForAutoRedirectToContactsList();
    }

    public boolean isThereAnyContact() {
        return isElementPresent(By.xpath("//table[@id='maintable']/tbody/tr[2]/td[8]/a/img"));
    }

    public int getContactCount() {
        return wd.findElements(By.cssSelector(".center>a>img[alt=\"Details\"]")).size();
    }

    public List<ContactData> getContactList() {
        List<ContactData> contacts = new ArrayList<ContactData>();
        List<WebElement> lastName = wd.findElements(By.cssSelector("#maintable>tbody>tr>td:nth-of-type(2)"));
        List<WebElement> firstName = wd.findElements(By.cssSelector("#maintable>tbody>tr>td:nth-of-type(2)"));


        for (int i = 0; i < lastName.size(); i++){
            String lname = lastName.get(i).getText();
            String fname = firstName.get(i).getText();
            ContactData contact = new ContactData(fname, "MiddleName", lname, "Nickname", "Address string", "+74951234567", "+75551234567", "nickname@mailserver.ru", "test1");
            contacts.add(contact);
        }
        return contacts;
    }
}
