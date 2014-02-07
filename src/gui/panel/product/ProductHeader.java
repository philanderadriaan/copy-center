
package gui.panel.product;

import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 * Header for the product.
 * 
 * @author Phil Adriaan
 * @version 1
 */
@SuppressWarnings("serial")
public class ProductHeader extends JPanel
{

  /**
   * Creates the product header.
   */
  public ProductHeader()
  {
    super(new BorderLayout());

    final String title = "Products: Edit, Add, Delete";
    final JLabel title_label = new JLabel(title, SwingConstants.CENTER);

    add(title_label);
  }
}
