package ru.stqa.pft.addressbook.model;

public class ContactData {
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    ContactData that = (ContactData) o;

    if (id != that.id) return false;
    if (!firstname.equals(that.firstname)) return false;
    return lastname.equals(that.lastname);
  }

  @Override
  public int hashCode() {
    int result = firstname.hashCode();
    result = 31 * result + lastname.hashCode();
    result = 31 * result + id;
    return result;
  }

  @Override
  public String toString() {
    return "ContactData{" +
            "firstname='" + firstname + '\'' +
            ", lastname='" + lastname + '\'' +
            ", id=" + id +
            '}';
  }

  private final String firstname;
  private final String lastname;
  private final String address;
  private final String email;
  private final String mobile;
  private int id;
  private String group;


  public ContactData( int id, String firstname, String lastname, String address, String email, String mobile, String group) {
    this.firstname = firstname;
    this.lastname = lastname;
    this.address = address;
    this.email = email;
    this.mobile = mobile;
    this.group = group;
    this.id = id;
  }

  public ContactData( String firstname, String lastname, String address, String email, String mobile, String group) {
    this.firstname = firstname;
    this.lastname = lastname;
    this.address = address;
    this.email = email;
    this.mobile = mobile;
    this.group = group;
    this.id = 0;
  }

  public String getFirstname() {
    return firstname;
  }

  public String getLastname() {
    return lastname;
  }

  public String getAddress() {
    return address;
  }

  public String getEmail() {
    return email;
  }

  public String getMobile() {
    return mobile;
  }

  public String getGroup() {
    return group;
  }

  public int getId() {    return id;  }

  public void setId(int id) {this.id = id;}


}
