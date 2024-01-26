package testcases;

import static framework.utils.extentreports.ExtentTestManager.startTest;

import java.lang.reflect.Method;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest{
    @Test
    void loginWithRightCredentials(Method method){
        startTest(method.getName(), "Login using right credentials");
        verifySchoolTitle();
    }
}
