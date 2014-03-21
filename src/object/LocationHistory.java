
package object;

import java.util.ArrayList;
import java.util.List;

import utility.Statistics;

/**
 * Location history for a single user.
 * 
 * @author padriaan
 * @version 1
 */
public class LocationHistory
{
  /**
   * Maximum history size.
   */
  private static final int HISTORY_LIMIT = 100;
  /**
   * List of history.
   */
  private List<String> my_history_list;
  /**
   * List of keywords for exclusions.
   */
  private List<String> my_exclusion_list;

  /**
   * Creates the object with a list of current histories.
   * 
   * @param the_history Current history.
   * @param the_exclusion Exclusions for default location.
   */
  public LocationHistory(final List<String> the_history, final List<String> the_exclusion)
  {
    my_history_list = the_history;
    my_exclusion_list = the_exclusion;
  }

  /**
   * Creates the object with no history.
   * 
   * @param the_exclusion Exclusions for default location.
   */
  public LocationHistory(final List<String> the_exclusion)
  {
    this(new ArrayList<String>(), the_exclusion);
  }

  /**
   * Gets the location with the most time appeared in the history.
   * 
   * @return Mode location.
   */
  public String getMode()
  {
    final String mode = Statistics.getMode(my_history_list);
    return mode;
  }

  /**
   * Adds a location to the history.
   * 
   * @param the_location The location in question.
   */
  public void add(final String the_location)
  {
    boolean excluded = false;

    for (String i : my_exclusion_list)
    {
      final String location_lower_case = the_location.toLowerCase();
      final String exclusion_lower_case = i.toLowerCase();
      if (location_lower_case.contains(exclusion_lower_case))
      {
        excluded = true;
        break;
      }
    }
    if (!excluded)
    {
      my_history_list.add(the_location);
    }
    while (my_history_list.size() > HISTORY_LIMIT)
    {
      my_history_list.remove(0);
    }
  }

  /**
   * Gets the whole list of history.
   * 
   * @return The list of all locations in a person's history.
   */
  public List<String> getList()
  {
    return my_history_list;
  }
}
