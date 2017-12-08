package ru.stqa.pft.soap;

import biz.futureware.mantis.rpc.soap.client.MantisConnectLocator;
import biz.futureware.mantis.rpc.soap.client.MantisConnectPortType;
import biz.futureware.mantis.rpc.soap.client.ProjectData;
import org.testng.annotations.Test;

import javax.xml.rpc.ServiceException;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;

/**
 * Created by test on 07.12.2017.
 */
public class SoapTests {

  @Test
  public void testGetProjects() throws MalformedURLException, ServiceException, RemoteException {
    MantisConnectPortType mc = new MantisConnectLocator()
            .getMantisConnectPort(new URL("http://localhost/mantisbt-2.6.0/api/soap/mantisconnect.php"));
    ProjectData[] projects = mc.mc_projects_get_user_accessible("administrator", "root");
    System.out.println(projects.length);
    for(ProjectData project: projects){
      System.out.println(project.getName());
    }
  }
}
