
package gui.panel.order;

import gui.frame.NKFrame;

import java.awt.GridLayout;

import javax.swing.JPanel;

/**
 * Components for the bottom of the header.
 * 
 * @author Phil Adriaan
 * @version 1
 */
@SuppressWarnings("serial")
public class BottomComponents extends JPanel
{

  /**
   * Creates the panel.
   * 
   * @param the_frame Container frame.
   */
  public BottomComponents(final NKFrame the_frame)
  {
    super(new GridLayout(1, 2));
    add(new ButtonPanel(the_frame));
    add(new BottomForm(the_frame));
  }

}
