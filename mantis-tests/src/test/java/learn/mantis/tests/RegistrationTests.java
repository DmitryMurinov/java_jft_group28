package learn.mantis.tests;

import org.testng.annotations.Test;

/**
 * Created by d.murinov on 18.04.2016.
 */
public class RegistrationTests extends TestBase {

    @Test
    public void testRegistration(){
        app.registration().start("user1", "user1@localhost.localdomain");

    }
}
