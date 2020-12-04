package university.utm.fcim.state;

import java.util.ArrayList;
import java.util.List;
import java.awt.*;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;

import university.utm.fcim.ui.Block;

public class LabirintState {
  
  @Value(value="${blocks.count.x}")
  private int blocksPerLine;

  @Value(value="${blocks.count.y}")
  private int blocksPerColun;

  @Value(value = "${block.size}")
  private int blockSize;

  private List<Block> airBlocks;

  public LabirintState(){}

  @PostConstruct
  private void onInit(){
    airBlocks = new ArrayList<>(blocksPerLine * blocksPerColun);

    for (int i = 0; i < blocksPerLine; i++) {
      for (int j = 0; j < blocksPerColun; j++) {
        airBlocks.add(new Block(i, j));
      }
    }
  }

  public void render(Graphics2D g){
    airBlocks.forEach(a -> {
      g.setColor(Color.GRAY);
      g.drawRect(a.getX() * blockSize, a.getY() * blockSize, blockSize, blockSize); 
    });
  }

}
