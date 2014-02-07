
package gui.panel.order;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Labels for the form at the bottom.
 * 
 * @author Phil Adriaan
 * @version 1
 */
@SuppressWarnings("serial")
public class BottomLabels extends JPanel
{
  /**
   * Number of rows for the labels.
   */
  private static final int NUMBER_OF_ROWS = 4;

  /**
   * Creates the labels by adding the quantity and discount labels, then 2 empty
   * panels for spacing.
   */
  public BottomLabels()
  {
    super(new GridLayout(NUMBER_OF_ROWS, 1));
    add(new JLabel(" Qty Keyboard Entry:"));
    add(new JLabel(" Percent Discount:"));
    add(new JPanel());
    add(new JPanel());
  }
}
