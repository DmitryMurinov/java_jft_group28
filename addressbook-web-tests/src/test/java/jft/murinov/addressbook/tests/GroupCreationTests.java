package jft.murinov.addressbook.tests;

import jft.murinov.addressbook.model.GroupData;
import org.testng.annotations.Test;

public class GroupCreationTests extends TestBase{

    @Test
    public void testGroupCreation() {
        app.getNavigationHelper().goToGroupPage();
        app.getGroupHelper().clickGroupCreation();
        app.getGroupHelper().fillGroupCreationForm(new GroupData("test1", "test2", "test3"));
        app.getGroupHelper().submitGroupCreationForm();
        app.getGroupHelper().returnToGroupPage();
    }

}
