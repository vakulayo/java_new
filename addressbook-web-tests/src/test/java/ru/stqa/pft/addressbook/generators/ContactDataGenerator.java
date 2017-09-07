package ru.stqa.pft.addressbook.generators;

import ru.stqa.pft.addressbook.model.ContactData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by test on 07.09.2017.
 */
public class ContactDataGenerator {

  public static void main(String[] args) throws IOException {

    int count = Integer.parseInt(args[0]);
    File file = new File(args[1]);


    System.out.println("ddddddddddd"+count);
    System.out.println("fffffffffff"+file.getAbsolutePath());
    List<ContactData> contacts = generateContacts(count);
    save(contacts,file);


  }

  private static void save(List<ContactData> contacts, File file) throws IOException {
    System.out.println(file.getAbsolutePath());
    System.out.println(new File(".").getAbsolutePath());
    Writer writer = new FileWriter(file);
    for(ContactData cd: contacts){

      writer.write(String.format("%s;%s;%s;%s;%s;%s;%s;%s;%s\n", cd.getFirstname(), cd.getLastname(), cd.getAddress(), cd.getEmail(),
              cd.getEmail2(), cd.getEmail3(), cd. getMobilePhone(), cd.getHomePhone(), cd.getWorkPhone() ));
    }
    writer.close();

  }

  private static List<ContactData> generateContacts(int count) {
    List<ContactData> contacts = new ArrayList<ContactData>();
    for(int i =0;i<count;i++){
      ContactData cd = new ContactData().withFirstname(String.format("Firstname%s",i))
              .withLastname(String.format("Lastname%s",i)).withAddress(String.format("Sirenevaya ul dom %s apt %s",i,i))
              .withEmail(String.format("lastname_firstname_%s@mail.ru",i)).withEmail2(String.format("lastname_firstname_%s@google.ru",i))
              .withEmail3(String.format("lastname_firstname_%s@yandex.ru.ru",i)).withMobilePhone(String.format("+7911123456%s",i))
              .withHomePhone(String.format("+7921123456%s",i)).withWorkPhone(String.format("+7905123456%s",i));
      contacts.add(cd);
    }
    return contacts;
  }
}
