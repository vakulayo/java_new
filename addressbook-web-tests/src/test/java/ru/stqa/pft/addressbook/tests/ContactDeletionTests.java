package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;

/**
 * Created by test on 28.07.2017.
 */
public class ContactDeletionTests extends TestBase{

  @Test
  public void testContactDeletion(){
    app.getNavigationHelper().gotoHomePage();
    app.getContactHelper().selectContact();
    app.getContactHelper().deleteSelectedContact();
    app.getContactHelper().closeAlertWindow();
    app.getNavigationHelper().gotoHomePage();
  }
}
