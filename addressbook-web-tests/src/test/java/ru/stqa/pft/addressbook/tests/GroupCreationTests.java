package ru.stqa.pft.addressbook.tests;


import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;


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


    int max = after.stream().max((o1, o2) -> Integer.compare(o1.getId(),o2.getId())).get().getId();
    groupNew.setId(max);
    before.add(groupNew);

    Assert.assertEquals(new HashSet<>(before), new HashSet<>(after));


     }

}
