package jft.murinov.addressbook.tests;

import jft.murinov.addressbook.model.ContactData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashSet;
import java.util.List;

public class ContactModifyTests extends TestBase {

    @Test
    public void testContactModify() {
        if (! app.getContactHelper().isThereAnyContact()){
            app.getContactHelper().createContact(new ContactData("FirstName", "MiddleName", "LastName", "Nickname", "Address string", "+74951234567", "+75551234567", "nickname@mailserver.ru", "test1"), true);
        }
        List<ContactData> before = app.getContactHelper().getContactList();
        app.getContactHelper().clickModifyContact(before.size() - 1);
        ContactData contact = new ContactData(before.get(before.size() - 1).getId(), "FirstNameModified", "MiddleNameModified", "LastNameModified", "NicknameModified", "Address stringModified", "+74957654321", "+75557654321", "nicknameModified@mailserver.ru", null);
        app.getContactHelper().fillContactForm(contact, false);
        app.getContactHelper().submitContactModify();
        app.getContactHelper().waitForAutoRedirectToContactsList();
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size());
        before.remove(before.size() - 1);
        before.add(contact);
        Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
    }

}
