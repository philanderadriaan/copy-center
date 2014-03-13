
package utility;

import java.io.File;
import java.util.Arrays;
import java.util.List;

/**
 * @author padriaan
 * @version 1
 */
public final class FileUtility
{

  /**
   * Cannot construct this class.
   */
  private FileUtility()
  {
  }

  /**
   * Gets a list of files in a folder..
   * 
   * @param the_folder_name folder name.
   * @return A list of all files in the folder.
   */
  public static List<File> getFiles(final String the_folder_name)
  {
    final File folder = new File(the_folder_name);
    final File[] file_array = folder.listFiles();
    final List<File> file_list = Arrays.asList(file_array);
    return file_list;
  }
  
  /**
   * Whether the file contains that keyword.
   * 
   * @return Whether it contains the 
   */
  public static boolean containsKeyword()
  {
    return false;
  }
}
