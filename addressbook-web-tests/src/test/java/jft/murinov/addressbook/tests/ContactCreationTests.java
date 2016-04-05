package jft.murinov.addressbook.tests;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.thoughtworks.xstream.XStream;
import jft.murinov.addressbook.model.ContactData;
import jft.murinov.addressbook.model.Contacts;
import jft.murinov.addressbook.model.GroupData;
import jft.murinov.addressbook.model.Groups;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase{


    @DataProvider
    public Iterator<Object[]> validContactsCSV() throws IOException {

        try(
        BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.csv")))
        ){
        List<Object[]> list = new ArrayList<Object[]>();
        File photo = new File("src/test/resources/people_to_remember.jpg");

        String line = reader.readLine();



        while (line != null) {
            String[] split = line.split(";");
            list.add(new Object[]{new ContactData()
                    .withFirstName(split[0]).withMiddleName(split[1]).withLastName(split[2]).withNickname(split[3]).withFirstAddress(split[4])
                    .withHomePhone(split[5]).withMobilePhone(split[6]).withFirstEmail(split[7]).withPhoto(photo)});
            line = reader.readLine();
        }

        return list.iterator();
        }
    }


    @DataProvider
    public Iterator<Object[]> validContactsXML() throws IOException {

        try(
        BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.xml")))
        ) {
            String xml = "";
            String line = reader.readLine();

            while (line != null) {
                xml += line;
                line = reader.readLine();
            }
            XStream xStream = new XStream();
            xStream.processAnnotations(ContactData.class);
            List<ContactData> contacts = (List<ContactData>) xStream.fromXML(xml);

            return contacts.stream().map((g) -> new Object[]{g}).collect(Collectors.toList()).iterator();
        }
    }

    @DataProvider
    public Iterator<Object[]> validContactsJSON() throws IOException {

        try
        (
        BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.json")))
        ) {
            String json = "";
            String line = reader.readLine();

            while (line != null) {
                json += line;
                line = reader.readLine();
            }

            Gson gson = new Gson();

            List<ContactData> contacts = gson.fromJson(json, new TypeToken<List<ContactData>>() {
            }.getType());

            return contacts.stream().map((g) -> new Object[]{g}).collect(Collectors.toList()).iterator();
        }
    }

    @BeforeMethod
    public void insurePrecondition(){
        app.goTo().GroupPage();
        if (app.db().groups().size() == 0){
            app.group().create(new GroupData().withName("test1"));
        }
    }

    @Test(enabled = true, dataProvider = "validContactsJSON")
    public void testContactCreation(ContactData contact) {
        Groups groups = app.db().groups();
        app.contact().openStartPage();
        Contacts before = app.db().contacts();
        app.contact().create(contact, true);
        assertThat(app.contact().count(), equalTo(before.size() + 1));
        Contacts after = app.db().contacts();
        assertThat(after, equalTo(
                before.withAdded(contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
        verifyContactListInUI();
    }

    @Test(enabled = true)
    public void testBadContactCreation() {
        Groups groups = app.db().groups();
        ContactData contact = new ContactData()
                .withFirstName("FirstName2'").withMiddleName("MiddleName").withLastName("LastName").withNickname("Nickname").withFirstAddress("Address string")
                .withHomePhone("+74951234567").withMobilePhone("+75551234567").withFirstEmail("nickname@mailserver.ru").withGroup(groups.iterator().next());
        Contacts before = app.db().contacts();
        app.contact().create(contact, true);
        assertThat(app.contact().count(), equalTo(before.size()));
        Contacts after = app.db().contacts();
        assertThat(after, equalTo(before));
        verifyContactListInUI();
    }

}
