
package utility;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author padriaan
 * @version 1
 */
public class DateUtility
{
  /**
   * Calendar instance containing today's date.
   */
  private static Calendar my_calendar = Calendar.getInstance();

  /**
   * Today's date.
   */
  private static Date my_date = my_calendar.getTime();

  /**
   * Format for tables and inputs.
   */
  private static Format my_format = new SimpleDateFormat("M/d/yyyy");

  /**
   * Format for reports.
   */
  private static Format my_report_format = new SimpleDateFormat("MMMM y");

  /**
   * Current month in 1 based index. 1=January, 12=December
   */
  private static int my_this_month = my_date.getMonth() + 1;

  /**
   * Previous month for today in 1 based index. 1=January, 12=December.
   */
  private static int my_last_month =
      NumberUtility.getPositiveModulo(my_this_month - 2, 12) + 1;

  /**
   * Utility class. Private constructor prevents instantiation.
   */
  private DateUtility()
  {
  }

  /**
   * Gets the month previous to this month.
   * 
   * @return Previous month.
   */
  public static int getLastMonth()
  {
    return my_last_month;
  }

  /**
   * Get formatted string of last month.
   * 
   * @return Last month in string.
   */
  public static String getLastMonthString()
  {
    Calendar calendar = Calendar.getInstance();
    Date date = calendar.getTime();
    date.setMonth(my_last_month - 1);
    String last_month_string = my_report_format.format(date);
    return last_month_string;
  }

  /**
   * Checks if the date is before this month.
   * 
   * @param the_date_string Current date in M/d/yyyy
   * @return true if date is before this month, false if otherwise.
   */
  public static boolean beforeThisMonth(final String the_date_string)
  {
    int month = getMonth(the_date_string);
    int month_number = getMonthNumber(month);
    int this_month_number = getMonthNumber(my_this_month);
    boolean before_this_month = month_number < this_month_number;
    return before_this_month;
  }

  /**
   * Check if date is set during last month.
   * 
   * @param the_date_string Date in M/d/yyyy
   * @return True if date is set during last month, false if otherwise.
   */
  public static boolean isLastMonth(final String the_date_string)
  {
    int month = getMonth(the_date_string);
    boolean is_last_month = month == my_last_month;
    return is_last_month;
  }

  /**
   * Get the month number starting from the beginning of school year.
   * September=1, August=12.
   * 
   * @param the_month Month.
   * @return Month number starting from september.
   */
  private static int getMonthNumber(final int the_month)
  {
    return NumberUtility.getPositiveModulo(the_month - 9, 12) + 1;
  }

  /**
   * Gets the month out of a string of date in M/d/yyyy.
   * 
   * @param the_date_string Date in M/d/yyyy.
   * @return Month on that date.
   */
  private static int getMonth(final String the_date_string)
  {
    String[] date_split = the_date_string.split("/");
    int month_index = 0;
    String month_string = date_split[0];
    int month = Integer.valueOf(month_string);
    return month;
  }
}
