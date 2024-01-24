package com.ti.Ejercicios.modulo5;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;

public class SeleniumNavigation {

    static WebDriver driver;
    static String googleSite = "https://www.google.com";
    static String demoSite= "https://demosite.titaniuminstitute.com.mx/wp-admin/admin.php?page=shc-dashboard";

    public static void main(String[] args) {
        WebDriverManager.chromedriver().setup();

        try{
            driver = new ChromeDriver();

            driver.navigate().to(googleSite);
            System.out.println(String.format("El titulo de la pagina es: %s", driver.getTitle()));
            System.out.println(String.format("La URL de la pagina es: %s", driver.getCurrentUrl()));
            wait(2);

            System.out.println("----------------------------------------------------------------------");

            driver.get(demoSite);
            System.out.println(String.format("El titulo de la pagina es: %s", driver.getTitle()));
            System.out.println(String.format("La URL de la pagina es: %s", driver.getCurrentUrl()));
            System.out.println(String.format("El codigo fuente de la pagina es: %s", driver.getPageSource()));
            wait(2);

            driver.navigate().back();
            wait(2);

            driver.navigate().forward();
            wait(2);

            driver.navigate().refresh();

        } catch (WebDriverException we) {
            System.err.println(String.format("Problemas al iniciar el driver: %s", we.getMessage()));
        }finally {
            driver.quit();
        }

    }

    static void wait(int seg) {
        try{
            Thread.sleep(seg*1000);
        } catch (InterruptedException ie){
            System.err.println("Falla en el sleeper :c");
        }
    }
}
