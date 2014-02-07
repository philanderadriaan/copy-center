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
  Map<String, Integer> map = new HashMap<String, Integer>();

  public IntegerAggregator()
  {

  }

  public void increment(String key)
  {
    add(key, 1);
  }

  public void add(String key, int value)
  {
    if (map.containsKey(key))
    {
      int current_value = map.get(key);
      int added_value = current_value + value;
      map.put(key, added_value);
    }
    else
    {
      map.put(key, value);
    }
  }

  public Map<String, Integer> getMap()
  {
    return map;
  }

  public String getMode()
  {
    String highest_key = null;
    int highest_value = 0;
    for (String i : map.keySet())
    {
      int current_value = map.get(i);
      if (current_value >= highest_value)
      {
        highest_key = i;
        highest_value = current_value;
      }
    }
    return highest_key;
  }
}
