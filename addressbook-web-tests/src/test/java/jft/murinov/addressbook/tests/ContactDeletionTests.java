package jft.murinov.addressbook.tests;

import jft.murinov.addressbook.model.ContactData;
import jft.murinov.addressbook.model.Contacts;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.*;
import static org.testng.Assert.*;

public class ContactDeletionTests extends TestBase{

    @BeforeMethod
    public void insurePreconditions(){
        app.goTo().HomePage();
        if (app.contact().all().size() == 0){
            app.contact().create(new ContactData()
                    .withFirstName("FirstName2").withMiddleName("MiddleName").withLastName("LastName").withNickname("Nickname").withFirstAddress("Address string")
                    .withHomePhoneString("+74951234567").withMobilePhoneString("+75551234567").withFirstEmail("nickname@mailserver.ru").withGroup("test1")
                    , true);
        }
    }

    @Test(enabled = true)
    public void testContactDeletion() {
        Contacts before = app.contact().all();
        ContactData contactToDelete = before.iterator().next();
        app.contact().delete(contactToDelete);
        Contacts after = app.contact().all();
        assertThat(after.size(), equalTo(before.size() - 1));
        assertThat(after, equalTo(before.without(contactToDelete)));
    }
}
