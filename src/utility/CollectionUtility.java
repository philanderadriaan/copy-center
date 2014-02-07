package utility;



import java.util.List;

import object.IntegerAggregator;

public final class CollectionUtility
{

  private CollectionUtility()
  {

  }

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
