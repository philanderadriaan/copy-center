import utility.FileUtility;

/**
 * This class is used for testing.
 * 
 * @author padriaan
 * @version 1
 */
public final class Test
{

  /**
   * Cannot construct this class.
   */
  private Test()
  {
  }

  /**
   * Starts the test.
   * 
   * @param args Command line arguments
   */
  public static void main(final String[] args)
  {
    System.out.println(FileUtility.getFiles("Table"));
  }

}
