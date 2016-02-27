package jft.murinov;

/**
 * Created by d.murinov on 24.02.2016.
 */
public class PointWithMath {

    public double x;
    public double y;

    public PointWithMath(double x, double y){
        this.x = x;
        this.y = y;
    }

    public double distance(PointWithMath pm){
        return Math.sqrt((this.x - pm.x) * (this.x - pm.x) + (this.y - pm.y) * (this.y - pm.y));
    }

}
