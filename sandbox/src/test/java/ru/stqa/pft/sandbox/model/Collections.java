package ru.stqa.pft.sandbox.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by test on 09.08.2017.
 */
public class Collections {
  public static void main(String[] args){
    String[] langs = {"Java",    "C#", "Phyton",    "PHP"};


    List<String> languages = Arrays.asList("Java",    "C#", "Phyton",    "PHP");

   /* languages.add("Java");
    languages.add("C#");
    languages.add("Phyton");
    languages.add("PHP");


    for(int i=0;i<langs.length;i++){
     System.out.println("Я хочу выучить "+langs[i]);
   }*/

    for(int i=0;i< languages.size();i++){
      System.out.println("Я хочу выучить "+languages.get(i));
    }

    for(String l : languages){
      System.out.println("Я хочу выучить "+l);
    }
  }
}
