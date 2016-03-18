package jft.murinov.addressbook.tests;

import jft.murinov.addressbook.model.ContactData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Set;

public class ContactModifyTests extends TestBase {

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
    public void testContactModify() {
        Set<ContactData> before = app.contact().all();
        ContactData contactToModify = before.iterator().next();
        ContactData contact = new ContactData()
                .withId(contactToModify.getId()).withFirstName("FirstNameModified").withMiddleName("MiddleNameModified").withLastName("LastNameModified")
                .withNickname("NicknameModified").withFirstAddress("Address stringModified").withHomePhoneString("+74957654321").withMobilePhoneString("+75557654321")
                .withFirstEmail("nicknameModified@mailserver.ru");
        app.contact().modify(contact);
        Set<ContactData> after = app.contact().all();
        Assert.assertEquals(after.size(), before.size());
        before.remove(contactToModify);
        before.add(contact);
        Assert.assertEquals(before, after);
    }



}
