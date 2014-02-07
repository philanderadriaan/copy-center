
package gui.textpane;

import java.util.List;

import javax.swing.JTextPane;

/**
 * @author Phil Adriaan
 * @version 1
 */
@SuppressWarnings("serial")
public class CostPerLocationTextPane extends JTextPane

{

  /**
   * Creates the text pane.
   * 
   * @param the_title Title of the text pane.
   * @param the_data Data to be displayed.
   */
  public CostPerLocationTextPane(final String the_title, final List<List<String>> the_data)
  {
    super();
    setContentType("text/html");
    
    final StringBuilder report_builder = new StringBuilder();
    report_builder.append("<html>");
    report_builder.append("<body>");
    report_builder.append("<font face = arial>");
    report_builder.append("<center>");
    report_builder.append("<b>");
    report_builder.append("NORTH KITSAP SCHOOL DISTRICT");
    report_builder.append("<br>");
    report_builder.append("COPY CENTER");
    report_builder.append("<br>");
    report_builder.append(the_title);
    report_builder.append("<Table style = 'width : 100%'>");

    for (int i = 0; i < the_data.size(); i++)
    {
      report_builder.append("<tr>");

      for (int j = 0; j < the_data.get(i).size(); j++)
      {
        report_builder.append("<td>");

        if (i == 0 || i == the_data.size() - 1)
        {
          report_builder.append("<b>");
        }

        if (j > 1)
        {
          report_builder.append("<p align=\"right\">");
        }

        report_builder.append(the_data.get(i).get(j).toString());
        report_builder.append("</td>");
      }

      report_builder.append("</tr>");
    }

    setText(report_builder.toString());
    setCaretPosition(0);
    setEditable(false);
  }
}
