package ru.stqa.pft.sandbox;

public class MyFirstProgram {

    public static void main(String[] args) {
       hello("world");
       hello("Kate");
       hello("super-user");


       Square square = new Square(5.0);

        System.out.println("площадь квадрата со стороной "+square.l+"  = "+area(square));


        Rectangle rectangle = new Rectangle(4,6);
       
        System.out.println("площадь прямоугольника со сторонами  "+rectangle.a+" и " +rectangle.b+"  = "+area(rectangle));


    }

    public static void hello(String s){
        System.out.println("Hello, " + s +"!");
    }

    public static double area(Square square){
        return square.l*square.l;
    }

    public static double area(Rectangle rectangle){
        return rectangle.a*rectangle.b;
    }
}