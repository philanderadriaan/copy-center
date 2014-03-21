
package gui.frame;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.Format;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import object.DataManager;
import object.LocationLearner;
import utility.Strings;

import com.toedter.calendar.JDateChooser;

import enums.FieldEnum;
import enums.PanelEnum;
import gui.combo.NKComboBox;
import gui.menu.NKMenuBar;
import gui.panel.FormView;
import gui.panel.location.LocationHeader;
import gui.panel.order.OrderHeader;
import gui.panel.product.ProductHeader;
import gui.panel.user.UserHeader;
import gui.table.NKTable;
import gui.textpane.CopiesPerLocationTextPane;
import gui.textpane.CostPerLocationTextPane;
import gui.textpane.ProductPerTextPane;
import gui.toolbar.NKToolBar;

/**
 * A class extended from JFrame custom made for NKSD Copy Center. Generates a UI
 * that is similar to the program for the previous year.
 * 
 * @author Phil Adriaan
 * @version 1
 */
@SuppressWarnings("serial")
public class NKFrame extends JFrame
{

  /**
   * Holds the current instance of all data required for the program.
   */
  private DataManager my_data_manager;
  /**
   * The component that is currently active on the frame. It holds everything
   * that is visualized currently.
   */
  private JComponent my_current_component;
  /**
   * The enum for the panel that is currently shown in the frame.
   */
  private PanelEnum my_current_panel;
  /**
   * The text pane that is currently shown on the frame if there is one.
   */
  private JTextPane my_current_text_pane;
  /**
   * The table that is currently shown on the frame if there is one.
   */
  private NKTable my_current_table;
  /**
   * The toolbar that is shown on the frame.
   */
  private NKToolBar my_toolbar;
  /**
   * For storing all the fields that are needed for the program. Maps the fields
   * to each field enums.
   */
  private final Map<FieldEnum, JComponent> my_field_map = new HashMap<FieldEnum, JComponent>();
  /**
   * Drop down menu to filter the locations in the user panel.
   */
  private NKComboBox my_location_filter_box;
  /**
   * Toggle button to filter the orders by date in the order panel. This button
   * will only be enabled in the order panel. The default filter filters orders
   * made in the last 2 weeks.
   */
  private JToggleButton my_order_filter_button =
      new JToggleButton(new ImageIcon("Icons/Filter.png"));
  /**
   * Drop down menu for selecting locations in the order panel. Will
   * automatically change to the default location of the selected user if a new
   * user is selected.
   */
  private NKComboBox my_location_selection_box;

  /**
   * Location learnining.
   */
  private LocationLearner my_location_learner = new LocationLearner();

  /**
   * Creates the frame by calling the JFrame constructor, then creates custom
   * elements that is specifically designed for the program.
   * 
   * @throws IOException For reading and writing errors.
   */
  public NKFrame() throws IOException
  {
    super();
    start();
  }

  /**
   * Gets the location learner.
   * 
   * @return The specific location learner.
   */
  public LocationLearner getLocationLearner()
  {
    return my_location_learner;
  }

  /**
   * Starts the GUI by making a loading screen, make the frame visible, maximize
   * the frame, and set the default close action. The program the reads all the
   * tables and populate the data manager with its data.It
   * 
   * @throws IOException For reading and writing errors.
   */
  private void start() throws IOException
  {
    setPanel(PanelEnum.MESSAGE, "Loading");

    setVisible(true);
    setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    my_data_manager = new DataManager();

    final List<List<String>> location_data = my_data_manager.getData("Table\\Location.csv");
    my_location_selection_box = new NKComboBox(location_data, false);

    setField();
    setOrderFilter();
    setLocationFilter();

    setPanel(PanelEnum.ORDER, null);
    setJMenuBar(new NKMenuBar(this));
    my_toolbar = new NKToolBar(my_order_filter_button);
    add(my_toolbar, BorderLayout.NORTH);

    addWindowListener(new WindowAdapter()
    {
      public void windowClosing(final WindowEvent the_event)
      {
        try
        {
          my_location_learner.save();
        }
        catch (final IOException exception)
        {
          JOptionPane.showMessageDialog(null, the_event.toString(), null,
                                        JOptionPane.ERROR_MESSAGE);
        }
      }
    });

    validate();
    sync();
  }

  /**
   * Whether the filter button is selected.
   * 
   * @return True if filter button selected, false if otherwise.
   */
  public boolean isFilterSelected()
  {
    return my_order_filter_button.isSelected();
  }

  /**
   * Adds an action listener into the order filter button to make it usable.
   */
  private void setOrderFilter()
  {
    my_order_filter_button.addActionListener(new ActionListener()
    {

      @Override
      public void actionPerformed(final ActionEvent the_event)
      {
        final boolean filter_selected = my_order_filter_button.isSelected();
        final List<List<String>> order_data = my_data_manager.getOrder(filter_selected);

        drawTable(order_data,
                  my_data_manager.getHeaderMap().get("Table\\" +
                                                         my_data_manager.getOrderFileName()));
      }
    });
  }

  /**
   * Creates the drop down menu for the location filter at the users panel, and
   * also adds an action listener to it to make it usable.
   */
  private void setLocationFilter()
  {
    final List<List<String>> location_data = my_data_manager.getData("Table\\Location.csv");
    my_location_filter_box = new NKComboBox(location_data, true);
    my_location_filter_box.addItemListener(new ItemListener()
    {
      public void itemStateChanged(final ItemEvent the_event)
      {
        String location_id = "";

        final Object selected_item = the_event.getItem();
        final String selected_string = (String) selected_item;

        if (!"".equals(selected_string))
        {
          final String[] selected_split = selected_string.split(", ");
          final int budget_code_index = selected_split.length - 1;
          final String budget_code = selected_split[budget_code_index];
          location_id = Strings.getLocationID(budget_code);
        }

        final List<List<String>> table_data = my_data_manager.getUser(location_id);
        drawTable(table_data, my_data_manager.getHeaderMap().get("Table\\User.csv"));
      }
    });
  }

  /**
   * Updates the current table from a collection of data. Used only to update
   * and refresh current table and not for creating new tables.
   * 
   * @param the_data A 2D list of data to be updated on the table. The data
   *          represented on the table will be in the exact same structure as
   *          the data ordered in the 2D list.
   * @param the_header Header of the table.
   */
  public void drawTable(final List<List<String>> the_data, final List<String> the_header)
  {
    my_current_table = new NKTable(the_data, the_header);

    final FormView current_view = (FormView) my_current_component;
    final JScrollPane scroll_pane = current_view.getScrollPane();

    scroll_pane.setViewportView(my_current_table);
  }

  /**
   * Sets the date fields for the user to be able to choose dates from. Will
   * create a new JDateChooser instance and adds it to the field mapping. Format
   * of the date will be M/d/y.
   * 
   * @param the_enum The field enum that the date fields will be mapped to. To
   *          make sure that the results are displayed correctly, make sure that
   *          it is mapped to only date related fields.
   */
  private void setDateField(final FieldEnum the_enum)
  {
    final JDateChooser date_chooser = new JDateChooser();
    date_chooser.setDateFormatString("M/d/y");
    date_chooser.setDate(Calendar.getInstance().getTime());
    my_field_map.put(the_enum, date_chooser);
  }

  /**
   * Sets the user field for the user to be able to select users from. It will
   * create a drop down menu containing all the user information, and adds a
   * listener to automatically select the default location of the selected user
   * in the location selection drop down menu. The default location will be the
   * first location in alphabetical order which the budget code matches the 3
   * digit location code of the selected user.
   */
  private void setUserField()
  {
    final List<List<String>> user_data = my_data_manager.getData("Table\\User.csv");
    final NKComboBox user_box = new NKComboBox(user_data, false);

    user_box.addItemListener(new ItemListener()
    {
      public void itemStateChanged(final ItemEvent the_event)
      {
        final String[] s = ((String) the_event.getItem()).split(", ");

        final String last_name = s[0];
        final String first_name = s[1];
        final String name = first_name + " " + last_name;

        if (my_location_learner.hasHistory(name))
        {
          for (int i = 0; i < my_location_selection_box.getItemCount(); i++)
          {
            final String item = my_location_selection_box.getItemAt(i);
            final String[] item_split = item.split(", ");
            final int location_index = 0;
            final String location = item_split[location_index];

            final String location_mode = my_location_learner.getMode(name);

            if (location_mode.equals(location))
            {
              my_location_selection_box.setSelectedIndex(i);
              break;
            }
            validate();
          }
        }
        else
        {
          final String location_id = s[s.length - 1];

          for (int i = 0; i < my_location_selection_box.getItemCount(); i++)
          {
            final String item = my_location_selection_box.getItemAt(i);
            final String[] item_split = item.split(", ");
            final int item_split_size = item_split.length;
            final int budget_code_index = item_split_size - 1;
            final String budget_code = item_split[budget_code_index];
            final String current_location_id = Strings.getLocationID(budget_code);

            if (location_id.equals(current_location_id))
            {
              my_location_selection_box.setSelectedIndex(i);
              break;
            }
            validate();
          }
        }
      }
    });

    my_field_map.put(FieldEnum.USER, user_box);
  }

  /**
   * Sets the product field by creating a drop down box containing the name and
   * price information of all available products.
   */
  private void setProductField()
  {
    final NKComboBox combo =
        new NKComboBox(my_data_manager.getData("Table\\Product.csv"), false);

    combo.addItemListener(new ItemListener()
    {
      public void itemStateChanged(final ItemEvent the_event)
      {
        sync();
      }
    });

    my_field_map.put(FieldEnum.PRODUCT, combo);
  }

  /**
   * Sets the fields for numerical inputs. Creates JTextFields and adds document
   * listeners to detect any changes made on the field so that it can notify the
   * output fields for new values.
   * 
   * @param the_enum Enums for the field type to be mapped with. Only to be
   *          mapped with field types that requires numerical inputs.
   */
  private void setInputField(final FieldEnum the_enum)
  {
    final JTextField field = new JTextField();

    field.getDocument().addDocumentListener(new DocumentListener()
    {
      @Override
      public void changedUpdate(final DocumentEvent the_event)
      {
        sync();
      }

      @Override
      public void insertUpdate(final DocumentEvent the_event)
      {
        sync();
      }

      @Override
      public void removeUpdate(final DocumentEvent the_event)
      {
        sync();
      }
    });

    my_field_map.put(the_enum, field);
  }

  /**
   * Sets the output fields for users to see the results of their selections.
   * The output fields are not editable and are filtered to only allow limited
   * information to be displayed.
   * 
   * @param the_enum Enums for the field type to be mapped with. Will only
   *          accept output fields, otherwise it is ignored.
   */
  private void setOutputField(final FieldEnum the_enum)
  {
    final JTextField output_field = new JTextField();
    final String[] string = the_enum.toString().split("_");

    if ("OUT".equals(string[string.length - 1]))
    {
      output_field.setEditable(false);
    }

    my_field_map.put(the_enum, output_field);
  }

  /**
   * Sets all of the fields by scanning through the enum for the required fields
   * to be mapped, then determine the type of fields that should be mapped to
   * the enum and creates the fields and its listeners if needed.
   */
  private void setField()
  {
    for (FieldEnum i : FieldEnum.values())
    {
      if (i == FieldEnum.JOB_DATE || i == FieldEnum.BILL_DATE)
      {
        setDateField(i);
      }
      else if (i == FieldEnum.LOCATION)
      {
        my_field_map.put(i, my_location_selection_box);
      }
      else if (i == FieldEnum.USER)
      {
        setUserField();
      }
      else if (i == FieldEnum.PRODUCT)
      {
        setProductField();
      }
      else if (i == FieldEnum.QUANTITY || i == FieldEnum.DISCOUNT)
      {
        setInputField(i);
      }
      else
      {
        setOutputField(i);
      }
    }
  }

  /**
   * Syncs the input and output fields. When a change in an input field is
   * detected, it will be read and displayed on the output fields in correct
   * format. Invalid inputs will be ignored.
   */
  private void sync()
  {
    final JComponent product_component = my_field_map.get(FieldEnum.PRODUCT);
    final JComboBox<String> product_combo = (JComboBox<String>) product_component;
    final Object selected_item = product_combo.getSelectedItem();
    final String selected_product = selected_item.toString();
    final String[] selected_split = selected_product.split(", ");
    final String selected_price = selected_split[1];
    final double price = Double.valueOf(selected_price);

    final JComponent price_out_component = my_field_map.get(FieldEnum.PRICE_OUT);
    final JTextField price_out_field = (JTextField) price_out_component;
    price_out_field.setText(selected_price);

    final JComponent quantity_component = my_field_map.get(FieldEnum.QUANTITY);
    final JTextField quantity_field = (JTextField) quantity_component;
    String quantity_text = quantity_field.getText();
    int quantity = 0;

    final JComponent discount_component = my_field_map.get(FieldEnum.DISCOUNT);
    final JTextField discount_field = (JTextField) discount_component;
    String discount_text = discount_field.getText();
    double discount = 0;

    try
    {
      quantity = Integer.valueOf(quantity_text);
    }
    catch (final NumberFormatException e)
    {
      quantity = 0;
    }

    try
    {
      discount = Double.valueOf(discount_text);
    }
    catch (final NumberFormatException e)
    {
      discount = 0;
    }

    quantity_text = Integer.toString(quantity);
    final JComponent quantity_out_component = my_field_map.get(FieldEnum.QUANTITY_OUT);
    final JTextField quantity_out_field = (JTextField) quantity_out_component;
    quantity_out_field.setText(quantity_text);

    discount_text = Double.toString(discount);
    final JComponent discount_out_component = my_field_map.get(FieldEnum.DISCOUNT_OUT);
    final JTextField discount_out_field = (JTextField) discount_out_component;
    discount_out_field.setText(discount_text);

    double cost = price * quantity * (100.0 - discount) / 100.0;
    cost = Math.round(cost * 1000.0) / 1000.0;
    final Format cost_formatter = new DecimalFormat("0.00#");
    final String cost_string = cost_formatter.format(cost);

    final JComponent cost_out_component = my_field_map.get(FieldEnum.COST_OUT);
    final JTextField cost_out_field = (JTextField) cost_out_component;
    cost_out_field.setText(cost_string);

    validate();
  }

  /**
   * Gets the location filter box used in the users panel.
   * 
   * @return The drop down menu to be used in the users panel for filtering
   */
  public NKComboBox getLocationFilterBox()
  {
    return my_location_filter_box;
  }

  /**
   * Gets all the fields used in the order panel.
   * 
   * @return All the fields available at the order panel.
   */
  public Map<FieldEnum, JComponent> getFields()
  {
    return my_field_map;
  }

  /**
   * Gets the current instance of the data manager.
   * 
   * @return Current instance of the data manager.
   */
  public DataManager getDataManager()
  {
    return my_data_manager;
  }

  /**
   * Gets the current screen that is shown in the frame.
   * 
   * @return Information of the panel that is currently shown in the frame.
   */
  public PanelEnum getPanel()
  {
    return my_current_panel;
  }

  /**
   * Gets the current text pane.
   * 
   * @return Current instance of the text pane. Will only give correct
   *         information if the text pane is currently shown in the frame
   */
  public JTextPane getTextPane()
  {
    return my_current_text_pane;
  }

  /**
   * Gets the current Table.
   * 
   * @return Current instance of the table. will only give correct information
   *         if the current panel is the order panel.
   */
  public NKTable getTable()
  {
    return my_current_table;
  }

  /**
   * Changes the panel to a new panel. This method will rename the title to the
   * desired panel, remove the old panel if there is any, and create a new panel
   * according to the desired panel. The panel will then be refreshed to show it
   * on screen. It will also configure all panel specific components to the
   * default state. Some of it include the order filter button and the user
   * filter drop down menu.
   * 
   * @param the_panel_enum Screen to be changed to.
   * @param the_message Message to be displayed if the panel to be changed is
   *          the message screen.
   * @throws IOException For reading and writing errors.
   */
  public void setPanel(final PanelEnum the_panel_enum, final String the_message)
      throws IOException
  {
    setTitle(Strings.enumToString(the_panel_enum));
    if (my_current_component != null)
    {
      remove(my_current_component);
    }
    if (the_panel_enum == PanelEnum.ORDER && my_current_panel != PanelEnum.ORDER)
    {
      my_order_filter_button.setSelected(true);
      my_order_filter_button.setEnabled(true);
    }
    else if (the_panel_enum != PanelEnum.ORDER)
    {
      my_order_filter_button.setSelected(false);
      my_order_filter_button.setEnabled(false);
    }
    switch (the_panel_enum)
    {
      case ORDER:
        final boolean filter_selected = my_order_filter_button.isSelected();
        final List<List<String>> order_data = my_data_manager.getOrder(filter_selected);
        final OrderHeader order_header = new OrderHeader(this);
        my_current_table =
            new NKTable(order_data, my_data_manager.getHeaderMap()
                .get("Table\\" + my_data_manager.getOrderFileName()));
        final String order_file_name = my_data_manager.getOrderFileName();
        my_current_component =
            new FormView(this, order_header, my_current_table, order_file_name);
        break;

      case USER:
        my_location_filter_box.setSelectedIndex(0);
        final List<List<String>> user_data = my_data_manager.getUser("");
        my_current_table =
            new NKTable(user_data, my_data_manager.getHeaderMap().get("Table\\User.csv"));
        final UserHeader user_header = new UserHeader(this);
        final String user_file_name = "Table\\User.csv";
        my_current_component =
            new FormView(this, user_header, my_current_table, user_file_name);
        break;

      case PRODUCT:
        final List<List<String>> product_data = my_data_manager.getProduct();
        my_current_table =
            new NKTable(product_data, my_data_manager.getHeaderMap().get("Table\\Product.csv"));
        final ProductHeader product_header = new ProductHeader();
        final String product_file_name = "Table\\Product.csv";
        my_current_component =
            new FormView(this, product_header, my_current_table, product_file_name);
        break;

      case LOCATION:
        final List<List<String>> location_data = my_data_manager.getLocation();
        my_current_table =
            new NKTable(location_data, my_data_manager.getHeaderMap()
                .get("Table\\Location.csv"));
        final LocationHeader location_header = new LocationHeader(this);
        final String location_file_name = "Table\\Location.csv";
        my_current_component =
            new FormView(this, location_header, my_current_table, location_file_name);
        break;

      case COST_PER_LOCATION:
        final String cost_title = "COST PER MONTH PER LOCATION";
        final List<List<String>> cost_data = my_data_manager.getCostPerLocation();
        my_current_text_pane = new CostPerLocationTextPane(cost_title, cost_data);
        my_current_component = new JScrollPane(my_current_text_pane);
        break;

      case COPIES_PER_LOCATION:
        final String copies_title = "COPIES PER MONTH PER LOCATION PER EMPLOYEE";
        final List<List<String>> copies_data = my_data_manager.getCopiesPerLocation();
        my_current_text_pane = new CopiesPerLocationTextPane(copies_title, copies_data);
        my_current_component = new JScrollPane(my_current_text_pane);
        break;

      case PRODUCT_PER_DESCRIPTION:
        final String description_title =
            "PRODUCT QUANTITY FOR MONTH BY DESCRIPTION BY LOCATION";
        final List<List<String>> description_data = my_data_manager.getProductPerDescription();
        my_current_text_pane = new ProductPerTextPane(description_title, description_data);
        my_current_component = new JScrollPane(my_current_text_pane);
        break;

      case PRODUCT_PER_LOCATION:
        final String location_title = "PRODUCT QUANTITY FOR MONTH BY LOCATION BY DESCRIPTION";
        final List<List<String>> loc_data = my_data_manager.getProductPerLocation();
        my_current_text_pane = new ProductPerTextPane(location_title, loc_data);
        my_current_component = new JScrollPane(my_current_text_pane);
        break;

      case MESSAGE:
        my_current_component = new JLabel(the_message, SwingConstants.CENTER);
        setTitle(the_message);
        break;

      default:
        break;
    }
    add(my_current_component);
    my_current_panel = the_panel_enum;
    validate();
  }
}
