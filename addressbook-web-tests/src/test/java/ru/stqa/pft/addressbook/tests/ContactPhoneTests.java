package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.net.PortUnreachableException;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by test on 24.08.2017.
 */
public class ContactPhoneTests extends TestBase {

  @Test (enabled = false)
  public void testContactPhones(){
    app.goTo().homePage();
    ContactData contact = app.contact().all().iterator().next();
    ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);
    System.out.println(contact.getMobilePhone()+"vvvvvvvvvvv   "+cleaned(contactInfoFromEditForm.getMobilePhone()));

    assertThat(contact.getMobilePhone(), equalTo(cleaned(contactInfoFromEditForm.getMobilePhone())));
    assertThat(contact.getWorkPhone(), equalTo(cleaned(contactInfoFromEditForm.getWorkPhone())));
    assertThat(contact.getHomePhone(), equalTo(cleaned(contactInfoFromEditForm.getHomePhone())));


  }




  public String cleaned(String phone){
    return phone.replaceAll("\\s","").replaceAll("[-()]","");
  }
}
