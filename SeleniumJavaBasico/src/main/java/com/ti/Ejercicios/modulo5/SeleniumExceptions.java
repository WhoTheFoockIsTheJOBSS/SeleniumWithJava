package com.ti.Ejercicios.modulo5;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class SeleniumExceptions {
    static WebDriver driver;
    static String demoSite= "https://demosite.titaniuminstitute.com.mx/wp-admin/admin.php?page=shc-dashboard";

    public static void main(String[] args) {

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get(demoSite);

        try{
            driver.findElement(By.id("user.log"));
            System.out.println("Prueba exitosa");
        } catch (NoSuchElementException nse){
            System.out.println("Prueba fallida. El elemnto no se encontro en el DOM " + nse.getMessage());
        } finally {
            driver.quit();
        }
    }
}
