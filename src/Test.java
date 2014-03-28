import io.TXT;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
   * @param the_args Command line arguments
   * @throws IOException 
   */
  public static void main(final String[] the_args) throws IOException
  {
    final List<String> data = new ArrayList<String>();
    data.add("w1");
    data.add("w2");
    data.add("w3");
    TXT.overwrite("test.txt", data);
    TXT.add("test.txt", "a1");
    TXT.add("test.txt", "a2");
    TXT.overwrite("test.txt", data );
  }
}