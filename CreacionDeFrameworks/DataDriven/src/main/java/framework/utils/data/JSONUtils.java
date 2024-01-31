package framework.utils.data;

import framework.config.PropertyManager;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;

import java.io.FileReader;
import java.io.IOException;

import static framework.config.Constants.DATA_FOLDER;

public class JSONUtils {
    private static String jsonfile = PropertyManager.getInstance().getProperty("JSONFile");
    private static JSONArray dataObj;
    private static JSONObject contentObj;

    public static Object[][] getJSONTableArray(String userType, byte JSONAttributes) throws IOException, ParseException {
        JSONParser parser = new JSONParser();

        contentObj = (JSONObject) parser.parse(new FileReader(new File(DATA_FOLDER + jsonfile)));
        dataObj = (JSONArray) contentObj.get(userType);

        Object[][] arr = new String[dataObj.size()][JSONAttributes];
                for (byte i=0; i< dataObj.size(); i++){
                    JSONObject obj = (JSONObject) dataObj.get(i);
                    arr[i][0] = String.valueOf(obj.get("user"));
                    arr[i][1] = String.valueOf(obj.get("password"));

                    if (JSONAttributes == 3){
                        arr[i][2] = String.valueOf(obj.get("error"));
                    }
                }
                return arr;
    }
}
