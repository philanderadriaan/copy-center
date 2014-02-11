import java.io.IOException;
import java.util.List;

import object.HistoryLearner;
import utility.CSVUtility;

/**
 * @author padriaan
 * @version 1
 */
public class QuickLearn
{
  /**
   * Utility class should not have a public or default constructor.
   */
  private QuickLearn()
  {
  }

  /**
   * This program quickly build up the history of users by analyzing their past
   * orders.
   * 
   * @param the_args Command line arguments.
   * @throws IOException Throws exception when there is error reading or
   *           writing.
   */
  public static void main(final String[] the_args) throws IOException
  {
    final HistoryLearner skynet = new HistoryLearner();
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
