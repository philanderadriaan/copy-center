package object;

import java.util.HashMap;
import java.util.Map;

/**
 * Stores integers with keys and aggregate them if they have the same keys.
 * 
 * @author Phil Adriaan
 * @version 1
 */
public class IntegerAggregator
{
  /**
   * Map to store the aggregation.
   */
  private Map<String, Integer> my_map = new HashMap<String, Integer>();

  /**
   * Creates the object.
   */
  public IntegerAggregator()
  {

  }

  /**
   * Increments the integer that is the value of the key.
   * 
   * @param the_key Key in question.
   */
  public void increment(final String the_key)
  {
    add(the_key, 1);
  }

  /**
   * Adds a certain value to the integer on the key.
   * 
   * @param the_key Key in question.
   * @param the_value Value to be added.
   */
  public void add(final String the_key, final int the_value)
  {
    if (my_map.containsKey(the_key))
    {
      final int current_value = my_map.get(the_key);
      final int added_value = current_value + the_value;
      my_map.put(the_key, added_value);
    }
    else
    {
      my_map.put(the_key, the_value);
    }
  }

  /**
   * Gets the storage.
   * 
   * @return The whole map containing the aggregation.
   */
  public Map<String, Integer> getMap()
  {
    return my_map;
  }

  /**
   * Gets the key with the highest integer.
   * 
   * @return Key with highest integer.
   */
  public String getMode()
  {
    String highest_key = null;
    int highest_value = 0;
    for (String i : my_map.keySet())
    {
      final int current_value = my_map.get(i);
      if (current_value >= highest_value)
      {
        highest_key = i;
        highest_value = current_value;
      }
    }
    return highest_key;
  }
}
