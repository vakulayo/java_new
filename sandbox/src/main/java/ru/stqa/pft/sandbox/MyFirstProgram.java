package ru.stqa.pft.sandbox;

public class MyFirstProgram {

    public static void main(String[] args) {
       hello("world");
       hello("Kate");
       hello("super-user");


       Square square = new Square(5.0);

        System.out.println("площадь квадрата со стороной "+square.l+"  = "+square.area());


        Rectangle rectangle = new Rectangle(4,6);

        System.out.println("площадь прямоугольника со сторонами  "+rectangle.a+" и " +rectangle.b+"  = "+rectangle.area());


    }

    public static void hello(String s){
        System.out.println("Hello, " + s +"!");
    }




}