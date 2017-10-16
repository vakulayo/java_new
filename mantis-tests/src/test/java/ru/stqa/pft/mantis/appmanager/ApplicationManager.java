package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

/**
 * Created by test on 27.07.2017.
 */
public class ApplicationManager {

  private final Properties properties;
  WebDriver wd;

  private String browser;


  public ApplicationManager(String browser)  {

    this.browser = browser;

    properties = new Properties();

  }


  public void init() throws IOException {

    String target = System.getProperty("target", "local");
    properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties",target))));


    if (browser.equals(BrowserType.FIREFOX)) {
      System.setProperty("webdriver.gecko.driver", "c:\\SeleniumGecko\\geckodriver-v0.17.0-win32\\geckodriver.exe");
      wd = new FirefoxDriver();

    }
    else if (browser.equals(BrowserType.IE)) {wd = new InternetExplorerDriver();}
    else if (browser.equals(BrowserType.CHROME)) {wd = new ChromeDriver();}

    wd.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);

   wd.get(properties.getProperty("web.baseUrl"));

  }


  public void stop() {
    wd.quit();
  }


}
