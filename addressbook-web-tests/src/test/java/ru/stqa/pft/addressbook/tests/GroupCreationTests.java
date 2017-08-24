package ru.stqa.pft.addressbook.tests;


import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class GroupCreationTests extends TestBase {

  @Test
  public void testGroupCreation() {

    app.goTo().groupPage();
    Groups before = app.group().all();
    GroupData groupNew = new GroupData().withName("test1");
    app.group().create(groupNew);
    assertThat(app.group().count(), equalTo(before.size()+1));
    Groups after = app.group().all();


    assertThat(after, equalTo(
            before.withAdded( groupNew.withId(after.stream().mapToInt((g)->g.getId()).max().getAsInt()))));


  }


  @Test
  public void testBadGroupCreation() {

    app.goTo().groupPage();
    Groups before = app.group().all();
    GroupData groupNew = new GroupData().withName("test'");
    app.group().create(groupNew);
    assertThat(app.group().count(), equalTo(before.size()));
    Groups after = app.group().all();


    assertThat(after, equalTo(before));


  }
}
