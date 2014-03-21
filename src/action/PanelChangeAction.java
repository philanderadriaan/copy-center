
package action;

import enums.PanelEnum;
import gui.frame.NKFrame;

import java.awt.event.ActionEvent;
import java.io.IOException;

import javax.swing.AbstractAction;
import javax.swing.JOptionPane;

import utility.Strings;

/**
 * Triggers the change of screen to a desired panel.
 * 
 * @author Phil Adriaan
 * @version 1
 */
@SuppressWarnings("serial")
public class PanelChangeAction extends AbstractAction
{
  /**
   * Container frame.
   */
  private NKFrame my_frame;
  /**
   * Screen to switch to.
   */
  private PanelEnum my_panel;

  /**
   * Creates the action.
   * 
   * @param the_frame Container frame.
   * @param the_panel Screen to switch to.
   */
  public PanelChangeAction(final NKFrame the_frame, final PanelEnum the_panel)
  {
    super(Strings.enumToString(the_panel));
    my_frame = the_frame;
    my_panel = the_panel;
  }

  @Override
  public void actionPerformed(final ActionEvent the_event)
  {
    try
    {
      my_frame.setPanel(my_panel, null);
    }
    catch (final IOException e)
    {
      JOptionPane.showMessageDialog(null, e.getMessage(), null, JOptionPane.ERROR_MESSAGE);
    }
  }
}
