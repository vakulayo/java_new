package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

import ru.stqa.pft.addressbook.model.ContactData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {


  @Test
  public void testContactCreation() {
    app.goTo().homePage();
    Set<ContactData> before = app.contact().all();


    ContactData newContact = new ContactData().withFirstname("Kate")
            .withLastname("Sorokina").withAddress("Sirenevaya ul. 3 apt 10").withEmail("kate.sorokina@mail.ru").withMobile("+79111234567").withGroup("test1");

    app.contact().create(newContact);
    Set<ContactData> after = app.contact().all();
    assertThat(after.size(),  equalTo(before.size() + 1));

    newContact.withId(after.stream().mapToInt((g)->g.getId()).max().getAsInt());
    before.add(newContact);

    assertThat(before, equalTo(after));


  }


}
