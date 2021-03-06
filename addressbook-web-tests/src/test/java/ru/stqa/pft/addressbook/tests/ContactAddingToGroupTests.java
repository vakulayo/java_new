package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.Iterator;
import java.util.Set;

/**
 * Created by test on 02.10.2017.
 */
public class ContactAddingToGroupTests extends TestBase {


  @BeforeMethod
  public static void checkPrecondition(){

    //There is at least one contact, if else it should be created
    if (app.db().contacts().size() == 0){
      app.contact().create(new ContactData().withFirstname("Kate")
              .withLastname("Sorokina"));
         }
    //GroupList contains at least one group, else it should be created
    if (app.db().groups().size() == 0){
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("addgroup"));
    }

    //There is at least one contact with groupslist size less then grouplist size, else create new group
    Boolean isFull = true;
    Contacts contacts = app.db().contacts();
    Groups groups = app.db().groups();
    for(ContactData contact : contacts){
     if (contact.getGroups().size() < groups.size()){
       isFull = false;
       break;
      }
     }

     if (isFull){
       app.group().create(new GroupData().withName("newgroup"));
     }



  }

  @Test
  public static void testContactAddToGroup(){

    Contacts contacts = app.db().contacts();
    Groups groups = app.db().groups();
    Iterator iterator =  contacts.iterator();
    ContactData addedContact = (ContactData) iterator.next();


    //chose any contact with possibility to add group
    while (addedContact.getGroups().size() == groups.size()){
      addedContact = (ContactData) iterator.next();
    }

    //chose free group for this contact
    groups.removeAll(addedContact.getGroups());
    GroupData mainGroup = groups.iterator().next();



    app.contact().addToGroup(addedContact,mainGroup);

    //for checking db
    Contacts afterContacts = app.db().contacts();
    Iterator iter = afterContacts.iterator();
    ContactData afterAddingContact = (ContactData) iter.next();

    //find the exact contact (using Id)

    while(afterAddingContact.getId() != addedContact.getId()){
      afterAddingContact = (ContactData) iter.next();
    }

    //compare groups Set before and after test
    Groups afterGroups = afterAddingContact.getGroups();
    Groups beforeGroups = addedContact.getGroups();
    beforeGroups.add(mainGroup);

    Assert.assertEquals(afterGroups,beforeGroups);
  }

}
