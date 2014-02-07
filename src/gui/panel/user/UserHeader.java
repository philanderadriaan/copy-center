/**
 * Header for the user screen.
 */

package gui.panel.user;

import gui.frame.NKFrame;

import java.awt.BorderLayout;

import javax.swing.JPanel;

/**
 * @author Phil Adriaan
 * @version 1
 */
@SuppressWarnings("serial")
public class UserHeader extends JPanel
{

  /**
   * Starts the header.
   * 
   * @param the_frame Container frame.
   */
  public UserHeader(final NKFrame the_frame)
  {
    super(new BorderLayout());
    add(new UserTitle(the_frame));
  }
}
