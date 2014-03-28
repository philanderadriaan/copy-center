
package object;

import io.CSV;
import io.TXT;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class contains mechanism to learn about user's location according to their histories.
 * 
 * @author padriaan
 * @version 1
 */
public class LocationLearner
{
  /**
   * Path of location history.
   */
  private static final String HISTORY_PATH = "Skynet\\Skynet.csv";
  /**
   * Path to the file containing the exclusions.
   */
  private static final String EXCLUSION_PATH = "Skynet\\Exclusion.txt";

  /**
   * 
   */
  private List<String> my_exclusion_list = TXT.read(EXCLUSION_PATH);

  /**
   * Map containing history for all selected users.
   */
  private Map<String, LocationHistory> my_histories = new HashMap<String, LocationHistory>();

  
  
  /**
   * Construct the learner object.
   * 
   * @throws IOException If history file not found.
   */
  public LocationLearner() throws IOException
  {

    final List<List<String>> histories = CSV.read(HISTORY_PATH);

    for (List<String> i : histories)
    {
      final String user = i.remove(0);
      final LocationHistory history = new LocationHistory(i, my_exclusion_list);
      my_histories.put(user, history);
    }
  }

  /**
   * Adds a location history to the user.
   * 
   * @param the_name Name of the user.
   * @param the_location Location to be added.
   */
  public void add(final String the_name, final String the_location)
  {
    LocationHistory history;
    if (!my_histories.containsKey(the_name))
    {
      history = new LocationHistory(my_exclusion_list);
      my_histories.put(the_name, history);
    }
    else
    {
      history = my_histories.get(the_name);
    }
    history.add(the_location);
  }

  /**
   * Gets the majority location for a user.
   * 
   * @param the_name User in question.
   * @return Location which the user is most likely be.
   */
  public String getMode(final String the_name)
  {
    return my_histories.get(the_name).getMode();
  }

  /**
   * Saves the history into a CSV file.
   * 
   * @throws IOException For read/write errors.
   */
  public void save() throws IOException
  {
    final List<List<String>> histories = new ArrayList<List<String>>();
    for (String i : my_histories.keySet())
    {
      final LocationHistory history = my_histories.get(i);
      final List<String> row = history.getList();
      row.add(0, i);
      histories.add(row);
    }
    CSV.overwrite(HISTORY_PATH, histories);
  }

  /**
   * Whether a user has history or not.
   * 
   * @param the_name Name of user in question.
   * @return True if user has history, false if otherwise.
   */
  public boolean hasHistory(final String the_name)
  {
    final boolean has_history = my_histories.containsKey(the_name);
    return has_history;
  }
  
  /**
   * Clears all history.
   */
  public void clear()
  {
    my_histories = new HashMap<String, LocationHistory>();
  }
}
