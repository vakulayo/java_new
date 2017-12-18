package test.ru.stqa.pft.sandbox;

import org.testng.annotations.Test;

/**
 * Created by test on 30.08.2017.
 */
public class Trash {

  @Test
  public static void main(){
    System.out.println("mmmmmmmmmmmmmmm");
    System.out.println("       Sirenevaya      ul.\n      3       apt     \n          10     \n");
    System.out.println(removeBlanks("       Sirenevaya      ul.\n      3       apt     \n          10     \n"));
  }

  private static String removeBlanks(String s) {


    while (s.contains("  ")){
      s=s.replace("  "," ");
    }

    return  s;
  }
}
