package jft.murinov.addressbook.tests;

import jft.murinov.addressbook.model.ContactData;
import org.testng.annotations.Test;

/**
 * Created by d.murinov on 22.03.2016.
 */
public class ContactPhoneTests extends TestBase{

    @Test(enabled = false)
    public void testContactPhones(){
        app.goTo().HomePage();
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);


    }


}
