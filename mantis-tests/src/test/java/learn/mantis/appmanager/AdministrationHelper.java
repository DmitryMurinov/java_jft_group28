package learn.mantis.appmanager;

import org.openqa.selenium.By;

/**
 * Created by d.murinov on 21.04.2016.
 */
public class AdministrationHelper extends HelperBase{

    public AdministrationHelper(ApplicationManager app) {
        super(app);
    }


    public void openUsersMenu() {
        click(By.xpath("/html/body/div[2]/p/span[1]/a"));
    }

    public void openUser(String username) {
        click(By.linkText(username));
    }

    public void resetUserPassword() {
        click(By.cssSelector("input[value='Reset Password']"));
    }

    public void changePasswordFromLink(String passwordChangeLink, String newpass) {
        wd.get(passwordChangeLink);
        typeInfoBox(By.name("password"), newpass);
        typeInfoBox(By.name("password_confirm"), newpass);
        click(By.cssSelector("input[value='Update User']"));
    }
}
