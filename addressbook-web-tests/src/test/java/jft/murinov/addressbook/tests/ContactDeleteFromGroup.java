package jft.murinov.addressbook.tests;

import jft.murinov.addressbook.model.ContactData;
import jft.murinov.addressbook.model.Contacts;
import jft.murinov.addressbook.model.GroupData;
import jft.murinov.addressbook.model.Groups;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.StringTokenizer;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * Created by Dima on 16.04.2016.
 */
public class ContactDeleteFromGroup extends TestBase{

    @BeforeMethod
    public void insurePreconditions(){
        app.goTo().GroupPage();
        if (app.db().groups().size() == 0){
            app.group().create(new GroupData().withName("test1"));
        }
        app.goTo().HomePage();
        Groups groups = app.db().groups();
        if (app.db().contacts().size() == 0){
            app.contact().create(new ContactData()
                            .withFirstName("FirstName2").withMiddleName("MiddleName").withLastName("LastName").withNickname("Nickname").withFirstAddress("Address string")
                            .withHomePhone("+74951234567").withMobilePhone("+75551234567").withFirstEmail("nickname@mailserver.ru").withGroup(groups.iterator().next()));
        }
    }

    @Test(enabled = true)
    public void testContactDeleteFromGroup() throws InterruptedException {
        Contacts before = app.db().contacts();
        ContactData contactToModify = before.iterator().next();
        ContactData contact = new ContactData()
                .withId(contactToModify.getId()).withFirstName(contactToModify.getFirstName()).withMiddleName(contactToModify.getMiddleName()).withLastName(contactToModify.getLastName())
                .withNickname(contactToModify.getNickname()).withFirstAddress(contactToModify.getFirstAddress())
                .withHomePhone(contactToModify.getHomePhoneString()).withMobilePhone(contactToModify.getMobilePhoneString())
                .withFirstEmail(contactToModify.getFirstEmail());

        WebElement contactInfo = app.contact().infoFromInfoForm(contact);
        String info = contactInfo.getText();
        GroupData group;
        Contacts after;

        if(!info.contains("Member of")){
            app.goTo().HomePage();
            group = new GroupData().withName(app.contact().findGroupName());
            app.contact().addContactToGroup(contact, group);
            contact = contact.withGroup(group);
            after = app.db().contacts();
            assertThat(after, is(before.without(contactToModify).withAdded(contact)));
            contact = contact.withoutGroup(group);
            app.goTo().HomePage();
            app.contact().deleteContactFromGroup(contact, group);
            Thread.sleep(500); //without it page loads too fast
            app.goTo().HomePage();

        } else if (info.contains("Member of")){
            app.goTo().HomePage();
            Groups groupsInContact = findAllGroupsForContact(info);
            group = groupsInContact.iterator().next();
            contact = contact.withoutGroup(group);
            app.contact().deleteContactFromGroup(contact, group);
            Thread.sleep(500); //without it page loads too fast
            app.goTo().HomePage();
        }

        after = app.db().contacts();

        assertThat(after, is(before.without(contactToModify).withAdded(contact)));
    }

    public Groups findAllGroupsForContact(String info) {
        int splitPoint = info.lastIndexOf("Member of: ");
        String groups = info.substring(splitPoint + 11, info.length());
        Groups contactGroups = new Groups();
        StringTokenizer st = new StringTokenizer(groups, ",");
        while(st.hasMoreTokens()){
            contactGroups.add(new GroupData().withName(st.nextToken().replaceAll("^\\s+", "").replaceAll("\\s+$", "")));
        }
        return contactGroups;
    }


}
