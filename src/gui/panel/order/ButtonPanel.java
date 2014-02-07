
package gui.panel.order;

import gui.frame.NKFrame;

import java.awt.BorderLayout;

import javax.swing.JPanel;

/**
 * Panel to contain buttons on the bottom left.
 * 
 * @author Phil Adriaan
 * @version 1
 */
@SuppressWarnings("serial")
public class ButtonPanel extends JPanel
{

  /**
   * Creates the panel by putting the extra buttons on the left and the numpad
   * on the right.
   * 
   * @param the_frame Container frame.
   */
  public ButtonPanel(final NKFrame the_frame)
  {
    super(new BorderLayout());
    add(new ExtraButtonPanel(the_frame), BorderLayout.WEST);
    add(new Numpad(the_frame));
  }

}
