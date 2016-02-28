package jft.murinov.addressbook;

import org.testng.annotations.Test;

public class ContactCreationTests extends TestBase{

    @Test
    public void testContactCreation() {
        clickAddNewContact();
        fillContactForm(new ContactData("FirstName", "MiddleName", "LastName", "Nickname", "Address string", "+74951234567", "+75551234567", "nickname@mailserver.ru"));
        submitContactForm();
        waitForAutoRedirectToContactsList("nickname@mailserver.ru");
    }

}
