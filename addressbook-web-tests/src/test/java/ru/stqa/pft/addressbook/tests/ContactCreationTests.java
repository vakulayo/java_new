package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase {


  @Test
  public void testContactCreation() {
    app.goTo().homePage();
    Set<ContactData> before = app.contact().all();


    ContactData newContact = new ContactData().withFirstname("Kate")
            .withLastname("Sorokina").withAddress("Sirenevaya ul. 3 apt 10").withEmail("kate.sorokina@mail.ru").withMobile("+79111234567").withGroup("test1");

    app.contact().create(newContact);
    Set<ContactData> after = app.contact().all();
    Assert.assertEquals(after.size(), before.size() + 1);

    newContact.withId(after.stream().mapToInt((g)->g.getId()).max().getAsInt());
    before.add(newContact);
    Assert.assertEquals(before, after);


  }


}
