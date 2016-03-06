package jft.murinov.addressbook.tests;

import jft.murinov.addressbook.model.ContactData;
import org.testng.annotations.Test;

public class ContactModifyTests extends TestBase{

    @Test
    public void testContactModify() {
        app.getContactHelper().clickModifyContact();
        app.getContactHelper().fillContactForm(new ContactData("FirstNameModified", "MiddleNameModified", "LastNameModified", "NicknameModified", "Address stringModified", "+74957654321", "+75557654321", "nicknameModified@mailserver.ru", null), false);
        app.getContactHelper().submitContactModify();
        app.getContactHelper().waitForAutoRedirectToContactsList("nicknameModified@mailserver.ru");
    }

}
