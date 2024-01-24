package com.ti.Ejercicios.modulo5;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class FramesAlerts {
    static WebDriver driver;
    static String demoSite = "https://www.w3schools.com/js/tryit.asp?filename=tryjs_prompt";
    static WebDriverWait wait;

    public static void main(String[] args) {
        WebDriverManager.chromedriver().setup();
        try {
            driver = new ChromeDriver();
            driver.manage().window().maximize();
            driver.get(demoSite);
            wait = new WebDriverWait(driver, Duration.ofSeconds(5));

            // si tuveramos un implicit Wait driver.switchTo().frame("iframeResult")
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("iframeResult"));

            WebElement btnTry = driver.findElement(By.xpath("*//button[text()='Try it']"));
            wait.until(ExpectedConditions.elementToBeClickable(btnTry));
            btnTry.click();

            Thread.sleep(1500);

            wait.until(ExpectedConditions.alertIsPresent());
            Alert windowAlert = driver.switchTo().alert();
            String alertText = windowAlert.getText();
            System.out.println(alertText);
            windowAlert.sendKeys("El Jobss");
            windowAlert.accept();

            String demoText = driver.findElement(By.id("demo")).getText();
            System.out.println(demoText.contains("El Jobss")?demoText:"Prueba fallida :c");


        } catch (NoSuchElementException nse) {
            System.out.println("No se encontro el Web Element "+nse.getMessage());
        }catch (NoSuchFrameException nsfe) {
            System.out.println("No se encontro el Frame "+nsfe.getMessage());
        }catch (NoAlertPresentException nape) {
            System.out.println("No se encontro la alerta "+nape.getMessage());
        }catch (TimeoutException te) {
            System.out.println("Tiempo de espera exedido "+te.getMessage());
        }catch (WebDriverException we) {
            System.err.println("Problemas al iniciar el driver "+we.getMessage());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }
}
