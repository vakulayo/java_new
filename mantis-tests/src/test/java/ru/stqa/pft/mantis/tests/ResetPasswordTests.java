package ru.stqa.pft.mantis.tests;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.mantis.model.MailMessage;
import ru.stqa.pft.mantis.model.User;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

/**
 * Created by test on 17.11.2017.
 */
public class ResetPasswordTests extends TestBase {
  @BeforeMethod
  public void startMailServer() {
    app.mail().start();
  }

  @Test
  public static void testResetPassword() throws IOException, MessagingException {
    String adminName = "administrator";
    String adminPassword = "root";
    app.user().login(adminName, adminPassword, "http://localhost/mantisbt-2.6.0/account_page.php");


    User user = findAnyValidUser();

    String username = user.getUsername();

    long now = System.currentTimeMillis();
    String newPassword = String.format("Password%s", now);


    app.user().resetPassword(user);
    List<MailMessage> mailMessages = app.mail().waitForMAil(1, 10000);
    String confirmationLink = findConfirmationLink(mailMessages, user.getEmail());
    app.user().changePassword(confirmationLink, user, newPassword);

    app.user().login(username, newPassword, "http://localhost/mantisbt-2.6.0/my_view_page.php");

  }

  private static User findAnyValidUser() {

    final SessionFactory sessionFactory;
    final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
    sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    List<User> users = session.createQuery("from User").list();

    session.getTransaction().commit();
    session.close();
    if (!users.get(0).getUsername().equals("administrator"))
      return users.get(0);
    else
      return users.get(1);

  }

  private static String findConfirmationLink(List<MailMessage> mailMessages, String email) {
    MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
    VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
    return regex.getText(mailMessage.text);
  }

  @AfterMethod(alwaysRun = true)

  public void stopMailServer() {
    app.mail().stop();
  }
}
