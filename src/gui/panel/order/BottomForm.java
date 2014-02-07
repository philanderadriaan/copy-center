
package gui.panel.order;

import gui.frame.NKFrame;

import java.awt.BorderLayout;

import javax.swing.JPanel;

/**
 * Panel to contain the labels and fields for the form located at the bottom.
 * 
 * @author Phil Adriaan
 * @version 1
 */
@SuppressWarnings("serial")
public class BottomForm extends JPanel
{

  /**
   * Creates the panel by adding the list of labels on the left, then the fields
   * on the right.
   * 
   * @param the_frame Container frame.
   */
  public BottomForm(final NKFrame the_frame)
  {
    super(new BorderLayout());
    add(new BottomLabels(), BorderLayout.WEST);
    add(new BottomFields(the_frame));
  }
}
