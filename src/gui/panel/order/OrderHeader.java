
package gui.panel.order;

import gui.frame.NKFrame;

import java.awt.BorderLayout;

import javax.swing.JPanel;

/**
 * The header of the order screen. Contains everything in it.
 * 
 * @author Phil Adriaan
 * @version 1
 */
@SuppressWarnings("serial")
public class OrderHeader extends JPanel
{

  /**
   * Creates the panel.
   * 
   * @param the_frame Instance of the frame.
   */
  public OrderHeader(final NKFrame the_frame)
  {
    super(new BorderLayout());
    add(new OrderTitle(), BorderLayout.NORTH);
    add(new OrderForm(the_frame));
  }
}
