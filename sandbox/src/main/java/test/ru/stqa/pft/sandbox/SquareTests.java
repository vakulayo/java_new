package test.ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.sandbox.Square;

/**
 * Created by test on 30.06.2017.
 */
public class SquareTests {

  @Test
  public void testArea(){
    Square square = new Square(5);
    Assert.assertEquals(square.area(),26.0);

  }
}
