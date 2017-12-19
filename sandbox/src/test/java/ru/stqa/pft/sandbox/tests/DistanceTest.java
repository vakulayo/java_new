package ru.stqa.pft.sandbox.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.sandbox.model.Point;


/**
 * Created by test on 03.07.2017.
 */
public class DistanceTest {
  @Test
  public void testDist(){
    Point a = new Point(0,0);
    Point b = new Point (3,4);

    Assert.assertEquals(a.distance(b),5.0);
  }


  @Test

  public  void testDist1(){
    Point a = new Point(-1,-1);
    Point b = new Point(-13,-6);
    Assert.assertEquals(a.distance(b),13.0);
  }

  @Test
  public void testDist2(){
   Point a = new Point (0,0);
   Point b = new Point(0,0);

   Assert.assertEquals(a.distance(b), 0.0);
  }

  @Test
  public void testDist3(){
    Point a = new Point(1,-1);
    Point b =  new Point(-4,2);
            Assert.assertEquals(a.distance(b),Math.sqrt(34),0.00000000000001);
  }

}
