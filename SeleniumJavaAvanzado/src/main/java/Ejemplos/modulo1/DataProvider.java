package Ejemplos.modulo1;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class DataProvider {
    WebDriver driver;
    String baseURL = "https://demosite.titaniuminstitute.com.mx/wp-login.php?loggedout=true&wp_lang=en_US";

    @BeforeTest
    void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get(baseURL);
        driver.manage().window().maximize();
    }

    @AfterTest
    void closeBrowser(){
        driver.quit();
    }

    // Iterations x Parameters -> 3x2
    @org.testng.annotations.DataProvider(name = "LoginProvider")
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
