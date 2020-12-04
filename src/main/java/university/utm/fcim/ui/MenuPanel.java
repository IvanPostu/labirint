package university.utm.fcim.ui;

import javax.annotation.PostConstruct;
import javax.swing.*;

import org.springframework.beans.factory.annotation.Value;

import java.awt.*;

public class MenuPanel extends JPanel {

  private static final long serialVersionUID = 1L;

  private JButton runButton;
  private JButton openFileButton;

  @Value(value = "${window.menuPanel.x}")
  private int menuPanelX;

  @Value(value = "${window.menuPanel.y}")
  private int menuPanelY;

  @Value(value = "${window.menuPanel.width}")
  private int menuPanelWidth;

  @Value(value = "${window.menuPanel.height}")
  private int menuPanelHeight;

  @PostConstruct
  private void onInit(){
    setBackground(new Color(150, 160, 210, 200));
    setBounds(menuPanelX, menuPanelY, menuPanelWidth, menuPanelHeight);
    setLayout(null);
    setBorder(BorderFactory.createLineBorder(new Color(120, 120, 120, 120)));

    final int BUTTON_WIDTH = 120;
    final int BUTTON_X = menuPanelWidth / 2 - BUTTON_WIDTH / 2;

    openFileButton = new JButton("Open file");
    openFileButton.setBounds(BUTTON_X, 20, BUTTON_WIDTH, 35);
    openFileButton.setBackground(Color.white);
    openFileButton.setBorder(BorderFactory.createLineBorder(Color.darkGray));
    add(openFileButton);

    runButton = new JButton("Run");
    runButton.setBounds(BUTTON_X, 70, BUTTON_WIDTH, 35);
    runButton.setBackground(Color.white);
    runButton.setBorder(BorderFactory.createLineBorder(Color.darkGray));
    add(runButton);
  }

  public MenuPanel() {
    super();
  }

}