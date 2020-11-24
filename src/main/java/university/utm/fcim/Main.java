package university.utm.fcim;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

  public static void main( String ...args ){
    ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
      "applicationContext.xml"
    );
   
    Animal a = context.getBean("animal", Animal.class);
    System.out.println(a.getName());

    context.close();
  }
}