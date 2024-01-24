package com.ti.Ejercicios.modulo5;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class VentanasYPestañas {
    static WebDriver driver;
    static String demoSite = "https://demosite.titaniuminstitute.com.mx/wp-admin/admin.php?page=shc-dashboard";
    static String username = "Jobss";
    static String password = "T1D3m0S1T3";

    public static void main(String[] args) {
        WebDriverManager.chromedriver().setup();
        try {
            driver = new ChromeDriver();
            driver.manage().window().maximize();
            driver.get(demoSite);


            // almacenar el ID de la ventana original
            String originalTab = driver.getWindowHandle();

            // revisar si no existe otra ventana
            System.out.println(driver.getWindowHandles().size() == 1 ? "Unca ventana/tab abierta":"Existe otra ventana/tab abierta");

            login();

            WebElement faceBook = driver.findElement(By.cssSelector(".backuply_button.backuply_button3"));
            faceBook.click();

            // Recorrer hasta encontrar una nueva ventana/tab
            for (String windowHandle : driver.getWindowHandles()) {
                if (!originalTab.equals(windowHandle)) {
                    driver.switchTo().window(windowHandle);
                    break;
                }
            }
            Thread.sleep(6000);

            WebElement cerrar = driver.findElement(By.cssSelector("div[aria-label='Cerrar']"));
            cerrar.click();
            Thread.sleep(2000);

        } catch (WebDriverException we) {
            System.err.println(String.format("Problemas al iniciar el driver: %s", we.getMessage()));
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        } finally {
            driver.quit();
        }
    }

    static void login(){
        WebElement txtUsername = driver.findElement(By.id("user_login"));
        txtUsername.clear();
        txtUsername.sendKeys(username);

        WebElement txtPassword = driver.findElement(By.name("pwd"));
        txtPassword.clear();
        txtPassword.sendKeys(password);

        WebElement btnLogin = driver.findElement(By.xpath("//input[contains(@value,'Log In')]"));
        btnLogin.click();
    }
}
