package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;
import ru.stqa.pft.mantis.model.User;

/**
 * Created by test on 17.11.2017.
 */
public class UserHelper extends HelperBase {

  public UserHelper(ApplicationManager app){
    super(app);
  }

  public void login(String username, String password){
    wd.get(app.getProperty("web.baseUrl") + "/login_page.php");
    type(By.name("username"), username);
    click(By.cssSelector("input[value = 'Войти']"));
    type(By.name("password"), password);
    click(By.cssSelector("input[value = 'Войти']"));
  }

  public void resetPassword(User user){
    //click on menu item управление
     click(By.cssSelector("a[href='/mantisbt-2.6.0/manage_overview_page.php']"));
    // click on управление пользователями
    click(By.cssSelector("a[href='/mantisbt-2.6.0/manage_user_page.php']"));
    //click on exact user FIND EXACT USER
//user.getId()
    click(By.cssSelector(String.format("a[href = 'manage_user_edit_page.php?user_id=%s']", user.getId())));
        //user.getUsername()
    //reset password
    click(By.cssSelector("input[value='Сбросить пароль']"));
  }
}
