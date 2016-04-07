package jft.murinov.addressbook.tests;

import jft.murinov.addressbook.appmanager.ContactHelper;
import jft.murinov.addressbook.model.ContactData;
import jft.murinov.addressbook.model.Contacts;
import jft.murinov.addressbook.model.GroupData;
import jft.murinov.addressbook.model.Groups;
import org.hamcrest.Matchers;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.collection.IsIterableContainingInOrder.contains;


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
                            .withHomePhone("+74951234567").withMobilePhone("+75551234567").withFirstEmail("nickname@mailserver.ru").withGroup(groups.iterator().next())
                    , true);
        }
    }

    @Test(enabled = true)
    public void testContactAddToGroup(){
        Contacts contacts = app.contact().all();
//        Groups groups = app.db().groups();
        Contacts before = app.db().contacts();
        ContactData contactToModify = before.iterator().next();
        ContactData contact = new ContactData()
                .withId(contactToModify.getId()).withFirstName(contactToModify.getFirstName()).withMiddleName(contactToModify.getMiddleName()).withLastName(contactToModify.getLastName())
                .withNickname(contactToModify.getNickname()).withFirstAddress(contactToModify.getFirstAddress())
                .withHomePhone(contactToModify.getHomePhoneString()).withMobilePhone(contactToModify.getMobilePhoneString())
                .withFirstEmail(contactToModify.getFirstEmail());

        WebElement contactInfo = app.contact().infoFromInfoForm(contact);
        GroupData group;


        if(!contactInfo.getText().contains("Member of")){
            app.goTo().HomePage();
            group = new GroupData().withName(app.contact().findGroupName());
            app.contact().addContactToGroup(contact, group);
            contact = contact.withGroup(group);
        } else if (contactInfo.getText().contains("Member of")){
            app.goTo().HomePage();
            Groups groupsInContact = app.contact().findAllGroupsForContact(contactInfo);
//            System.out.println(groupsInContact);
            Groups groupsToAdd = app.contact().findAllGroupsToAdd();
            System.out.println(groupsToAdd);

//            group = findFreeGroup();
//            if(group == null){
//                group = new GroupData().withName("test1");
//                app.group().create(group);
//            }
//            addContactToGroup(contact, group);
        }
/*

        Contacts after = app.db().contacts();

        System.out.println("from java");
        System.out.println(contact);
        System.out.println("from db");
        System.out.println(contacts);

        assertThat(after, is(before.without(contactToModify).withAdded(contact)));
*/

    }

}


