package ru.stqa.pft.addressbook.tests;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.io.*;
import java.nio.Buffer;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {

  @DataProvider
  public Iterator<Object[]> nextContactFromJsonFile() throws IOException{
    BufferedReader br = new BufferedReader(new FileReader(new File("src/test/resources/contacts.json")));
    String json = "";
    String s = br.readLine();

    while(s != null){
      json = json + s;
      s = br.readLine();
    }
    Gson gson = new Gson();
    List<ContactData> contacts = gson.fromJson(json, new TypeToken<List<ContactData>>() {}.getType());
    return contacts.stream().map((c)-> new Object[] {c}).collect(Collectors.toList()).iterator();
  }

  @Test(dataProvider = "nextContactFromJsonFile")
  public void testContactCreation(ContactData newContact) {
    app.goTo().homePage();
    Contacts before = app.contact().all();
    File photo = new File("src/test/resources/Koala.jpg");


    app.contact().create(newContact);


    assertThat(app.contact().count(), equalTo(before.size() + 1));
    Contacts after = app.contact().all();
    assertThat(after, equalTo(before.withAdded(newContact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
  }




}
