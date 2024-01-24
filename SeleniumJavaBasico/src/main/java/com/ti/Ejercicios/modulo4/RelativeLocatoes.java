package com.ti.Ejercicios.modulo4;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import org.openqa.selenium.support.locators.RelativeLocator;

public class RelativeLocatoes {
    static WebDriver driver;
    static String demoSite= "https://demosite.titaniuminstitute.com.mx/wp-admin/admin.php?page=shc-dashboard";
    static String expResult = "Sorry, you are not allowed to access this page.";
    static String actResult = "";
    static String username = "admin";
    static String password = "G3-ySzY%";

    public static void main(String[] args) {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get(demoSite);

        // driver.findElement(By.id("user_login")).sendKeys(username);
        driver.findElement(RelativeLocator.with(By.tagName("input")).above(By.name("pwd"))).sendKeys(username);

        // driver.findElement(By.name("pwd")).sendKeys(password);
        driver.findElement(RelativeLocator.with(By.className("input")).below(By.id("user_login"))).sendKeys((password));

        // driver.findElement(By.cssSelector("#rememberme")).click();
        driver.findElement(RelativeLocator.with(By.tagName("input")).toLeftOf(By.xpath("//input[contains(@value,'Log In')]"))).click();

        // driver.findElement(By.xpath("//input[contains(@value,'Log In')]")).click();
        driver.findElement(RelativeLocator.with(By.tagName("input")).toRightOf(By.cssSelector("#rememberme"))).click();

        actResult = driver.findElement(By.cssSelector("div.wp-die-message")).getText();
        System.out.println(actResult.contains(expResult)?"Prueba superada "+actResult: "La prueba falló "+actResult);

        driver.quit();
    }
}
