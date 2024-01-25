package testcases;

import framework.base.BrowserType;
import framework.base.DriverFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import pages.LoginPage;
import pages.NavPage;
import pages.StudentsPage;

import java.util.HashMap;
import java.util.Map;

public class BaseTest {
    String baseURL = "https://demosite.titaniuminstitute.com.mx/wp-login.php?loggedout=true&wp_lang=en_US";
    Map<String, String> userCredentials = new HashMap<>();
    Map<String, String> studentAccountInfo = new HashMap<>();
    String[] studentPersonalDetails = {"Female", "TestStudent", "StudentLasName", "14", "TestAddress"};
    StudentsPage studentsPage;
    LoginPage loginPage;
    NavPage navPage;

    @BeforeTest
    @Parameters("browser")
    void setup(String browser){
        //Arrange (configuracion inicial de cada una de mis pruebas)
        DriverFactory.getInstance().setDriver(BrowserType.valueOf(browser));
        DriverFactory.getInstance().getDriver().navigate().to(baseURL);

        userCredentials.put("username", "admin");
        userCredentials.put("userpass", "G3-ySzY%");

        studentAccountInfo.put("email","test1@email.com");
        studentAccountInfo.put("user","testuser");
        studentAccountInfo.put("password","test123");

        loginPage = new LoginPage();
    }

    @AfterTest
    void TurnDown(){
        studentsPage.deleteStudent();
        DriverFactory.getInstance().removeDriver();
    }
}
