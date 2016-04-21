package learn.mantis.appmanager;

import org.openqa.selenium.By;

/**
 * Created by d.murinov on 18.04.2016.
 */
public class RegistrationHelper extends HelperBase{

    public RegistrationHelper(ApplicationManager app) {
        super(app);
    }

    public void start(String username, String email) {
        wd.get(app.getProperty("web.baseURL") + "/signup_page.php");
        typeInfoBox(By.name("username"), username);
        typeInfoBox(By.name("email"), email);
        click(By.cssSelector("input[value='Signup']"));
    }

    public void finish(String confirmationLink, String password) {
        wd.get(confirmationLink);
        typeInfoBox(By.name("password"), password);
        typeInfoBox(By.name("password_confirm"), password);
        click(By.cssSelector("input[value='Update User']"));
    }
}
