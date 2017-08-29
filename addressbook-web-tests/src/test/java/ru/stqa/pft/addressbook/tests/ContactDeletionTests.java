package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by test on 28.07.2017.
 */
public class ContactDeletionTests extends TestBase {


  @BeforeMethod
  public void ensurePrecondition(){
    app.goTo().homePage();
    if (!app.contact().isThereAContact()) {
      app.contact().createContact(new ContactData().withFirstname("Kate")
              .withLastname("Sorokina").withAddress("Sirenevaya ul. 3 apt 10").withEmail("kate.sorokina@mail.ru").withMobilePhone("+79111234567").withGroup("test1"));
    }
  }

  @Test
  public void testContactDeletion() {



    Contacts before = app.contact().all();
    ContactData deletedContact = before.iterator().next();
    app.contact().delete(deletedContact);


    assertThat(app.contact().count(), equalTo(before.size() - 1));
    Contacts after = app.contact().all();
        assertThat(after, equalTo(before.without(deletedContact)));

  }


}
