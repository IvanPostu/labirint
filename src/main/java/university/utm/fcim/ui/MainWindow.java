package university.utm.fcim.ui;

import javax.annotation.PostConstruct;
import javax.swing.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;

import java.awt.*;

@PropertySource("classpath:data.properties")
public class MainWindow extends JFrame {

  private static final long serialVersionUID = 1L;

  @Value("${window.height}")
  private int windowHeight;

  @Value("${window.width}")
  private int windowWidth;

  @Value("${window.name}")
  private String windowName;


  private MenuPanel menuPanel;

  @Autowired
  public void setMenuPanel(MenuPanel menuPanel) {
    this.menuPanel = menuPanel;
  }

  private GraphicPanel graphicPanel;

  @Autowired
  public void setMenuPanel(GraphicPanel graphicPanel) {
    this.graphicPanel = graphicPanel;
  }
  
  @PostConstruct
  public void onInit() {
    add(menuPanel);
    add(graphicPanel);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setTitle(windowName);
    setSize(new Dimension(windowWidth, windowHeight));
    setResizable(false);
    setLocationRelativeTo(null);
    setLayout(null);
    setVisible(true);
  }

  public MainWindow() {
    super();
  }

}