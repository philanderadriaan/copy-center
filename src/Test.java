import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import utility.Texts;

/**
 * This class is used for testing.
 * 
 * @author padriaan
 * @version 1
 */
public final class Test
{

  /**
   * Cannot construct this class.
   */
  private Test()
  {
  }

  /**
   * Starts the test.
   * 
   * @param args Command line arguments
   * @throws IOException 
   */
  public static void main(final String[] args) throws IOException
  {
    final List<String> data = new ArrayList<String>();
    data.add("w1");
    data.add("w2");
    data.add("w3");
    Texts.overwrite("test.txt", data);
    Texts.add("test.txt", "a1");
    Texts.add("test.txt", "a2");
    Texts.overwrite("test.txt", data );
  }
}