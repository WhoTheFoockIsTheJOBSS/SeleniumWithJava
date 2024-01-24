package Ejemplos.modulo5;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class EjemploExtendReport {
    WebDriver driver;
    String baseURL = "https://demosite.titaniuminstitute.com.mx/wp-login.php?loggedout=true&wp_lang=en_US";
    String destination;
    String dateOption = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
    ExtentReports extent;
    ExtentSparkReporter tiSpark;

    public String getScreenshot(WebElement element, String screenshotName) throws IOException {
        destination = System.getProperty("user.dir") + "/Results/screenshots/" + screenshotName+dateOption+".png";
        FileUtils.copyFile((element == null?
                        ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE)
                        :element.getScreenshotAs(OutputType.FILE)),
                new File(destination));
        return destination;
    }

    @BeforeTest
    void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get(baseURL);
        driver.manage().window().maximize();
        extent = new ExtentReports();
        tiSpark = new ExtentSparkReporter("Results/TIReport.html");
        extent.attachReporter(tiSpark);
    }

    @BeforeMethod
    void checkDemoSiteHome() throws IOException {
        Assert.assertTrue(driver.getTitle().contains("Log In"));
        extent.createTest("checkDemoSiteHome")
                .pass(MediaEntityBuilder
                        .createScreenCaptureFromPath(getScreenshot(null, "demoSite")).build());
    }

    @Test
    void testVerifyRegisterPage() throws IOException {
        WebElement linkRegister = driver.findElement(By.xpath("*//a[contains(text(),'Register')]"));
        linkRegister.click();
        Assert.assertTrue(driver.getTitle().contains("Registration Form"));
        extent.createTest("testVerifyRegistraterPage").pass(String.valueOf(Status.PASS));
    }

    @Test
    void testVerifyLastPassPage() throws IOException {
        try{
            driver.findElement(By.linkText("Lost your passw")).click();
            Assert.assertTrue(driver.getTitle().contains("Lost Password"));
        } catch (WebDriverException we){
            extent.createTest("testVerifyLastPassPage").fail(we.getMessage());
        }
    }

    @AfterMethod
    void navigateBack(){
        driver.navigate().back();
    }

    @AfterTest
    void closeBrowser(){
        driver.quit();
        extent.flush();
    }
}
