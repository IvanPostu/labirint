package university.utm.fcim.logic;

import java.io.File;

import org.junit.jupiter.api.Test;
import org.springframework.util.ResourceUtils;
import org.junit.jupiter.api.Assertions;

public class FileParserTest {

  @Test
  public void parseTest() throws Exception {
    FileParser parser = new FileParser();
    File file = ResourceUtils.getFile("classpath:test-levels/level_test.in");
    Assertions.assertTrue(file.exists());
    
    char[][]data = parser.parse(file, 8, 8);

    Assertions.assertEquals(data[1][1], 'W');
    Assertions.assertEquals(data[2][2], 'W');
    Assertions.assertEquals(data[2][1], 'S');
    Assertions.assertEquals(data[1][2], 'F');

  }
}
