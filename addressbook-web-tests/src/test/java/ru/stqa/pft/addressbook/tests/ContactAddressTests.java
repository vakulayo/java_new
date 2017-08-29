package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by test on 30.08.2017.
 */
public class ContactAddressTests extends TestBase {
  @Test
  public void testContactAddress(){
    app.goTo().homePage();
    ContactData contact = app.contact().all().iterator().next();
    ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);
   assertThat(contact.getAddress(), equalTo(removeBlanks(contactInfoFromEditForm.getAddress())));

  }

  private String  removeBlanks(String address) {

    while (address.contains(" \n")){
      address=address.replace(" \n","\n");
    }

    while (address.contains("\n ")){
      address=address.replace("\n ","\n");
    }
    while (address.contains("  ")){
      address=address.replace("  "," ");
    }

    while (address.startsWith(" ")||address.startsWith("\n")){
      address=address.substring(1);
    }
    while (address.endsWith(" ")||address.endsWith("\n")){
      address=address.substring(0,address.length()-1);
    }

     return address;
  }
}
