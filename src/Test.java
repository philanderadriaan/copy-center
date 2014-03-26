import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import utility.IOtxt;

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
    IOtxt.overwrite("test.txt", data);
    IOtxt.add("test.txt", "a1");
    IOtxt.add("test.txt", "a2");
    IOtxt.overwrite("test.txt", data );
  }
}