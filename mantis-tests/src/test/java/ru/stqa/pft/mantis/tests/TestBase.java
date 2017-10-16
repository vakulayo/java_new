package ru.stqa.pft.mantis.tests;


import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.AfterSuite;
import ru.stqa.pft.mantis.appmanager.ApplicationManager;
import org.testng.annotations.BeforeSuite;





/**
 * Created by test on 27.07.2017.
 */
public class TestBase {



  protected static final ApplicationManager app = new ApplicationManager(System.getProperty("browser",BrowserType.FIREFOX));

  @BeforeSuite
  public void setUp() throws Exception {
    app.init();

  }

  @AfterSuite(alwaysRun = true)
  public void tearDown() {
    app.stop();
  }





}
