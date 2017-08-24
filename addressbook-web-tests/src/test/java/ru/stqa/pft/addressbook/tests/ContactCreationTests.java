package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {


  @Test
  public void testContactCreation() {
    app.goTo().homePage();
    Contacts before = app.contact().all();


    ContactData newContact = new ContactData().withFirstname("Kate")
            .withLastname("Sorokina").withAddress("Sirenevaya ul. 3 apt 10").withEmail("kate.sorokina@mail.ru").withMobile("+79111234567").withGroup("test1");

    app.contact().create(newContact);
    Contacts after = app.contact().all();
    assertThat(after.size(),  equalTo(before.size() + 1));
    assertThat(after, equalTo(before.withAdded( newContact.withId(after.stream().mapToInt((g)->g.getId()).max().getAsInt()))));


  }


}
