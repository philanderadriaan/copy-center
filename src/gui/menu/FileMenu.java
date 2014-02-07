
package gui.menu;

import gui.frame.NKFrame;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import action.PrintAction;
import action.RefreshAction;

/**
 * Contains all the menu items under the file menu.
 * 
 * @author Phil Adriaan
 * @version 1
 */
@SuppressWarnings("serial")
public class FileMenu extends JMenu
{

  /**
   * Creates file menu.
   * 
   * @param the_frame Container frame.
   */
  public FileMenu(final NKFrame the_frame)
  {
    super("File");
    add(new JMenuItem(new PrintAction(the_frame)));
    add(new JMenuItem(new RefreshAction(the_frame)));
  }
}
