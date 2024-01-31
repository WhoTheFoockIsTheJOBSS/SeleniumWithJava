package testcases;

import framework.config.PropertyManager;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

import static framework.utils.extentreports.ExtentTestManager.startTest;

public class LoginTest extends BaseTest{
    private static String caseId = PropertyManager.getInstance().getProperty("LoginTestId");
    @Test
    void loginWithRightCredentials(Method method){
        startTest(method.getName(), "Login using right credentials");
        verifySchoolTitle();
        caseModel.setCaseId(Integer.parseInt(caseId));
    }
}
