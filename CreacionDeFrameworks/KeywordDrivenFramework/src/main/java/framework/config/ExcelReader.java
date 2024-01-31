package framework.config;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import static framework.config.Constants.DATA_ENGINE;

public class ExcelReader {
    protected static Workbook workBook;
    protected static FileInputStream excelFile;

    private static FileInputStream setExcelFile(String excelWorkBook) throws FileNotFoundException {
        return new FileInputStream(DATA_ENGINE + excelWorkBook);
    }

    public static void readExcel(String excelWorkBook) throws IOException {
        excelFile = setExcelFile(excelWorkBook);
        String fileExtensionName = excelWorkBook.substring(excelWorkBook.indexOf("."));

        if (fileExtensionName.equals(".xlsx")){
            workBook = new XSSFWorkbook(excelFile);
        } else if (fileExtensionName.equals(".xls")) {
            workBook = new HSSFWorkbook(excelFile);
        }
    }

    public static Sheet getWorkSheet(String sheetName){
        return workBook.getSheet(sheetName);
    }
}
