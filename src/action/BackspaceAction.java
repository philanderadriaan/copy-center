
package action;

import enums.FieldEnum;
import gui.frame.NKFrame;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JTextField;

/**
 * This action executes removes the last character in the quantity field.
 * 
 * @author Phil Adriaan
 * @version 1
 */
@SuppressWarnings("serial")
public class BackspaceAction extends AbstractAction
{

  /**
   * Container frame.
   */
  private NKFrame my_frame;

  /**
   * @param the_frame Container frame.
   * 
   */
  public BackspaceAction(final NKFrame the_frame)
  {
    super("Backspace");
    my_frame = the_frame;
  }

  @Override
  public void actionPerformed(final ActionEvent the_event)
  {
    final JTextField field = (JTextField) my_frame.getFields().get(FieldEnum.QUANTITY);
    final String text = field.getText();
    final int length = text.length();
    if (length > 0)
    {
      field.setText(text.substring(0, length - 1));
    }
  }

}
