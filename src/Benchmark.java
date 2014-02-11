import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

/**
 * Class to benchmark speeds between reading and writing with POI HSSF on XLS
 * files and BufferedReader/BufferedWriter on CSV files.
 * 
 * @author padriaan
 * @version 1
 */
public final class Benchmark
{
  /**
   * Maximum decimal points.
   */
  private static final int DECIMAL_POINTS = 3;

  /**
   * Utility class should not have a public or default constructor.
   */
  private Benchmark()
  {
  }

  /**
   * Starts the tests.
   * 
   * @param the_args Command line arguments.
   * @throws IOException For reading and writing errors.
   */
  public static void main(final String[] the_args) throws IOException
  {
    testCSV();
    testXLS();
  }

  /**
   * Test the CSV file.
   * 
   * @throws IOException For read/write errors.
   */
  private static void testCSV() throws IOException
  {
    final long start = System.currentTimeMillis();
    final List<List<String>> data = new ArrayList<List<String>>();

    final BufferedReader reader = new BufferedReader(new FileReader("test.csv"));
    String line = reader.readLine();
    while (line != null)
    {
      if (line.split(",").length > 0)
      {
        data.add(Arrays.asList(line.split(",", -1)));
      }
      line = reader.readLine();
    }
    reader.close();

    final BufferedWriter writer = new BufferedWriter(new FileWriter("test2.csv"));
    final StringBuilder builder = new StringBuilder();
    for (List<String> i : data)
    {
      for (String j : i)
      {
        builder.append(j);
        builder.append(',');
      }
      builder.append("\n");
    }
    writer.write(builder.toString());
    writer.close();

    final long end = System.currentTimeMillis();
    System.out.println("CSV: " + (end - start) + "ms");
  }

  /**
   * Tests the XLS file.
   * 
   * @throws IOException For read/write errors.
   */
  private static void testXLS() throws IOException
  {
    final long start = System.currentTimeMillis();

    final List<List<String>> data = new ArrayList<List<String>>();
    final FileInputStream file = new FileInputStream(new File("test.xls"));
    final Workbook book = new HSSFWorkbook(file);
    file.close();

    final Sheet sheet = book.getSheetAt(0);
    for (Row row : sheet)
    {
      final List<String> row_data = new ArrayList<String>();
      for (int i = 0; i < row.getLastCellNum(); i++)
      {
        final Cell cell = row.getCell(i, Row.CREATE_NULL_AS_BLANK);
        switch (cell.getCellType())
        {
          case Cell.CELL_TYPE_STRING:
            final String s = cell.getRichStringCellValue().getString();
            row_data.add(s);
            break;
          case Cell.CELL_TYPE_NUMERIC:

            if (DateUtil.isCellDateFormatted(cell))
            {
              row_data.add(new SimpleDateFormat("M/d/y").format(cell.getDateCellValue()));
            }
            else
            {
              row_data.add(getNumber(cell.getNumericCellValue()));
            }
            break;
          default:
            row_data.add("");
            break;
        }
      }
      while (data.size() > 0 && row_data.size() < data.get(0).size())
      {
        row_data.add("");
      }
      data.add(row_data);
    }

    final String path = "test2.xls";

    final Workbook book2 = new HSSFWorkbook();
    final Sheet sheet2 = book2.createSheet(path);
    for (int i = 0; i < data.size(); i++)
    {
      final Row row = sheet2.createRow(i);
      for (int j = 0; j < data.get(i).size(); j++)
      {
        final String cell = data.get(i).get(j);
        row.createCell(j).setCellValue(cell);
      }
    }
    for (int i = 0; i < data.size(); i++)
    {
      sheet2.autoSizeColumn(i);
    }
    FileOutputStream output;
    output = new FileOutputStream(path);
    book2.write(output);
    output.close();

    final long end = System.currentTimeMillis();
    System.out.println("XLS: " + (end - start) + "ms");
  }

  /**
   * Gets the number from the input.
   * 
   * @param the_number Number from the input.
   * @return String representation of the number.
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
      decimal = decimal.setScale(DECIMAL_POINTS, BigDecimal.ROUND_HALF_UP);
      return Double.toString(decimal.doubleValue());
    }
  }
}
