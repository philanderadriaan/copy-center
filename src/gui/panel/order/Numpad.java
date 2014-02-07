
package gui.panel.order;

import gui.frame.NKFrame;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

import action.NumberAction;

/**
 * GUI for the numpad in the order panel. Used for entering numbersto the
 * quantity field.
 * 
 * @author Phil Adriaan
 * @version 1
 */
@SuppressWarnings("serial")
public class Numpad extends JPanel
{

  /**
   * Number of rows for numpad.
   */
  private static final int NUMBER_OF_ROWS = 4;
  /**
   * Number of columns for numpad.
   */
  private static final int NUMBER_OF_COLUMNS = 3;

  /**
   * Creates the numpad.
   * 
   * @param the_frame Container frame.
   */
  public Numpad(final NKFrame the_frame)
  {
    super(new GridLayout(NUMBER_OF_ROWS, NUMBER_OF_COLUMNS));

    for (int i = 2; i >= 0; i--)
    {
      for (int j = 0; j < NUMBER_OF_COLUMNS; j++)
      {
        final int number = i * NUMBER_OF_COLUMNS + j + 1;
        final NumberAction number_action = new NumberAction(the_frame, number);
        final JButton number_button = new JButton(number_action);
        add(number_button);
      }
    }

    final NumberAction zero_action = new NumberAction(the_frame, 0);
    final JButton zero_button = new JButton(zero_action);
    add(zero_button);
  }

}
