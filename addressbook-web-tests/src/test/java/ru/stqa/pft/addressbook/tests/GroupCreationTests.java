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
    Groups after = app.group().all();

    assertThat(after.size(), equalTo(before.size()+1));
    assertThat(after, equalTo(
            before.withAdded( groupNew.withId(after.stream().mapToInt((g)->g.getId()).max().getAsInt()))));


     }

}
