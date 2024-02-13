package testcases;

import io.github.bonigarcia.wdm.WebDriverManager;
import mobiles.MobileEmulators;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.LoginPage;

import java.util.HashMap;
import java.util.Map;

public class ResponsiveTesting {
    WebDriver  driver;
    LoginPage loginPage;
    Map<String, String> deviceMobEmu;
    String baseURL = "https://demosite.titaniuminstitute.com.mx/wp-login.php?loggedout=true&wp_lang=en_US";
    String userName = "admin";
    String password = "G3-ySzY%";

    @BeforeTest
    void setUp() {
        WebDriverManager.chromedriver().setup();
        deviceMobEmu = new HashMap<String, String>();
    }

    @AfterMethod
    void closeBrowser() { driver.quit(); }

    @Test(dataProviderClass = MobileEmulators.class, dataProvider = "mobileEmulations")
    public void responsiveTest(String emulation) {
        deviceMobEmu.put("deviceName", emulation);
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setExperimentalOption("mobileEmulation", deviceMobEmu);

        driver = new ChromeDriver(chromeOptions);
        driver.get(baseURL);
        loginPage = new LoginPage(driver);
        loginPage.loginAs(userName).withPassword(password).login();
        //WebElement goToDashboard = new WebDriverWait(driver, Duration.ofSeconds(15))
                //.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("wp-admin-bar-dashboard"))));
        //goToDashboard.click();
    }
}
