package ru.stqa.pft.addressbook.appmanager;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by test on 28.07.2017.
 */
public class ContactHelper extends HelperBase {

  public ContactHelper(WebDriver wd) {
    super(wd);
  }

  public void fillContactForm(ContactData contactData, boolean creation) {
    type(By.name("firstname"), contactData.getFirstname());
    type(By.name("lastname"), contactData.getLastname());
    type(By.name("address"), contactData.getAddress());
    type(By.name("email"), contactData.getEmail());
    type(By.name("mobile"), contactData.getMobile());

    if (creation){
      new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
    }
    else {
      Assert.assertFalse(isElementPresent(By.name("new_group")));
    }


  }

  public void deleteSelectedContact(){
    click(By.xpath("//div[@id='content']/form[2]/div[2]/input"));
  }




  public void submitContactCreation(){
    click(By.xpath("//div[@id='content']/form/input[21]"));
  }

  public void selectContact(){
    click(By.xpath("//div/div[4]/form[2]/table/tbody/tr[2]/td[1]/input"));


  }

  public void closeAlertWindow(){
    wd.switchTo().alert().accept();
  }


  public void initContactModification(){
    click(By.xpath("//table[@id='maintable']/tbody/tr[2]/td[8]/a/img"));
  }

  public void submitContactModification(){
    click(By.name("update"));
  }

  public boolean isThereAContact() {
    return isElementPresent(By.name("selected[]"));
  }

  public void createContact(ContactData contactData) {
    gotoAddNewPage();
    fillContactForm(new ContactData("Kate", "Sorokina", "Sirenevaya ul. 3 apt 10", "kate.sorokina@mail.ru", "+79111234567", "test1"),true);
    submitContactCreation();
    gotoHomePage();
  }

  private void gotoHomePage() {
    click(By.linkText("home"));
  }

  private void gotoAddNewPage() {
    click(By.linkText("add new"));
  }

  public int getContactCount() {
    return wd.findElements(By.name("selected[]")).size();

  }

  public List<ContactData> getContactList() {
    List<ContactData> contacts = new ArrayList<ContactData>();
    List<WebElement> elements = wd.findElements(By.name("selected[]"));
    for(WebElement we : elements){
      String s = we.getAttribute("title");
      contacts.add(new ContactData(null, s, null, null, null, null));
    }
    return contacts;
  }
}
