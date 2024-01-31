package test;

import framework.base.BasePage;
import framework.base.BrowserType;
import framework.base.DriverFactory;
import framework.config.PropertyManager;
import framework.utils.data.ExcelUtils;
import framework.utils.logs.Log;
import framework.utils.ui.ActionKeywords;
import framework.utils.video.VideoRecorder;
import models.TestCase;
import models.TestSteps;
import org.apache.poi.ss.usermodel.Row;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.asserts.SoftAssert;

import java.io.IOException;
import java.lang.reflect.Method;

import static framework.config.Constants.VIDEO_FOLDER;
import static framework.utils.logs.Log.log;

public class Base extends BasePage {
    protected static VideoRecorder videoRec;
    protected static Method[] methods;
    protected static ActionKeywords actionKeywords;
    protected static TestSteps testStep;
    protected static TestCase testCase;
    protected static String  excelWorkbook = PropertyManager.getInstance().getProperty("DataEngine");
    protected static String  excelTSWorkSheet = PropertyManager.getInstance().getProperty("TestStepSheet");
    protected static String  excelTCWorkSheet = PropertyManager.getInstance().getProperty("TestCaseSheet");
    protected static int startStepsRow;
    protected static int startStepsCol;
    protected static boolean breaking = false;
    protected static boolean testCaseResult;
    protected static  boolean testStepResult = true;
    protected static ExcelUtils excelReader;
    protected static Row row;
    protected static int totalDataRows;
    protected static String param = "";
    protected static SoftAssert softAssert;


    public Base(){
        testStep = new TestSteps();
        testCase = new TestCase();
        softAssert = new SoftAssert();
    }
    String baseURL = "https://demosite.titaniuminstitute.com.mx/wp-login.php?loggedout=true&wp_lang=en_US";

    @BeforeTest
    @Parameters("browser")
    void setup(String browser) {
        videoRec = new VideoRecorder();

        DriverFactory.getInstance().setDriver(BrowserType.valueOf(browser));

        if (Boolean.parseBoolean(PropertyManager.getInstance().getProperty("Video"))){
            videoRec.startRecording(VIDEO_FOLDER);
        }

        DriverFactory.getInstance().getDriver().navigate().to(baseURL);
        log.info("Test is starting!");
        actionKeywords = new ActionKeywords();
        methods = actionKeywords.getClass().getMethods();
    }

    @AfterTest
    void TurnDown() throws IOException {
        Log.info("Test is ending!");
        DriverFactory.getInstance().removeDriver();
        softAssert.assertAll();
    }
}
