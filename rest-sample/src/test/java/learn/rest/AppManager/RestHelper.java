package learn.rest.AppManager;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.jayway.restassured.RestAssured;
import learn.rest.Model.Issue;

import java.io.IOException;
import java.util.Set;

/**
 * Created by Dima on 24.04.2016.
 */
public class RestHelper {

    private final ApplicationManager app;

    public RestHelper(ApplicationManager app) {
        this.app = app;
    }

    public Set<Issue> getIssuesAssured() throws IOException {
        String json = RestAssured.get(app.getProperty("rest.address") + "/issues.json").asString();
        JsonElement parsed = new JsonParser().parse(json);
        JsonElement issues = parsed.getAsJsonObject().get("issues");
        return new Gson().fromJson(issues, new TypeToken<Set<Issue>>(){}.getType());
    }

    public Issue getIssueAssured(int issueId) throws IOException {
        String json = RestAssured.get(String.format(app.getProperty("rest.address") + "/issues/%s.json", issueId)).asString();
        JsonElement parsed = new JsonParser().parse(json);
        JsonElement issues = parsed.getAsJsonObject().get("issues");
        Set<Issue> issuesGson = new Gson().fromJson(issues, new TypeToken<Set<Issue>>(){}.getType());
        return issuesGson.iterator().next();
    }

    public int createIssueAssured(Issue newIssue) throws IOException {
        String json = RestAssured.given()
                .parameter("subject", newIssue.getSubject())
                .parameter("description", newIssue.getDescription())
                .post("http://demo.bugify.com/api/issues.json").asString();
        JsonElement parsed = new JsonParser().parse(json);
        return parsed.getAsJsonObject().get("issue_id").getAsInt();
    }



}
