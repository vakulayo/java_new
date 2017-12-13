package ru.stqa.pft.mantis.appmanager;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.openqa.selenium.json.Json;

import java.io.IOException;

/**
 * Created by test on 13.12.2017.
 */
public class RestHelper {

  private final ApplicationManager app;

  public RestHelper(ApplicationManager app) {
    this.app = app;
  }


  public String statusIssue(int issueId) throws IOException {
    String URL = String.format("http://demo.bugify.com/api/issues/%d.json",issueId);
    String json = getExecutor().execute(Request.Get(URL)).returnContent().asString();
    JsonElement parsed = new JsonParser().parse(json);
    JsonElement issues = parsed.getAsJsonObject().get("issues");
    JsonElement jsonElement = issues.getAsJsonArray().get(0);
    JsonElement state_name = jsonElement.getAsJsonObject().get("state_name");
    return state_name.getAsString();
  }

  private Executor getExecutor(){
   return Executor.newInstance().auth("28accbe43ea112d9feb328d2c00b3eed","");
  }
}
