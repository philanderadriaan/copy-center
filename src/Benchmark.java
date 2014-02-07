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

public class Benchmark
{
  public static void main(String[] args) throws IOException
  {
    testCSV();
    testXLS();
  }

  private static void testCSV() throws IOException
  {
    long start = System.currentTimeMillis();
    List<List<String>> data = new ArrayList<List<String>>();

    BufferedReader reader = new BufferedReader(new FileReader("test.csv"));
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

    BufferedWriter writer = new BufferedWriter(new FileWriter("test2.csv"));
    StringBuilder builder = new StringBuilder();
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

    long end = System.currentTimeMillis();
    System.out.println("CSV: " + (end - start) + "ms");
  }

  private static void testXLS() throws IOException
  {
    long start = System.currentTimeMillis();

    final List<List<String>> data = new ArrayList<List<String>>();
    FileInputStream file = new FileInputStream(new File("test.xls"));
    Workbook book = new HSSFWorkbook(file);
    file.close();

    final Sheet sheet = book.getSheetAt(0);
    for (Row row : sheet)
    {
      final List<String> row_data = new ArrayList<String>();
      for (int i = 0; i < row.getLastCellNum(); i++)
      {
        Cell cell = row.getCell(i, Row.CREATE_NULL_AS_BLANK);
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

    String the_path = "test2.xls";

    final Workbook book2 = new HSSFWorkbook();
    final Sheet sheet2 = book2.createSheet(the_path);
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
    output = new FileOutputStream(the_path);
    book2.write(output);
    output.close();

    long end = System.currentTimeMillis();
    System.out.println("XLS: " + (end - start) + "ms");
  }

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
      decimal = decimal.setScale(3, BigDecimal.ROUND_HALF_UP);
      return Double.toString(decimal.doubleValue());
    }
  }
}