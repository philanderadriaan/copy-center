
package object;

import java.util.ArrayList;
import java.util.List;

import utility.CollectionUtility;

public class LocationHistory
{
  private static final int HISTORY_SIZE = 100;
  private List<String> my_history_list;
  private List<String> my_exclusion_list;

  public LocationHistory(List<String> the_history, List<String> the_exclusion)
  {
    my_history_list = the_history;
    my_exclusion_list = the_exclusion;
  }

  public LocationHistory(List<String> the_exclusion)
  {
    this(new ArrayList<String>(), the_exclusion);
  }

  public String getMode()
  {
    String mode = CollectionUtility.getMode(my_history_list);
    return mode;
  }

  public void add(String location)
  {
    boolean excluded = false;

    for (String i : my_exclusion_list)
    {
      String location_lower_case = location.toLowerCase();
      String exclusion_lower_case = i.toLowerCase();
      if (location_lower_case.contains(exclusion_lower_case))
      {
        excluded = true;
        break;
      }
    }
    if (!excluded)
    {
      my_history_list.add(location);
    }
    while (my_history_list.size() > HISTORY_SIZE)
    {
      my_history_list.remove(0);
    }
  }

  public List<String> getList()
  {
    return my_history_list;
  }
}