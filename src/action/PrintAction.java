
package action;

import enums.PanelEnum;
import gui.frame.NKFrame;

import java.awt.event.ActionEvent;
import java.awt.print.PrinterException;

import javax.swing.AbstractAction;
import javax.swing.JOptionPane;
import javax.swing.JTextPane;

/**
 * Triggers printing.
 * 
 * @author Phil Adriaan
 * @version 1
 */
@SuppressWarnings("serial")
public class PrintAction extends AbstractAction
{
  /**
   * Container frame.
   */
  private NKFrame my_frame;

  /**
   * Creates the action.
   * 
   * @param the_frame Container frame.
   */
  public PrintAction(final NKFrame the_frame)
  {
    super("Print");
    my_frame = the_frame;
  }

  @Override
  public void actionPerformed(final ActionEvent the_event)
  {
    try
    {
      if (my_frame.getPanel() == PanelEnum.COPIES_PER_LOCATION ||
          my_frame.getPanel() == PanelEnum.COST_PER_LOCATION ||
          my_frame.getPanel() == PanelEnum.PRODUCT_PER_DESCRIPTION ||
          my_frame.getPanel() == PanelEnum.PRODUCT_PER_LOCATION)
      {
        ((JTextPane) my_frame.getTextPane()).print();
      }
    }
    catch (final PrinterException e)
    {
      JOptionPane.showMessageDialog(null, e.getMessage(), null, JOptionPane.ERROR_MESSAGE);
    }

  }

}
