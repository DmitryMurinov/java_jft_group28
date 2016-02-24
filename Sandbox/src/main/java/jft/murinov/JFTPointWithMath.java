package jft.murinov;

/**
 * Created by d.murinov on 24.02.2016.
 */
public class JFTPointWithMath {

    public double x;
    public double y;

    public JFTPointWithMath(double x, double y){
        this.x = x;
        this.y = y;
    }

    public double distance(JFTPointWithMath pm){
        return Math.sqrt((this.x - pm.x) * (this.x - pm.x) + (this.y - pm.y) * (this.y - pm.y));
    }

}