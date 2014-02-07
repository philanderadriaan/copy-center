
package gui.menu;

import enums.PanelEnum;
import gui.frame.NKFrame;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import action.PanelChangeAction;

/**
 * Menu containing all reports which allow the users to generate excel reports
 * and printable formats.
 * 
 * @author Phil Adriaan
 * @version 1
 */
@SuppressWarnings("serial")
public class ReportsMenu extends JMenu
{

  /**
   * Creates the menu.
   * 
   * @param the_frame Container frame.
   */
  public ReportsMenu(final NKFrame the_frame)
  {
    super("Reports");
    add(new JMenuItem(new PanelChangeAction(the_frame, PanelEnum.COST_PER_LOCATION)));
    add(new JMenuItem(new PanelChangeAction(the_frame, PanelEnum.COPIES_PER_LOCATION)));
    add(new JMenuItem(new PanelChangeAction(the_frame, PanelEnum.PRODUCT_PER_DESCRIPTION)));
    add(new JMenuItem(new PanelChangeAction(the_frame, PanelEnum.PRODUCT_PER_LOCATION)));
  }
}
