package learn.mantis.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static learn.mantis.tests.TestBase.app;

/**
 * Created by d.murinov on 21.04.2016.
 */
public class AdministrationTests extends TestBase{

    @Test
    public void changeUserPassword(){
        app.registration().login("administrator", "root");
//        app.administration().openUsersMenu();

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
}
