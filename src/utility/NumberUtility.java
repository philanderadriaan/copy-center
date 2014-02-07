
package utility;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Utility class to process numbers.
 * 
 * @author Phil Adriaan
 * @version 1
 */
public final class NumberUtility
{
  /**
   * Maximum decimal places for a number.
   */
  private static final int MAX_DECIMAL_PLACES = 3;

  /**
   * Prevents instantiation.
   */
  private NumberUtility()
  {
  }

  /**
   * Converts a double to an int if the double does not have any decimal places.
   * 
   * @param the_number Any double.
   * @return An int if the double does not have a decimal place, otherwise
   *         returns the same double.
   */
  public static String getNumber(final double the_number)
  {
    BigDecimal decimal = new BigDecimal(the_number);
    if (decimal.scale() <= 0)
    {
      final int number = decimal.intValueExact();
      return Integer.toString(number);

    }
    else
    {
      decimal = decimal.setScale(MAX_DECIMAL_PLACES, BigDecimal.ROUND_HALF_UP);
      return Double.toString(decimal.doubleValue());
    }
  }

  /**
   * Converts a double into a currency unit with 2 decimal points.
   * 
   * @param the_number Any double.
   * @return A string with the double containing 2 decimal places always.
   */
  public static String getMoney(final double the_number, final boolean the_dollar_sign)
  {
    if (the_dollar_sign)
    {
      return new DecimalFormat("$#,##0.00").format(Math.round(the_number * 100.0) / 100.0);
    }
    else
    {
      return new DecimalFormat("#,##0.00").format(Math.round(the_number * 100.0) / 100.0);
    }
  }

  /**
   * Converts the number into a string with commas per thousand.
   * 
   * @param the_number Original number.
   * @return Formatted number with commas on every thousands.
   */
  public static String getQuantity(final int the_number)
  {
    return new DecimalFormat("#,###").format(the_number);
  }

  /**
   * Formats the date to M/d/y.
   * 
   * @param the_date Original date object.
   */
  public static void formatDate(final Date the_date)
  {
    new SimpleDateFormat("M/d/y").format(the_date);
  }

  public static int getPositiveModulo(int a, int b)
  {
    return (a % b + b) % b;
  }
}
