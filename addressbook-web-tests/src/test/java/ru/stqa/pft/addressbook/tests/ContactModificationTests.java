package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

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

    int before = app.getContactHelper().getContactCount();
    app.getContactHelper().initContactModification();
    app.getContactHelper().fillContactForm(new ContactData("Olga", "Sorokina", "Sirenevaya ul. 3 apt 10", "kate.sorokina@mail.ru", "+79111234567",null),false);
    app.getContactHelper().submitContactModification();
    app.getNavigationHelper().gotoHomePage();

    int after = app.getContactHelper().getContactCount();
    Assert.assertEquals(after,before);

  }
}
