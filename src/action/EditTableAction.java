
package action;

import gui.frame.NKFrame;

import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;

import javax.swing.AbstractAction;
import javax.swing.JOptionPane;

/**
 * This action open an excel file of the table to allow adding, removing, or
 * editing. It will also close the program so that multiple programs would not
 * access the excel file at the same time.
 * 
 * @author Phil Adriaan
 * @version 1
 */
@SuppressWarnings("serial")
public class EditTableAction extends AbstractAction
{
  /**
   * Container frame.
   */
  private NKFrame my_frame;

  /**
   * File name of the table.
   */
  private String my_file_path;

  /**
   * Creates the action to edit tables.
   * 
   * @param the_frame Container frame for closing.
   * @param the_file_path File name for opening the file.
   */
  public EditTableAction(final NKFrame the_frame, final String the_file_path)
  {
    super("Edit, Add, Delete");
    my_frame = the_frame;
    my_file_path = the_file_path;
  }

  @Override
  public void actionPerformed(final ActionEvent the_event)
  {
    my_frame.dispose();
    try
    {
      Desktop.getDesktop().open(new File(my_file_path));
    }
    catch (final IOException e)
    {
      JOptionPane.showMessageDialog(null, e.getMessage(), null, JOptionPane.ERROR_MESSAGE);
    }
  }
}
