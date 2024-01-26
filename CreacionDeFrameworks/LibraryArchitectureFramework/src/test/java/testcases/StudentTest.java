package testcases;

import org.testng.annotations.Test;

import java.lang.reflect.Method;

import static framework.utils.extentreports.ExtentTestManager.startTest;

public class StudentTest extends BaseTest{
    @Test
    void createNewStudent(Method method){
        startTest(method.getName(), "Verify new student is created");
        navigateToCreateNewStudent();
        studentPersonalDetails();
        studentAccountInformation();
        verifyUserIsAdded();

    }
}
