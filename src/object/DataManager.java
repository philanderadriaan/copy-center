
package object;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import utility.CSVs;
import utility.Dates;
import utility.Folders;
import utility.Numbers;
import utility.XLS;

/**
 * Holds and manages all the data required for this program.
 * 
 * @author Phil Adriaan
 * @version 1
 */
public class DataManager
{
  /**
   * File name for the order table.
   */
  private static String order_file_name;
  /**
   * Contains all the data for all Table.
   */
  private Map<String, List<List<String>>> my_data = new HashMap<String, List<List<String>>>();
  /**
   * Resulting data to be shown to the UI.
   */
  private List<List<String>> my_output;
  /**
   * Map of headers for all tables.
   */
  private Map<String, List<String>> my_header_map = new HashMap<String, List<String>>();

  /**
   * Instantiate the data_manager.
   * 
   * @throws IOException For errors in Reading and Writing.
   * 
   */
  public DataManager() throws IOException
  {

    setOrderTableName();

    setTableHeader();

    refresh();

    if (!my_data.containsKey("Table\\" + order_file_name))
    {
      createOrderTable();
    }

  }

  /**
   * gets the map of all headers of the tables.
   * 
   * @return Map containing the headers of the table.
   */
  public Map<String, List<String>> getHeaderMap()
  {
    return my_header_map;
  }

  /**
   * Populates the header of the tables.
   */
  private void setTableHeader()
  {
    final List<String> order_header = new ArrayList<String>();
    order_header.add("Location");
    order_header.add("Last Name");
    order_header.add("First Name");
    order_header.add("Description");
    order_header.add("Job Date");
    order_header.add("Bill Date");
    order_header.add("Unit Price");
    order_header.add("Quantity");
    order_header.add("Percent Discount");
    order_header.add("Job Cost");
    my_header_map.put("Table\\" + "Table\\" + order_file_name, order_header);

    final List<String> location_header = new ArrayList<String>();
    location_header.add("Location");
    location_header.add("Budget Code");
    my_header_map.put("Table\\Location.csv", location_header);

    final List<String> product_header = new ArrayList<String>();
    product_header.add("Description");
    product_header.add("Unit Price");
    my_header_map.put("Table\\Product.csv", product_header);

    final List<String> user_header = new ArrayList<String>();
    user_header.add("Last Name");
    user_header.add("First Name");
    user_header.add("Location ID");
    my_header_map.put("Table\\User.csv", user_header);
  }

  /**
   * Sets the order file name to the appropriate name.
   */
  private void setOrderTableName()
  {
    final StringBuilder order_file_name_builder = new StringBuilder();
    order_file_name_builder.append("Order");

    if (Dates.getCurrentMonth() < Dates.getFirstMonth())
    {
      order_file_name_builder.append(Dates.getCurrentYear() - 1);
      order_file_name_builder.append('-');
      order_file_name_builder.append(Dates.getCurrentYear());
    }
    else
    {
      order_file_name_builder.append(Dates.getCurrentYear());
      order_file_name_builder.append('-');
      order_file_name_builder.append(Dates.getCurrentYear() + 1);
    }

    order_file_name_builder.append(".csv");
    order_file_name = order_file_name_builder.toString();
  }

  /**
   * Create the table for the orders if the appropriate order file is not
   * available. Applicable for when the new fiscal year starts, or the order
   * file is deleted.
   * 
   * @throws IOException For reading and writing errors.
   */
  private void createOrderTable() throws IOException
  {

    final List<List<String>> empty_list = new ArrayList<List<String>>();
    my_data.put("Table\\" + order_file_name, empty_list);

    final String order_location = "Table\\" + order_file_name;
    CSVs.overwrite(order_location, empty_list);
  }

  /**
   * Refreshes the screen and update the tables to give the users the most up to
   * date information on the data.
   * 
   * @throws IOException For reading and writing errors.
   */
  public void refresh() throws IOException
  {
    final List<File> files = Folders.getFiles("Table");

    for (File i : files)
    {
      final String file_name = i.getName();
      final boolean is_order_file = "Order".equalsIgnoreCase(file_name.substring(0, 5));
      final boolean is_valid_order_file =
          is_order_file && order_file_name.equalsIgnoreCase(file_name);
      final boolean is_valid_file = !is_order_file || is_valid_order_file;
      if (is_valid_file)
      {
        my_data.put(i.getPath(), CSVs.read(i.getPath()));
        CSVs.overwrite(i.getPath(), my_data.get(i.getPath()));
      }
    }
  }

  /**
   * Gets the order file name.
   * 
   * @return Order file name in the format of OrderXXXX-YYYY.csv. If the current
   *         date is before
   */
  public String getOrderFileName()
  {
    return "Table\\" + order_file_name;
  }

  /**
   * Adds a row to a Table.
   * 
   * @param the_row Row data to be added to the Table.
   * @throws IOException For reading and writing errors.
   */
  public void addOrder(final List<String> the_row) throws IOException
  {
    final List<List<String>> data = my_data.get("Table\\" + order_file_name);
    data.add(the_row);
    my_data.put("Table\\" + order_file_name, data);
    CSVs.add("Table\\" + order_file_name, the_row);
  }

  /**
   * Reset the output.
   * 
   * @param the_size Number of rows.
   */
  public void resetOutput(final int the_size)
  {
    my_output = new ArrayList<List<String>>();
    for (int i = 0; i < the_size; i++)
    {
      my_output.add(new ArrayList<String>());
    }
  }

  /**
   * Gets all data for cost per location.
   * 
   * @return 2D list of cost per location.
   * @throws IOException For reading and writing errors.
   */
  public List<List<String>> getCostPerLocation() throws IOException
  {
    final List<List<String>> order = my_data.get("Table\\" + order_file_name);
    final List<List<String>> location = my_data.get("Table\\Location.csv");
    resetOutput(order.size());
    select(order, 0);
    join(order, location, 0, 0, 1);
    select(order, 9);
    select(order, 5);
    final Map<String, Double> month_aggregation = new TreeMap<String, Double>();
    final Map<String, Double> aggregation = new TreeMap<String, Double>();
    double month_total = 0;
    double year_total = 0;

    for (int i = 0; i < my_output.size(); i++)
    {

      final String date_string = my_output.get(i).get(3);
      //final String[] date_split = date_string.split("/");
      //final String month_string = date_split[0];
      //final int month = Integer.valueOf(month_string);

      if (Dates.isBeforeCurrentMonth(date_string))
      {
        final String current_location = my_output.get(i).get(0);
        final String current_budget_code = my_output.get(i).get(1);
        final String key = current_location + ", " + current_budget_code;
        final String job_cost_string = my_output.get(i).get(2);
        final double job_cost = Double.valueOf(job_cost_string);
        year_total += job_cost;

        if (aggregation.containsKey(key))
        {
          double current_total = aggregation.get(key);
          current_total += job_cost;
          aggregation.put(key, current_total);
        }
        else
        {
          aggregation.put(key, job_cost);
          month_aggregation.put(key, 0.0);
        }

        if (Dates.isPreviousMonth(date_string))
        {
          month_total += job_cost;
          if (month_aggregation.containsKey(key))
          {
            double current_total = month_aggregation.get(key);
            current_total += job_cost;
            month_aggregation.put(key, current_total);
          }
          else
          {
            month_aggregation.put(key, job_cost);
          }
        }
      }
    }
    resetOutput(0);

    final List<String> header = new ArrayList<>();
    header.add("Location:");
    header.add("Budget Code:");
    header.add(Dates.getPreviousMonthFormatted());
    header.add("School Year to End of " + Dates.getPreviousMonthFormatted() + ":");
    my_output.add(header);

    my_output.add(new ArrayList<String>());

    for (String i : aggregation.keySet())
    {
      final String[] key_split = i.split(", ");
      final String loc = key_split[0];
      final String bc = key_split[1];
      my_output.add(Arrays.asList(new String[] {loc, bc,
          Numbers.getMoney(month_aggregation.get(i), false),
          Numbers.getMoney(aggregation.get(i), false)}));
    }
    my_output.add(new ArrayList<String>());
    my_output.add(Arrays.asList(new String[] {"Total for North Kitsap School District", "",
        Numbers.getMoney(month_total, true), Numbers.getMoney(year_total, true)}));
    XLS.write("Report/Cost Per Location.xls", my_output);

    return my_output;
  }

  /**
   * Gets all data of copies per locaiton.
   * 
   * @return a 2D list of all data for copies per location.
   * @throws IOException For reading and writing errors.
   */
  public List<List<String>> getCopiesPerLocation() throws IOException
  {
    final List<List<String>> order_table = my_data.get("Table\\" + order_file_name);
    resetOutput(order_table.size());
    select(order_table, 0);
    select(order_table, 1);
    select(order_table, 2);
    select(order_table, 7);
    select(order_table, 9);
    select(order_table, 5);

    final Set<String> key_set = new TreeSet<String>();
    final Map<String, Integer> location_quantity_month_map = new HashMap<String, Integer>();
    final Map<String, Double> location_cost_month_map = new HashMap<String, Double>();
    final Map<String, Integer> location_quantity_map = new HashMap<String, Integer>();
    final Map<String, Double> location_cost_map = new HashMap<String, Double>();

    for (List<String> i : my_output)
    {
      final int date_index = 5;
      final String date_string = i.get(date_index);
      final String[] date_split = date_string.split("/");
      final String month_string = date_split[0];
      final int month = Integer.valueOf(month_string);

      if (Dates.isBeforeCurrentMonth(date_string))
      {

        final int location_index = 0;
        final int last_name_index = 1;
        final int first_name_index = 2;
        final String location = i.get(location_index);
        final String last_name = i.get(last_name_index);
        final String first_name = i.get(first_name_index);
        final String name = last_name + ", " + first_name;
        final String key = location + " - " + name;

        final int quantity_index = 3;
        final int cost_index = 4;
        final String current_quantity_string = i.get(quantity_index);
        final int current_quantity = Integer.valueOf(current_quantity_string);
        final String current_cost_string = i.get(cost_index);
        final double current_cost = Double.valueOf(current_cost_string);

        if (key_set.contains(key))
        {
          int total_quantity = location_quantity_map.get(key);
          total_quantity += current_quantity;
          location_quantity_map.put(key, total_quantity);
          double total_cost = location_cost_map.get(key);
          total_cost += current_cost;
          location_cost_map.put(key, total_cost);
          if (month % Dates.getMonthsPerYear() == Dates.getPreviousMonth())
          {
            int month_quantity = location_quantity_month_map.get(key);
            month_quantity += current_quantity;
            location_quantity_month_map.put(key, month_quantity);
            double month_cost = location_cost_month_map.get(key);
            month_cost += current_cost;
            location_cost_month_map.put(key, month_cost);
          }
        }
        else
        {
          key_set.add(key);
          location_quantity_map.put(key, current_quantity);
          location_cost_map.put(key, current_cost);
          if (month % Dates.getMonthsPerYear() == Dates.getPreviousMonth())
          {
            location_quantity_month_map.put(key, current_quantity);
            location_cost_month_map.put(key, current_cost);
          }
          else
          {
            location_quantity_month_map.put(key, 0);
            location_cost_month_map.put(key, 0.0);
          }
        }
      }
    }
    final Map<String, Integer> total_quantity_month = new HashMap<String, Integer>();
    final Map<String, Double> total_cost_month = new HashMap<String, Double>();
    final Map<String, Integer> total_quantity = new HashMap<String, Integer>();
    final Map<String, Double> total_cost = new HashMap<String, Double>();
    final Map<String, List<List<String>>> table_map =
        new TreeMap<String, List<List<String>>>();
    for (String i : key_set)
    {
      final String[] split = i.split(" - ");
      final String loc = split[0];
      final String name = split[1];
      if (table_map.containsKey(loc))
      {
        final List<List<String>> table = table_map.get(loc);
        final List<String> row = new ArrayList<String>();
        row.add(name);
        row.add(Numbers.getQuantity(location_quantity_month_map.get(i)));
        row.add(Numbers.getMoney(location_cost_month_map.get(i), true));
        row.add(Numbers.getQuantity(location_quantity_map.get(i)));
        row.add(Numbers.getMoney(location_cost_map.get(i), true));
        table.add(row);
        table_map.put(loc, table);

        final int current_quantity_month = location_quantity_month_map.get(i);
        final double current_cost_month = location_cost_month_map.get(i);
        final int current_quantity = location_quantity_map.get(i);
        final double current_cost = location_cost_map.get(i);

        int quantity_month = total_quantity_month.get(loc);
        double cost_month = total_cost_month.get(loc);
        int quantity = total_quantity.get(loc);
        double cost = total_cost.get(loc);

        quantity_month += current_quantity_month;
        cost_month += current_cost_month;
        quantity += current_quantity;
        cost += current_cost;

        total_quantity_month.put(loc, quantity_month);
        total_cost_month.put(loc, cost_month);
        total_quantity.put(loc, quantity);
        total_cost.put(loc, cost);
      }
      else
      {
        final List<List<String>> table = new ArrayList<List<String>>();
        final List<String> row = new ArrayList<String>();
        row.add(name);
        row.add(Numbers.getQuantity(location_quantity_month_map.get(i)));
        row.add(Numbers.getMoney(location_cost_month_map.get(i), true));
        row.add(Numbers.getQuantity(location_quantity_map.get(i)));
        row.add(Numbers.getMoney(location_cost_map.get(i), true));
        table.add(row);
        table_map.put(loc, table);

        total_quantity_month.put(loc, location_quantity_month_map.get(i));
        total_cost_month.put(loc, location_cost_month_map.get(i));
        total_quantity.put(loc, location_quantity_map.get(i));
        total_cost.put(loc, location_cost_map.get(i));
      }
    }
    final List<List<String>> excel = new ArrayList<List<String>>();
    final List<String> excel_head = new ArrayList<String>();
    excel_head.add("Location: Employee:");
    excel_head.add(Dates.getPreviousMonthFormatted());
    excel_head.add("");
    excel_head.add("School Year to End of " + Dates.getPreviousMonthFormatted() + ":");
    excel.add(excel_head);
    for (String i : table_map.keySet())
    {
      excel.add(new ArrayList<String>());
      excel.add(Arrays.asList(new String[] {i}));
      for (List<String> j : table_map.get(i))
      {
        excel.add(j);
      }
      final List<String> aggregation = new ArrayList<String>();
      aggregation.add("Total for " + i);
      aggregation.add(Numbers.getQuantity(total_quantity_month.get(i)));
      aggregation.add(Numbers.getMoney(total_cost_month.get(i), true));
      aggregation.add(Numbers.getQuantity(total_quantity.get(i)));
      aggregation.add(Numbers.getMoney(total_cost.get(i), true));
      excel.add(aggregation);
    }
    XLS.write("Report/Copies Per Location.xls", excel);
    return excel;
  }

  /**
   * Gets all data for product per description.
   * 
   * @return A 2D list of product per description data.
   * @throws IOException For reading and writing errors.
   */
  public List<List<String>> getProductPerDescription() throws IOException
  {
    final List<List<String>> order_table = my_data.get("Table\\" + order_file_name);
    resetOutput(order_table.size());
    select(order_table, 3);
    select(order_table, 0);
    select(order_table, 7);
    select(order_table, 5);

    final Map<String, Map<String, Integer>> map = new TreeMap<String, Map<String, Integer>>();
    final Map<String, Integer> description_total_map = new TreeMap<String, Integer>();
    for (List<String> i : my_output)
    {
      final int bill_date_index = 3;
      final String bill_date_string = i.get(bill_date_index);
      final String[] bill_date_split = bill_date_string.split("/");
      final String month_string = bill_date_split[0];
      final int current_month = Integer.valueOf(month_string);
      if (current_month % Dates.getMonthsPerYear() == Dates.getPreviousMonth())
      {
        final int description_index = 0;
        final String current_description = i.get(description_index);
        Map<String, Integer> description_map;
        if (map.containsKey(current_description))
        {
          description_map = map.get(current_description);
        }
        else
        {
          description_map = new TreeMap<String, Integer>();
        }
        final int location_index = 1;
        final String current_location = i.get(location_index);

        final int quantity_index = 2;
        final String current_quantity_string = i.get(quantity_index);
        final int current_quantity = Integer.valueOf(current_quantity_string);
        int total_quantity_location;
        if (description_map.containsKey(current_location))
        {
          total_quantity_location = description_map.get(current_location);
        }
        else
        {
          total_quantity_location = 0;
        }
        total_quantity_location += current_quantity;
        description_map.put(current_location, total_quantity_location);
        map.put(current_description, description_map);
        int total_quantity_description;
        if (description_total_map.containsKey(current_description))
        {
          total_quantity_description = description_total_map.get(current_description);
        }
        else
        {
          total_quantity_description = 0;
        }
        total_quantity_description += current_quantity;
        description_total_map.put(current_description, total_quantity_description);
      }
    }

    final List<List<String>> excel = new ArrayList<List<String>>();
    final List<String> excel_head = new ArrayList<String>();
    excel_head.add("Location: Product Description:");
    excel_head.add("Product Quantity:");
    excel.add(excel_head);
    for (String i : map.keySet())
    {
      excel.add(new ArrayList<String>());
      final List<String> description_head = new ArrayList<String>();
      description_head.add(i);
      excel.add(description_head);
      final Map<String, Integer> location_quantity_map = map.get(i);
      for (String j : map.get(i).keySet())
      {
        final int q = location_quantity_map.get(j);
        final String qs = Numbers.getQuantity(q);
        final List<String> location_quantity_list = new ArrayList<String>();
        location_quantity_list.add(j);
        location_quantity_list.add(qs);
        excel.add(location_quantity_list);
      }
      final String description_total_string =
          Numbers.getQuantity(description_total_map.get(i));
      final List<String> total_list = new ArrayList<String>();
      total_list.add(i);
      total_list.add(description_total_string);
      excel.add(total_list);
    }
    XLS.write("Report/Product Per Description.xls", excel);
    return excel;
  }

  /**
   * Gets all data for product per location.
   * 
   * @return A 2D list of data for product per location.
   * @throws IOException For reading and writing errors.
   */
  public List<List<String>> getProductPerLocation() throws IOException
  {
    final List<List<String>> order_table = my_data.get("Table\\" + order_file_name);
    resetOutput(order_table.size());
    select(order_table, 3);
    select(order_table, 0);
    select(order_table, 7);
    select(order_table, 5);
    final Map<String, Map<String, Integer>> map = new TreeMap<String, Map<String, Integer>>();
    final Map<String, Integer> description_total_map = new TreeMap<String, Integer>();
    for (List<String> i : my_output)
    {
      final int bill_date_index = 3;
      final String bill_date_string = i.get(bill_date_index);
      final String[] bill_date_split = bill_date_string.split("/");
      final String month_string = bill_date_split[0];
      final int current_month = Integer.valueOf(month_string);
      if (current_month % Dates.getMonthsPerYear() == Dates.getPreviousMonth())
      {
        final int description_index = 1;
        final String current_description = i.get(description_index);
        Map<String, Integer> description_map;
        if (map.containsKey(current_description))
        {
          description_map = map.get(current_description);
        }
        else
        {
          description_map = new TreeMap<String, Integer>();
        }
        final int location_index = 0;
        final String current_location = i.get(location_index);
        final int quantity_index = 2;
        final String current_quantity_string = i.get(quantity_index);
        final int current_quantity = Integer.valueOf(current_quantity_string);
        int total_quantity_location;
        if (description_map.containsKey(current_location))
        {
          total_quantity_location = description_map.get(current_location);
        }
        else
        {
          total_quantity_location = 0;
        }
        total_quantity_location += current_quantity;
        description_map.put(current_location, total_quantity_location);
        map.put(current_description, description_map);
        int total_quantity_description;
        if (description_total_map.containsKey(current_description))
        {
          total_quantity_description = description_total_map.get(current_description);
        }
        else
        {
          total_quantity_description = 0;
        }
        total_quantity_description += current_quantity;
        description_total_map.put(current_description, total_quantity_description);
      }
    }
    final List<List<String>> excel = new ArrayList<List<String>>();
    final List<String> excel_head = new ArrayList<String>();
    excel_head.add("Location: Product Description:");
    excel_head.add("Product Quantity:");
    excel.add(excel_head);
    for (String i : map.keySet())
    {
      excel.add(new ArrayList<String>());
      final List<String> description_head = new ArrayList<String>();
      description_head.add(i);
      excel.add(description_head);
      final Map<String, Integer> location_quantity_map = map.get(i);
      for (String j : map.get(i).keySet())
      {
        final int q = location_quantity_map.get(j);
        final String qs = Numbers.getQuantity(q);
        final List<String> location_quantity_list = new ArrayList<String>();
        location_quantity_list.add(j);
        location_quantity_list.add(qs);
        excel.add(location_quantity_list);
      }
      final String description_total_string =
          Numbers.getQuantity(description_total_map.get(i));
      final List<String> total_list = new ArrayList<String>();
      total_list.add(i);
      total_list.add(description_total_string);
      excel.add(total_list);
    }
    XLS.write("Report/Product Per Location.xls", excel);
    return excel;
  }

  /**
   * Gets the raw data from a file.
   * 
   * @param the_name File name.
   * @return All data from that Table.
   */
  public List<List<String>> getData(final String the_name)
  {
    return my_data.get(the_name);
  }

  /**
   * Get the order data.
   * 
   * @param the_filter Whether the table is filtered to only show the last two
   *          weeks or not.
   * @return A 2D list containing fields for order.
   */
  public List<List<String>> getOrder(final boolean the_filter)
  {
    final List<List<String>> order = my_data.get("Table\\" + order_file_name);
    resetOutput(order.size());
    select(order, 0);
    select(order, 1);
    select(order, 2);
    select(order, 3);
    select(order, 4);
    select(order, 5);
    select(order, 6);
    select(order, 7);
    select(order, 8);
    select(order, 9);
    if (the_filter)
    {
      int i = 0;
      while (i < my_output.size())
      {
        final String[] m = my_output.get(i).get(4).split("/");
        final Calendar d = new GregorianCalendar();
        d.set(Integer.valueOf(m[2]), Integer.valueOf(m[0]) - 1, Integer.valueOf(m[1]));
        final long n =
            (Calendar.getInstance().getTimeInMillis() - d.getTimeInMillis()) /
                Dates.getMillisecondsPerWeek();
        if (0 <= n && n <= 2)
        {
          i++;
        }
        else
        {
          my_output.remove(i);
        }
      }
    }
    return my_output;
  }

  /**
   * Gets the data for users.
   * 
   * @param the_location_id_filter Location of the user.
   * @return 2D list of user data.
   */
  public List<List<String>> getUser(final String the_location_id_filter)
  {
    final List<List<String>> user = my_data.get("Table\\User.csv");
    resetOutput(user.size());
    select(user, 0);
    select(user, 1);
    select(user, 2);
    if (!"".equals(the_location_id_filter))
    {
      int i = 0;
      while (i < my_output.size())
      {
        final int location_id_index = 2;
        final List<String> current_row = my_output.get(i);
        final String current_location_id = current_row.get(location_id_index);
        if (the_location_id_filter.equals(current_location_id))
        {
          i++;
        }
        else
        {
          my_output.remove(i);
        }
      }
    }
    return my_output;
  }

  /**
   * Gets the data of products.
   * 
   * @return 2D list of products data.
   */
  public List<List<String>> getProduct()
  {
    final List<List<String>> product = my_data.get("Table\\Product.csv");
    resetOutput(product.size());
    select(product, 0);
    select(product, 1);
    return my_output;
  }

  /**
   * Gets the location data.
   * 
   * @return 2D list of location data.
   */
  public List<List<String>> getLocation()
  {
    final List<List<String>> location = my_data.get("Table\\Location.csv");
    resetOutput(location.size());
    select(location, 0);
    select(location, 1);
    return my_output;
  }

  /**
   * Adds a column to the resulting data.
   * 
   * @param the_table Table the column is from.
   * @param the_selection The column to be taken from the Table.
   */
  private void select(final List<List<String>> the_table, final int the_selection)
  {
    for (int i = 0; i < the_table.size(); i++)
    {
      my_output.get(i).add(the_table.get(i).get(the_selection));
    }
  }

  /**
   * Inner joins 2 Table and adds the selected column to the resulting data.
   * 
   * @param table_1 First Table.
   * @param table_2 Second Table.
   * @param key_1 Key from first Table.
   * @param key_2 Key from second Table.
   * @param the_selection Selected column from second Table.
   */
  private void join(final List<List<String>> table_1, final List<List<String>> table_2,
                    final int key_1, final int key_2, final int the_selection)
  {
    for (int i = 0; i < table_1.size(); i++)
    {
      boolean found = false;
      for (int j = 0; j < table_2.size(); j++)
      {
        if (table_1.get(i).get(key_1).equals(table_2.get(j).get(key_2)))
        {
          my_output.get(i).add(table_2.get(j).get(the_selection));
          found = true;
          break;
        }
      }
      if (!found)
      {
        my_output.get(i).add(null);
      }
    }
  }
}
