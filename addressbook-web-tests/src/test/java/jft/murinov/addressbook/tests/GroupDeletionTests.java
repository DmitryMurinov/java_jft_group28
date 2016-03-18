package jft.murinov.addressbook.tests;

import jft.murinov.addressbook.model.GroupData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Set;

public class GroupDeletionTests extends TestBase {

    @BeforeMethod
    public void insurePrecondition(){
        app.goTo().GroupPage();
        if (app.group().all().size() == 0){
            app.group().create(new GroupData().withName("test1"));
        }
    }

    @Test
    public void testGroupDeletion() {
        Set<GroupData> before = app.group().all();
        GroupData groupToDelete = before.iterator().next();
        app.group().delete(groupToDelete);
        Set<GroupData> after = app.group().all();
        Assert.assertEquals(after.size(), before.size() - 1);
        before.remove(groupToDelete);
        Assert.assertEquals(before, after);
    }

}
