

package gui.panel.order;

import gui.frame.NKFrame;

import java.awt.BorderLayout;

import javax.swing.JPanel;

/**
 * Form on the right side.
 * 
 * @author Phil Adriaan
 * @version 1
 */
@SuppressWarnings("serial")
public class RightForm extends JPanel
{

  /**
   * Creates the form by adding labels on the left and fields on the right.
   * 
   * @param the_frame Container frame.
   */
  public RightForm(final NKFrame the_frame)
  {
    super(new BorderLayout());
    add(new RightLabels(), BorderLayout.WEST);
    add(new RightFields(the_frame));
  }

}
