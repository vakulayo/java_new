package ru.stqa.pft.mantis.tests;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import javax.xml.rpc.ServiceException;
import java.net.MalformedURLException;
import java.rmi.RemoteException;

/**
 * Created by test on 11.12.2017.
 */
public class StatusTest extends TestBase {
  @BeforeTest
  public void skip() throws RemoteException, ServiceException, MalformedURLException {
    skipIfNotFixed(3);
  }

  @Test
  public void testStatus() throws RemoteException, ServiceException, MalformedURLException {
    System.out.println(isIssueOpen(1));
    System.out.println(isIssueOpen(2));
    System.out.println(isIssueOpen(3));
    System.out.println("тест прогнался");
     }



}
