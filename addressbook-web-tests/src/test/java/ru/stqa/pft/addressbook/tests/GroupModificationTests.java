package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

/**
 * Created by test on 28.07.2017.
 */
public class GroupModificationTests extends TestBase {

  @BeforeMethod

  public void ensurePreconditions() {
    app.goTo().groupPage();

    if (app.group().all().size() == 0) {
      app.group().create(new GroupData().withName("test1"));
    }
  }

  @Test
  public void testGroupModification() {

    Set<GroupData> before = app.group().all();
    GroupData modifiedGroup = before.iterator().next();

    GroupData groupNew = new GroupData().withId(modifiedGroup.getId()).withName("test1").withHeader("test2").withFooter("test3");
    app.group().modify(groupNew);
    Set<GroupData> after = app.group().all();
    Assert.assertEquals(after.size(), before.size());
    before.remove(modifiedGroup);
    before.add(groupNew);


    Assert.assertEquals(before, after);
  }


}
