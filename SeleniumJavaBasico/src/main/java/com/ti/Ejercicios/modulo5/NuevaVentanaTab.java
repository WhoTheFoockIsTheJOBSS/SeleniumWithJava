package com.ti.Ejercicios.modulo5;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;

public class NuevaVentanaTab {
    static WebDriver driver;
    static String demoSite = "https://demosite.titaniuminstitute.com.mx/wp-admin/admin.php?page=shc-dashboard";
    static String WPSchoolURL = "https://wpschoolpress.com";
    static String googleURL = "https://www.google.com/";

    public static void main(String[] args) {
        WebDriverManager.chromedriver().setup();
        try {
            driver = new ChromeDriver();
            driver.manage().window().maximize();
            driver.get(demoSite);
            String originalTab = driver.getWindowHandle();

            driver.switchTo().newWindow(WindowType.TAB);
            driver.navigate().to(WPSchoolURL);

            driver.switchTo().newWindow(WindowType.WINDOW);
            driver.navigate().to(googleURL);

            driver.switchTo().window(originalTab);
            driver.close();
            Thread.sleep(2000);

        } catch (WebDriverException we) {
            System.err.println(String.format("Problemas al iniciar el driver: %s", we.getMessage()));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            driver.quit();
        }
    }
}
