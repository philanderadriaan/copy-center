/**
 * 
 */

package object;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import utility.CSVUtility;
import utility.FileUtility;

/**
 * @author padriaan
 * 
 */
public class HeaderManager
{
  private Map<String, List<String>> header_map = new HashMap<String, List<String>>();

  /**
   * @throws IOException 
   * 
   */
  public HeaderManager() throws IOException
  {
    final List<File> file_list = FileUtility.getFiles("Header");
    for (File i : file_list)
    {
      final String path = i.getPath();
      final List<List<String>> data = CSVUtility.read(path);
      final List<String> header = data.get(0);
      header_map.put(path, header);
    }
  }

  public int getIndex(final String the_table, final String the_column)
  {
    final List<String> header = header_map.get(the_table);
    final int index = header.indexOf(the_column);
    return index;
  }

  public List<String> getHeader(final String the_table)
  {
    final List<String> header = header_map.get(the_table);
    return header;
  }
}
