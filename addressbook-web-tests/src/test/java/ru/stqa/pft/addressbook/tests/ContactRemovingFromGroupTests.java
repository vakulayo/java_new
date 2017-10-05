package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.Iterator;

/**
 * Created by test on 04.10.2017.
 */
public class ContactRemovingFromGroupTests extends TestBase{


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

   //At least one contact has non-empty GroupList, else add any contact to any group
   Boolean isEmpty = true;
   Contacts contacts = app.db().contacts();
   Groups groups = app.db().groups();

   for (ContactData contact : contacts){
     if (contact.getGroups().size() != 0){
       isEmpty = false;
       break;
     }
   }
   ContactData anyContact = contacts.iterator().next();
   GroupData anyGroup = groups.iterator().next();
   if (isEmpty){
     app.contact().addToGroup(anyContact,anyGroup);
   }
 }


  @Test
  public static void testRemovingContactFromGroup(){

    Contacts contacts = app.db().contacts();

    Iterator iter = contacts.iterator();
    ContactData removedContact = (ContactData) iter.next();

 //find proper contact with non-empty grouplist
     while(removedContact.getGroups().size() == 0){
      removedContact = (ContactData) iter.next();
    }

    GroupData mainGroup  = removedContact.getGroups().iterator().next();


    app.contact().removeFromGroup(removedContact,mainGroup);

    //check

    Contacts afterRemovingContacts = app.db().contacts();

    Iterator iterator = afterRemovingContacts.iterator();
    ContactData afterContact = (ContactData) iterator.next();

    //find the exact contact by id
    while (afterContact.getId() != removedContact.getId()){
      afterContact = (ContactData) iterator.next();
    }

    Groups beforeGroups = removedContact.getGroups();
    Groups afterGroups = afterContact.getGroups();



    beforeGroups.remove(mainGroup);

    Assert.assertEquals(beforeGroups,afterGroups);


  }
}
