package jft.murinov;

public class MyFirstProgram {

    public static void main(String[] args) {
        hello("world");
        hello("user");
        hello("Dima");

        Square s = new Square(5);
        System.out.println("Площадь квадрата со стороной " + s.length + " равна " + s.area());

        Rectangle r = new Rectangle(4, 6);

        System.out.println("Площадь прямоугольника со сторонами " + r.a + " и " + r.b + " равна " + r.area());

        Point p1 = new Point(10, 10);

        Point p2 = new Point(20, 20);

        System.out.println("Расстояние между точкой с координатами " + p1.x + ", " + p1.y + " и " + p2.x + ", " + p2.y + " равно " + distance(p1, p2));

        PointsWithMath pMath = new PointsWithMath(100, 100, 200, 200);

        System.out.println("Расстояние между точкой с координатами " + pMath.x1 + ", " + pMath.y1 + " и " + pMath.x2 + ", " + pMath.y2 + " равно " + pMath.distance());

        PointWithMath pm1 = new PointWithMath(20, 20);
        PointWithMath pm2 = new PointWithMath(30, 30);

        System.out.println("Расстояние между точкой с координатами " + pm1.x + ", " + pm1.y + " и " + pm2.x + ", " + pm2.y + " равно " + pm1.distance(pm2));
    }

    public static void hello(String somebody){
        System.out.println("Hello, " + somebody + "!");
    }

    public static double distance(Point p1, Point p2){

        return Math.sqrt((p1.x - p2.x) * (p1.x - p2.x) + (p1.y - p2.y) * (p1.y - p2.y));
    }



}
