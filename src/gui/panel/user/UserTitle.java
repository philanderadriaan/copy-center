
package gui.panel.user;

import gui.frame.NKFrame;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 * Title of the user form.
 * 
 * @author Phil Adriaan
 * @version 1
 */
@SuppressWarnings("serial")
public class UserTitle extends JPanel
{

  /**
   * @param the_frame Container frame.
   */
  public UserTitle(final NKFrame the_frame)
  {
    super(new GridLayout(2, 1));
    add(new JLabel("Users: Edit, Add, Delete", SwingConstants.CENTER));
    add(new LocationCombo(the_frame));
  }
}
