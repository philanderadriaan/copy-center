
package gui.panel.order;

import enums.FieldEnum;
import gui.frame.NKFrame;

import java.awt.GridLayout;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;

import action.AddOrderAction;

/**
 * Holds the fields on the right side.
 * 
 * @author Phil Adriaan
 * @version 1
 */
@SuppressWarnings("serial")
public class RightFields extends JPanel
{

  /**
   * Number of rows of fields.
   */
  private static final int NUMBER_OF_ROWS = 8;

  /**
   * create fields for bill date, price output, quantity output, discount output
   * and cost output.
   * 
   * @param the_frame Container frame.
   */
  public RightFields(final NKFrame the_frame)
  {
    super(new GridLayout(NUMBER_OF_ROWS, 1));
    final Map<FieldEnum, JComponent> fields = the_frame.getFields();
    add(fields.get(FieldEnum.BILL_DATE));
    add(fields.get(FieldEnum.PRICE_OUT));
    add(fields.get(FieldEnum.QUANTITY_OUT));
    add(fields.get(FieldEnum.DISCOUNT_OUT));
    add(new JPanel());
    add(fields.get(FieldEnum.COST_OUT));
    add(new JButton(new AddOrderAction(the_frame, "OK!")));
  }
}
