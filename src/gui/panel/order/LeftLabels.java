
package gui.panel.order;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Labels of the left form.
 * 
 * @author Phil Adriaan
 * @version 1
 */
@SuppressWarnings("serial")
public class LeftLabels extends JPanel
{

  /**
   * Number of rows for the labels.
   */
  private static final int NUMBER_OF_ROWS = 8;

  /**
   * Creates the panel by adding the labels for job date, user selection,
   * product selection and quantity.
   */
  public LeftLabels()
  {
    super(new GridLayout(NUMBER_OF_ROWS, 1));
    add(new JLabel(" Our Job Date Is:"));
    add(new JLabel(" Pick User:"));
    add(new JLabel(" Pick Location:"));
    add(new JLabel(" Pick Product:"));
    add(new JLabel(" Enter Quantity:"));
    add(new JPanel());
    add(new JPanel());
    add(new JPanel());
  }

}
