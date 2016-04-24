package learn.rest.Tests;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.jayway.restassured.RestAssured;
import learn.rest.Model.Issue;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Set;

import static org.testng.Assert.assertEquals;

/**
 * Created by Dima on 24.04.2016.
 */
public class RestAssuredTests extends TestBase{

    @BeforeClass
    public void init(){
        RestAssured.authentication = RestAssured.basic(app.getProperty("rest.key"), "");
    }

    @Test
    public void testCreateIssue() throws IOException {
        Set<Issue> oldIssues = app.rest().getIssuesAssured();
        long time = System.currentTimeMillis();
        Issue newIssue = new Issue().withSubject("Subject Murinov " + time).withDescription("Descrition Murinov " + time);
        int issueId = app.rest().createIssueAssured(newIssue);
        Set<Issue> newIssues = app.rest().getIssuesAssured();
        oldIssues.add(newIssue.withId(issueId));
        assertEquals(newIssues, oldIssues);
    }

    @Test
    public void testCreateIssueSkip() throws IOException {
        skipIfNotFixed(7);
        Set<Issue> oldIssues = app.rest().getIssuesAssured();
        long time = System.currentTimeMillis();
        Issue newIssue = new Issue().withSubject("Subject Murinov " + time).withDescription("Descrition Murinov " + time);
        int issueId = app.rest().createIssueAssured(newIssue);
        Set<Issue> newIssues = app.rest().getIssuesAssured();
        oldIssues.add(newIssue.withId(issueId));
        assertEquals(newIssues, oldIssues);
    }

}
