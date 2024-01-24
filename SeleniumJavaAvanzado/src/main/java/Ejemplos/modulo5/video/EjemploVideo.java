package Ejemplos.modulo5.video;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.monte.media.Format;
import org.monte.media.FormatKeys;
import org.monte.media.math.Rational;
import org.monte.screenrecorder.ScreenRecorder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.File;
import java.io.IOException;

import static org.monte.media.FormatKeys.*;
import static org.monte.media.VideoFormatKeys.*;

public class EjemploVideo {
    private ScreenRecorder screenRecorder;
    WebDriver driver;
    String baseURL = "https://demosite.titaniuminstitute.com.mx/wp-login.php?loggedout=true&wp_lang=en_US";

    private  void stopRecording() throws IOException {
        this.screenRecorder.stop();
    }

    private void  startRecording(String videoPath) throws IOException, AWTException {
        File file = new File(videoPath);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Rectangle captureSize = new Rectangle(0,0, screenSize.width, screenSize.height);
        GraphicsConfiguration rc = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration();
        this.screenRecorder = new SpecializedScreenRecorder(rc, captureSize,
                new Format(MediaTypeKey, MediaType.FILE, MimeTypeKey, MIME_AVI),
                new Format(MediaTypeKey, MediaType.VIDEO, EncodingKey, ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE,
                        CompressorNameKey, ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE, DepthKey, 24, FrameRateKey, Rational.valueOf(15),
                        QualityKey, 1.0f, KeyFrameIntervalKey, 15*60),
                new Format(MediaTypeKey, MediaType.VIDEO, EncodingKey, "black", FrameRateKey, Rational.valueOf(30)),
                null, file, "ScreenRecorder");
        this.screenRecorder.start();

    }

    @BeforeTest
    void setup() throws IOException, AWTException {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        startRecording(System.getProperty("user.dir")+"/Result/video/");
        driver.get(baseURL);
        driver.manage().window().maximize();
    }

    @AfterTest
    void closeBrowser() throws IOException {
        stopRecording();
        driver.quit();
    }

    // Iterations x Parameters -> 3x2
    @DataProvider(name = "LoginProvider")
    Object[][] getDataFromDataProvider(){
        return new Object[][]{
                {"admin", "G3-ySzY%"},
                {"Jobss", "T1D3m0S1T3"},
                {"admin", "12345"}
        };
    }

    @Test(description = "Test case de varios usuarios", dataProvider = "LoginProvider")
    void loginTest(String userName, String password){
        WebElement txtLoginUserName = driver.findElement(By.id("user_login"));
        txtLoginUserName.clear();
        txtLoginUserName.sendKeys(userName);

        WebElement txtLoginPassword = driver.findElement(By.name("pwd"));
        txtLoginPassword.clear();
        txtLoginPassword.sendKeys(password);

        Assert.assertTrue(txtLoginUserName.getAttribute("value").contains(userName));
    }
}
