package university.utm.fcim;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import university.utm.fcim.logic.FileParser;
import university.utm.fcim.state.LabirintState;
import university.utm.fcim.ui.GraphicPanel;
import university.utm.fcim.ui.MainWindow;
import university.utm.fcim.ui.MenuPanel;

@Configuration
@PropertySource("classpath:data.properties")
public class SpringConfig {

  @Value("${window.width}")
  private String name;

  @Bean(name="mainWindow")
  public MainWindow getWindow(){
    MainWindow mainWindow = new MainWindow();
    return mainWindow;
  }

  @Bean(name="menuPanel")
  public MenuPanel getMenuPanel(){
    return new MenuPanel();
  }

  @Bean(name="graphicPanel")
  public GraphicPanel getGraphicPanel(){
    return new GraphicPanel(getLabirintState());
  }

  @Bean(name="labirintState")
  public LabirintState getLabirintState(){
    return new LabirintState(getFileParser());
  }

  @Bean(name="fileParser")
  public FileParser getFileParser(){
    return new FileParser();
  }

}