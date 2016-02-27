package jft.murinov;

/**
 * Created by Dima on 23.02.2016.
 */
public class Square {

   public double length;

   public Square(double length){
      this.length = length;
   }

   public double area(){
      return this.length * this.length;
   }

}
