package jft.murinov.addressbook.tests;

import jft.murinov.addressbook.model.ContactData;
import jft.murinov.addressbook.model.Contacts;
import jft.murinov.addressbook.model.GroupData;
import jft.murinov.addressbook.model.Groups;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.swing.text.html.HTMLDocument;
import java.util.Iterator;

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
        Contacts contacts = app.db().contacts();
        Groups groups = app.db().groups();
        ContactData contactWithoutGroup;
        Iterator it = contacts.iterator();
        System.out.println(contacts);
        while(it.hasNext()){
            Object contact = it.next();
            System.out.println(contact);



        }





    }



}
