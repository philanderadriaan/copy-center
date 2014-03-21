
package utility;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Utility for processing text files.
 * 
 * @author padriaan
 * @version 1
 */
public final class TXTs
{

  /**
   * Prevents instantiation.
   */
  private TXTs()
  {
  }

  /**
   * Reads a text file.
   * 
   * @param the_path Path to the text file.
   * @return A list of lines extracted from the text file.
   * @throws IOException For reading or writing errors.
   */
  public static List<String> read(final String the_path) throws IOException
  {
    final List<String> data = new ArrayList<String>();
    final FileReader file_reader = new FileReader(the_path);
    final BufferedReader buffered_reader = new BufferedReader(file_reader);
    String line = buffered_reader.readLine();
    while (line != null)
    {
      data.add(line);
      line = buffered_reader.readLine();
    }
    buffered_reader.close();
    return data;
  }

  public static void overwrite(final String the_path, final List<String> the_data)
      throws IOException
  {
    write(the_path, the_data, false);
  }

  public static void add(final String the_path, final String the_line) throws IOException
  {
    final List<String> data = new ArrayList<String>();
    data.add(the_line);
    write(the_path, data, true);
  }

  private static void write(final String the_path, final List<String> the_data,
                            final boolean the_append) throws IOException
  {
    final FileWriter file_writer = new FileWriter(the_path, the_append);
    final BufferedWriter buffered_writer = new BufferedWriter(file_writer);
    final StringBuilder string_builder = new StringBuilder();
    for (String i : the_data)
    {
      string_builder.append(i);
      string_builder.append("\n");
    }
    buffered_writer.write(string_builder.toString());
    buffered_writer.close();
  }
}
