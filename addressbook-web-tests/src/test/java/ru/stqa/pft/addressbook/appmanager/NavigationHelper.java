package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Created by test on 28.07.2017.
 */
public class NavigationHelper extends HelperBase{


  public NavigationHelper(WebDriver wd) {
    super(wd);
  }

  public void groupPage() {

    if ( isElementPresent(By.tagName("h1"))
            && wd.findElement(By.tagName("h1")).getText().equals("Groups")
            &&  isElementPresent(By.name("New"))) {
      return;
    }
    click(By.linkText("groups"));
    }

  public void  gotoAddNewPage() {
    click(By.linkText("add new"));

  }

  public void homePage(){
    if (isElementPresent(By.id("maintable"))) {
      return;}
    click(By.linkText("home"));
  }
}
