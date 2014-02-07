
package gui.table;

import java.util.Arrays;
import java.util.List;

import javax.swing.JTable;

import utility.TableUtility;

/**
 * Template for all tables in the program.
 * 
 * @author Phil Adriaan
 * @version 1
 */
@SuppressWarnings("serial")
public class NKTable extends JTable
{

  /**
   * Creates the Table.
   * 
   * @param the_data Data for the Table.
   */
  public NKTable(final List<List<String>> the_data, List<String> the_header)
  {
    super(getData(the_data), the_header.toArray());
    TableUtility.sort(this, 0);
  }

  /**
   * Gets the data as a 2 dimensional array.
   * 
   * @param the_data A 2 dimensional list of data.
   * @return A 2 dimensional array of data.
   */
  private static Object[][] getData(final List<List<String>> the_data)
  {
    int width = 10;
    if (the_data.size() > 0)
    {
      width = the_data.get(0).size();
    }
    final Object[][] array = new Object[the_data.size()][width];
    for (int i = 0; i < the_data.size(); i++)
    {
      array[i] = the_data.get(i).toArray();
    }
    return array;
  }

  @Override
  public boolean isCellEditable(final int the_row, final int the_column)
  {
    return false;
  }

}
