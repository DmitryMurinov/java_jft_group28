package jft.murinov.addressbook.tests;

import jft.murinov.addressbook.model.ContactData;
import jft.murinov.addressbook.model.GroupData;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class ContactCreationTests extends TestBase{

    @Test
    public void testContactCreation() {
        List<ContactData> before = app.getContactHelper().getContactList();
        ContactData contact = new ContactData("FirstName2", "MiddleName", "LastName", "Nickname", "Address string", "+74951234567", "+75551234567", "nickname@mailserver.ru", "test1");
        app.getContactHelper().createContact(contact, true);
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size() + 1);
        contact.setId(after.stream().max((o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId());
        before.add(contact);
        Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);    }
}
