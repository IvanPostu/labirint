package university.utm.fcim;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

  public static void main(String... args) {
    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);

    Animal a = context.getBean("animal", Animal.class);
    System.out.println(a.getName());

    context.close();
    System.out.println(1);
  }
}