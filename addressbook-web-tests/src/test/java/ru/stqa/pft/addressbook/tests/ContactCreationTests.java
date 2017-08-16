package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.List;

import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase {


  @Test
  public void testContactCreation() {
    app.getNavigationHelper().gotoHomePage();
    List<ContactData> before = app.getContactHelper().getContactList();

   

    app.getNavigationHelper().gotoAddNewPage();
    ContactData newContact = new ContactData("Kate", "Sorokina", "Sirenevaya ul. 3 apt 10", "kate.sorokina@mail.ru", "+79111234567", "test1");
    app.getContactHelper().fillContactForm(newContact, true);
    app.getContactHelper().submitContactCreation();
    app.getNavigationHelper().gotoHomePage();
    List<ContactData> after = app.getContactHelper().getContactList();


    Assert.assertEquals(after.size(), before.size() + 1);
    before.add(newContact);
    Comparator<? super ContactData> ById = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
    before.sort(ById);
    after.sort(ById);
    Assert.assertEquals(before, after);


  }
}
