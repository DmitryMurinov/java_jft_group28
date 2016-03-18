package jft.murinov.addressbook.tests;

import jft.murinov.addressbook.model.ContactData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Set;

public class ContactCreationTests extends TestBase{

    @Test(enabled = true)
    public void testContactCreation() {
        Set<ContactData> before = app.contact().all();
        ContactData contact = new ContactData()
                .withFirstName("FirstName2").withMiddleName("MiddleName").withLastName("LastName").withNickname("Nickname").withFirstAddress("Address string")
                .withHomePhoneString("+74951234567").withMobilePhoneString("+75551234567").withFirstEmail("nickname@mailserver.ru").withGroup("test1");
        app.contact().create(contact, true);
        Set<ContactData> after = app.contact().all();
        Assert.assertEquals(after.size(), before.size() + 1);
        contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt());
        before.add(contact);
        Assert.assertEquals(before, after);    }
}
