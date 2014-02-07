
package gui.panel.order;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Labels for the form on the right.
 * 
 * @author Phil Adriaan
 * @version 1
 */
@SuppressWarnings("serial")
public class RightLabels extends JPanel
{

  /**
   * Number of rows for the labels.
   */
  private static final int NUMBER_OF_ROWS = 8;

  /**
   * Add labels of bill date, unit price, quantity, percent discount and job cost.
   */
  public RightLabels()
  {
    super(new GridLayout(NUMBER_OF_ROWS, 1));
    add(new JLabel(" Our Bill Date Is:"));
    add(new JLabel(" Product Unit Price:"));
    add(new JLabel(" Quantity:"));
    add(new JLabel(" Percent Discount:"));
    add(new JPanel());
    add(new JLabel(" Job Cost: "));
  }

}
