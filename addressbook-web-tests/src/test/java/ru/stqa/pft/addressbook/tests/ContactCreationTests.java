package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {


  @Test(enabled = true)
  public void testContactCreation() {
    app.goTo().homePage();
    Contacts before = app.contact().all();
    File photo = new File("src/test/resources/Koala.jpg");

    ContactData newContact = new ContactData().withFirstname("Kate")
            .withLastname("Sorokina").withAddress("Sirenevaya ul. 3 apt 10").withEmail("kate.sorokina@mail.ru").withMobilePhone("+79111234567").withPhoto(photo).withGroup("test1");

    app.contact().create(newContact);


    assertThat(app.contact().count(), equalTo(before.size() + 1));
    Contacts after = app.contact().all();
    assertThat(after, equalTo(before.withAdded(newContact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
  }




}
