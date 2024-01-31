package test;

import org.testng.annotations.Test;
import pages.LoginPage;
import pages.MainPage;
import testdata.DataClass;

import java.lang.reflect.Method;

import static framework.utils.extentreports.ExtentTestManager.startTest;

public class TestClass extends Base{
    @Test(dataProvider = "UsersJSONData", dataProviderClass = DataClass.class, priority = 1)
    void loginWithWrongCredentials(String user, String pass, String error, Method method){
        startTest(method.getName(), String.format("Getting error: %s, using user: %s, and password: %s", error, user, pass));

        actualPage = getInstance(LoginPage.class)
                .loginAs(user)
                .withPassword(pass)
                .andRememberMe(true)
                .login();
        actualPage = getInstance(LoginPage.class);
        actualPage.as(LoginPage.class).verifyErrorText(error);
    }

    @Test(dataProvider = "UsersJSONData", dataProviderClass = DataClass.class, priority = 2)
    void loginWithRightCredentials(String user, String pass, Method method){
        startTest(method.getName(), String.format("Login Using right credentials user: %s, and password: %s", user, pass));

        actualPage = getInstance(LoginPage.class)
                .loginAs(user)
                .withPassword(pass)
                .andRememberMe(true)
                .login()
                .goToDashboard();

        actualPage.as(MainPage.class).verifySchoolName();
    }


}
