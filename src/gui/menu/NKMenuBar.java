
package gui.menu;

import gui.frame.NKFrame;

import javax.swing.JMenuBar;

/**
 * Menu bar to let the users select menus.
 * 
 * @author Phil Adriaan
 * @version 1
 */
@SuppressWarnings("serial")
public class NKMenuBar extends JMenuBar
{

  /**
   * Creates menu bar.
   * 
   * @param the_frame Container frame.
   */
  public NKMenuBar(final NKFrame the_frame)
  {
    add(new FileMenu(the_frame));
    add(new ScreensMenu(the_frame));
    add(new ReportsMenu(the_frame));
  }
}
