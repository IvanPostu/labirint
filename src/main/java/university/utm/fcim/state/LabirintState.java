package university.utm.fcim.state;

import java.util.ArrayList;
import java.util.List;
import java.awt.*;
import java.io.File;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.ResourceUtils;

import university.utm.fcim.logic.AStar;
import university.utm.fcim.logic.FileParser;
import university.utm.fcim.logic.Node;
import university.utm.fcim.ui.Block;

public class LabirintState {

  @Value(value = "${blocks.count.x}")
  private int blocksPerLine;

  @Value(value = "${blocks.count.y}")
  private int blocksPerColumn;

  @Value(value = "${block.size}")
  private int blockSize;

  @Value(value = "${labirint.state.initial-level}")
  private String initialLevel;

  private List<Block> airBlocks;
  private List<Block> wallBlocks;
  private List<Block> pathBlocks;
  private Block start;
  private Block finish;
  private List<int[]> blocks;

  private final FileParser fileParser;

  public LabirintState(FileParser fileParser) {
    this.fileParser = fileParser;
  }

  @PostConstruct
  private void onInit() throws Exception {
    File file = ResourceUtils.getFile(initialLevel);
 

    char[][] fileData = fileParser.parse(file, blocksPerLine, blocksPerColumn);
    setState(fileData);

  }

  public void setState(char[][] newState) {
    airBlocks = new ArrayList<>(blocksPerLine * blocksPerColumn);
    wallBlocks = new ArrayList<>();
    pathBlocks = new ArrayList<>();
    blocks = new ArrayList<>();
    
    for (int i = 0; i < blocksPerLine; i++) {
      for (int j = 0; j < blocksPerColumn; j++) {
        airBlocks.add(new Block(i, j));

        if (newState[i][j] == BlockType.WALL.getFileNotation()) {
          wallBlocks.add(new Block(j, i));
          int[] arr = new int[2];
          arr[0] = i;
          arr[1] = j;
          blocks.add(arr);
        }

        if (newState[i][j] == BlockType.START.getFileNotation()) {
          start = new Block(j, i);
        }

        if (newState[i][j] == BlockType.FINISH.getFileNotation()) {
          finish = new Block(j, i);
        }
      }
    }

  }

  public void run() {
    AStar aStar = new AStar();
    aStar.setData(blocksPerLine, blocksPerColumn, new Node(start.getY(), start.getX()),
        new Node(finish.getY(), finish.getX()));
    int[][] blocksArray = new int[blocks.size()][];

    for (int i = 0; i < blocks.size(); i++) {
      blocksArray[i] = blocks.get(i);
    }

    aStar.setBlocks(blocksArray);
    List<Node> path = aStar.findPath();
    for (Node node : path) {
      pathBlocks.add(new Block(node.getCol(), node.getRow()));
    }
  }

  public void render(Graphics2D g) {
    airBlocks.forEach(a -> {
      g.setColor(Color.GRAY);
      g.drawRect(a.getX() * blockSize, a.getY() * blockSize, blockSize, blockSize);
    });

    wallBlocks.forEach(a -> {
      g.setColor(Color.DARK_GRAY);
      g.drawRect(a.getX() * blockSize, a.getY() * blockSize, blockSize, blockSize);
      g.fillRect(a.getX() * blockSize, a.getY() * blockSize, blockSize, blockSize);
    });

    pathBlocks.forEach(a -> {
      g.setColor(Color.RED);
      g.drawOval(a.getX() * blockSize, a.getY() * blockSize, blockSize, blockSize);
    });

    if (start != null) {
      g.setColor(Color.GREEN);
      g.fillRect(start.getX() * blockSize, start.getY() * blockSize, blockSize, blockSize);
    }

    if (finish != null) {
      g.setColor(Color.BLUE);
      g.fillRect(finish.getX() * blockSize, finish.getY() * blockSize, blockSize, blockSize);
    }

  }

}
