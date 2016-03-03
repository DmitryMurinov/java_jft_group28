package jft.murinov.addressbook.tests;

import org.testng.annotations.Test;

public class ContactDeletionTests extends TestBase{

    @Test
    public void testContactDeletion() {
        app.getNavigationHelper().goToHomePage();
        app.getContactHelper().selectContact(2); //Don't forget to enter row number
        app.getContactHelper().deleteSelectedContacts();
        app.getContactHelper().acceptAlert();
        app.getContactHelper().waitForAutoRedirectToContactsListAfterDelete(".fdTableSortTrigger");
    }
}
