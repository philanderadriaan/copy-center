
package utility;

import enums.PanelEnum;

/**
 * String manipulation utility.
 * 
 * @author Phil Adriaan
 * @version 1
 */
public final class StringUtility
{
  /**
   * Amount of space(s) for padding around strings.
   */
  private static final String PADDING = " ";
  /**
   * Position of location code in a budget code.
   */
  private static final int LOCATION_CODE_INDEX = 3;

  /**
   * Private constructor prevents instantiation.
   */
  private StringUtility()
  {
  }

  /**
   * Converts an enum to formatted string.
   * 
   * @param the_enum Raw string of enum.
   * @return Formatted string of enum.
   */
  public static String enumToString(final PanelEnum the_enum)
  {
    final StringBuilder builder = new StringBuilder();
    boolean after_space = true;
    for (char i : the_enum.toString().toCharArray())
    {
      if (i == '_')
      {
        builder.append(PADDING);
        after_space = true;
      }
      else if (after_space)
      {
        builder.append(i);
        after_space = false;
      }
      else
      {
        builder.append(Character.toLowerCase(i));
      }
    }
    return builder.toString();
  }

  /**
   * Gets the name only from a file name, stripping the folder locations and the
   * file extension.
   * 
   * @param the_path Name of the file.
   * @return File name without location and extension.
   */
  public static String getFileNameOnly(final String the_path)
  {
    int left = 0;
    int right = 0;
    for (int i = 0; i < the_path.length(); i++)
    {
      if (the_path.charAt(i) == '/' || the_path.charAt(i) == '\\')
      {
        left = i;
      }
      else if (the_path.charAt(i) == '.')
      {
        right = i;
        break;
      }
    }
    return the_path.substring(left + 1, right);
  }

  /**
   * Gets the location ID from a budget code.
   * 
   * @param the_budget_code Original budget code.
   * @return Location ID embedded in the budget code.
   */
  public static String getLocationID(final String the_budget_code)
  {
    final String[] budget_code_split = the_budget_code.split(" ");
    final String location_id = budget_code_split[LOCATION_CODE_INDEX];
    return location_id;
  }
}
