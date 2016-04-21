package learn.mantis.tests;

import learn.mantis.Model.MailMessage;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;

import java.util.List;

import static learn.mantis.tests.TestBase.app;
import static org.testng.Assert.assertTrue;

/**
 * Created by d.murinov on 21.04.2016.
 */
public class AdministrationTests extends TestBase{

    @BeforeMethod
    public void startMailServer() {
        app.mail().start();
    }

    @Test
    public void changeUserPassword(){
        String user = "user1";
        String newPassword = "pass" + System.currentTimeMillis();
        app.registration().login("administrator", "root");
        app.administration().openUsersMenu();
        app.administration().openUser(user);
        app.administration().resetUserPassword();
        List<MailMessage> mailMessages = app.mail().waitForMail(1, 10000);
        String passwordChangeLink = findPasswordChangeLink(mailMessages, "user1@localhost.localdomain");
        app.administration().changePasswordFromLink(passwordChangeLink, newPassword);
        app.registration().login(user, newPassword);
        assertTrue(app.getWebDriver().findElement(By.linkText("Logout")) != null);
    }

    private String findPasswordChangeLink(List<MailMessage> mailMessages, String email) {
        MailMessage mailMessage =  mailMessages.stream().filter((m) -> m.to.equals(email)).findAny().get();
        VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
        return regex.getText(mailMessage.text);
    }

    @AfterMethod(alwaysRun = true)
    public void stopMailServer() {
        app.mail().stop();
    }
}
