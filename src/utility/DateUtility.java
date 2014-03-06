
package utility;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author padriaan
 * @version 1
 */
public final class DateUtility
{
  /**
   * First month of the school year.
   */
  private static final int FIRST_MONTH = 9;
  /**
   * Number of months per year.
   */
  private static final int MONTHS_PER_YEAR = 12;
  private static final int DECEMBER = 12;
  private static final int MILLISECONDS_PER_WEEK = 1000 * 60 * 60 * 24 * 7;
  /**
   * Calendar instance containing today's date.
   */
  private static Calendar my_calendar = Calendar.getInstance();

  /**
   * Today's date.
   */
  private static Date my_date = my_calendar.getTime();

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
  private static int my_previous_month =
      NumberUtility.getPositiveModulo(my_this_month - 2, MONTHS_PER_YEAR) + 1;

  /**
   * Utility class. Private constructor prevents instantiation.
   */
  private DateUtility()
  {
  }
  
  public static int getMonthsPerYear()
  {
    return MONTHS_PER_YEAR;
  }
  
  public static int getMillisecondsPerWeek()
  {
    return MILLISECONDS_PER_WEEK;
  }
  
  public static int getDecember()
  {
    return DECEMBER;
  }
  
  private static final int getFirstMonth()
  {
    return FIRST_MONTH;
  }

  /**
   * Gets the month previous to this month.
   * 
   * @return Previous month.
   */
  public static int getPreviousMonth()
  {
    return my_previous_month;
  }

  /**
   * Get formatted string of last month.
   * 
   * @return Last month in string.
   */
  public static String getPreviousMonthString()
  {
    final Calendar calendar = Calendar.getInstance();
    final Date date = calendar.getTime();
    date.setMonth(my_previous_month - 1);
    final String last_month_string = my_report_format.format(date);
    return last_month_string;
  }

  /**
   * Checks if the date is before this month.
   * 
   * @param the_date_string Current date in M/d/yyyy
   * @return true if date is before this month, false if otherwise.
   */
  public static boolean isBeforeThisMonth(final String the_date_string)
  {
    final int month = getMonth(the_date_string);
    final int month_number = getMonthRank(month);
    final int this_month_number = getMonthRank(my_this_month);
    final boolean before_this_month = month_number < this_month_number;
    return before_this_month;
  }

  /**
   * Check if date is set during previous month.
   * 
   * @param the_date_string Date in M/d/yyyy
   * @return True if date is set during last month, false if otherwise.
   */
  public static boolean isPreviousMonth(final String the_date_string)
  {
    final int month = getMonth(the_date_string);
    final boolean is_last_month = month == my_previous_month;
    return is_last_month;
  }

  /**
   * Get the month rank starting from the beginning of school year.
   * September=1, August=12.
   * 
   * @param the_month Month.
   * @return Month number starting from september.
   */
  private static int getMonthRank(final int the_month)
  {
    return NumberUtility.getPositiveModulo(the_month - FIRST_MONTH, MONTHS_PER_YEAR) + 1;
  }

  /**
   * Gets the month out of a string of date in M/d/yyyy.
   * 
   * @param the_date_string Date in M/d/yyyy.
   * @return Month on that date.
   */
  private static int getMonth(final String the_date_string)
  {
    final String[] date_split = the_date_string.split("/");
    final int month_index = 0;
    final String month_string = date_split[month_index];
    final int month = Integer.valueOf(month_string);
    return month;
  }
}
