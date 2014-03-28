/**
 * 
 */

package utility;

import io.CSV;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import enums.PathEnum;

/**
 * @author padriaan
 * 
 */
public class Paths
{
  /**
   * 
   */
  private static Map<PathEnum, String> my_paths = getPaths();

  public Paths()
  {
  }

  private static Map<PathEnum, String> getPaths() throws IOException
  {
    final Map<PathEnum, String> paths = new HashMap<PathEnum, String>();
    final List<List<String>> locations = CSV.read("Paths.txt");
    return paths;
  }
}
