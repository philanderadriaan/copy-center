import gui.frame.NKFrame;

import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import utility.DateUtility;

/**
 * The class that starts the whole program.
 * 
 * @author Phil Adriaan
 * @version 1
 */
public final class Main
{
  /**
   * Private constructor to prevent instantiation.
   */
  private Main()
  {
  }

  /**
   * First method to run in the whole program. Scans all system installed look
   * and feels to search for the "Nimbus" look and feel. If the look and feel is
   * found, set the "Nimbus" look and feel into the UI Manager, then opens a
   * window of the program.
   * 
   * @param the_args Command line arguments. Not used in this program.
   */
  public static void main(final String[] the_args)
  {
    try
    {
      for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels())
      {
        if ("Nimbus".equals(info.getName()))
        {
          UIManager.setLookAndFeel(info.getClassName());

        }
      }
      new NKFrame();
    }
    catch (Exception e)
    {
      JOptionPane.showMessageDialog(null, e.toString(), null, JOptionPane.ERROR_MESSAGE);
      e.printStackTrace();
    }
  }
}
