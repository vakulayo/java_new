package ru.stqa.pft.github;

import com.google.common.collect.ImmutableMap;
import com.jcabi.github.*;
import org.testng.annotations.Test;

import java.io.IOException;

/**
 * Created by test on 14.12.2017.
 */
public class GitHubTests {
  @Test
  public void testCommits() throws IOException {
    Github github = new RtGithub("a18ddbec5bf0b6d536ac1f9b5d3620ed8948780a ");
    RepoCommits commits = github.repos().get(new Coordinates.Simple("vakulayo", "java_new")).commits();
    for(RepoCommit commit: commits.iterate(new ImmutableMap.Builder<String,String>().build())){
      System.out.println(new RepoCommit.Smart(commit).message() );
    }
  }

}
