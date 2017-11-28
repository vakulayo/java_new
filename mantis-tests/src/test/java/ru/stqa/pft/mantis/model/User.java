package ru.stqa.pft.mantis.model;

import org.hibernate.annotations.Type;

import javax.persistence.*;

/**
 * Created by test on 21.11.2017.
 */
@Entity
@Table(name = "mantis_user_table")
public class User {

 // @Column(name="username")
  //@Type(type = "text")

  private String username;

 // @Column(name="email")

  private String email;

  @Id
//  @Column(name="id")
  private int id;

 // @Column(name="password")
//  @Type(type = "text")
  private String password;

  public String getUsername() {
    return username;
  }

  public User withUsername(String username) {
    this.username = username;
    return this;
  }

  public String getEmail() {
    return email;
  }

  public User withEmail(String email) {
    this.email = email;
    return this;
  }

  public int getId() {
    return id;
  }

  public User withId(int id) {
    this.id = id;
    return this;
  }

  public String getPassword() {
    return password;
  }

  public User withPassword(String password) {
    this.password = password;
    return this;
  }
}
