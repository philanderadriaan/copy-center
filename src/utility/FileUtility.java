/**
 * 
 */

package utility;

import java.io.File;
import java.util.Arrays;
import java.util.List;

/**
 * @author padriaan
 * 
 */
public final class FileUtility
{

  /**
   * 
   */
  private FileUtility()
  {
  }

  public static List<File> getFiles(final String the_folder_name)
  {
    final File folder = new File(the_folder_name);
    final File[] file_array = folder.listFiles();
    final List<File> file_list = Arrays.asList(file_array);
    return file_list;
  }
  
  public static boolean containsKeyword()
  {
    return false;
  }
}
