package learn.rest.Tests;


import learn.rest.AppManager.ApplicationManager;
import learn.rest.Model.Issue;
import org.testng.SkipException;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.RemoteException;

/**
 * Created by Dima on 28.02.2016.
 */
public class TestBase {

    protected static final ApplicationManager app = new ApplicationManager();

    @BeforeSuite
    public void setUp() throws Exception {
        app.init();
    }


    boolean isIssueOpen(int issueId) throws IOException {
        Issue issue = app.rest().getIssueAssured(issueId);
        return (!issue.getState_name().equals("Closed"));
    }

    public void skipIfNotFixed(int issueId) throws IOException {
        if (isIssueOpen(issueId)) {
            throw new SkipException("Ignored because of issue " + issueId);
        }
    }


}
