
package utility;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Utility class to process CSV files.
 * 
 * @author padriaan
 * @version 1
 */
public final class CSVUtility
{

  /**
   * Private constructor prevents instantiation.
   */
  private CSVUtility()
  {
  }

  /**
   * Reads a CSV file and outputs it as a 2D list of strings.
   * 
   * @param the_path Path to the CSV file.
   * @return 2D list containing the data.
   * @throws IOException For IO errors.
   */
  public static List<List<String>> read(final String the_path) throws IOException
  {
    final List<List<String>> data = new ArrayList<List<String>>();

    final BufferedReader reader = new BufferedReader(new FileReader(the_path));
    String line = reader.readLine();
    while (line != null)
    {
      if (line.split(",").length > 1)
      {
        final List<String> l = new ArrayList(Arrays.asList(line.split(",", -1)));
        if ("Table\\User.csv".equals(the_path) && "0".equals(l.get(2)))
        {
          l.remove(2);
          l.add("000");
        }
        data.add(l);
      }
      line = reader.readLine();
    }
    reader.close();
    return data;
  }

  /**
   * Writes the data from a 2D list to the file defined by the path.
   * 
   * @param the_path Path of CSV file.
   * @param the_data Data to be written to the CSV file.
   * @throws IOException Throws exception when error in reading or writing.
   */
  public static void write(final String the_path, final List<List<String>> the_data)
      throws IOException
  {
    final BufferedWriter writer = new BufferedWriter(new FileWriter(the_path));
    final StringBuilder builder = new StringBuilder();
    for (List<String> i : the_data)
    {
      builder.append(i.get(0));
      for (int j = 1; j < i.size(); j++)
      {
        builder.append(',');
        builder.append(i.get(j));
      }
      builder.append("\n");
    }
    writer.write(builder.toString());
    writer.close();
  }

  /**
   * Appends a line of data to the end of a CSV file. No commas allowed!
   * 
   * @param the_path Path of CSV file.
   * @param the_line Line to be added to the CSV file.
   * @throws IOException Throws exception when error in reading or writing.
   */
  public static void append(final String the_path, final List<String> the_line)
      throws IOException
  {
    final BufferedWriter writer = new BufferedWriter(new FileWriter(the_path, true));
    final StringBuilder builder = new StringBuilder();
    builder.append(the_line.get(0));
    for (int i = 1; i < the_line.size(); i++)
    {
      builder.append(',');
      builder.append(the_line.get(i));
    }
    builder.append("\n");
    writer.append(builder.toString());
    writer.close();
  }
}
