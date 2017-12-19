package ru.stqa.pft.sandbox.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.sandbox.model.Square;


/**
 * Created by test on 30.06.2017.
 */
public class SquareTests {

  @Test
  public void testArea(){
    Square square = new Square(5);
    Assert.assertEquals(square.area(),25.0);

  }
}
