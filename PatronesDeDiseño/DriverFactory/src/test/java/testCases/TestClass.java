package testCases;

import org.testng.annotations.Test;

public class TestClass extends BaseTest{
    @Test(priority = 1)
    void loginWithRightCredentials(){
        loginPage.login(userCredentials.get("username"), userCredentials.get("userpass"));
        loginPage.verifySchoolName();
    }

    @Test(priority = 2)
    void createNewStudent(){
        studentsPage.clickStudents();
        studentsPage.clickCreateNew();
        studentsPage.studentPersonalDetails(studentPersonalDetails);
        studentsPage.accountInformation(studentAccountInfo);
        studentsPage.schoolDetails("088");
        studentsPage.validateStudentIsAdded(studentPersonalDetails[1]);

    }

}
