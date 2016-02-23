package jft.murinov;

public class JFTMyFirstProgram {

    public static void main(String[] args) {
        hello("world");
        hello("user");
        hello("Dima");

        double length = 5;
        System.out.println("Площадь квадрата со стороной " + length + " равна " + area(length));

        double a = 4;
        double b = 6;
        System.out.println("Площадь прямоугольника со сторонами " + a + " и " + b + " равна " + area(a ,b));

    }

    public static void hello(String somebody){
        System.out.println("Hello, " + somebody + "!");
    }

    public static double area(double len){
        return len * len;
    }

    public static double area(double a, double b){
        return a * b;
    }

}