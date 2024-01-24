package com.ti.Ejercicios.modulo6;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;

public class KeyboardAndMouse {
    static WebDriver driver;
    static String demoSiteLogin = "https://demosite.titaniuminstitute.com.mx/wp-login.php?loggedout=true&wp_lang=en_US";

    public static void main(String[] args) {
        WebDriverManager.chromedriver().setup();
        try {
            driver = new ChromeDriver();
            driver.manage().window().maximize();
            driver.get(demoSiteLogin);

            WebElement txtUsername = driver.findElement(By.id("user_login"));
            WebElement btnLogin = driver.findElement(By.xpath("//input[contains(@value,'Log In')]"));

            Actions builder = new Actions(driver);

            Action actionsToLogin = builder
                    .moveToElement(txtUsername)
                    .click()
                    .sendKeys("admin")
                    .sendKeys(Keys.TAB)
                    .keyDown(Keys.SHIFT)
                    .sendKeys("g")
                    .keyUp(Keys.SHIFT)
                    .sendKeys("3-ySz")
                    .keyDown(Keys.SHIFT)
                    .sendKeys("y")
                    .keyUp(Keys.SHIFT)
                    .sendKeys("%")
                    .moveToElement(btnLogin)
                    .click()
                    .build();
            actionsToLogin.perform();

            WebElement dashboard = driver.findElement(By.id("wp-admin-bar-dashboard"));
            dashboard.click();

            driver.findElement(By.className("wpsp-userPic")).click();
            Thread.sleep(4000);

            WebElement linkSignOut = driver.findElement(By.xpath("*//a[contains(text(), 'Sign Out')]"));
            builder.click().build().perform();

            System.out.println("La prueba fue exitosa");

        } catch (NoSuchElementException nse) {
            System.out.println(String.format("No se encontro el Web Element " + nse.getMessage()));
        }catch (WebDriverException we) {
            System.err.println(String.format("Problemas al iniciar el driver: %s", we.getMessage()));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            driver.quit();
        }
    }
}
