
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
  private Map<String, Double> my_aggregator_map = new HashMap<String, Double>();
  /**
   * Total for all values.
   */
  private double my_total;

  /**
   * Prevents instantiation.
   */
  public DoubleAggregator()
  {
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
    if (my_aggregator_map.containsKey(the_key))
    {
      final double current_value = my_aggregator_map.get(the_key);
      final double added_value = current_value + the_value;
      my_aggregator_map.put(the_key, added_value);
    }
    else
    {
      my_aggregator_map.put(the_key, the_value);
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
    return my_aggregator_map;
  }
  
}
