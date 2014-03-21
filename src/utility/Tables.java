

package utility;

import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultRowSorter;
import javax.swing.JTable;
import javax.swing.RowSorter;
import javax.swing.SortOrder;

/**
 * Utility class for table manipulation.
 * 
 * @author Phil Adriaan
 * @version 1
 */
public final class Tables
{

  /**
   * Prevents instantiation.
   */
  private Tables()
  {
  }

  /**
   * Sorts the Table.
   * 
   * @param the_table Table to be sorted.
   * @param the_index Index of which the sorting is based on.
   */
  public static void sort(final JTable the_table, final int the_index)
  {
    the_table.setAutoCreateRowSorter(true);
    final DefaultRowSorter sorter = (DefaultRowSorter) the_table.getRowSorter();
    final List<RowSorter.SortKey> list = new ArrayList<RowSorter.SortKey>();
    list.add(new RowSorter.SortKey(the_index, SortOrder.ASCENDING));
    sorter.setSortKeys(list);
    sorter.sort();
  }
}
