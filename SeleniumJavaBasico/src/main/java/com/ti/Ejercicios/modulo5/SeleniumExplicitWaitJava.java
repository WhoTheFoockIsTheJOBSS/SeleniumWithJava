package com.ti.Ejercicios.modulo5;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SeleniumExplicitWaitJava {
    static WebDriver driver;
    static String demoSite = "https://demosite.titaniuminstitute.com.mx/wp-admin/admin.php?page=shc-dashboard";
    static String username = "Jobss";
    static String password = "T1D3m0S1T3";
    static WebDriverWait wait;

    public static void main(String[] args) {
        WebDriverManager.chromedriver().setup();
        try {
            driver = new ChromeDriver();
            driver.manage().window().maximize();
            driver.get(demoSite);

            wait = new WebDriverWait(driver, Duration.ofSeconds(5));

            wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("user_login"))));
            WebElement txtUsername = driver.findElement(By.id("user_login"));
            txtUsername.clear();
            txtUsername.sendKeys(username);

            WebElement txtPassword = driver.findElement(By.name("pwd"));
            txtPassword.clear();
            txtPassword.sendKeys(password);

            WebElement btnLogin = driver.findElement(By.xpath("//input[contains(@value,'Log In')]"));
            btnLogin.click();

            WebElement spnStudents = new WebDriverWait(driver, Duration.ofSeconds(6)).until(driver -> driver.findElement(By.cssSelector("li.has-submenu")));
            spnStudents.click();

        } catch (NoSuchElementException nse) {
            System.out.println(String.format("No se encontro el Web Element " + nse.getMessage()));
        }catch (TimeoutException te) {
            System.out.println(String.format("Tiempo de espera exedido " + te.getMessage()));
        }catch (WebDriverException we) {
            System.err.println(String.format("Problemas al iniciar el driver: %s", we.getMessage()));
        } finally {
            driver.quit();
        }
    }
}
