package ru.stqa.pft.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import org.junit.runners.Parameterized;
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

  @Parameter(names = "-c", description = "Contact number")
  public int count;

  @Parameter(names = "-f", description = "Path to file")
  public String file;

  public static void main(String[] args) throws IOException {
    ContactDataGenerator generator = new ContactDataGenerator();
    JCommander jCommander = new JCommander(generator);
    try {
      jCommander.parse(args);
    } catch (ParameterException ex){
      jCommander.usage();
      return;
    }
    generator.run();


  }

  private  void run() throws IOException {
    List<ContactData> contacts = generateContacts(count);
    save(contacts,new File(file));
  }

  private  void save(List<ContactData> contacts, File file) throws IOException {


    Writer writer = new FileWriter(file);
    for(ContactData cd: contacts){

      writer.write(String.format("%s;%s;%s;%s;%s;%s;%s;%s;%s\n", cd.getFirstname(), cd.getLastname(), cd.getAddress(), cd.getEmail(),
              cd.getEmail2(), cd.getEmail3(), cd. getMobilePhone(), cd.getHomePhone(), cd.getWorkPhone() ));
    }
    writer.close();

  }

  private  List<ContactData> generateContacts(int count) {
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
