
package gui.panel.order;

import java.awt.GridLayout;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 * Panel to display title.
 * 
 * @author Phil Adriaan
 * @version 1
 */
@SuppressWarnings("serial")
public class OrderTitle extends JPanel
{

  /**
   * Creates the title by adding a formal title and today's date.
   */
  public OrderTitle()
  {
    super(new GridLayout(2, 1));
    final String title = "NKSD Copy Center Order Form";
    final JLabel title_label = new JLabel(title, SwingConstants.CENTER);
    add(title_label);
    final Calendar today = Calendar.getInstance();
    final Date todays_date = today.getTime();
    final String date_format = "M/d/y";
    final Format date_formatter = new SimpleDateFormat(date_format);
    String date_title = date_formatter.format(todays_date);
    date_title = "Today is: " + date_title;
    final JLabel date_label = new JLabel(date_title, SwingConstants.CENTER);
    add(date_label);
  }
}
