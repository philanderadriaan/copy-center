
package gui.panel.order;

import enums.FieldEnum;
import gui.frame.NKFrame;

import java.awt.GridLayout;

import javax.swing.JPanel;

/**
 * Contains all the fields to the top left of the header.
 * 
 * @author Phil Adriaan
 * @version 1
 */
@SuppressWarnings("serial")
public class LeftFields extends JPanel
{

  /**
   * Number of rows of fields.
   */
  private static final int NUMBER_OF_ROWS = 4;

  /**
   * Creates the component by adding the job date, user, location and product
   * fields to the panel.
   * 
   * @param the_frame Container frame.
   */
  public LeftFields(final NKFrame the_frame)
  {
    super(new GridLayout(NUMBER_OF_ROWS, 1));
    add(the_frame.getFields().get(FieldEnum.JOB_DATE));
    add(the_frame.getFields().get(FieldEnum.USER));
    add(the_frame.getFields().get(FieldEnum.LOCATION));
    add(the_frame.getFields().get(FieldEnum.PRODUCT));
  }
}
