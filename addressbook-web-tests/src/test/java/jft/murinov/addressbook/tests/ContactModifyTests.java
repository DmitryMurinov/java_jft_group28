package jft.murinov.addressbook.tests;

import jft.murinov.addressbook.model.ContactData;
import jft.murinov.addressbook.model.Contacts;
import jft.murinov.addressbook.model.GroupData;
import jft.murinov.addressbook.model.Groups;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactModifyTests extends TestBase {

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
    public void testContactModify() {
        app.contact().openStartPage();
        Contacts before = app.db().contacts();
        ContactData contactToModify = before.iterator().next();
        ContactData contact = new ContactData()
                .withId(contactToModify.getId()).withFirstName("FirstNameModified").withMiddleName("MiddleNameModified").withLastName("LastNameModified")
                .withNickname("NicknameModified").withFirstAddress("Address stringModified").withHomePhone("+74957654321").withMobilePhone("+75557654321")
                .withFirstEmail("nicknameModified@mailserver.ru");

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        app.contact().modify(contact);
        assertThat(app.contact().count(), equalTo(before.size()));
        Contacts after = app.db().contacts();
        assertThat(after, equalTo(before.without(contactToModify).withAdded(contact)));
        verifyContactListInUI();
    }



}
