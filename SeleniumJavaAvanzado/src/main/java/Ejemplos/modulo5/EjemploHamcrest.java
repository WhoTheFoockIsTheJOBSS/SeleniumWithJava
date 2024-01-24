package Ejemplos.modulo5;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import java.lang.reflect.Method;
import java.time.Duration;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.*;

public class EjemploHamcrest {
    WebDriver driver;
    String baseURL = "https://demosite.titaniuminstitute.com.mx/wp-login.php?loggedout=true&wp_lang=en_US";
    String userName = "admin";
    String password = "G3-ySzY%";

    //Recovery Scenario
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

    @DataProvider(name = "SchoolProvider")
    Object[][] getSchoolDate(Method method){
        if (method.getName().equals("testToCreateClass")){
            return new Object[][]{
                    {"Art", "Art001", "20", "Christopher Matte Kenniff", "Free"},
                    {"Electronics", "Art001", "20", "Christopher Matte Kenniff", "Free"}
            };
        } else if (method.getName().equals("testToCreateSubject")){
            return new Object[][]{
                    {"wpsp standard-1", "Math", "Math01", "Wolfie Gallahue", "The Maths Book"},
                    {"wpsp standard-2", "French", "Lang001", "Judye Duhig", "Speak French"},
                    {"wpsp standard-3", "Computer Science", "CCs03", "Adam Hodgson", "Introduction to Computer Science"},
            };
        }

        return null;
    }

    @BeforeMethod
    void goToClassesMenu(){
        WebElement mnClasses = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("(//*[text()='Classes'])[1]"))));
        new Actions(driver).moveToElement(mnClasses).click().build().perform();
        preloading();
    }

    @Test(description = "Creacion de una Clase", dataProvider = "SchoolProvider", enabled = true)
        // void testToCreateClass(String nameClass, String codeClass, String capacity, String teacher, String feeType){}
    void testToCreateClass(String ... classInfo){
        WebElement classesTab = new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("(//*[text()='Classes'])[2]"))));
        classesTab.click();
        preloading();

        WebElement createNewBtn = new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//*[text()=' Create New']"))));
        try{
            createNewBtn.click();
        } catch(ElementClickInterceptedException ecie){
            ((JavascriptExecutor)driver).executeScript("window.scrollby(0,-250)");
            createNewBtn.click();
        }
        preloading();

        //Class Name
        WebElement txtClassName = new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOf(driver.findElement(By.name("Name"))));
        txtClassName.clear();
        txtClassName.sendKeys(classInfo[0]);
        assertThat(classInfo[0], equalTo(txtClassName.getAttribute("value")));
        assertThat(classInfo[0], is(txtClassName.getAttribute("value")));

        //Class Number
        driver.findElement(By.name("Number")).sendKeys(classInfo[1]);
        //Class Capacity
        driver.findElement(By.id("c_capacity")).sendKeys(classInfo[2]);
        // Teacher
        new Select(driver.findElement(By.name("ClassTeacherID"))).selectByVisibleText(classInfo[3]);
        // Start Date
        WebElement dtpStartClass = driver.findElement(By.name("Sdate"));
        new Actions(driver).moveToElement(dtpStartClass).click().build().perform();
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(driver -> driver.findElement(By.xpath("//td[contains(@class,'today')]"))).click();
        // Fee type
        new Select(driver.findElement(By.name("classfeetype"))).selectByVisibleText(classInfo[4]);

        System.out.println("Soft assert method was executed!");
    }

    @Test(description = "Crear una materia", dataProvider = "SchoolProvider", enabled = true)
    void testToCreateSubject(String className, String subjectName, String subjectCode, String subjectTeacher, String book){
        WebElement subjectTab = new WebDriverWait(driver, Duration.ofSeconds(8))
                .until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//*[text()='Subjects']"))));
        subjectTab.click();
        preloading();

        WebElement createNewBtn = new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//*[text()=' Create New']"))));
        try{
            createNewBtn.click();
        } catch(ElementClickInterceptedException ecie){
            ((JavascriptExecutor)driver).executeScript("window.scrollby(0,-250)");
            createNewBtn.click();
        }
        preloading();

        WebElement drpClass = new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(driver->driver.findElement(By.id("SCID")));
        new Select(drpClass).selectByVisibleText(className);
        assertThat(drpClass.getText(), containsString(className));

        //Subject Name
        driver.findElement(By.name("SNames[]")).sendKeys(subjectName);
        //Subject Code
        WebElement txtSubjectCode = driver.findElement(By.name("SCodes[]"));
        //assertThat(txtSubjectCode.getAttribute("value"), is(not(equalTo("Subject Text"))));
        assertThat(txtSubjectCode.getAttribute("value"), is(equalTo("Subject Text")));

        //Teacher
        new Select(driver.findElement(By.name("STeacherID[]"))).selectByVisibleText(subjectTeacher);
        //Book name
        driver.findElement(By.name("BNames[]")).sendKeys(book);
    }
}
