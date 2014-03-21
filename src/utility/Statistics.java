
package utility;

import java.util.List;

import object.IntegerAggregator;

/**
 * Utility to manipulate collections of objects.
 * 
 * @author padriaan
 * @version 1.
 */
public final class Statistics
{

  /**
   * Prevents instantiation.
   */
  private Statistics()
  {

  }

  /**
   * Gets the mode object in the list.
   * 
   * @param the_list List of object.
   * @return Mode object n the list.
   */
  public static String getMode(final List<String> the_list)
  {
    final IntegerAggregator aggie = new IntegerAggregator();
    for (String i : the_list)
    {
      aggie.increment(i);
    }
    final String mode = aggie.getMode();
    return mode;
  }
}
