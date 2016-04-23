package learn.mantis.appmanager;

import biz.futureware.mantis.rpc.soap.client.*;
import learn.mantis.Model.Issue;
import learn.mantis.Model.Project;
import learn.mantis.Model.Status;

import javax.xml.rpc.ServiceException;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by Dima on 23.04.2016.
 */
public class SoapHelper  {


    private ApplicationManager app;

    public SoapHelper(ApplicationManager app) {
        this.app = app;
    }

    public Set<Project> getProjects() throws MalformedURLException, RemoteException, ServiceException {
        MantisConnectPortType mantisConnect = getMantisConnect();
        ProjectData[] projects = mantisConnect.mc_projects_get_user_accessible
                (app.getProperty("soap.user"), app.getProperty("soap.password"));
        return Arrays.asList(projects).stream()
                .map((p) -> new Project().withId(p.getId().intValue()).withName(p.getName())).collect(Collectors.toSet());
    }

    private MantisConnectPortType getMantisConnect() throws ServiceException, MalformedURLException {
        return new MantisConnectLocator()
                    .getMantisConnectPort(new URL(app.getProperty("soap.address")));
    }

    public Issue addIssue(Issue issue) throws MalformedURLException, ServiceException, RemoteException {
        MantisConnectPortType mantisConnect = getMantisConnect();

        String[] categories = mantisConnect.mc_project_get_categories(app.getProperty("soap.user"),
                app.getProperty("soap.password"), BigInteger.valueOf(issue.getProject().getId()));
        IssueData issueData = new IssueData();
        issueData.setSummary(issue.getSummary());
        issueData.setDescription(issue.getDescription());
        issueData.setProject(new ObjectRef(BigInteger.valueOf(issue.getProject().getId()), issue.getProject().getName()));
        issueData.setCategory(categories[0]);
        BigInteger issueId = mantisConnect.mc_issue_add(app.getProperty("soap.user"), app.getProperty("soap.password"), issueData);
        IssueData issueDataCreated = mantisConnect.mc_issue_get(app.getProperty("soap.user"), app.getProperty("soap.password"), issueId);

        return new Issue().withId(issueDataCreated.getId().intValue())
                .withDescription(issueDataCreated.getDescription()).withSummary(issueDataCreated.getSummary())
                .withProject(new Project().withId(issueDataCreated.getProject().getId().intValue())
                        .withName(issueDataCreated.getProject().getName()));
    }

    public Issue getIssue(int issueId) throws MalformedURLException, ServiceException, RemoteException {
        MantisConnectPortType mantisConnect = getMantisConnect();


        IssueData issueDataById = mantisConnect.mc_issue_get
                (app.getProperty("soap.user"), app.getProperty("soap.password"), BigInteger.valueOf(issueId));

        return new Issue().withId(issueDataById.getId().intValue())
                .withDescription(issueDataById.getDescription()).withSummary(issueDataById.getSummary())
                .withProject(new Project().withId(issueDataById.getProject().getId().intValue())
                .withName(issueDataById.getProject().getName()))
                .withStatus(new Status().withId(issueDataById.getId().intValue()).withName(issueDataById.getStatus().getName()));
    }
}
