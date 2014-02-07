
package gui.combo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.JComboBox;

/**
 * Drop down menu containing the data for input data, with columns separated by
 * comma, and headers removed. The data type for all the items are in string.
 * 
 * @author Phil Adriaan
 * @version 1
 */
@SuppressWarnings("serial")
public class NKComboBox extends JComboBox<String>
{
  /**
   * Creates the drop down menu.
   * 
   * @param the_data All the data needed to be included, with the sub-lists
   *          listed as individual items, and the data in the sub-lists
   *          separated by comma.
   * @param the_empty Whether the table will start out with empty item or not.
   */
  public NKComboBox(final List<List<String>> the_data, final boolean the_empty)
  {
    final List<String> list = consolidateData(the_data);

    if (the_empty)
    {
      addItem("");
    }

    for (String i : list)
    {
      addItem(i);
    }
  }

  /**
   * Consolidate data from a 2D list of data into a list of strings with comma
   * separated value.
   * 
   * @param the_data
   * @return
   */
  private List<String> consolidateData(final List<List<String>> the_data)
  {
    final List<String> list = new ArrayList<String>();

    for (List<String> i : the_data)
    {
      final StringBuilder builder = new StringBuilder();
      boolean first = true;

      for (String j : i)
      {
        if (first)
        {
          first = false;
        }
        else
        {
          builder.append(", ");
        }
        builder.append(j);
      }

      list.add(builder.toString());
    }

    Collections.sort(list);

    return list;
  }
}
