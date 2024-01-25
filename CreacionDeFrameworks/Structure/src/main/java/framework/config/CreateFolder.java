package framework.config;

import framework.base.FrameworkException;
import framework.utils.logs.Log;

import java.io.File;

public class CreateFolder {
    public static String createFolder(String folderName) throws FrameworkException {
        File dir = new File(folderName);
        if (!dir.exists()){
            Log.info("creating directory: " + dir.getName());

            try{
                dir.mkdir();
                Log.info("Folder created");
            } catch (SecurityException se){
                throw new FrameworkException("Class CreateFolder | Method createFolder | Exception: "+se.getMessage());
            }
        }
        return dir.toString();
    }
}
