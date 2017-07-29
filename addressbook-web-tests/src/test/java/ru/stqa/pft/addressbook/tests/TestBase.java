package ru.stqa.pft.addressbook.tests;


import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import ru.stqa.pft.addressbook.appmanager.ApplicationManager;
import sun.plugin2.util.BrowserType;


/**
 * Created by test on 27.07.2017.
 */
public class TestBase {

  protected final ApplicationManager app = new ApplicationManager(BrowserType.MOZILLA);

  @BeforeMethod
  public void setUp() throws Exception {
    app.init();
  }

  @AfterMethod
  public void tearDown() {
    app.stop();
  }

}
