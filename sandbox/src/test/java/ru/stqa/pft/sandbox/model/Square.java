package ru.stqa.pft.sandbox.model;

/**
 * Created by test on 29.06.2017.
 */
public class Square {
  public double l;

  public Square(double l)
  {
    this.l=l;
  }

  public  double area(){
    return this.l*this.l;
  }
}
