package jft.murinov.addressbook.tests;

import jft.murinov.addressbook.model.ContactData;
import org.testng.annotations.Test;

public class ContactCreationTests extends TestBase{

    @Test
    public void testContactCreation() {
        app.clickAddNewContact();
        app.fillContactForm(new ContactData("FirstName", "MiddleName", "LastName", "Nickname", "Address string", "+74951234567", "+75551234567", "nickname@mailserver.ru"));
        app.submitContactForm();
        app.waitForAutoRedirectToContactsList("nickname@mailserver.ru");
    }

}
