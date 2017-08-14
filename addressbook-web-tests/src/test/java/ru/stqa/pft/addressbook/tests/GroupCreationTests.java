package ru.stqa.pft.addressbook.tests;


import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;


import java.util.Comparator;
import java.util.HashSet;
import java.util.List;


public class GroupCreationTests extends TestBase {

  @Test
  public void testGroupCreation() {

    app.getNavigationHelper().gotoGroupPage();
    List<GroupData> before = app.getGroupHelper().getGroupList();
    GroupData groupNew = new GroupData("test1", null, null);
    app.getGroupHelper().createGroup(groupNew);
    List<GroupData> after = app.getGroupHelper().getGroupList();
    Assert.assertEquals(after.size(),before.size() + 1);



    before.add(groupNew);

    Comparator<? super GroupData> ById = (g1, g2) -> Integer.compare(g1.getId(),g2.getId());
    before.sort(ById);
    after.sort(ById);
    Assert.assertEquals(before, after);


     }

}
