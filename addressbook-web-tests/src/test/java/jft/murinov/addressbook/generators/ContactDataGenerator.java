package jft.murinov.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.thoughtworks.xstream.XStream;
import jft.murinov.addressbook.model.ContactData;
import jft.murinov.addressbook.model.GroupData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by d.murinov on 24.03.2016.
 */
public class ContactDataGenerator {

    @Parameter(names = "-c", description = "how many contact to generate")
    public int contactsTotal;

    @Parameter(names = "-path", description = "file name and path relayted to module folder")
    public String filename;

    @Parameter(names = "-f", description = "file format, csv or xml")
    public String format;

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
        }else {
            System.out.println("Unrecognized format, choose csv or xml please " + format);
        }
    }

    private static void saveAsCSV(List<ContactData> contacts, File file) throws IOException {

        Writer writer = new FileWriter(file);

        for(ContactData contact : contacts){
            writer.write(String.format("%s;%s;%s;%s;%s;%s;%s;%s;%s\n",
                    contact.getFirstName(), contact.getMiddleName(), contact.getLastName(),
                    contact.getNickname(), contact.getFirstAddress(), contact.getHomePhone(),
                    contact.getMobilePhone(), contact.getFirstEmail(), contact.getGroup()));
        }
        writer.close();

    }

    private void saveAsXML(List<ContactData> contacts, File file) throws IOException {
        System.out.println(file.getAbsolutePath());
        XStream xStream = new XStream();
        xStream.processAnnotations(ContactData.class);
        String xml = xStream.toXML(contacts);
        Writer writer = new FileWriter(file);
        writer.write(xml);
        writer.close();
    }

    private static List<ContactData> generateContact(int totalContacts) {
        List<ContactData> contacts = new ArrayList<ContactData>();
        for(int i = 0; i < totalContacts; i++){
            contacts.add(new ContactData()
                    .withFirstName(String.format("FirstNameGen %s", i)).withMiddleName(String.format("MiddleNameGen %s", i))
                    .withLastName(String.format("LastNameGen %s", i)).withNickname(String.format("NicknameGen %s", i))
                    .withFirstAddress(String.format("Address string Generated %s", i))
                    .withHomePhone("+74951234567").withMobilePhone("+75551234567").withFirstEmail(String.format("nicknameGen%s@mailserver.ru", i))
                    .withGroup("test1"))
            ;
        }
        return contacts;
    }
}
