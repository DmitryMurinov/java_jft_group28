package jft.murinov.addressbook.tests;

import jft.murinov.addressbook.model.GroupData;
import org.testng.annotations.Test;

/**
 * Created by Dima on 28.02.2016.
 */
public class GroupModifyTests extends TestBase{

    @Test
    public void testGroupModify(){
        app.getNavigationHelper().goToGroupPage();
        app.getGroupHelper().selectGroup();
        app.getGroupHelper().initGroupModify();
        app.getGroupHelper().fillGroupCreationForm(new GroupData("test1", "test2mod", "test3mod"));
        app.getGroupHelper().submitGroupModify();
        app.getGroupHelper().returnToGroupPage();
    }
}
