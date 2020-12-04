package university.utm.fcim;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:data.properties")
public class SpringConfig {

  @Value("${animal.name}")
  private String name;

  @Bean(name = "animal")
  public Animal getAnimal(){
    Animal a =  new Animal();
    a.setName(name);
    return a;
  }

}