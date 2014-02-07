
package gui.panel.user;

import gui.combo.NKComboBox;
import gui.frame.NKFrame;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 * Panel containing the location drop down menu in the user panel.
 * 
 * @author Phil Adriaan
 * @version 1
 */
@SuppressWarnings("serial")
public class LocationCombo extends JPanel
{
  /**
   * Number of columns.
   */
  static final int NUMBER_OF_COLUMNS = 3;

  /**
   * Creates a panel with the location combo in the middle column.
   * 
   * @param the_frame Container frame.
   */
  public LocationCombo(final NKFrame the_frame)
  {
    super(new GridLayout(1, NUMBER_OF_COLUMNS));

    final int middle_column = NUMBER_OF_COLUMNS / 2;

    for (int i = 0; i < NUMBER_OF_COLUMNS; i++)
    {
      if (i == middle_column)
      {
        final String label_string = "Pick Location:";
        final JLabel label = new JLabel(label_string, SwingConstants.LEFT);

        final NKComboBox combo_box = the_frame.getLocationFilterBox();
        
        final JPanel panel = new JPanel(new BorderLayout());
        panel.add(label, BorderLayout.WEST);
        panel.add(combo_box);
        add(panel);
      }
      else
      {
        add(new JPanel());
      }
    }
  }
}
