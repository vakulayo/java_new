package ru.stqa.pft.addressbook.appmanager;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

  public void delete(){

    click(By.xpath("//div[@id='content']/form[2]/div[2]/input"));
    }




  public void submitContactCreation(){


    click(By.name("submit"));
  }

  public void delete(ContactData contact) {
    selectContactById(contact.getId());
    delete();
    closeAlertWindow();
    gotoHomePage();
  }

  private void selectContactById(int id) {
    wd.findElement(By.cssSelector("input[value ='" + id + "']")).click();
  }




  public void delete(int index) {
    select(index);
    delete();
    closeAlertWindow();
   gotoHomePage();
  }
  public void select(int index){
       wd.findElements(By.name("selected[]")).get(index).click();

  }

  public void closeAlertWindow(){
    wd.switchTo().alert().accept();
  }


  public void initContactModification(int index){
    click(By.xpath("//table[@id='maintable']/tbody/tr["+(index+1)+"]/td[8]/a/img"));
  }

  public void submitContactModification(){
    click(By.name("update"));
  }

  public boolean isThereAContact() {
    return isElementPresent(By.name("selected[]"));
  }

  public void createContact(ContactData contactData) {
    gotoAddNewPage();
    fillContactForm(new ContactData().withFirstname("Kate").withLastname("Sorokina").withAddress("Sirenevaya ul. 3 apt 10")
            .withEmail("kate.sorokina@mail.ru").withMobile("+79111234567").withGroup("test1"),true);
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

  public void modify(Set<ContactData> before, ContactData newContact) {
   initContactModificationById(newContact.getId());
    fillContactForm(newContact,false);
    submitContactModification();
    gotoHomePage();
  }

  private void initContactModificationById(int id) {

   WebElement we = wd.findElement(By.cssSelector("input[value ='" + id + "']"));
    initContactModification((we.getLocation().getY()-256)/24);
  }

  public void create(ContactData newContact) {
    gotoAddNewPage();
    fillContactForm(newContact, true);
    submitContactCreation();
    gotoHomePage();
  }

  public List<ContactData> getContactList() {
    List<ContactData> contacts = new ArrayList<ContactData>();
    List<WebElement> elements = wd.findElements(By.name("selected[]"));
    for(WebElement we : elements){

      String s = we.getAttribute("title");
      s = s.substring(8,s.length()-1);
      String firstname = s.split(" ")[0];
      String lastname = s.split(" ")[1];
      int id = Integer.parseInt(we.getAttribute("id"));
      contacts.add(new ContactData().withId(id).withFirstname(firstname).withLastname(lastname));
    }
    return contacts;
  }

  public Set<ContactData> all() {
    Set<ContactData> contacts = new HashSet<ContactData>();
    List<WebElement> elements = wd.findElements(By.name("selected[]"));
    for(WebElement we : elements){

      String s = we.getAttribute("title");
      s = s.substring(8,s.length()-1);
      String firstname = s.split(" ")[0];
      String lastname = s.split(" ")[1];
      int id = Integer.parseInt(we.getAttribute("id"));
      contacts.add(new ContactData().withId(id).withFirstname(firstname).withLastname(lastname));
    }
    return contacts;
  }


  public List<WebElement> outPoint() {
      List<WebElement> we = wd.findElements(By.name("selected[]"));
      return we;

  }
}
