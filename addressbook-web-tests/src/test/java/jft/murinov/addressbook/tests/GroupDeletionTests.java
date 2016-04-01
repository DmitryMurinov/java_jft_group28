package jft.murinov.addressbook.tests;

import jft.murinov.addressbook.model.GroupData;
import jft.murinov.addressbook.model.Groups;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupDeletionTests extends TestBase {

    @BeforeMethod
    public void insurePrecondition(){
        app.goTo().GroupPage();
        if (app.db().groups().size() == 0){
            app.group().create(new GroupData().withName("test1"));
        }
    }

    @Test
    public void testGroupDeletion() {
        Groups before = app.db().groups();
        GroupData groupToDelete = before.iterator().next();
        app.group().delete(groupToDelete);
        assertThat(app.group().count(), equalTo(before.size() - 1));
        Groups after = app.db().groups();
        assertThat(after, equalTo(before.without(groupToDelete)));
        verifyGroupListInUI();
    }
}