package university.utm.fcim.ui;

import javax.annotation.PostConstruct;
import javax.swing.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import university.utm.fcim.logic.FileParser;
import university.utm.fcim.state.LabirintState;

import java.awt.*;
import java.io.File;
import java.awt.event.*;

public class MenuPanel extends JPanel {

  private static final long serialVersionUID = 1L;

  private JButton runButton;
  private JButton openFileButton;

  @Value(value = "${blocks.count.x}")
  private int blocksPerLine;

  @Value(value = "${blocks.count.y}")
  private int blocksPerColumn;

  @Value(value = "${window.menuPanel.x}")
  private int menuPanelX;

  @Value(value = "${window.menuPanel.y}")
  private int menuPanelY;

  @Value(value = "${window.menuPanel.width}")
  private int menuPanelWidth;

  @Value(value = "${window.menuPanel.height}")
  private int menuPanelHeight;

  @Autowired
  private LabirintState labirintState;

  @Autowired
  private GraphicPanel graphicPanel;

  @Autowired
  private FileParser parser;

  @PostConstruct
  private void onInit() {
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
    openFileButton.addActionListener(this::onOpenFileClick);
    add(openFileButton);

    runButton = new JButton("Run");
    runButton.setBounds(BUTTON_X, 70, BUTTON_WIDTH, 35);
    runButton.setBackground(Color.white);
    runButton.setBorder(BorderFactory.createLineBorder(Color.darkGray));
    runButton.addActionListener(this::onRunClick);
    add(runButton);
  }

  private void onOpenFileClick(ActionEvent e) {
    JFileChooser fileChooser = new JFileChooser();
    fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
    int result = fileChooser.showOpenDialog(this);
    if (result == JFileChooser.APPROVE_OPTION) {
      File selectedFile = fileChooser.getSelectedFile();
      System.out.println("Selected file: " + selectedFile.getAbsolutePath());
      try {
        char[][] fileData = parser.parse(selectedFile, blocksPerLine, blocksPerColumn);
        labirintState.setState(fileData);
        graphicPanel.refresh();
      } catch (Exception e1) {
        e1.printStackTrace();
      }
    }
  }

  private void onRunClick(ActionEvent e){
    labirintState.run();
  }

  public MenuPanel() {
    super();
  }

}