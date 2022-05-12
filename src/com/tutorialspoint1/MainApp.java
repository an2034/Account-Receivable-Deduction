package com.tutorialspoint1;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainApp {
   private static ApplicationContext context;

public static void main(String[] args) {
      context = new ClassPathXmlApplicationContext("applicationContext.xml");

      TextEditor te = (TextEditor) context.getBean("textEditor1");
      te.spellCheck();
   }
}