package framework.config;

public class Constants {
    public static final String BASE_URL = PropertyManager.getInstance().getProperty("Url");

    //System Variables
    private  static final String USER_DIR = System.getProperty("user.dir");
    public static final String RESULTS_FOLDER = USER_DIR + "/results/";
    public static final String RESOURCE_FOLDER = USER_DIR + "/src/main/resources";

    //Results Directory
    public static final String SCREENSHOT_FOLDER = RESULTS_FOLDER + "sreenshots/";
    public static final String VIDEO_FOLDER = RESULTS_FOLDER + "video/";
}
