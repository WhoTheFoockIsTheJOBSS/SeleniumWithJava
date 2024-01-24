package com.ti.Ejercicios.modulo3;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class PrimerScript {
    static WebDriver driver;
    static java.lang.String demoSite= "https://demosite.titaniuminstitute.com.mx/wp-admin/admin.php?page=shc-dashboard";
    static java.lang.String actResult = "";
    static java.lang.String expResult = "TI Demo Site";

    public static void main(String[] args) {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get(demoSite);

        actResult = driver.getTitle();
        System.out.println(actResult.contains(expResult)?"Prueba superada "+actResult: "La prueba falló "+actResult);

        // Cerrar navegador
        driver.quit();

    }
}
