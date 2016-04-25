package jft.murinov;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by Dima on 27.02.2016.
 */
public class SquareTests {

   @Test
   public void testArea(){
      Square s = new Square(5);
      Assert.assertEquals(s.area(), 22.0);

   }

}
