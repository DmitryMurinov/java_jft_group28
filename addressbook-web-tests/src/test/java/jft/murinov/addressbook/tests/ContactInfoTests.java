package jft.murinov.addressbook.tests;

import jft.murinov.addressbook.model.ContactData;
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
        app.goTo().HomePage();
        if (app.contact().all().size() == 0){
            app.contact().create(new ContactData()
                            .withFirstName("FirstName2").withMiddleName("MiddleName").withLastName("LastName").withNickname("Nickname")
                            .withTitle("Software Engineer").withCompany("Google").withFirstAddress("Address string")
                            .withHomePhone("+74951234567").withMobilePhone("+75551234567").withWorkPhone("22-55").withFax("12345-888")
                            .withFirstEmail("nickname@mailserver.ru").withSecondEmail("nickname2@mailserver.ru").withThirdEmail("nickname2@mailserver.ru")
                            .withHomepage("www.google.com").withGroup("test1").withAddress2("Miami beach road 88").withPhone2("USA-MIAMI-DIMA").withNotes("It's good to have a dream")
                    , true);
        }
    }

    @Test(enabled = true)
    public void testContactPhones(){
        app.goTo().HomePage();

        ContactData contact = app.contact().all().iterator().next();

        ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

        WebElement contactInfo = app.contact().infoFromInfoForm(contact);

        System.out.println();

        String FIO = contactInfo.findElement(By.tagName("b")).getText();

        assertThat(FIO, equalTo(mergeFIO(contactInfoFromEditForm)));

        assertThat(contactInfo.getText(), containsString(contactInfoFromEditForm.getNickname()));
        assertThat(contactInfo.getText(), containsString(contactInfoFromEditForm.getFirstAddress()));
        assertThat(contactInfo.getText(), containsString(contactInfoFromEditForm.getHomePhone()));
        assertThat(contactInfo.getText(), containsString(contactInfoFromEditForm.getMobilePhone()));
        assertThat(contactInfo.getText(), containsString(contactInfoFromEditForm.getWorkPhone()));
        assertThat(contactInfo.getText(), containsString(contactInfoFromEditForm.getFirstEmail()));
        assertThat(contactInfo.getText(), containsString(contactInfoFromEditForm.getSecondEmail()));
        assertThat(contactInfo.getText(), containsString(contactInfoFromEditForm.getThirdEmail()));
    }

    private String mergeFIO(ContactData contact) {
        return Arrays.asList(contact.getFirstName(), contact.getMiddleName(), contact.getLastName())
                .stream().filter(s -> ! s.equals(""))
                .collect(Collectors.joining(" "));
    }
    
}
