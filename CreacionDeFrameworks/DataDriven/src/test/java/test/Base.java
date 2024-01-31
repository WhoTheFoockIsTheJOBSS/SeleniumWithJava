package test;

import framework.base.BasePage;
import framework.base.BrowserType;
import framework.base.DriverFactory;
import framework.config.PropertyManager;
import framework.utils.logs.Log;
import framework.utils.video.VideoRecorder;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import java.sql.SQLException;

import static framework.config.Constants.BASE_URL;
import static framework.config.Constants.VIDEO_FOLDER;
import static framework.utils.data.SQLUtils.closeConnection;
import static framework.utils.logs.Log.log;

public class Base extends BasePage {
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
    }

    @AfterTest
    void TurnDown() throws SQLException {
        Log.info("Test is ending!");
        DriverFactory.getInstance().removeDriver();
        closeConnection();
    }
}
