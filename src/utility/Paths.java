
package utility;

import io.CSV;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import enums.PathEnum;

/**
 * This class manages the header.
 * 
 * @author padriaan
 * @version 1
 */
public class Paths
{
  /**
   * Map to store the paths.
   */
  private static Map<PathEnum, String> my_path_map = getPaths();

  /**
   * Prevents instantiation.
   */
  private Paths()
  {
  }

  private static Map<PathEnum, String> getPaths()
  {
    final Map<PathEnum, String> path_map = new HashMap<PathEnum, String>();
    try
    {
      final List<List<String>> path_list = CSV.read("Paths.csv");
      for (List<String> i : path_list)
      {
        final String key_string = i.get(0);
        final PathEnum key = PathEnum.valueOf(key_string);
        final String value = i.get(1);
        path_map.put(key, value);
      }
    }
    catch (final IOException e)
    {
      e.printStackTrace();
    }
    return path_map;
  }

  public static String getPath(final PathEnum the_path_enum)
  {
    final String path = my_path_map.get(the_path_enum);
    return path;
  }
}
