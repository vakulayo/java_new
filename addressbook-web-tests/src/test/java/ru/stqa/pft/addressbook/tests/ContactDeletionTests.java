package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;

/**
 * Created by test on 28.07.2017.
 */
public class ContactDeletionTests extends TestBase {


  @BeforeMethod
  public void ensurePrecondition(){
    app.goTo().homePage();
    if (!app.contact().isThereAContact()) {
      app.contact().createContact(new ContactData().withFirstname("Kate")
              .withLastname("Sorokina").withAddress("Sirenevaya ul. 3 apt 10").withEmail("kate.sorokina@mail.ru").withMobile("+79111234567").withGroup("test1"));
    }
  }

  @Test
  public void testContactDeletion() {



    List<ContactData> before = app.contact().getContactList();
    int index = before.size() - 1;
    app.contact().select(index);
    app.contact().delete();
    app.contact().closeAlertWindow();
    app.goTo().homePage();
    List<ContactData> after = app.contact().getContactList();
    Assert.assertEquals(after.size(), before.size() - 1);

    before.remove(index);


    Assert.assertEquals(after, before);

  }
}
