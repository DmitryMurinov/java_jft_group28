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
 * Created by Dima on 05.04.2016.
 */
public class ContactAddToGroup extends TestBase{

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
    public void testContactAddToGroup() throws InterruptedException {
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

        if(!info.contains("Member of")){
            app.goTo().HomePage();
            group = new GroupData().withName(app.contact().findGroupName());
            app.contact().addContactToGroup(contact, group);
            contact = contact.withGroup(group);
        } else if (info.contains("Member of")){
            app.goTo().HomePage();
            Groups groupsInContact = findAllGroupsForContact(info);
            Groups groupsToAdd = app.contact().findAllGroupsToAdd();

            group = findFreeGroup(groupsInContact, groupsToAdd);
            if(group == null){
                int suffix = app.contact().randomNumber(10000, 99999);
                group = new GroupData().withName("test " + suffix);
                app.goTo().GroupPage();
                app.group().create(group);
            }
            Thread.sleep(500); //without it page loads too fast, with no added group >> exception
            app.goTo().HomePage();
            app.contact().addContactToGroup(contact, group);
        }

        Contacts after = app.db().contacts();

        assertThat(after, is(before.without(contactToModify).withAdded(contact)));
    }

    private GroupData findFreeGroup(Groups groupsInContact, Groups groupsToAdd) {
        if(groupsInContact.equals(groupsToAdd)){return null;}
        GroupData groupToAdd = new GroupData();
        for(GroupData group: groupsToAdd){
            if(!groupsInContact.contains(group)){return group;}
        }
        return groupToAdd;
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


