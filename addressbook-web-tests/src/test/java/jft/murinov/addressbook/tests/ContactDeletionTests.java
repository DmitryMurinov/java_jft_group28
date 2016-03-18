package jft.murinov.addressbook.tests;

import jft.murinov.addressbook.model.ContactData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Set;

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
        Set<ContactData> before = app.contact().all();
        ContactData contactToDelete = before.iterator().next();
        app.contact().delete(contactToDelete);
        Set<ContactData> after = app.contact().all();
        Assert.assertEquals(after.size(), before.size() - 1);
        before.remove(contactToDelete);
        Assert.assertEquals(before, after);
    }
}
