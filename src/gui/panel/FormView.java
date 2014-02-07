
package gui.panel;

import gui.frame.NKFrame;
import gui.table.NKTable;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import action.EditTableAction;

/**
 * Panel structured as forms.
 * 
 * @author Phil Adriaan
 * @version 1
 */
@SuppressWarnings("serial")
public class FormView extends JPanel
{
  /**
   * Scroll pane containing the tables.
   */
  private JScrollPane my_scroll_pane;

  /**
   * Creates the panel.
   * 
   * @param the_frame Container frame.
   * @param the_header Header of the panel.
   * @param the_table Table to be shown.
   * @param the_file_name File name for the edit button.
   */
  public FormView(final NKFrame the_frame, final JComponent the_header,
                  final NKTable the_table, final String the_file_name)
  {
    super(new BorderLayout());
    add(the_header, BorderLayout.NORTH);
    my_scroll_pane = new JScrollPane(the_table);
    add(my_scroll_pane);
    final EditTableAction edit_action = new EditTableAction(the_frame, the_file_name);
    final JButton edit_button = new JButton(edit_action);
    add(edit_button, BorderLayout.SOUTH);
  }

  /**
   * Gets the current instance of scroll pane.
   * 
   * @return Current instance of scroll pane.
   */
  public JScrollPane getScrollPane()
  {
    return my_scroll_pane;
  }
}
