package jft.murinov;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by Dima on 27.02.2016.
 */
public class PointTests {


   @Test
   public void distanceTest(){

      PointWithMath p1 = new PointWithMath(10, 10);
      PointWithMath p2 = new PointWithMath(20, 10);

      Assert.assertEquals(p1.distance(p2), 10.0);
   }

   @Test
   public void distanceTestZero(){

      PointWithMath p1 = new PointWithMath(10, 10);
      PointWithMath p2 = new PointWithMath(10, 10);

      Assert.assertEquals(p1.distance(p2), 0.0);
   }

   @Test(enabled = false)
   public void distanceTestInt(){

      PointWithMath p1 = new PointWithMath(10, 10);
      PointWithMath p2 = new PointWithMath(10, 10);

      Assert.assertEquals(p1.distance(p2), 0);
   }

   @Test(enabled = false)
   public void distanceTestLetter(){

      PointWithMath p1 = new PointWithMath(10, 10);
      PointWithMath p2 = new PointWithMath(25, 25);

      Assert.assertEquals(p1.distance(p2), "A");
   }

}
