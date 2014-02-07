
package utility;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 * Utility class for processing images.
 * 
 * @author Phil Adriaan
 * @version 1
 */
public final class ImageUtility
{

  /**
   * This is a utility class. No instatiation necessary.
   */
  private ImageUtility()
  {
  }

  /**
   * Gets an image as an icon.
   * 
   * @param the_name Name of the file.
   * @return The icon object of that image.
   */
  public static ImageIcon getIcon(final String the_name)
  {
    try
    {
      return new ImageIcon(ImageIO.read(new File("Icons/" + the_name)));
    }
    catch (final IOException e)
    {
      JOptionPane.showMessageDialog(null, e.getMessage(), null, JOptionPane.ERROR_MESSAGE);
    }
    return null;
  }

}
