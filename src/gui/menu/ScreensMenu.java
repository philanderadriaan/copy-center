
package gui.menu;

import enums.PanelEnum;
import gui.frame.NKFrame;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import action.PanelChangeAction;

/**
 * Menu for selecting panels.
 * 
 * @author Phil Adriaan
 * @version 1
 */
@SuppressWarnings("serial")
public class ScreensMenu extends JMenu
{

  /**
   * Creates the menu.
   * 
   * @param the_frame Container frame.
   */
  public ScreensMenu(final NKFrame the_frame)
  {
    super("Screens");
    add(new JMenuItem(new PanelChangeAction(the_frame, PanelEnum.ORDER)));
    add(new JMenuItem(new PanelChangeAction(the_frame, PanelEnum.USER)));
    add(new JMenuItem(new PanelChangeAction(the_frame, PanelEnum.PRODUCT)));
    add(new JMenuItem(new PanelChangeAction(the_frame, PanelEnum.LOCATION)));
  }

}
