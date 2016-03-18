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
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    public void modify(ContactData contact) {
        clickModifyContactById(contact.getId());
        fillContactForm(contact, false);
        submitContactModify();
        waitForAutoRedirectToContactsList();
    }

    public void delete(ContactData contactToDelete) {
        selectContactById(contactToDelete.getId());
        deleteSelectedContacts();
        acceptAlert();
        waitForAutoRedirectToContactsListAfterDelete();
    }

    public void selectContactById(int id) {
        click(By.cssSelector("input[id='" + id + "']"));
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

    public void clickModifyContactById(int id) {
        click(By.cssSelector("a[href='edit.php?id=" + id + "']"));
    }

    public void submitContactModify() { click(By.xpath("//div[@id='content']/form[1]/input[22]"));
    }

    public void create(ContactData contactData, boolean isCreation) {
        clickAddNewContact();
        fillContactForm(contactData, isCreation);
        submitContactForm();
        waitForAutoRedirectToContactsList();
    }

    public int getContactCount() {
        return wd.findElements(By.cssSelector(".center>a>img[alt=\"Details\"]")).size();
    }

    public Set<ContactData> all() {
        Set<ContactData> contacts = new HashSet<>();
        List<WebElement> idWeb = wd.findElements(By.cssSelector("#maintable>tbody>tr>td:nth-of-type(1)"));
        List<WebElement> lastName = wd.findElements(By.cssSelector("#maintable>tbody>tr>td:nth-of-type(2)"));
        List<WebElement> firstName = wd.findElements(By.cssSelector("#maintable>tbody>tr>td:nth-of-type(3)"));

        for (int i = 0; i < idWeb.size(); i++){
            int id = Integer.parseInt(idWeb.get(i).findElement(By.tagName("input")).getAttribute("id"));
            String lname = lastName.get(i).getText();
            String fname = firstName.get(i).getText();
            contacts.add(new ContactData()
                    .withId(id).withFirstName(fname).withMiddleName("MiddleName").withLastName(lname).withNickname("Nickname")
                    .withFirstAddress("Address string").withHomePhoneString("+74951234567").withMobilePhoneString("+75551234567")
                    .withFirstEmail("nickname@mailserver.ru").withGroup("test1")
            );
        }
        return contacts;
    }


}
