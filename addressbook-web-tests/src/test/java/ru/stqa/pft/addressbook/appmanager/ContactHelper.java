package ru.stqa.pft.addressbook.appmanager;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.ArrayList;
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
    type(By.name("mobile"), contactData.getMobilePhone());

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
    contactsCache = null;
    gotoHomePage();
  }

  private void selectContactById(int id) {
    wd.findElement(By.cssSelector("input[value ='" + id + "']")).click();
  }




  public void delete(int index) {
    select(index);
    delete();
    closeAlertWindow();
    contactsCache = null;
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
            .withEmail("kate.sorokina@mail.ru").withMobilePhone("+79111234567").withGroup("test1"),true);
    submitContactCreation();
    contactsCache = null;
    gotoHomePage();
  }

  private void gotoHomePage() {
    click(By.linkText("home"));
  }

  private void gotoAddNewPage() {
    click(By.linkText("add new"));
  }

  public int count() {
    return wd.findElements(By.name("selected[]")).size();

  }

  public void modify(Set<ContactData> before, ContactData newContact) {
   initContactModificationById(newContact.getId());
    fillContactForm(newContact,false);
    submitContactModification();
    contactsCache = null;
    gotoHomePage();
  }

  private void initContactModificationById(int id) {

    WebElement checkbox = wd.findElement(By.cssSelector(String.format("input[value ='%s']",id)));
    WebElement row = checkbox.findElement(By.xpath("./../.."));
    List<WebElement> cells = row.findElements(By.tagName("td"));
    cells.get(7).findElement(By.tagName("a")).click();
   //WebElement we = wd.findElement(By.cssSelector("input[value ='" + id + "']"));
    //initContactModification((we.getLocation().getY()-256)/24);
  }

  public void create(ContactData newContact) {
    gotoAddNewPage();
    fillContactForm(newContact, true);
    submitContactCreation();
    contactsCache = null;
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


  private Contacts contactsCache = null;

  public Contacts all() {

    if (contactsCache != null){
      return new Contacts(contactsCache);
    }

   contactsCache = new Contacts();

    List<WebElement> rows = wd.findElements(By.name("entry"));

    for(WebElement we  : rows){
      List<WebElement> cells = we.findElements(By.tagName("td"));
      int id = Integer.parseInt(cells.get(0).findElement(By.tagName("input")).getAttribute("value"));
      String lastname = cells.get(1).getText();
      String firstname = cells.get(2).getText();
      String allPhones = cells.get(5).getText();
      String allEmails = cells.get(4).getText();
      contactsCache.add(new ContactData().withId(id).withFirstname(firstname).withLastname(lastname)
             .withAllPhones(allPhones).withAllEmails(allEmails));
    }
    return new Contacts(contactsCache);

/*    List<WebElement> elements = wd.findElements(By.name("selected[]"));
    for(WebElement we : elements){

      String s = we.getAttribute("title");
      s = s.substring(8,s.length()-1);
      String firstname = s.split(" ")[0];
      String lastname = s.split(" ")[1];
      int id = Integer.parseInt(we.getAttribute("id"));
      contacts.add(new ContactData().withId(id).withFirstname(firstname).withLastname(lastname));
    }*/



  }


  public List<WebElement> outPoint() {
      List<WebElement> we = wd.findElements(By.name("selected[]"));
      return we;

  }

  public ContactData infoFromEditForm(ContactData contact) {
  initContactModificationById(contact.getId());
  String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
    String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
    String address = wd.findElement(By.name("address")).getAttribute("value");
    String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
    String home = wd.findElement(By.name("home")).getAttribute("value");
    String work = wd.findElement(By.name("work")).getAttribute("value");
    String email = wd.findElement(By.name("email")).getAttribute("value");
    String email2 = wd.findElement(By.name("email2")).getAttribute("value");
    String email3 = wd.findElement(By.name("email3")).getAttribute("value");

  return new ContactData().withId(contact.getId()).
          withFirstname(firstname).withLastname(lastname).withAddress(address)
          .withMobilePhone(mobile).withHomePhone(home).withWorkPhone(work)
          .withEmail(email).withEmail2(email2).withEmail3(email3);
  }
}
