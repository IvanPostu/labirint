package university.utm.fcim.ui;

import javax.annotation.PostConstruct;
import javax.swing.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import university.utm.fcim.state.LabirintState;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.VolatileImage;

public class GraphicPanel extends JPanel implements ActionListener {

  private static final long serialVersionUID = -3703834452784786480L;

  private static final Color DEFAULT_BG_COLOR = Color.DARK_GRAY;

  private Timer timer;
  private VolatileImage image;

  @Value(value = "${window.graphicPanel.x}")
  private int graphicPanelX;

  @Value(value = "${window.graphicPanel.y}")
  private int graphicPanelY;

  @Value(value = "${window.graphicPanel.width}")
  private int graphicPanelWidth;

  @Value(value = "${window.graphicPanel.height}")
  private int graphicPanelHeight;

  private final LabirintState labirintState;

  @Autowired
  public GraphicPanel(LabirintState labirintState) {
    super();

    this.labirintState = labirintState;
  }

  @PostConstruct
  private void onInit() {
    setBackground(DEFAULT_BG_COLOR);
    setBounds(graphicPanelX, graphicPanelY, graphicPanelWidth, graphicPanelHeight);
    setPreferredSize(new Dimension(graphicPanelWidth, graphicPanelHeight));
    setFocusable(true);
    requestFocus();
  }

  @Override
  public void addNotify() {
    super.addNotify();
    image = createVolatileImage(graphicPanelWidth, graphicPanelHeight);
    this.timer = new Timer(1000 / 60, this);
    this.timer.start();
  }

  private void update() {
    repaint();
    setFocusable(true);
    requestFocus();
    
  }
  
  public void refresh(){
    image = createVolatileImage(graphicPanelWidth, graphicPanelHeight);
  }

  @Override
  public void actionPerformed(ActionEvent actionEvent) {
    update();
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);

    Graphics2D g2 = image.createGraphics();
    g2.setBackground(DEFAULT_BG_COLOR);
    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
    labirintState.render(g2);
    g.drawImage(image.getScaledInstance(graphicPanelWidth, graphicPanelHeight, Image.SCALE_FAST),
    0, 0, null
    );

  }

}