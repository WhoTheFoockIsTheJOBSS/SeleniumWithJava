package Ejemplos.modulo4;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;


public class EjemplosLogs {
    private static Logger log = LogManager.getLogger(EjemplosLogs.class.getName());
    WebDriver driver;
    String baseURL = "https://demosite.titaniuminstitute.com.mx/wp-login.php?loggedout=true&wp_lang=en_US";

    @BeforeTest
    void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get(baseURL);
        log.info("Opening Website: "+baseURL);
        driver.manage().window().maximize();
    }

    @BeforeMethod
    void checkDemoSiteHome(){
        log.info("Checar si estas en la pagina principal");
        Assert.assertTrue(driver.getTitle().contains("Log In"));
    }

    @Test(priority = 1)
    void testVerifyRegisterPage(){
        driver.findElement(By.xpath("*//a[contains(text(),'Register')]")).click();
        log.info("Checar if you are on register page");
        Assert.assertTrue(driver.getTitle().contains("Registration Form"));
    }

    @Test(priority = 2)
    void testVerifyLostPassPage(){
        try{
            driver.findElement(By.xpath("*//a[contains(text(),'Lost your passwo')]")).click();
            Assert.assertTrue(driver.getTitle().contains("Lost Password"));
        }catch (WebDriverException we){
            log.error(we.getMessage());
        }
    }

    @AfterMethod
    void navigateBack(){
        driver.navigate().back();
        log.info("Back in the navigation history");
    }

    @AfterTest
    void closeBrowser(){
        driver.quit();
        log.info("Browser Closed!");
    }
}
