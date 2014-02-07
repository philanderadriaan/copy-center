
package action;

import enums.FieldEnum;
import gui.frame.NKFrame;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JTextField;

/**
 * Action to input numbers on the numpad into the quantity fields.
 * 
 * @author Phil Adriaan
 * @version 1
 */
@SuppressWarnings("serial")
public class NumberAction extends AbstractAction
{

  /**
   * Container frame.
   */
  private NKFrame my_frame;

  /**
   * Creates the action.
   * 
   * @param the_frame Container frame.
   * @param the_number Number to be input.
   */
  public NumberAction(final NKFrame the_frame, final int the_number)
  {
    super(Integer.toString(the_number));
    my_frame = the_frame;
  }

  @Override
  public void actionPerformed(final ActionEvent the_event)
  {
    final JTextField field = (JTextField) my_frame.getFields().get(FieldEnum.QUANTITY);
    field.setText(field.getText() + ((JButton) the_event.getSource()).getText());
  }
}
