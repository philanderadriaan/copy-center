
package object;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import utility.CSVUtility;
import utility.TXTUtility;

/**
 * 
 * @author padriaan
 * 
 */
public class Skynet
{
  /**
   * Path of location history.
   */
  private static final String HISTORY_PATH = "Skynet\\Skynet.csv";
  private static final String EXCLUSION_PATH = "Skynet\\Exclusion.txt";

  List<String> exclusion_list = TXTUtility.read(EXCLUSION_PATH);

  /**
   * Map containing history for all selected users.
   */
  Map<String, LocationHistory> my_histories = new HashMap<String, LocationHistory>();

  
  
  /**
   * Construct the learner object.
   * 
   * @throws IOException If history file not found.
   */
  public Skynet() throws IOException
  {

    List<List<String>> histories = CSVUtility.read(HISTORY_PATH);

    for (List<String> i : histories)
    {
      String user = i.get(0);
      i.remove(0);
      LocationHistory history = new LocationHistory(i, exclusion_list);
      my_histories.put(user, history);
    }
  }

  public void add(String name, String location)
  {
    LocationHistory history;
    if (!my_histories.containsKey(name))
    {
      history = new LocationHistory(exclusion_list);
      my_histories.put(name, history);
    }
    else
    {
      history = my_histories.get(name);
    }
    history.add(location);
  }

  public String getMode(String name)
  {
    return my_histories.get(name).getMode();
  }

  public void save() throws IOException
  {
    List<List<String>> histories = new ArrayList<List<String>>();
    for (String i : my_histories.keySet())
    {
      LocationHistory history = my_histories.get(i);
      List<String> row = history.getList();
      row.add(0, i);
      histories.add(row);
    }
    List<List<String>> file_histories = CSVUtility.read(HISTORY_PATH);
    CSVUtility.write(HISTORY_PATH, histories);
  }

  public boolean hasHistory(String the_name)
  {
    boolean has_history = my_histories.containsKey(the_name);
    return has_history;
  }
  
  public void clear()
  {
    my_histories = new HashMap<String, LocationHistory>();
  }
}
