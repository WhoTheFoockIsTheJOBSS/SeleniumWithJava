package testcases;

import org.testng.annotations.Test;

public class TestClass extends BaseTest{
    @Test(priority = 1)
    void loginWithRightCredentials(){
        //Act (pasos y acciones que hacemos durante el test)
        loginPage
                .loginAs(userCredentials.get("username"))
                .withPassword(userCredentials.get("userpass"))
                .andRememberMe(true)
                .login();
        //Instanciacion de la pagina Nav
        //navigate = new NavPage();
        navPage = loginPage.goToDashboard();

        //Assert (se realizan las verificaciones y aserciones correpondientes)
        loginPage.verifySchoolName();
    }

    @Test(priority = 2)
    void createNewStudent(){
        //Act
        //StudentsPage = new StudentsPage();
        //Instanciacion de la pagina Students
        studentsPage = navPage.goToStudents();

        studentsPage.andCreateNew();
        studentsPage
                .genderAs(studentPersonalDetails[0])
                .withFistName(studentPersonalDetails[1])
                .withLastName(studentPersonalDetails[2])
                .withDayOfBirth(studentPersonalDetails[3])
                .andCurrentAddress(studentPersonalDetails[4]);
        studentsPage
                .userEmail(studentAccountInfo.get("email"))
                .withUserName(studentAccountInfo.get("user"))
                .withPassword(studentAccountInfo.get("password"))
                .andConfirmPass(studentAccountInfo.get("password"));
        studentsPage.schoolDetails("088");

        //Assert
        studentsPage.validateStudentIsAdded(studentPersonalDetails[1]);

    }

}
