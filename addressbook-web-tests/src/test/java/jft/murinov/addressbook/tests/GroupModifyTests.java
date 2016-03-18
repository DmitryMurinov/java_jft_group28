package jft.murinov.addressbook.tests;

import jft.murinov.addressbook.model.GroupData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Set;

/**
 * Created by Dima on 28.02.2016.
 */
public class GroupModifyTests extends TestBase{

    @BeforeMethod
    public void insurePrecondition(){
        app.goTo().GroupPage();
        if (app.group().all().size() == 0){
            app.group().create(new GroupData().withName("test1"));
        }
    }

    @Test
    public void testGroupModify(){
        Set<GroupData> before = app.group().all();
        GroupData groupToModify = before.iterator().next();
        GroupData group = new GroupData()
                .withId(groupToModify.getId()).withName("test1").withHeader("test2").withFooter("test3");
        app.group().modify(group);
        Set<GroupData> after = app.group().all();
        Assert.assertEquals(after.size(), before.size());
        before.remove(groupToModify);
        before.add(group);
        Assert.assertEquals(before, after);
    }

}
