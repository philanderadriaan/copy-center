
package gui.panel.order;

import gui.frame.NKFrame;

import java.awt.BorderLayout;

import javax.swing.JPanel;

/**
 * Panel for the form to the left side.
 * 
 * @author Phil Adriaan
 * @version 1
 */
@SuppressWarnings("serial")
public class LeftForm extends JPanel
{

  /**
   * Adds the left label to the left, and the other components to the right.
   * 
   * @param the_frame Container frame.
   */
  public LeftForm(final NKFrame the_frame)
  {
    super(new BorderLayout());
    add(new LeftLabels(), BorderLayout.WEST);
    add(new LeftComponents(the_frame));
  }

}
