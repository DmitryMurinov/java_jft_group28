package jft.murinov.addressbook.appmanager;

import jft.murinov.addressbook.model.ContactData;
import jft.murinov.addressbook.model.Contacts;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

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
        if(contactData.getFirstName() != null){
        typeInfoBox(By.name("firstname"), contactData.getFirstName());}
        if(contactData.getMiddleName() != null){
        typeInfoBox(By.name("middlename"), contactData.getMiddleName());}
        if(contactData.getLastName() != null){
        typeInfoBox(By.name("lastname"), contactData.getLastName());}
        if(contactData.getNickname() != null){
        typeInfoBox(By.name("nickname"), contactData.getNickname());}
        if(contactData.getPhoto() != null){
        attachFile(By.name("photo"), contactData.getPhoto());}
        if(contactData.getFirstAddress() != null){
        typeInfoBox(By.name("address"), contactData.getFirstAddress());}
        if(contactData.getHomePhone() != null){
        typeInfoBox(By.name("home"), contactData.getHomePhone());}
        if(contactData.getMobilePhone() != null){
        typeInfoBox(By.name("mobile"), contactData.getMobilePhone());}
        if(contactData.getWorkPhone() != null){
        typeInfoBox(By.name("work"), contactData.getWorkPhone());}
        if(contactData.getFirstEmail() != null){
        typeInfoBox(By.name("email"), contactData.getFirstEmail());}
        if(contactData.getSecondEmail() != null){
        typeInfoBox(By.name("email2"), contactData.getSecondEmail());}
        if(contactData.getThirdEmail() != null){
        typeInfoBox(By.name("email3"), contactData.getThirdEmail());}

        if(contactData.getGroup() != null) {
            if (creation) {
                new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
            } else {
                Assert.assertFalse(isElementPresent(By.name("new_group")));
            }
        }
    }

    public void modify(ContactData contact) {
        clickModifyContactById(contact.getId());
        fillContactForm(contact, false);
        submitContactModify();
        contactCache = null;
        waitForAutoRedirectToContactsList();
    }

    public void delete(ContactData contactToDelete) {
        selectContactById(contactToDelete.getId());
        deleteSelectedContacts();
        acceptAlert();
        contactCache = null;
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
        WebElement checkbox = wd.findElement(By.cssSelector(String.format("input[value='%s']", id)));
        WebElement row = checkbox.findElement(By.xpath("./../.."));
        List<WebElement> cells = row.findElements(By.tagName("td"));
        cells.get(7).findElement(By.tagName("a")).click();

//        click(By.cssSelector(String.format("a[href='edit.php?id=%s']", id)));
    }

    public void clickInfoContactById(int id) {
        click(By.cssSelector(String.format("a[href='view.php?id=%s']", id)));
    }

    public void submitContactModify() { click(By.xpath("//div[@id='content']/form[1]/input[22]"));
    }

    public void create(ContactData contactData, boolean isCreation) {
        clickAddNewContact();
        fillContactForm(contactData, isCreation);
        submitContactForm();
        contactCache = null;
        waitForAutoRedirectToContactsList();
    }

    public int count() {
        return wd.findElements(By.cssSelector(".center>a>img[alt=\"Details\"]")).size();
    }

    private Contacts contactCache = null;

    public Contacts all() {
        if(contactCache != null){
            return new Contacts(contactCache);
        }

        contactCache = new Contacts();

        List<WebElement> rows = wd.findElements(By.name("entry"));

        for(WebElement row: rows){
            List<WebElement> cells = row.findElements(By.tagName("td"));
            int id = Integer.parseInt(cells.get(0).findElement(By.tagName("input")).getAttribute("id"));
            String lname = cells.get(1).getText();
            String fname = cells.get(2).getText();
            String address = cells.get(3).getText();
            String allEmails = cells.get(4).getText();
            String allPhones = cells.get(5).getText();

            contactCache.add(new ContactData()
                    .withId(id).withFirstName(fname).withMiddleName("MiddleName").withLastName(lname).withNickname("Nickname")
                    .withFirstAddress(address).withAllPhones(allPhones).withFirstEmail("nickname@mailserver.ru").withAllEmail(allEmails).withGroup("test1"));
        }

        return new Contacts(contactCache);
    }


    public ContactData infoFromEditForm(ContactData contact) {
        clickModifyContactById(contact.getId());
        String firstName = wd.findElement(By.name("firstname")).getAttribute("value");
        String middleName = wd.findElement(By.name("middlename")).getAttribute("value");
        String lastName = wd.findElement(By.name("lastname")).getAttribute("value");
        String nickname = wd.findElement(By.name("nickname")).getAttribute("value");
        String firstAddress = wd.findElement(By.name("address")).getText();
        String home = wd.findElement(By.name("home")).getAttribute("value");
        String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
        String work = wd.findElement(By.name("work")).getAttribute("value");
        String email = wd.findElement(By.name("email")).getAttribute("value");
        String email2 = wd.findElement(By.name("email2")).getAttribute("value");
        String email3 = wd.findElement(By.name("email3")).getAttribute("value");

        wd.navigate().back();
        return new ContactData().withId(contact.getId()).withFirstName(firstName).withLastName(lastName)
                .withMiddleName(middleName).withNickname(nickname)
                .withFirstAddress(firstAddress).withHomePhone(home).withMobilePhone(mobile).withWorkPhone(work)
                .withFirstEmail(email).withSecondEmail(email2).withThirdEmail(email3);
    }

    public WebElement infoFromInfoForm(ContactData contact) {
        clickInfoContactById(contact.getId());
        WebElement element = wd.findElement(By.xpath(".//*[@id='content']"));
        return element;
    }
}
