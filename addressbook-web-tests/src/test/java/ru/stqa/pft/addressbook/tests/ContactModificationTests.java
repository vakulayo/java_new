package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

/**
 * Created by test on 28.07.2017.
 */
public class ContactModificationTests extends TestBase {

  @Test
  public void testContactModification() {



    app.getNavigationHelper().gotoHomePage();

    if (! app.getContactHelper().isThereAContact()){
      app.getContactHelper().createContact(new ContactData("Kate", "Sorokina", "Sirenevaya ul. 3 apt 10", "kate.sorokina@mail.ru", "+79111234567", "test1"));
    }

    List<ContactData> before = app.getContactHelper().getContactList();

    app.getContactHelper().initContactModification(before.size());


    ContactData newContact = new ContactData(before.get(before.size()-1).getId(),"Olga", "Sorokina", "Sirenevaya ul. 3 apt 10", "kate.sorokina@mail.ru", "+79111234567",null);

    app.getContactHelper().fillContactForm(newContact,false);
    app.getContactHelper().submitContactModification();
    app.getNavigationHelper().gotoHomePage();

    List<ContactData> after = app.getContactHelper().getContactList();
    before.remove(before.size()-1);
    before.add(newContact);

    Assert.assertEquals(after.size(),before.size());

    Comparator<? super ContactData> ById = (c1,c2)->Integer.compare(c1.getId(),c2.getId());
    before.sort(ById);
    after.sort(ById);
    Assert.assertEquals(before,after);


  }
}
