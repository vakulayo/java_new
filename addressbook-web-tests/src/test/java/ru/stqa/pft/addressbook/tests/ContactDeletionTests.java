package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;

/**
 * Created by test on 28.07.2017.
 */
public class ContactDeletionTests extends TestBase {

  @Test
  public void testContactDeletion() {


    app.getNavigationHelper().gotoHomePage();


    if (!app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(new ContactData("Kate", "Sorokina", "Sirenevaya ul. 3 apt 10", "kate.sorokina@mail.ru", "+79111234567", "test1"));
    }
    List<ContactData> before = app.getContactHelper().getContactList();

    app.getContactHelper().selectContact(before.size() - 1);
    app.getContactHelper().deleteSelectedContact();
    app.getContactHelper().closeAlertWindow();
    app.getNavigationHelper().gotoHomePage();
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size() - 1);

    before.remove(before.size() - 1);

    for (ContactData cd : before){
      System.out.println(cd.getFirstname());
      System.out.println(cd.getId());
      System.out.println(cd.getLastname());
      System.out.println("fffffffffffffffffffffff");
    }

    for (ContactData cd : after){
      System.out.println(cd.getFirstname());
      System.out.println(cd.getId());
      System.out.println(cd.getLastname());
      System.out.println("aaaaaaaaaaaa");
    }
    Assert.assertEquals(after, before);

  }
}
