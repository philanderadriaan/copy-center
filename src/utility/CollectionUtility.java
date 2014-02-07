package utility;



import java.util.List;

import object.IntegerAggregator;

public class CollectionUtility
{

  private CollectionUtility()
  {

  }

  public static String getMode(List<String> list)
  {
    IntegerAggregator aggie = new IntegerAggregator();
    for (String i : list)
    {
      aggie.increment(i);
    }
    String mode = aggie.getMode();
    return mode;  
  }
}
