package ru.stqa.pft.addressbook.tests;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

/**
 * Created by test on 28.07.2017.
 */
public class ContactModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePrecondition(){
    app.goTo().homePage();
    if (!app.contact().isThereAContact()) {
      app.contact().createContact(new ContactData().withFirstname("Kate")
              .withLastname("Sorokina").withAddress("Sirenevaya ul. 3 apt 10").withEmail("kate.sorokina@mail.ru").withMobile("+79111234567").withGroup("test1"));
    }
  }

  @Test
  public void testContactModification() {


    Set<ContactData> before = app.contact().all();


    ContactData modifiedContact = before.iterator().next();

    ContactData newContact = new ContactData().withId(modifiedContact.getId()).withFirstname("Olga")
            .withLastname("Sorokina").withAddress("Sirenevaya ul. 3 apt 10").withAddress("kate.sorokina@mail.ru").withMobile("+79111234567");
    app.contact().modify(before, newContact);


    Set<ContactData> after = app.contact().all();
    before.remove(modifiedContact);
    before.add(newContact);

    Assert.assertEquals(after.size(),before.size());


    Assert.assertEquals(before,after);


  }


}
