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
public class CSVUtility
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
    List<List<String>> data = new ArrayList<List<String>>();

    BufferedReader reader = new BufferedReader(new FileReader(the_path));
    String line = reader.readLine();
    while (line != null)
    {
      if (line.split(",").length > 1)
      {
        List<String> l = new ArrayList(Arrays.asList(line.split(",", -1)));
        if (the_path.equals("Table\\User.csv") && l.get(2).equals("0"))
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
   * @param the_path
   * @param the_data
   * @throws IOException
   */
  public static void write(final String the_path, final List<List<String>> the_data)
      throws IOException
  {
    BufferedWriter writer = new BufferedWriter(new FileWriter(the_path));
    StringBuilder builder = new StringBuilder();
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
   * Appends a line of data to the CSV file. No commas allowed!
   * 
   * @param the_path
   * @param the_data
   * @throws IOException
   */
  public static void append(final String the_path, final List<String> the_data)
      throws IOException
  {
    BufferedWriter writer = new BufferedWriter(new FileWriter(the_path, true));
    StringBuilder builder = new StringBuilder();
    builder.append(the_data.get(0));
    for (int i = 1; i < the_data.size(); i++)
    {
      builder.append(',');
      builder.append(the_data.get(i));
    }
    builder.append("\n");
    writer.append(builder.toString());
    writer.close();
  }
}