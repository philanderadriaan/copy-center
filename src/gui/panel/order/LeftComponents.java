
package gui.panel.order;

import gui.frame.NKFrame;

import java.awt.GridLayout;

import javax.swing.JPanel;

/**
 * Contains the components to the lefts side of the header.
 * 
 * @author Phil Adriaan
 * @version 1
 */
@SuppressWarnings("serial")
public class LeftComponents extends JPanel
{

  /**
   * Creates the component by adding the left fields on the top and the bottom
   * field on the bottom.
   * 
   * @param the_frame Container frame.
   */
  public LeftComponents(final NKFrame the_frame)
  {
    super(new GridLayout(2, 1));
    add(new LeftFields(the_frame));
    add(new BottomComponents(the_frame));
  }

}
