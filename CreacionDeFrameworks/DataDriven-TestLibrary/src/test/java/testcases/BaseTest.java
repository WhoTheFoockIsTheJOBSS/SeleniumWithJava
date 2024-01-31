package testcases;

import framework.base.BrowserType;
import framework.base.FrameworkException;
import framework.base.LocalDriverFactory;
import framework.config.PropertyManager;
import framework.utils.logs.Log;
import framework.utils.testrail.CaseModel;
import framework.utils.video.VideoRecorder;
import library.TestLibrary;
import org.testng.ITestResult;
import org.testng.annotations.*;
import pages.StudentsPage;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static framework.config.Constants.*;
import static framework.utils.logs.Log.log;
import static framework.utils.testrail.TestRailUtil.addResultforTestCase;

public class BaseTest extends TestLibrary {
    protected static VideoRecorder videoRec;
    String baseURL = "https://demosite.titaniuminstitute.com.mx/wp-login.php?loggedout=true&wp_lang=en_US";
    CaseModel caseModel;
    protected int i;

    @BeforeTest
    @Parameters("browser")
    void setup(String browser) {
        i = 1;
        LocalDriverFactory.getInstance().setDriver(BrowserType.valueOf(browser));
        if (Boolean.parseBoolean(PropertyManager.getInstance().getProperty("Video"))){
            videoRec = new VideoRecorder();
            videoRec.startRecording(VIDEO_FOLDER);
        }
        LocalDriverFactory.getInstance().getDriver().navigate().to(baseURL);
        log.info("Test is starting!");
        login();
    }

    @BeforeMethod
    void caseInstance(){caseModel = new CaseModel();}

    @AfterMethod
    void checkResult(ITestResult iTestResult) throws FrameworkException, IOException {
        Map<Object, Object> results = new HashMap<>();
        results.put(ITestResult.SUCCESS, TEST_CASE_PASSED_STATUS);
        results.put(ITestResult.FAILURE, TEST_CASE_FAILED_STATUS);

        caseModel.setStatusId((int)results.get(iTestResult.getStatus()));
        caseModel.setComment(caseModel.getStatusId() == 1?
                iTestResult.getName() + "Passed! in iteration " + i:
                iTestResult.getName() + "Failed! in iteration " + i);
        addResultforTestCase(caseModel);
    }

    @AfterTest
    void TurnDown() throws IOException {
        actualPage.as(StudentsPage.class).deleteStudent();
        LocalDriverFactory.getInstance().removeDriver();
        Log.info("Test is ending!");
        if (videoRec!=null){
            videoRec.stopRecording();
        }
    }
}
