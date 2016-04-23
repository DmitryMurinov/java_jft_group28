package learn.mantis.tests;

import biz.futureware.mantis.rpc.soap.client.ProjectData;
import learn.mantis.Model.Issue;
import learn.mantis.Model.Project;
import org.testng.Assert;
import org.testng.annotations.Test;

import javax.xml.rpc.ServiceException;
import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.util.Set;

public class SoapTests extends TestBase {

    @Test
    public void testGetProjects() throws MalformedURLException, ServiceException, RemoteException {

        Set<Project> projects = app.soap().getProjects();
        System.out.println(projects.size());

        for(Project project : projects){
            System.out.println(project.getName());
        }
    }

    @Test
    public void testCreateIssue() throws RemoteException, ServiceException, MalformedURLException {
        long time = System.currentTimeMillis();
        Set<Project> projects = app.soap().getProjects();
        Issue issue = new Issue().withSummary("Test issue " + time)
                .withDescription("Test issue description " + time).withProject(projects.iterator().next());
        Issue created = app.soap().addIssue(issue);

        Assert.assertEquals(issue.getSummary(), created.getSummary());
    }

    @Test
    public void testCreateIssueIsClosed() throws RemoteException, ServiceException, MalformedURLException {
        skipIfNotFixed(0000002);
        long time = System.currentTimeMillis();
        Set<Project> projects = app.soap().getProjects();
        Issue issue = new Issue().withSummary("Test issue " + time)
                .withDescription("Test issue description " + time).withProject(projects.iterator().next());
        Issue created = app.soap().addIssue(issue);

        Assert.assertEquals(issue.getSummary(), created.getSummary());
    }


}
