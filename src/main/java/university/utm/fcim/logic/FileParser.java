package university.utm.fcim.logic;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class FileParser {
  public FileParser() {

  }

  public char[][] parse(File f, int lines, int columns) throws Exception {

    char[][] result = new char[lines][columns];
    FileReader fileReader = new FileReader(f);
    try (BufferedReader bufferedReader = new BufferedReader(fileReader)) {
      String strline;

      for (int i = 0; i < lines; i++) {
        strline = bufferedReader.readLine();
        for (int j = 0; j < columns; j++) {
          result[i][j] = strline.charAt(j);
        }
      }
    }

    return result;
  }
}
