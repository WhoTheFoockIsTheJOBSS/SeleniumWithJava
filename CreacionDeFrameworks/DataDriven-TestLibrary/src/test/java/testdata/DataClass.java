package testdata;

import framework.config.PropertyManager;
import org.json.simple.parser.ParseException;
import org.testng.annotations.DataProvider;

import java.io.IOException;

import static framework.utils.data.JSONUtils.getJSONTableArray;

public class DataClass {
    static Object[][] testObjArray;
    private static String jsonfile = PropertyManager.getInstance().getProperty("JSONStudentsFile");

    @DataProvider(name = "StudentsJSONDate")
    public static Object[][] getDataFromJSON() throws IOException, ParseException {
        return getJSONTableArray(jsonfile, "Students", (byte)9);

    }
}
