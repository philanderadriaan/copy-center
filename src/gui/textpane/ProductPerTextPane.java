
package gui.textpane;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.JTextPane;

import utility.Dates;

/**
 * Text Pane for product per <anything>.
 * 
 * @author Phil Adriaan
 * @version 1
 */
@SuppressWarnings("serial")
public class ProductPerTextPane extends JTextPane
{

  /**
   * Creates the text pane.
   * 
   * @param the_title Title of the document.
   * @param the_data 
   */
  public ProductPerTextPane(final String the_title, final List<List<String>> the_data)
  {
    final Calendar calendar = Calendar.getInstance();
    final Date today = calendar.getTime();
    final Format date_formatter = new SimpleDateFormat("MMMM y");
    final String this_month = Dates.getPreviousMonthFormatted();
    
    setContentType("text/html");
    final StringBuilder report_builder = new StringBuilder();
    
    report_builder.append("<html>");
    report_builder.append("<body>");
    report_builder.append("<font face = arial>");
    report_builder.append("<b>");
    report_builder.append(this_month);
    report_builder.append("<center>");
    report_builder.append("NORTH KITSAP SCHOOL DISTRICT");
    report_builder.append("<br>");
    report_builder.append("COPY CENTER");
    report_builder.append("<br>");
    report_builder.append(the_title);
    report_builder.append("</b>");
    report_builder.append("<Table style = 'width : 100%' font face = arial>");
    
    for (int i = 0; i < the_data.size(); i++)
    {
      final boolean category = the_data.get(i).size() == 1;
      report_builder.append("<tr>");
      
      for (int j = 0; j < the_data.get(i).size(); j++)
      {
        report_builder.append("<td>");
        
        if (i == 0 || category)
        {
          report_builder.append("<b>");
        }
        
        if (j > 0)
        {
          report_builder.append("<p align = right>");
        }
        
        report_builder.append(the_data.get(i).get(j));
        report_builder.append("</td>");
      }
      
      report_builder.append("</tr>");
    }
    
    setText(report_builder.toString());
    setCaretPosition(0);
    setEditable(false);
  }
}
