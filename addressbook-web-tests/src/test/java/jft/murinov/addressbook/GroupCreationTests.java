package jft.murinov.addressbook;

import org.testng.annotations.Test;

public class GroupCreationTests extends TestBase{

    @Test
    public void testGroupCreation() {
        goToGroupPage();
        clickGroupCreation();
        fillGroupCreationForm(new GroupData("test1", "test2", "test3"));
        submitGroupCreationForm();
        returnToGroupPage();
    }

}
