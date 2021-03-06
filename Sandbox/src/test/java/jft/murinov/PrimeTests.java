package jft.murinov;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by Dima on 13.03.2016.
 */
public class PrimeTests {

    @Test
    public void testPrime(){
        Assert.assertTrue(Primes.isPrimeFast(Integer.MAX_VALUE));
    }

    @Test(enabled = true)
    public void testPrimeLong(){
        long n = Integer.MAX_VALUE;
        Assert.assertTrue(Primes.isPrime(n));
    }

    @Test
    public void testNonPrime(){
        Assert.assertFalse(Primes.isPrime(Integer.MAX_VALUE - 2));
    }
}
