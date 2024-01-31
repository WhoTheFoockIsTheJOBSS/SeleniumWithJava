package testdata;

import framework.config.PropertyManager;
import java.lang.reflect.Method;
import org.testng.annotations.DataProvider;

import static framework.utils.data.ExcelUtils.getExcelTableArray;

public class DataClass {
    static Object[][] testObjArray;
    private static String excelWorkBook = PropertyManager.getInstance().getProperty("DataEngine");
    private static String excelValidWorkSheet = PropertyManager.getInstance().getProperty("TestCaseSheet");

    @DataProvider(name = "UsersExcelData")
    public  static  Object[][] getDataFromExcel(Method method){
        testObjArray = getExcelTableArray(excelWorkBook, excelValidWorkSheet);
        return testObjArray;
    }
}
