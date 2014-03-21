import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import utility.TXTs;

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
    TXTs.write("test.txt", data);
    TXTs.append("test.txt", "a1");
    TXTs.append("test.txt", "a2");
    TXTs.write("test.txt", data );
  }
}