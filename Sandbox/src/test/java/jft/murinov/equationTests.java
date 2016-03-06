package jft.murinov;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by Dima on 06.03.2016.
 */
public class equationTests {

    @Test
    public void test0(){
        Equation e = new Equation(1, 1, 1);
        Assert.assertEquals(e.totalRoots(), 0);
    }

    @Test
    public void test1(){
        Equation e = new Equation(1, 2, 1);
        Assert.assertEquals(e.totalRoots(), 1);
    }

    @Test
    public void test2(){
        Equation e = new Equation(1, 5, 6);
        Assert.assertEquals(e.totalRoots(), 2);
    }

    @Test
    public void testLinear(){
        Equation e = new Equation(0, 1, 1);
        Assert.assertEquals(e.totalRoots(), 1);
    }

    @Test
    public void testConstant(){
        Equation e = new Equation(0, 0, 1);
        Assert.assertEquals(e.totalRoots(), 0);
    }

    @Test
    public void testZero(){
        Equation e = new Equation(0, 0, 0);
        Assert.assertEquals(e.totalRoots(), -1);
    }
}
