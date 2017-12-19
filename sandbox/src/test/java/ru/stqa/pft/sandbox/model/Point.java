package ru.stqa.pft.sandbox.model;

/**
 * Created by test on 29.06.2017.
 */
public class Point {
  public double x;
  public double y;

  public Point(double x, double y){
    this.x = x;
    this.y = y;
  }

  public  double distance( Point b){
    double dist;
    dist = Math.sqrt((this.x-b.x)*(this.x-b.x)+(this.y-b.y)*(this.y-b.y));
    return dist;
  }
}
