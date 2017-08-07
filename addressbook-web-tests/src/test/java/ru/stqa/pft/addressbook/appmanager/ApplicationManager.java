package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import sun.plugin2.util.BrowserType;

import java.util.concurrent.TimeUnit;

/**
 * Created by test on 27.07.2017.
 */
public class ApplicationManager {

  WebDriver wd;
  private  NavigationHelper navigationHelper;
  private  GroupHelper groupHelper;
  private SessionHelper sessionHelper;
  private ContactHelper contactHelper;
  private int browser;

  public ApplicationManager(int browser) {

    this.browser = browser;
  }


  public void init() {




    if (browser == BrowserType.MOZILLA) {
      wd = new FirefoxDriver();
      System.setProperty("webdriver.gecko.driver", "c:\\SeleniumGecko\\geckodriver-v0.17.0-win32\\geckodriver.exe");
    }
    else if (browser == BrowserType.INTERNET_EXPLORER) {wd = new InternetExplorerDriver();}
    else if (browser == BrowserType.DEFAULT) {wd = new ChromeDriver();}

    wd.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

   wd.get("http://localhost/addressbook/group.php");
    groupHelper = new GroupHelper(wd);
    navigationHelper = new NavigationHelper(wd);
    sessionHelper = new SessionHelper(wd);
    contactHelper = new ContactHelper(wd);
    sessionHelper.login("admin", "secret");
  }


  public void stop() {
    wd.quit();
  }

  public GroupHelper getGroupHelper() {
    return groupHelper;
  }

  public NavigationHelper getNavigationHelper() {
    return navigationHelper;
  }

  public ContactHelper getContactHelper(){return contactHelper;}
}
