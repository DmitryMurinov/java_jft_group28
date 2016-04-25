package jft.murinov.addressbook.tests;

import jft.murinov.addressbook.model.ContactData;
import jft.murinov.addressbook.model.Contacts;
import jft.murinov.addressbook.model.GroupData;
import jft.murinov.addressbook.model.Groups;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.*;

public class ContactDeletionTests extends TestBase{

    @BeforeMethod
    public void insurePreconditions(){
        if (app.db().groups().size() == 0){
            app.goTo().GroupPage();
            app.group().create(new GroupData().withName("test1"));
        }
        Groups groups = app.db().groups();
        if (app.db().contacts().size() == 0){
            app.goTo().HomePage();
            app.contact().create(new ContactData()
                    .withFirstName("FirstName2").withMiddleName("MiddleName").withLastName("LastName").withNickname("Nickname").withFirstAddress("Address string")
                    .withHomePhone("+74951234567").withMobilePhone("+75551234567").withFirstEmail("nickname@mailserver.ru").withGroup(groups.iterator().next()));
        }
    }

    @Test(enabled = true)
    public void testContactDeletion() {
        app.goTo().HomePage();
        Contacts before = app.db().contacts();
        ContactData contactToDelete = before.iterator().next();
        app.goTo().HomePage();
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        app.contact().delete(contactToDelete);
        assertThat(app.contact().count(), equalTo(before.size() - 1));
        Contacts after = app.db().contacts();
        assertThat(after, equalTo(before.without(contactToDelete)));
        verifyContactListInUI();
    }
}
