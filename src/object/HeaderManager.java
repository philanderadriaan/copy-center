/**
 * 
 */

package object;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import utility.CSVs;
import utility.Folders;

/**
 * @author padriaan
 * @version 1
 */
public class HeaderManager
{
  
  /**
   * Maps all the headers to table names.
   */
  private Map<String, List<String>> my_header_map = new HashMap<String, List<String>>();

  /**
   * @throws IOException 
   * 
   */
  public HeaderManager() throws IOException
  {
    final List<File> file_list = Folders.getFiles("Header");
    for (File i : file_list)
    {
      final String path = i.getPath();
      final List<List<String>> data = CSVs.read(path);
      final List<String> header = data.get(0);
      my_header_map.put(path, header);
    }
  }

  /**
   * Gets the index of the column of the table.
   * 
   * @param the_table Table in question.
   * @param the_column Column needed.
   * @return Integer of coilumn index.
   */
  public int getIndex(final String the_table, final String the_column)
  {
    final List<String> header = my_header_map.get(the_table);
    final int index = header.indexOf(the_column);
    return index;
  }

  /**
   * Gets the header.
   * 
   * @param the_table Table of the headers.
   * @return A list of strings containing header.
   */
  public List<String> getHeader(final String the_table)
  {
    final List<String> header = my_header_map.get(the_table);
    return header;
  }
}
