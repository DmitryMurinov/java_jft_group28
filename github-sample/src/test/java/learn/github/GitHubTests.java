package learn.github;

import com.google.common.collect.ImmutableMap;
import com.jcabi.github.*;
import org.testng.annotations.Test;

import java.io.IOException;

/**
 * Created by Dima on 24.04.2016.
 */
public class GitHubTests {

    @Test
    public void testCommits() throws IOException {

        Github github = new RtGithub("d8e543b3ef18413ed7a68ff5e74a97e621b6e294");

        RepoCommits commits = github.repos().get(new Coordinates.Simple("DmitryMurinov", "java_jft_group28")).commits();
        for (RepoCommit commit : commits.iterate(new ImmutableMap.Builder<String, String>().build())){
            System.out.println(new RepoCommit.Smart(commit).message());
        }


    }
}
