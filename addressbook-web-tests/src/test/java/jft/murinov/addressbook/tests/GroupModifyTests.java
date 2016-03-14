package jft.murinov.addressbook.tests;

import jft.murinov.addressbook.model.GroupData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

/**
 * Created by Dima on 28.02.2016.
 */
public class GroupModifyTests extends TestBase{

    @Test
    public void testGroupModify(){
        app.getNavigationHelper().goToGroupPage();
        if (! app.getGroupHelper().isThereAnyGroup()){
            app.getGroupHelper().createGroup(new GroupData("test1", null, null));
        }
        List<GroupData> before = app.getGroupHelper().getGroupList();
        app.getGroupHelper().selectGroup(before.size() - 1);
        app.getGroupHelper().initGroupModify();
        GroupData group = new GroupData(before.get(before.size() - 1).getId() ,"test1", "test2", "test3");
        app.getGroupHelper().fillGroupCreationForm(group);
        app.getGroupHelper().submitGroupModify();
        app.getGroupHelper().returnToGroupPage();
        List<GroupData> after = app.getGroupHelper().getGroupList();
        Assert.assertEquals(after.size(), before.size());
        before.remove(before.size() - 1);
        before.add(group);
        Comparator<? super GroupData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
    }
}
