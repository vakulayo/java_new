package ru.stqa.pft.addressbook.tests;


import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;


import java.util.Comparator;
import java.util.List;
import java.util.Set;


public class GroupCreationTests extends TestBase {

  @Test
  public void testGroupCreation() {

    app.goTo().groupPage();
    Set<GroupData> before = app.group().all();
    GroupData groupNew = new GroupData().withName("test1");
    app.group().create(groupNew);
    Set<GroupData> after = app.group().all();
    Assert.assertEquals(after.size(),before.size() + 1);
    groupNew.withId(after.stream().mapToInt((g)->g.getId()).max().getAsInt());
    before.add(groupNew);
    Assert.assertEquals(before, after);


     }

}
