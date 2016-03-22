package jft.murinov.addressbook.tests;

import jft.murinov.addressbook.model.GroupData;
import jft.murinov.addressbook.model.Groups;
import org.testng.annotations.Test;

import static com.google.common.base.Predicates.equalTo;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.*;

public class GroupCreationTests extends TestBase{

    @Test
    public void testGroupCreation() {
        app.goTo().GroupPage();
        Groups before = app.group().all();
        GroupData group = new GroupData().withName("test2");
        app.group().create(group);
        assertThat(app.group().count(), equalTo(before.size() + 1));
        Groups after = app.group().all();
        assertThat(after, equalTo(
                before.withAdded(group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
    }

    @Test
    public void testBadGroupCreation() {
        app.goTo().GroupPage();
        Groups before = app.group().all();
        GroupData group = new GroupData().withName("test2'");
        app.group().create(group);
        assertThat(app.group().count(), equalTo(before.size()));
        Groups after = app.group().all();
        assertThat(after, equalTo(before));
    }
}
