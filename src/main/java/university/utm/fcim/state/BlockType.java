package university.utm.fcim.state;

public enum BlockType {
  AIR('0'), WALL('W'), START('S'), FINISH('F');

  private char fileNotation;

  public char getFileNotation() {
    return fileNotation;
  }

  private BlockType(char fileNotation){
    this.fileNotation = fileNotation;
  }
}
