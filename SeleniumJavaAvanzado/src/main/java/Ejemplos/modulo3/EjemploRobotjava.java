package Ejemplos.modulo3;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.time.Duration;

public class EjemploRobotjava {
    WebDriver driver;
    String baseURL = "https://demosite.titaniuminstitute.com.mx/wp-login.php?loggedout=true&wp_lang=en_US";
    String userName = "admin";
    String password = "G3-ySzY%";
    String imagePath = "C:\\Users\\jobss\\Downloads";
    String imageFile = "niro_taxi_driver.jpg";
    Robot robot;

    void preloading(){
        WebElement dvPreloading = driver.findElement(By.className("wpsp-preLoading"));
        try{
            new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.invisibilityOf(dvPreloading));
        } catch(TimeoutException te){
            driver.navigate().refresh();
            preloading();
        }
    }

    @BeforeTest
    void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get(baseURL);
        driver.manage().window().maximize();

        WebElement txtLoginUserName = driver.findElement(By.id("user_login"));
        txtLoginUserName.clear();
        txtLoginUserName.sendKeys(userName);

        WebElement txtLoginPassword = driver.findElement(By.name("pwd"));
        txtLoginPassword.clear();
        txtLoginPassword.sendKeys(password);

        driver.findElement(By.xpath("*//input[contains(@value, 'Log')]")).click();

        WebElement goToDashboard = new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(ExpectedConditions.visibilityOf(driver.findElement(By.id("wp-admin-bar-dashboard"))));
        goToDashboard.click();

        preloading();
    }

    @AfterTest
    void closeBrowser(){
        driver.quit();
    }

    @Test
    void robotExecution() throws AWTException {
        WebElement spnTeacher = new  WebDriverWait(driver, Duration.ofSeconds(7))
                .until(driver -> driver.findElement(By.xpath("(//span[contains(text(),'Teachers')])[1]")));
        spnTeacher.click();
        preloading();

        driver.findElement(By.xpath("*//a[contains(text(), ' Create New')]")).click();
        preloading();

        robot = new Robot();

        //x=412, y=630 Icono de Camara
        moveAndClick(412, 700);

        //copiar y pegar path
        moveAndClick(241,51);
        selectFromClipboard(imagePath);
        robot.keyRelease(KeyEvent.VK_ENTER);

        //dropdown de extenciones y seleccionar todos los archivos
        moveAndClick(590,415);
        moveAndClick(555, 455);

        //pegar el nombre del archivo y pulsar enter
        moveAndClick(380,417);
        selectFromClipboard(imageFile);
        robot.keyRelease(KeyEvent.VK_ENTER);

        robot.delay(3000);
    }

    //Metodo donde agreguemos un string como parametro y se agregue al clipboard
    private void selectFromClipboard(String elementForClipboard){
        StringSelection stringSelection = new StringSelection(elementForClipboard);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);

        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_ENTER);
    }

    private void moveAndClick (int x, int y){
        robot.mouseMove(x, y);
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
        robot.delay(800);
    }
}
