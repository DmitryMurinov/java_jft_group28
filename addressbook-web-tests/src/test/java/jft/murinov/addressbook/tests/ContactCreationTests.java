package jft.murinov.addressbook.tests;

import jft.murinov.addressbook.model.ContactData;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class ContactCreationTests extends TestBase{

    @Test
    public void testContactCreation() {
        app.getContactHelper().createContact(new ContactData("FirstName", "MiddleName", "LastName", "Nickname", "Address string", "+74951234567", "+75551234567", "nickname@mailserver.ru", "test1"), true);
    }

}
