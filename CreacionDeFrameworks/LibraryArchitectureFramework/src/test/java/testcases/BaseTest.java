package testcases;

import framework.base.BrowserType;
import framework.base.DriverFactory;
import framework.config.PropertyManager;
import framework.utils.logs.Log;
import framework.utils.video.VideoRecorder;
import library.TestLibrary;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import pages.StudentsPage;

import java.io.IOException;

import static framework.config.Constants.VIDEO_FOLDER;
import static framework.utils.logs.Log.log;

public class BaseTest extends TestLibrary {
    protected static VideoRecorder videoRec;
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
        login();
    }

    @AfterTest
    void TurnDown() throws IOException {
        actualPage.as(StudentsPage.class).deleteStudent();
        DriverFactory.getInstance().removeDriver();
        Log.info("Test is ending!");
        if (videoRec!=null){
            videoRec.stopRecording();
        }
    }
}
