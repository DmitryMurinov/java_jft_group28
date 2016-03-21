package jft.murinov.addressbook.tests;

import jft.murinov.addressbook.model.GroupData;
import jft.murinov.addressbook.model.Groups;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

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
        Groups before = app.group().all();
        GroupData groupToModify = before.iterator().next();
        GroupData group = new GroupData()
                .withId(groupToModify.getId()).withName("test1").withHeader("test2").withFooter("test3");
        app.group().modify(group);
        Groups after = app.group().all();
        assertThat(after.size(), equalTo(before.size()));
        assertThat(after, equalTo(before.without(groupToModify).withAdded(group)));
    }

}
