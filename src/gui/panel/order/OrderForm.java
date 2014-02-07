
package gui.panel.order;

import gui.frame.NKFrame;

import java.awt.GridLayout;

import javax.swing.JPanel;

/**
 * Contains all the components in the order form.
 * 
 * @author Phil Adriaan
 * @version 1
 */
@SuppressWarnings("serial")
public class OrderForm extends JPanel
{

  /**
   * Creates a form by combining the left form and the right form.
   * 
   * @param the_frame Container frame.
   */
  public OrderForm(final NKFrame the_frame)
  {
    super(new GridLayout(1, 2));
    add(new LeftForm(the_frame));
    add(new RightForm(the_frame));
  }

}
