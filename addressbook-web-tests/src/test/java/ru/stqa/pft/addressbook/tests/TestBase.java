package ru.stqa.pft.addressbook.tests;


import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import ru.stqa.pft.addressbook.appmanager.ApplicationManager;
import sun.plugin2.util.BrowserType;


/**
 * Created by test on 27.07.2017.
 */
public class TestBase {

  protected static final ApplicationManager app = new ApplicationManager(BrowserType.DEFAULT);

  @BeforeSuite
  public void setUp() throws Exception {
    app.init();
  }

  @AfterSuite
  public void tearDown() {
    app.stop();
  }

}
