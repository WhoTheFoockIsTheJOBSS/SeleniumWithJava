package com.ti.Ejercicios.modulo5;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Forms {
    static WebDriver driver;
    static String demoSiteLogin = "https://demosite.titaniuminstitute.com.mx/wp-login.php?loggedout=true&wp_lang=en_US";
    static String demoSite = "https://demosite.titaniuminstitute.com.mx/wp-admin/admin.php?page=shc-dashboard";
    static String username = "admin";
    static String password = "G3-ySzY%";

    public static void main(String[] args) {
        WebDriverManager.chromedriver().setup();
        try {
            driver = new ChromeDriver();
            driver.manage().window().maximize();

            driver.get(demoSiteLogin);

            login();

            createNewTeacher();

        } catch (NoSuchElementException nse) {
            System.out.println(String.format("No se encontro el Web Element " + nse.getMessage()));
        }catch (TimeoutException te) {
            System.out.println(String.format("Tiempo de espera exedido " + te.getMessage()));
        }catch (WebDriverException we) {
            System.err.println(String.format("Problemas al iniciar el driver: %s", we.getMessage()));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
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

    static void createNewTeacher() throws InterruptedException {
        WebElement dashboardLink = new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(driver -> driver.findElement(By.id("wp-admin-bar-dashboard")));
        dashboardLink.click();

        WebElement spnTeacher = new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(driver -> driver.findElement(By.xpath("(*//span[contains(text(),'Teach')])[1]")));
        spnTeacher.click();

        driver.findElement(By.cssSelector(".wpsp-btn.administrator")).click();

        personalDetails();

        accountInfo();

        Thread.sleep(2000);
    }

    private static void personalDetails() {
        WebElement rdbFemale = new WebDriverWait(driver, Duration.ofSeconds(6))
                .until(driver -> driver.findElement(By.id("Female")));
        rdbFemale.click();

        WebElement txtFirstName = driver.findElement(By.id("firstname"));
        txtFirstName.clear();
        txtFirstName.sendKeys("Loren");

        WebElement txtLastName = driver.findElement(By.id("lastname"));
        txtLastName.clear();
        txtLastName.sendKeys("Miles");

        WebElement txtStreet = driver.findElement(By.name("Address"));
        txtStreet.clear();
        txtStreet.sendKeys("Test Address");

        Select drpCountry = new Select(driver.findElement(By.id("Country")));
        drpCountry.selectByVisibleText("Brazil");

    }
    private static void accountInfo() {
        String pass = "T3stPAss.";

        WebElement txtEmail = driver.findElement(By.id("Email"));
        txtEmail.clear();
        txtEmail.sendKeys("test@email.com");

        WebElement txtUsername = driver.findElement(By.id("Username"));
        txtUsername.clear();
        txtUsername.sendKeys("lmiles");
        System.out.println(txtUsername.getAttribute("value"));

        WebElement txtPassword = driver.findElement(By.id("Password"));
        txtPassword.clear();
        txtPassword.sendKeys(pass);

        WebElement txtConfirmPass = driver.findElement(By.id("ConfirmPassword"));
        txtConfirmPass.clear();
        txtConfirmPass.sendKeys(pass);

        WebElement txtWorkingHour = driver.findElement(By.id("whours"));
        txtWorkingHour.clear();
        txtWorkingHour.sendKeys("24");
        txtWorkingHour.submit();
    }
}
