/**
 * 
 */

package utility;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author padriaan
 * 
 */
public class TXTUtility
{

  /**
   * 
   */
  private TXTUtility()
  {
    // TODO Auto-generated constructor stub
  }

  public static List<String> read(String the_path) throws IOException
  {
    List<String> data = new ArrayList<String>();
    FileReader file_reader = new FileReader(the_path);
    BufferedReader buffered_reader = new BufferedReader(file_reader);
    String line = buffered_reader.readLine();
    while (line != null)
    {
      data.add(line);
      line = buffered_reader.readLine();
    }
    buffered_reader.close();
    return data;
  }
}
