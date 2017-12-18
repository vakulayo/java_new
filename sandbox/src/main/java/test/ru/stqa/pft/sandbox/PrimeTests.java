package test.ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.sandbox.Primes;

/**
 * Created by test on 09.08.2017.
 */
public class PrimeTests {

  @Test
  public  void TestPrime() {
    Assert.assertTrue(Primes.isPrimeFast(Integer.MAX_VALUE));
  }

  @Test
  public  void TestNonPrime() {
    Assert.assertFalse(Primes.isPrime(Integer.MAX_VALUE -2 ));
  }

  @Test (enabled = false)
  public  void TestPrimeLong() {
    long n = Integer.MAX_VALUE;
    Assert.assertTrue(Primes.isPrime(n));
  }
}
