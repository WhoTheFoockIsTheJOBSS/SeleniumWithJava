package framework.utils.data;

import framework.base.FrameworkException;
import framework.config.ExcelReader;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import static framework.config.Constants.DATA_ENGINE;
import static framework.config.ExcelReader.getWorkSheet;
import static framework.config.ExcelReader.readExcel;

public class ExcelUtils extends ExcelReader {
    private static int iNumber = 0;
    private static Sheet excelWSheet;
    private static Cell cell;
    private static Row row;

    public static int getRowCount(){
        try {
            iNumber = excelWSheet.getLastRowNum();
        }catch (Exception e){
            if (e.getMessage() != null){
                new FrameworkException("Class ExcelUtils | Method getRowCount | Exception desc: "+e.getMessage());
            }
        }
        return iNumber;
    }

    public static int getColumnCount(){
        try {
            iNumber = excelWSheet.getRow(0).getLastCellNum();
        }catch (Exception e){
            if (e.getMessage() != null){
                new FrameworkException("Class ExcelUtils | Method getRowCount | Exception desc: "+e.getMessage());
            }
        }
        return iNumber;
    }

    public static String getCellData(int rowNum, int colNum){
        String data = "";
        try{
            cell = excelWSheet.getRow(rowNum).getCell(colNum);
            if (cell == null){
                return "";
            } else if (cell.getCellType() == CellType.STRING){
                data = cell.getStringCellValue();
            } else if (cell.getCellType() == CellType.NUMERIC){
                data = String.valueOf(cell.getNumericCellValue());
            }
        }catch (Exception e){
            new FrameworkException("Class ExcelUtils | Method getCellData | Excel desc: " + e.getMessage());
        }
        return  data;
    }

    public static Object[][] getExcelTableArray(String excelWorkBook, String excelWorkSheet){
        String [][] tabArray = null;
        try{
            readExcel(excelWorkBook);
            excelWSheet = getWorkSheet(excelWorkSheet);
            tabArray = new String[getRowCount()][getColumnCount()];

            for (int ci = 0; ci < getRowCount(); ci++){
                for (int cj = 0; cj < getColumnCount(); cj++){
                    tabArray[ci][cj] = getCellData(ci+1, cj);
                }
            }
        }catch (Exception e){
            new FrameworkException("Class ExcelUtils | Method getExcelTableArray | Exception desc: "+e.getMessage());
        }
        return tabArray;
    }

    public static void setCellData(String result, int rowNum, int colNum, String excelWorkSheet, String excelWorkbook){
        try{
            row = getWorkSheet(excelWorkSheet).getRow(rowNum);
            cell = row.getCell(colNum, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
            if (cell == null){
                cell = row.getCell(colNum);
            }
            cell.setCellValue(result);

            FileOutputStream fileOut = new FileOutputStream(DATA_ENGINE + excelWorkbook);
            workBook.write(fileOut);

            fileOut.flush();
            fileOut.close();
            workBook = new XSSFWorkbook(new FileInputStream(DATA_ENGINE + excelWorkbook));
        }catch (Exception e){
            new FrameworkException("Class ExcelUtils | Method setCellData | Exception desc: "+e.getMessage());
        }
    }
}
