package jft.murinov.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import jft.murinov.addressbook.model.ContactData;

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

    @Parameter(names = "-f", description = "relayted to module path to file")
    public String filepath;



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
        save(contacts, new File(filepath));
    }

    private static void save(List<ContactData> contacts, File file) throws IOException {

        Writer writer = new FileWriter(file);

        for(ContactData contact : contacts){
            writer.write(String.format("%s;%s;%s;%s;%s;%s;%s;%s;%s\n",
                    contact.getFirstName(), contact.getMiddleName(), contact.getLastName(),
                    contact.getNickname(), contact.getFirstAddress(), contact.getHomePhone(),
                    contact.getMobilePhone(), contact.getFirstEmail(), contact.getGroup()));
        }
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
