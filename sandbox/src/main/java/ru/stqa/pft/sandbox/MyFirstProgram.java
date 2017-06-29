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

        Point a = new Point(0,2);
        Point b = new Point(3,3);


      System.out.println("расстояние между точками ("  +  a.x +   "," + a.y +") и ( "+  b.x +   "," + b.y +") равно " + a.distance(b));
    }

    public static void hello(String s){
        System.out.println("Hello, " + s +"!");
    }





}