
package gui.toolbar;

import javax.swing.JToggleButton;
import javax.swing.JToolBar;

/**
 * Toolbar containing the filter button.
 * 
 * @author Phil Adriaan
 * @version 1
 */
@SuppressWarnings("serial")
public class NKToolBar extends JToolBar
{
  /**
   * Creates the toolbar.
   * 
   * @param the_filter_button Filter button for ordering.
   */
  public NKToolBar(final JToggleButton the_filter_button)
  {
    super();
    add(the_filter_button);
  }
}
