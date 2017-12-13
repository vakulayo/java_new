package ru.stqa.pft.mantis.tests;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

/**
 * Created by test on 13.12.2017.
 */
public class StatusBugifyTests extends TestBase{
  @BeforeTest
  public void checkIssueStatus() throws IOException {
    skipIfNotFixedBugify(506);
  }

  @Test
  public void testBugifyStatus() throws IOException {
    System.out.println("тест не скипнули");
    System.out.println(app.rest().statusIssue(47)+" oopen");
    System.out.println(app.rest().statusIssue(441)+" in prooogresssss");
    System.out.println(app.rest().statusIssue(506)+" resssolved");
    System.out.println(app.rest().statusIssue(512)+"000000 closed");
    //System.out.println(isIssueOpenBugify(423));
    //System.out.println(isIssueOpenBugify(47));
    //System.out.println(isIssueOpenBugify(423));

  }
}
