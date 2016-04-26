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
        if (app.db().groups().size() == 0){
            app.goTo().GroupPage();
            app.group().create(new GroupData().withName("test1"));
        }
    }

    @Test
    public void testGroupModify(){
        Groups before = app.db().groups();
        GroupData groupToModify = before.iterator().next();
        GroupData group = new GroupData()
                .withId(groupToModify.getId()).withName("test1mod").withHeader("test2").withFooter("test3");
        app.goTo().GroupPage();
        app.group().modify(group);
        assertThat(app.group().count(), equalTo(before.size()));
        Groups after = app.db().groups();
        assertThat(after, equalTo(before.without(groupToModify).withAdded(group)));
        verifyGroupListInUI();
    }

}
