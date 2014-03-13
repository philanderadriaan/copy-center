import utility.FileUtility;

/**
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
   * @param args
   */
  public static void main(final String[] args)
  {
    System.out.println(FileUtility.getFiles("Table"));
  }

}
