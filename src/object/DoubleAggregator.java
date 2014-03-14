/**
 * 
 */

package object;

import java.util.HashMap;
import java.util.Map;

/**
 * @author padriaan
 * @version 1
 */
public class DoubleAggregator
{

  /**
   * Map to store the aggregation.
   */
  private Map<String, Double> my_map = new HashMap<String, Double>();
  /**
   * Total for all values.
   */
  private double my_total;

  /**
   * 
   */
  public DoubleAggregator()
  {
    // TODO Auto-generated constructor stub
  }

  /**
   * Gets the total value.
   * 
   * @return Total value.
   */
  public double getTotal()
  {
    return my_total;
  }

  /**
   * Adds a certain value to the integer on the key.
   * 
   * @param the_key Key in question.
   * @param the_value Value to be added.
   */
  public void add(final String the_key, final double the_value)
  {
    if (my_map.containsKey(the_key))
    {
      final double current_value = my_map.get(the_key);
      final double added_value = current_value + the_value;
      my_map.put(the_key, added_value);
    }
    else
    {
      my_map.put(the_key, the_value);
    }
    my_total += the_value;
  }

  /**
   * Gets the storage.
   * 
   * @return The whole map containing the aggregation.
   */
  public Map<String, Double> getMap()
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
    double highest_value = 0;
    for (String i : my_map.keySet())
    {
      final double current_value = my_map.get(i);
      if (current_value >= highest_value)
      {
        highest_key = i;
        highest_value = current_value;
      }
    }
    return highest_key;
  }

}
