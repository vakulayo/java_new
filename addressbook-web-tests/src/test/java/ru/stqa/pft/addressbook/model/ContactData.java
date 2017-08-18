package ru.stqa.pft.addressbook.model;

public class ContactData {


  private  String firstname;
  private  String lastname;
  private  String address;
  private  String email;
  private  String mobile;
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
    this.id = Integer.MAX_VALUE;
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

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    ContactData that = (ContactData) o;

    if (firstname != null ? !firstname.equals(that.firstname) : that.firstname != null) return false;
    return lastname != null ? lastname.equals(that.lastname) : that.lastname == null;
  }

  @Override
  public int hashCode() {
    int result = firstname != null ? firstname.hashCode() : 0;
    result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
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
}
