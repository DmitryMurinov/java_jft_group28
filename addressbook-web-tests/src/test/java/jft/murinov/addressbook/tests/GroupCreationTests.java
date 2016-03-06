package jft.murinov.addressbook.tests;

import jft.murinov.addressbook.model.GroupData;
import org.testng.annotations.Test;

public class GroupCreationTests extends TestBase{

    @Test
    public void testGroupCreation() {
        app.getNavigationHelper().goToGroupPage();
        app.getGroupHelper().clickGroupCreation();
        app.getGroupHelper().fillGroupCreationForm(new GroupData("test1", null, null));
        app.getGroupHelper().submitGroupCreationForm();
        app.getGroupHelper().returnToGroupPage();
    }
}
