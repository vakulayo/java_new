package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

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


    List<ContactData> before = app.contact().getContactList();
    int index = before.size() - 1;
    ContactData newContact = new ContactData().withId(before.get(index).getId()).withFirstname("Olga")
            .withLastname("Sorokina").withAddress("Sirenevaya ul. 3 apt 10").withAddress("kate.sorokina@mail.ru").withMobile("+79111234567");
    app.contact().modify(before, newContact);

    List<ContactData> after = app.contact().getContactList();
    before.remove(index);
    before.add(newContact);

    Assert.assertEquals(after.size(),before.size());

    Comparator<? super ContactData> ById = (c1,c2)->Integer.compare(c1.getId(),c2.getId());
    before.sort(ById);
    after.sort(ById);
    Assert.assertEquals(before,after);


  }


}
