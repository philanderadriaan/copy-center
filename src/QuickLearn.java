import java.io.IOException;
import java.util.List;

import object.History;
import utility.CSVUtility;

/**
 * 
 */

/**
 * @author padriaan
 * 
 */
public class QuickLearn
{

  /**
   * @throws IOException
   * 
   */

  public static void main(final String[] args) throws IOException
  {
    final History skynet = new History();
    skynet.clear();
    final List<List<String>> order = CSVUtility.read("Table\\Order2013-2014.csv");
    for (List<String> i : order)
    {
      final String location = i.get(0);
      final String last_name = i.get(1);
      final String first_name = i.get(2);
      final String name = first_name + " " + last_name;
      skynet.add(name, location);
    }
    skynet.save();
  }
}
