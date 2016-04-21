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
}
