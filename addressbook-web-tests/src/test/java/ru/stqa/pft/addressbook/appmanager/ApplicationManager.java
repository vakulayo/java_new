package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;


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
  private String browser;

  public ApplicationManager(String browser) {

    this.browser = browser;
  }


  public void init() {



    if (browser.equals(BrowserType.FIREFOX)) {
      System.setProperty("webdriver.gecko.driver", "c:\\SeleniumGecko\\geckodriver-v0.17.0-win32\\geckodriver.exe");
      wd = new FirefoxDriver();

    }
    else if (browser.equals(BrowserType.IE)) {wd = new InternetExplorerDriver();}
    else if (browser.equals(BrowserType.CHROME)) {wd = new ChromeDriver();}

    wd.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);

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

  public GroupHelper group() {
    return groupHelper;
  }

  public NavigationHelper goTo() {
    return navigationHelper;
  }

  public ContactHelper contact(){return contactHelper;}
}
