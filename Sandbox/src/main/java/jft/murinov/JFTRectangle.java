package jft.murinov;

/**
 * Created by Dima on 23.02.2016.
 */
public class JFTRectangle {

   public double a;
   public double b;

   public JFTRectangle(double a, double b){
      this.a = a;
      this.b = b;
   }

   public double area(){
      return this.a * this.b;
   }

}
