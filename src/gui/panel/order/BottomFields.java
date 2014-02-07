
package gui.panel.order;

import enums.FieldEnum;
import gui.frame.NKFrame;

import java.awt.GridLayout;

import javax.swing.JPanel;

/**
 * Panel to contain fields for the bottom of the panel.
 * 
 * @author Phil Adriaan
 * @version 1
 */
@SuppressWarnings("serial")
public class BottomFields extends JPanel
{

  /**
   * Rows of components for the panel.
   */
  private static final int NUMBER_OF_ROWS = 4;

  /**
   * Creates the panel by adding the quantity and discount field on the bottom,
   * then 2 empty panels for spacing.
   * 
   * @param the_frame Container frame.
   */
  public BottomFields(final NKFrame the_frame)
  {
    super(new GridLayout(NUMBER_OF_ROWS, 1));
    add(the_frame.getFields().get(FieldEnum.QUANTITY));
    add(the_frame.getFields().get(FieldEnum.DISCOUNT));
    add(new JPanel());
    add(new JPanel());
  }

}
