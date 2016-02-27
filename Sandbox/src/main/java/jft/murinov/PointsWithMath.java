package jft.murinov;

/**
 * Created by Dima on 23.02.2016.
 */
public class PointsWithMath {

   public double x1;
   public double y1;
   public double x2;
   public double y2;

   public PointsWithMath(double x1, double y1, double x2, double y2){
      this.x1 = x1;
      this.y1 = y1;
      this.x2 = x2;
      this.y2 = y2;
   }

   public double distance(){
      return Math.sqrt((this.x1 - this.x2) * (this.x1 - this.x2) + (this.y1 - this.y2) * (this.y1 - this.y2));
   }
}
