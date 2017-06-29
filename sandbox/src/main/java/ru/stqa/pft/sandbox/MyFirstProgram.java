package ru.stqa.pft.sandbox;

import com.sun.javaws.jnl.ResourcesDesc;

public class MyFirstProgram {

    public static void main(String[] args) {
       hello("world");
       hello("Kate");
       hello("super-user");


       Square square = new Square(5.0);

        System.out.println("площадь квадрата со стороной "+square.l+"  = "+square.area());


        Rectangle rectangle = new Rectangle(4,6);

        System.out.println("площадь прямоугольника со сторонами  "+rectangle.a+" и " +rectangle.b+"  = "+rectangle.area());

        Point a = new Point();
        Point b = new Point();

        a.x=0;
        a.y=2;

        b.x=3;
        b.y=3;
      System.out.println("расстояние между точками ("  +  a.x +   "," + a.y +") и ( "+  b.x +   "," + b.y +") равно " + distance(a,b));
    }

    public static void hello(String s){
        System.out.println("Hello, " + s +"!");
    }

    public static double distance(Point a, Point b){
      double dist;
      dist = Math.sqrt((a.x-b.x)*(a.x-b.x)+(a.y-b.y)*(a.y-b.y));
      return dist;
    }



}