
package action;

import gui.frame.NKFrame;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

/**
 * Action that will quit the program.
 * 
 * @author Phil Adriaan
 * @version 1
 */
@SuppressWarnings("serial")
public class QuitAction extends AbstractAction
{
  /**
   * Container frame.
   */
  private NKFrame my_frame;

  /**
   * Creates the quit action.
   * 
   * @param the_frame Container frame.
   */
  public QuitAction(final NKFrame the_frame)
  {
    super("Quit");
    my_frame = the_frame;
  }

  @Override
  public void actionPerformed(final ActionEvent the_event)
  {
    my_frame.dispose();
  }

}
