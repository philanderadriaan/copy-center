
package action;

import gui.frame.NKFrame;

import java.awt.event.ActionEvent;
import java.io.IOException;

import javax.swing.AbstractAction;
import javax.swing.JOptionPane;

/**
 * Action to refresh the entire program, used to update the tables or other
 * information to see what the others have entered after the other
 * 
 * @author Phil Adriaan
 * @version 1
 */
@SuppressWarnings("serial")
public class RefreshAction extends AbstractAction
{
  /**
   * Container frame.
   */
  private NKFrame my_frame;

  /**
   * Creates the action.
   * 
   * @param the_frame Container frame to be triggered with refresh.
   */
  public RefreshAction(final NKFrame the_frame)
  {
    super("Refresh");
    my_frame = the_frame;
  }

  @Override
  public void actionPerformed(final ActionEvent the_event)
  {
    try
    {
      my_frame.getDataManager().refresh();
    }
    catch (IOException e)
    {
      JOptionPane.showMessageDialog(null, e.getMessage(), null, JOptionPane.ERROR_MESSAGE);
    }
    try
    {
      my_frame.setPanel(my_frame.getPanel(), null);
    }
    catch (IOException e)
    {
      JOptionPane.showMessageDialog(null, e.getMessage(), null, JOptionPane.ERROR_MESSAGE);
    }

  }

}
