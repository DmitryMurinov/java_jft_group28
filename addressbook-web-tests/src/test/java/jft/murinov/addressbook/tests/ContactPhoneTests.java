package jft.murinov.addressbook.tests;

import jft.murinov.addressbook.model.ContactData;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.*;

/**
 * Created by d.murinov on 22.03.2016.
 */
public class ContactPhoneTests extends TestBase{

    @BeforeMethod
    public void insurePreconditions(){
        app.goTo().HomePage();
        if (app.db().contacts().size() == 0){
            app.contact().create(new ContactData()
                            .withFirstName("FirstName2").withMiddleName("MiddleName").withLastName("LastName").withNickname("Nickname").withFirstAddress("Address string")
                            .withHomePhone("+74951234567").withMobilePhone("+75551234567").withWorkPhone("22-55").withFirstEmail("nickname@mailserver.ru")
                            .withSecondEmail("nickname2@mailserver.ru").withThirdEmail("nickname2@mailserver.ru")//.withGroup("test1")
                    , true);
        }
    }

    @Test(enabled = true)
    public void testContactPhones(){
        app.goTo().HomePage();

        ContactData contact = app.contact().all().iterator().next();

        ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

        assertThat(contact.getAllPhones(), equalTo(mergePhones(contactInfoFromEditForm)));
        assertThat(contact.getFirstAddress(), equalTo(contactInfoFromEditForm.getFirstAddress()));
        assertThat(contact.getAllEmails(), equalTo(mergeEmails(contactInfoFromEditForm)));
    }
}
