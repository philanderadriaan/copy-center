
package utility;

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
public final class CSVs
{

  /**
   * Private constructor prevents instantiation.
   */
  private CSVs()
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
    final List<String> lines = Texts.read(the_path);
    final List<List<String>> data = new ArrayList<List<String>>();

    for (String i : lines)
    {
      final String[] row_split = i.split(",");
      final int column_count = row_split.length;

      if (column_count > 1)
      {
        final String[] exact_row_split = i.split(",", -1);
        final List<String> fixed_cell_list = Arrays.asList(exact_row_split);
        final List<String> cell_list = new ArrayList<String>(fixed_cell_list);
        final String user_path = "Table\\User.csv";

        if (user_path.equals(the_path) && "0".equals(fixed_cell_list.get(2)))
        {
          cell_list.remove(2);
          cell_list.add("000");
        }
        data.add(cell_list);
      }
    }
    return data;
  }

  /**
   * Writes the data from a 2D list to the file defined by the path.
   * 
   * @param the_path Path of CSV file.
   * @param the_data Data to be written to the CSV file.
   * @throws IOException Throws exception when error in reading or writing.
   */
  public static void overwrite(final String the_path, final List<List<String>> the_data)
      throws IOException
  {
    final List<String> lines = new ArrayList<String>();
    for (List<String> i : the_data)
    {
      final String line = toLine(i);
      lines.add(line);
    }
    Texts.overwrite(the_path, lines);
  }

  /**
   * Appends a line of data to the end of a CSV file. No commas allowed!
   * 
   * @param the_path Path of CSV file.
   * @param the_row Line to be added to the CSV file.
   * @throws IOException Throws exception when error in reading or writing.
   */
  public static void add(final String the_path, final List<String> the_row) throws IOException
  {
    final String line = toLine(the_row);
    Texts.add(the_path, line);
  }

  /**
   * Convert a list of data into a comma separated line.
   * 
   * @param the_data List of data.
   * @return Comma separated string of the data.
   */
  private static String toLine(final List<String> the_data)
  {
    final StringBuilder string_builder = new StringBuilder();
    final String first_column = the_data.get(0);
    string_builder.append(first_column);
    for (int i = 1; i < the_data.size(); i++)
    {
      string_builder.append(',');
      final String current_column = the_data.get(i);
      string_builder.append(current_column);
    }
    final String line = string_builder.toString();
    return line;
  }
}
