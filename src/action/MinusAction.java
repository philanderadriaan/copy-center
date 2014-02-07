
package action;

import enums.FieldEnum;
import gui.frame.NKFrame;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JTextField;

/**
 * This action toggles the minus sign on the quantity fields.
 * 
 * @author Phil Adriaan
 * @version 1
 */
@SuppressWarnings("serial")
public class MinusAction extends AbstractAction
{

  /**
   * Container frame.
   */
  private NKFrame my_frame;

  /**
   * Creates the action.
   * 
   * @param the_frame Container frame to inform the quantity fields for the
   *          changes.
   */
  public MinusAction(final NKFrame the_frame)
  {
    super("Minus");
    my_frame = the_frame;
  }

  @Override
  public void actionPerformed(final ActionEvent the_event)
  {
    final JTextField field = (JTextField) my_frame.getFields().get(FieldEnum.QUANTITY);
    final String text = field.getText();
    if (text.contains("-"))
    {
      field.setText(field.getText().substring(1));
    }
    else
    {
      field.setText('-' + field.getText());
    }
  }

}
