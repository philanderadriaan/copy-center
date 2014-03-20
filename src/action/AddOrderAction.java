
package action;

import enums.FieldEnum;
import gui.frame.NKFrame;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.swing.AbstractAction;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import object.DataManager;

import com.toedter.calendar.JDateChooser;

/**
 * An action for adding orders to the records. It will gather all the required
 * information from the fields, compile it into an ordered list according to the
 * columns of the order table, and append the information at the end of the
 * table. The information appended on the table can be accessed immediately by
 * other programs. This action will only be able to run if the order excel file
 * is not locked.
 * 
 * @author Phil Adriaan
 * @version 1
 */
@SuppressWarnings("serial")
public class AddOrderAction extends AbstractAction
{
  /**
   * Container frame.
   */
  private NKFrame my_frame;

  /**
   * Creates the action and sets a reference to the current frame in use for
   * later use.
   * 
   * @param the_frame Container frame.
   * @param the_name Name of the Table.
   */
  public AddOrderAction(final NKFrame the_frame, final String the_name)
  {
    super(the_name);
    my_frame = the_frame;
  }

  @Override
  public void actionPerformed(final ActionEvent the_event)
  {
    final Map<FieldEnum, JComponent> fields = my_frame.getFields();
    final JComponent job_date_component = fields.get(FieldEnum.JOB_DATE);
    final JComponent bill_date_component = fields.get(FieldEnum.JOB_DATE);
    final JDateChooser job_date_chooser = (JDateChooser) job_date_component;
    final JDateChooser bill_date_chooser = (JDateChooser) bill_date_component;
    final Date job_date = job_date_chooser.getDate();
    final Date bill_date = bill_date_chooser.getDate();
    final Format format = new SimpleDateFormat("M/d/y");
    final String job_date_string = format.format(job_date);
    final String bill_date_string = format.format(bill_date);
    getSelected(FieldEnum.USER, 0);
    final List<String> input = new ArrayList<String>();
    input.add(getSelected(FieldEnum.LOCATION, 0));
    input.add(getSelected(FieldEnum.USER, 0));
    input.add(getSelected(FieldEnum.USER, 1));
    input.add(getSelected(FieldEnum.PRODUCT, 0));
    input.add(job_date_string);
    input.add(bill_date_string);
    input.add(((JTextField) fields.get(FieldEnum.PRICE_OUT)).getText());
    input.add(((JTextField) fields.get(FieldEnum.QUANTITY_OUT)).getText());
    input.add(((JTextField) fields.get(FieldEnum.DISCOUNT_OUT)).getText());
    input.add(((JTextField) fields.get(FieldEnum.COST_OUT)).getText());
    ((JTextField) my_frame.getFields().get(FieldEnum.QUANTITY)).setText("");
    ((JTextField) my_frame.getFields().get(FieldEnum.DISCOUNT)).setText("");
    final DataManager data_manager = my_frame.getDataManager();
    try
    {
      data_manager.addOrder(input);
    }
    catch (final IOException e)
    {
      JOptionPane.showMessageDialog(null, e.getMessage(), null, JOptionPane.ERROR_MESSAGE);
    }
    my_frame.getLocationLearner().add(input.get(2) + " " + input.get(1), input.get(0));
    final List<List<String>> new_data = data_manager.getOrder(my_frame.isFilterSelected());
    my_frame.drawTable(new_data,
                       my_frame.getDataManager().getHeaderMap()
                           .get("Table\\" + my_frame.getDataManager().getOrderFileName()));
  }

  /**
   * Get the data from the selected field and the index of the comma separated
   * value.
   * 
   * @param the_field_enum Enum of the field type. Works only with fields that
   *          are instances of JComboBoxf
   * @param the_index Zero based index of the desired value in a selected item.
   * @return The desired value taken out of a selected item in a drop down menu.
   */
  private String getSelected(final FieldEnum the_field_enum, final int the_index)
  {
    final Map<FieldEnum, JComponent> field_map = my_frame.getFields();
    final JComponent field = field_map.get(the_field_enum);
    final JComboBox<String> combo = (JComboBox<String>) field;
    final Object selected_object = combo.getSelectedItem();
    final String selected_string = selected_object.toString();
    final String[] split = selected_string.split(", ");
    final String result = split[the_index];
    return result;
  }
}
