package object;
/**
 * 
 */

import java.util.HashMap;
import java.util.Map;

/**
 * @author Phil Adriaan
 * @version 1
 * 
 */
public class IntegerAggregator
{
  Map<String, Integer> my_map = new HashMap<String, Integer>();

  public IntegerAggregator()
  {

  }

  public void increment(String the_key)
  {
    add(the_key, 1);
  }

  public void add(String the_key, int the_value)
  {
    if (my_map.containsKey(the_key))
    {
      int current_value = my_map.get(the_key);
      int added_value = current_value + the_value;
      my_map.put(the_key, added_value);
    }
    else
    {
      my_map.put(the_key, the_value);
    }
  }

  public Map<String, Integer> getMap()
  {
    return my_map;
  }

  public String getMode()
  {
    String highest_key = null;
    int highest_value = 0;
    for (String i : my_map.keySet())
    {
      int current_value = my_map.get(i);
      if (current_value >= highest_value)
      {
        highest_key = i;
        highest_value = current_value;
      }
    }
    return highest_key;
  }
}
