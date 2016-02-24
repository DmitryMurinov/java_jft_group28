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

        JFTPoint p1 = new JFTPoint(10, 10);

        JFTPoint p2 = new JFTPoint(20, 20);

        System.out.println("Расстояние между точкой с координатами " + p1.x + ", " + p1.y + " и " + p2.x + ", " + p2.y + " равно " + distance(p1, p2));

        JFTPointsWithMath pMath = new JFTPointsWithMath(100, 100, 200, 200);

        System.out.println("Расстояние между точкой с координатами " + pMath.x1 + ", " + pMath.y1 + " и " + pMath.x2 + ", " + pMath.y2 + " равно " + pMath.distance());

        //Реализация варианта, который утром 24.02.2016 обсуждали в Скайпе (p2.distance(p1)

        //Инициализируем объекты для 2 точек

        JFTPointWithMath pm1 = new JFTPointWithMath(20, 20);
        JFTPointWithMath pm2 = new JFTPointWithMath(30, 30);

        System.out.println("Расстояние между точкой с координатами " + pm1.x + ", " + pm1.y + " и " + pm2.x + ", " + pm2.y + " равно " + pm1.distance(pm2));
    }

    public static void hello(String somebody){
        System.out.println("Hello, " + somebody + "!");
    }

    public static double distance(JFTPoint p1, JFTPoint p2){

        return Math.sqrt((p1.x - p2.x) * (p1.x - p2.x) + (p1.y - p2.y) * (p1.y - p2.y));
    }



}