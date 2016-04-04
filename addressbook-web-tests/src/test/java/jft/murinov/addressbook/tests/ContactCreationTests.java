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
/*

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
                    .withHomePhone(split[5]).withMobilePhone(split[6]).withFirstEmail(split[7]).withGroup(split[8]).withPhoto(photo)});
            line = reader.readLine();
        }

        return list.iterator();
        }
    }
*/

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

    /**
     * Created by d.murinov on 24.03.2016.
     */
    public static class ContactDataGenerator extends TestBase{

        @Parameter(names = "-c", description = "how many contact to generate")
        public int contactsTotal;

        @Parameter(names = "-path", description = "file name and path relayed to module folder")
        public String filename;

        @Parameter(names = "-f", description = "file format, csv, xml or json")
        public String format;

        @BeforeMethod
        public void insurePrecondition(){
            app.goTo().GroupPage();
            if (app.db().groups().size() == 0){
                app.group().create(new GroupData().withName("test1"));
            }
        }

        public static void main(String[] args) throws IOException {

            ContactDataGenerator generator = new ContactDataGenerator();
            JCommander jCommander = new JCommander(generator);
            try {
                jCommander.parse(args);
            }catch (ParameterException pe){
                jCommander.usage();
                return;
            }
            generator.run();
        }

        private void run() throws IOException {
            List<ContactData> contacts = generateContact(contactsTotal);
            if(format.equals("csv")){
                filename += "csv";
                saveAsCSV(contacts, new File(filename));
            }else
            if(format.equals("xml")){
                filename += "xml";
                saveAsXML(contacts, new File(filename));
                return;
            }else
            if(format.equals("json")) {
                filename += "json";
                saveAsJSON(contacts, new File(filename));
                return;
            }else
            {
                System.out.println("Unrecognized format, choose csv or xml please " + format);
            }
        }

        private static void saveAsCSV(List<ContactData> contacts, File file) throws IOException {

            try(
            Writer writer = new FileWriter(file);
            ) {
                for (ContactData contact : contacts) {
                    writer.write(String.format("%s;%s;%s;%s;%s;%s;%s;%s;%s\n",
                            contact.getFirstName(), contact.getMiddleName(), contact.getLastName(),
                            contact.getNickname(), contact.getFirstAddress(), contact.getHomePhone(),
                            contact.getMobilePhone(), contact.getFirstEmail(), contact.getGroups()));
                }
            }
        }

        private void saveAsXML(List<ContactData> contacts, File file) throws IOException {
            XStream xStream = new XStream();
            xStream.processAnnotations(ContactData.class);
            String xml = xStream.toXML(contacts);
            try(
            Writer writer = new FileWriter(file);
            ) {
                writer.write(xml);
            }
        }

        private void saveAsJSON(List<ContactData> contacts, File file) throws IOException {
            Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setPrettyPrinting().create();
            String json = gson.toJson(contacts);
            try(
            Writer writer = new FileWriter(file);
            ) {
                writer.write(json);
            }
        }

        private static List<ContactData> generateContact(int totalContacts) {
            List<ContactData> contacts = new ArrayList<ContactData>();
            Groups groups = app.db().groups();
            for(int i = 0; i < totalContacts; i++){
                contacts.add(new ContactData()
                        .withFirstName(String.format("FirstNameGen %s", i)).withMiddleName(String.format("MiddleNameGen %s", i))
                        .withLastName(String.format("LastNameGen %s", i)).withNickname(String.format("NicknameGen %s", i))
                        .withFirstAddress(String.format("Address string Generated %s", i))
                        .withHomePhone("+74951234567").withMobilePhone("+75551234567").withFirstEmail(String.format("nicknameGen%s@mailserver.ru", i))
    //                    .withGroup(groups.iterator().next())
                )
                ;
            }
            return contacts;
        }
    }
}
