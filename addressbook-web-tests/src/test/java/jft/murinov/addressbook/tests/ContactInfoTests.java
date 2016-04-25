package jft.murinov.addressbook.tests;

import jft.murinov.addressbook.model.ContactData;
import jft.murinov.addressbook.model.GroupData;
import jft.murinov.addressbook.model.Groups;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by d.murinov on 22.03.2016.
 */
public class ContactInfoTests extends TestBase{

    @BeforeMethod
    public void insurePreconditions(){
        app.goTo().GroupPage();
        if (app.db().groups().size() == 0){
            app.group().create(new GroupData().withName("test1"));
        }
        Groups groups = app.db().groups();
        app.goTo().HomePage();
        if (app.db().contacts().size() == 0){
            app.contact().create(new ContactData()
                            .withFirstName("FirstName2").withMiddleName("MiddleName").withLastName("LastName").withNickname("Nickname")
                            .withTitle("Software Engineer").withCompany("Google").withFirstAddress("Address string")
                            .withHomePhone("+74951234567").withMobilePhone("+75551234567").withWorkPhone("22-55").withFax("12345-888")
                            .withFirstEmail("nickname@mailserver.ru").withSecondEmail("nickname2@mailserver.ru").withThirdEmail("nickname2@mailserver.ru")
                            .withHomepage("www.google.com").withGroup(groups.iterator().next()).withAddress2("Miami beach road 88").withPhone2("USA-MIAMI-DIMA").withNotes("It's good to have a dream"));
        }
    }

    @Test(enabled = true)
    public void testContactPhones(){
        app.goTo().HomePage();
        ContactData contact = app.db().contacts().iterator().next();
        ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);
        WebElement contactInfo = app.contact().infoFromInfoForm(contact);
        String contactI = cleaned(contactInfo);
        String contactFromEdit = mergeContactInfo(contactInfoFromEditForm);
        assertThat(contactI, equalTo(contactFromEdit));
    }

    private String mergeContactInfo(ContactData contact) {
        return Arrays.asList(contact.getFirstName(), contact.getMiddleName(), contact.getLastName(), contact.getNickname()
                        , contact.getFirstAddress(), contact.getHomePhone(), contact.getMobilePhone(), contact.getWorkPhone()
                        , contact.getFirstEmail(), contact.getSecondEmail(), contact.getThirdEmail())
                .stream().filter(s -> ! s.equals(""))
                .map(ContactInfoTests::cleaned)
                .collect(Collectors.joining(""));
    }

    private String cleaned(WebElement contactInfo){
        String contact = contactInfo.getText().replaceAll("www.mailserver.ru|H:|M:|W:|Member of.*|\n| |\\(|\\)", "");
        return contact;
    }

    public static String cleaned(String phone){
        return  phone.replaceAll(" ", "");
    }


    
}
