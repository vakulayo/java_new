package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

/**
 * Created by test on 31.08.2017.
 */
public class DetailsFormTests extends TestBase {
  @Test
  public void testDetailsForm() {
    app.goTo().homePage();
    ContactData contact = app.contact().all().iterator().next();


    String content = app.contact().infoFromDetailsForm(contact);
    content = content.replace("Notice: Undefined variable: page_ext_qry in C:\\xampp\\htdocs\\addressbook\\include\\view.w.php on line 189", "");
    content = content.replace("test1,", "");
    content = content.replace("test1", "");
    content = content.replace("Member of:", "");

    String[] st = content.split("\n");
    String infoFromDetailsForm = Arrays.stream(st).filter((s)-> s.length()!=0).collect(Collectors.joining("\n"));


    String contactInfoFromEditForm = app.contact().allInfoFromEditForm(contact);


   assertThat(infoFromDetailsForm, equalTo(contactInfoFromEditForm));
  }
}
