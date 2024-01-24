package com.ti.Ejercicios.modulo5;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;

public class ManipulacionVentana {
    static WebDriver driver;
    static String demoSite= "https://demosite.titaniuminstitute.com.mx/wp-admin/admin.php?page=shc-dashboard";

    public static void main(String[] args) {
        WebDriverManager.chromedriver().setup();
        try {
            driver = new ChromeDriver();
            driver.get(demoSite);
            getSizeAndPosition();
            setSizeAndPosition();
            getSizeAndPosition();
            wait(2);

            driver.manage().window().maximize();
            wait(2);

            driver.manage().window().minimize();
            wait(2);

            driver.manage().window().fullscreen();
        } catch (WebDriverException we) {
            System.err.println(String.format("Problemas al iniciar el driver: %s", we.getMessage()));
        } finally {
            driver.quit();
        }
    }

    static void getSizeAndPosition(){
        Dimension size = driver.manage().window().getSize();
        System.out.println(String.format("La atura de la venta es de %d pixeles", size.getHeight()));
        System.out.println(String.format("El ancho de la venta es de %d pixeles", size.getWidth()));

        Point position = driver.manage().window().getPosition();
        System.out.println(String.format("La posicion en X es %d", position.getX()));
        System.out.println(String.format("La posicion en Y es %d", position.getY()));
    }

    static void setSizeAndPosition(){
        driver.manage().window().setSize(new Dimension(1024, 768));
        driver.manage().window().setPosition(new Point(0,0));
    }

    static void wait(int seg) {
        try{
            Thread.sleep(seg*1000);
        } catch (InterruptedException ie){
            System.err.println("Falla en el sleeper :c");
        }
    }
}
