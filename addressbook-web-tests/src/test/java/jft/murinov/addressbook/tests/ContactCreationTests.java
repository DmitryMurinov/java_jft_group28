package jft.murinov.addressbook.tests;

import jft.murinov.addressbook.model.ContactData;
import jft.murinov.addressbook.model.Contacts;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.*;

public class ContactCreationTests extends TestBase{

    @Test(enabled = true)
    public void testContactCreation() {
        Contacts before = app.contact().all();
        ContactData contact = new ContactData()
                .withFirstName("FirstName2").withMiddleName("MiddleName").withLastName("LastName").withNickname("Nickname").withFirstAddress("Address string")
                .withHomePhoneString("+74951234567").withMobilePhoneString("+75551234567").withFirstEmail("nickname@mailserver.ru").withGroup("test1");
        app.contact().create(contact, true);
        Contacts after = app.contact().all();
        assertThat(after.size(), equalTo(before.size() + 1));
        assertThat(after, equalTo(
                before.withAdded(contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
    }
}
