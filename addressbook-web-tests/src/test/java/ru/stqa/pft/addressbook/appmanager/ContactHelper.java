package ru.stqa.pft.addressbook.appmanager;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.*;

/**
 * Created by test on 28.07.2017.
 */
public class ContactHelper extends HelperBase {

  private Contacts contactsCache = null;

  public ContactHelper(WebDriver wd) {
    super(wd);
  }

  public void fillContactForm(ContactData contactData, boolean creation) {
    type(By.name("firstname"), contactData.getFirstname());
    type(By.name("lastname"), contactData.getLastname());
    type(By.name("address"), contactData.getAddress());
    type(By.name("email"), contactData.getEmail());
    if (contactData.getEmail3()!=null){
      type(By.name("email3"), contactData.getEmail3());
    }
    if (contactData.getEmail2()!=null){
      type(By.name("email2"), contactData.getEmail2());
    }
    type(By.name("mobile"), contactData.getMobilePhone());

    if (contactData.getWorkPhone()!=null){
      type(By.name("work"), contactData.getWorkPhone());
    }

    if (contactData.getHomePhone()!=null){
      type(By.name("home"), contactData.getHomePhone());
    }

    if (contactData.getPhoto()!=null) {
      attach(By.name("photo"), contactData.getPhoto());
    }

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
    gotoHomePage();
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
      String address = cells.get(3).getText();
      contactsCache.add(new ContactData().withId(id).withFirstname(firstname).withLastname(lastname)
             .withAllPhones(allPhones).withAllEmails(allEmails).withAddress(address));
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

  public String infoFromDetailsForm(ContactData contact) {
    gotoHomePage();
    gotoDetailsPage(contact.getId());
    WebElement content = wd.findElement(By.id("content"));
    return content.getText();
  }

  private void gotoDetailsPage(int id) {
    gotoHomePage();
    WebElement checkbox = wd.findElement(By.cssSelector(String.format("input[value ='%s']",id)));
    WebElement row = checkbox.findElement(By.xpath("./../.."));
    List<WebElement> cells = row.findElements(By.tagName("td"));
    cells.get(6).findElement(By.tagName("a")).click();
  }

  public String allInfoFromEditForm(ContactData contact) {
    initContactModificationById(contact.getId());
    String result ="";
    if (!wd.findElement(By.name("firstname")).getAttribute("value").equals("")){
      result = result + wd.findElement(By.name("firstname")).getAttribute("value").trim();
    }
    if (!wd.findElement(By.name("middlename")).getAttribute("value").equals("")){
      result = result +" "+ wd.findElement(By.name("middlename")).getAttribute("value").trim();
    }
    if (!wd.findElement(By.name("lastname")).getAttribute("value").equals("")){
      result = result +" "+ wd.findElement(By.name("lastname")).getAttribute("value").trim()+"\n";
    }
    if (!wd.findElement(By.name("nickname")).getAttribute("value").equals("")){
      result = result + wd.findElement(By.name("nickname")).getAttribute("value").trim()+"\n";
    }
    if (!wd.findElement(By.name("title")).getAttribute("value").equals("")){
      result = result + wd.findElement(By.name("title")).getAttribute("value").trim()+"\n";
    }
    if (!wd.findElement(By.name("company")).getAttribute("value").equals("")){
      result = result + wd.findElement(By.name("company")).getAttribute("value").trim()+"\n";
    }
    if (!wd.findElement(By.name("address")).getAttribute("value").equals("")){
      result = result + wd.findElement(By.name("address")).getAttribute("value").trim()+"\n";
    }
    if (!wd.findElement(By.name("home")).getAttribute("value").equals("")){
      result = result +"H: "+ wd.findElement(By.name("home")).getAttribute("value").trim()+"\n";
    }
    if (!wd.findElement(By.name("mobile")).getAttribute("value").equals("")){
      result = result +"M: "+ wd.findElement(By.name("mobile")).getAttribute("value").trim()+"\n";
    }
    if (!wd.findElement(By.name("work")).getAttribute("value").equals("")){
      result = result +"W: "+ wd.findElement(By.name("work")).getAttribute("value").trim()+"\n";
    }
    if (!wd.findElement(By.name("fax")).getAttribute("value").equals("")){
      result = result +"F: "+ wd.findElement(By.name("fax")).getAttribute("value").trim()+"\n";
    }
    if (!wd.findElement(By.name("email")).getAttribute("value").equals("")){
      result = result + addPortal(wd.findElement(By.name("email")).getAttribute("value").trim())+"\n";
    }
    if (!wd.findElement(By.name("email2")).getAttribute("value").equals("")){
      result = result + addPortal(wd.findElement(By.name("email2")).getAttribute("value").trim())+"\n";
    }
    if (!wd.findElement(By.name("email3")).getAttribute("value").equals("")){
      result = result + addPortal(wd.findElement(By.name("email3")).getAttribute("value").trim())+"\n";
    }
    if (!wd.findElement(By.name("homepage")).getAttribute("value").equals("")){
      result = result +"Homepage:\n"+ wd.findElement(By.name("homepage")).getAttribute("value").trim()+"\n";
    }


    if (!wd.findElement(By.name("bday")).getAttribute("value").equals(0)){
      String text = wd.findElement(By.cssSelector("select[name=bday]>option[selected=selected]")).getText()+ " "
              + wd.findElement(By.cssSelector("select[name=bmonth]>option[selected=selected]")).getText() + " "
              + wd.findElement(By.name("byear")).getAttribute("value");

      result = result + "Birthday "+ addAge(text)+"\n";
    }
/*
    if (!wd.findElement(By.name("bmonth")).getAttribute("value").equals("-")){
      result = result + " " + wd.findElement(By.cssSelector("select[name=bmonth]>option[selected=selected]")).getText();
    }
    if (!wd.findElement(By.name("byear")).getAttribute("value").equals("")){
      result = result + " " + addAge(wd.findElement(By.name("byear")).getAttribute("value"))+"\n";
    }*/



    if (!wd.findElement(By.name("aday")).getAttribute("value").equals(0)){


      String text = wd.findElement(By.cssSelector("select[name=aday]>option[selected=selected]")).getText()+ " "
              + wd.findElement(By.cssSelector("select[name=amonth]>option[selected=selected]")).getText() + " "
              + wd.findElement(By.name("ayear")).getAttribute("value");

      result = result + "Anniversary "+ addAge(text)+"\n";
    }


    /*if (!wd.findElement(By.name("amonth")).getAttribute("value").equals("-")){
      result = result + " " + wd.findElement(By.cssSelector("select[name=amonth]>option[selected=selected]")).getText();
    }
    if (!wd.findElement(By.name("ayear")).getAttribute("value").equals("")){
      result = result + " " + addAge(wd.findElement(By.name("ayear")).getAttribute("value"))+"\n";
    }*/


    if (!wd.findElement(By.name("address2")).getAttribute("value").equals("")){
      result = result + wd.findElement(By.name("address2")).getAttribute("value").trim()+"\n";
    }
    if (!wd.findElement(By.name("phone2")).getAttribute("value").equals("")){
      result = result+"P: " + wd.findElement(By.name("phone2")).getAttribute("value").trim()+"\n";
    }
    if (!wd.findElement(By.name("notes")).getAttribute("value").equals("")){
      result = result + wd.findElement(By.name("notes")).getAttribute("value").trim()+"\n";
    }

    return result.substring(0,result.length()-1);
  }

  private String addAge(String text) {
    HashMap<String,Integer> months = new HashMap<String,Integer>();
    months.put("January",1);
    months.put("February",2);
    months.put("March",3);
    months.put("April",4);
    months.put("May",5);
    months.put("June",6);
    months.put("July",7);
    months.put("August",8);
    months.put("September",9);
    months.put("October",10);
    months.put("November",11);
    months.put("December",12);
    String[] st = text.split(" ");
    int year = Integer.parseInt(st[2]);
    int month = months.get(st[1]);
    int day = Integer.parseInt(st[0]);

    Calendar currentDate = new GregorianCalendar();
    Calendar birthday = new GregorianCalendar(year,month,day);

    int diff;
    if (currentDate.get(Calendar.DAY_OF_YEAR) >= birthday.get(Calendar.DAY_OF_YEAR)){
      diff = currentDate.get(Calendar.YEAR)-birthday.get(Calendar.YEAR);
    } else {
      diff = currentDate.get(Calendar.YEAR)-birthday.get(Calendar.YEAR)-1;
    }

    return day+"."+" "+st[1]+" "+year+" ("+diff+")";
  }

  private String addPortal(String email) {
    String[] s = email.split("@");
    return email+" (www."+s[s.length-1]+")";
  }
}
