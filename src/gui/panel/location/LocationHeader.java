
package gui.panel.location;

import gui.frame.NKFrame;

import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 * Header for the location screen.
 * 
 * @author Phil Adriaan
 * @version 1
 */
@SuppressWarnings("serial")
public class LocationHeader extends JPanel
{

  /**
   * Creates the header.
   * 
   * @param the_frame Container frame.
   */
  public LocationHeader(final NKFrame the_frame)
  {
    super(new BorderLayout());
    add(new JLabel("Locations: Edit or Add", SwingConstants.CENTER), BorderLayout.NORTH);
    add(new JLabel("Caution: do not delete during school year", SwingConstants.CENTER));
  }
}
