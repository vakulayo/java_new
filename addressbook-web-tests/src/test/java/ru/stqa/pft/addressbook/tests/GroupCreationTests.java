package ru.stqa.pft.addressbook.tests;


import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;


import java.util.Comparator;
import java.util.List;


public class GroupCreationTests extends TestBase {

  @Test
  public void testGroupCreation() {

    app.goTo().groupPage();
    List<GroupData> before = app.group().list();
    GroupData groupNew = new GroupData("test1", null, null);
    app.group().create(groupNew);
    List<GroupData> after = app.group().list();
    Assert.assertEquals(after.size(),before.size() + 1);



    before.add(groupNew);

    /*for(GroupData gd :before){
      System.out.println("bbb"+gd.getName()+gd.getId());
    }
    System.out.println("gggggggggggggggggggggggggggggggggggggggggggggggggggggggggg");
    for(GroupData gd :after){
      System.out.println("bbb"+gd.getName()+gd.getId());
    }*/
    Comparator<? super GroupData> ById = (g1, g2) -> Integer.compare(g1.getId(),g2.getId());
    before.sort(ById);
    after.sort(ById);
    Assert.assertEquals(before, after);


     }

}
