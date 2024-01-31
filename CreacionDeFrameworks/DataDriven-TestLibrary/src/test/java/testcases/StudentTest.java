package testcases;

import framework.config.PropertyManager;
import org.testng.annotations.Test;
import testdata.DataClass;

import java.lang.reflect.Method;

import static framework.utils.extentreports.ExtentTestManager.startTest;

public class StudentTest extends BaseTest{

    private static String caseId = PropertyManager.getInstance().getProperty("AddStudentTestId");

    @Test(dataProviderClass = DataClass.class, dataProvider = "StudentsJSONDate")
    void createNewStudent(Method method, String... studentInfo){
        startTest(method.getName(), "Verify new student is created");
        navigateToCreateNewStudent();
        studentPersonalDetails(studentInfo[0], studentInfo[1], studentInfo[2], studentInfo[3], studentInfo[4]);
        studentAccountInformation(studentInfo[5], studentInfo[6], studentInfo[7], studentInfo[8]);
        verifyUserIsAdded();
        caseModel.setCaseId(Integer.parseInt(caseId));
    }
}
