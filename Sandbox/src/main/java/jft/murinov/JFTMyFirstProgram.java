package jft.murinov;

import java.awt.*;

public class JFTMyFirstProgram {

    public static void main(String[] args) {
        hello("world");
        hello("user");
        hello("Dima");

        JFTSquare s = new JFTSquare(5);
        System.out.println("Площадь квадрата со стороной " + s.length + " равна " + s.area());

        JFTRectangle r = new JFTRectangle(4, 6);

        System.out.println("Площадь прямоугольника со сторонами " + r.a + " и " + r.b + " равна " + r.area());

        Point p1 = new Point(10, 10);

        Point p2 = new Point(20, 20);

        System.out.println("Расстояние между точкой с координатами " + p1.x + ", " + p1.y + " и " + p2.x + ", " + p2.y + " равно " + distance(p1, p2));

        JFTPointsWithMath pMath = new JFTPointsWithMath(100, 100, 200, 200);

        System.out.println("Расстояние между точкой с координатами " + pMath.x1 + ", " + pMath.y1 + " и " + pMath.x2 + ", " + pMath.y2 + " равно " + pMath.distance());

    }

    public static void hello(String somebody){
        System.out.println("Hello, " + somebody + "!");
    }

    public static double distance(Point p1, Point p2){

        return Math.sqrt((p1.x - p2.x) * (p1.x - p2.x) + (p1.y - p2.y) * (p1.y - p2.y));
    }



}