package com.ti.Ejercicios.modulo4;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Locators {
    static WebDriver driver;
    static String demoSite= "https://demosite.titaniuminstitute.com.mx/wp-admin/admin.php?page=shc-dashboard";
    static String expResult = "Sorry, you are not allowed to access this page.";
    static String actResult = "";
    static String username = "admin";
    static String password = "G3-ySzY%";

    public static void main(String[] args) throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get(demoSite);

        driver.findElement(By.id("user_login")).sendKeys(username);
        driver.findElement(By.name("pwd")).sendKeys(password);
        driver.findElement(By.cssSelector("#rememberme")).click();
        driver.findElement(By.xpath("//input[contains(@value,'Log In')]")).click();

        actResult = driver.findElement(By.cssSelector("div.wp-die-message")).getText();
        System.out.println(actResult.contains(expResult)?"Prueba superada "+actResult: "La prueba falló "+actResult);

        Thread.sleep(2000);

        driver.quit();
    }
}
