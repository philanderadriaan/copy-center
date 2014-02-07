
package gui.panel.order;

import gui.frame.NKFrame;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

import action.BackspaceAction;
import action.MinusAction;

/**
 * Auxiliary buttons. Originally used to contain the minus and backspace
 * buttons.
 * 
 * @author Phil Adriaan
 * @version 1
 */
@SuppressWarnings("serial")
public class ExtraButtonPanel extends JPanel
{

  /**
   * Number of rows of components.
   */
  private static final int NUMBER_OF_ROWS = 4;

  /**
   * Creates the panel.
   * 
   * @param the_frame Container frame.
   */
  public ExtraButtonPanel(final NKFrame the_frame)
  {
    super(new GridLayout(NUMBER_OF_ROWS, 1));
    add(new JButton(new BackspaceAction(the_frame)));
    add(new JButton(new MinusAction(the_frame)));
  }

}
