package library;

import framework.base.BasePage;
import pages.LoginPage;
import pages.MainPage;
import pages.NavPage;
import pages.StudentsPage;

import java.util.HashMap;
import java.util.Map;


public class TestLibrary extends BasePage {
    Map<String, String> userCredentials = new HashMap<>();
    Map<String, String> studentAccountInfo = new HashMap<>();
    String[] studentPersonalDetails = {"Female", "TestStudent", "StudentLasName", "14", "TestAddress"};

    public void login(){
        userCredentials.put("username", "admin");
        userCredentials.put("userpass", "G3-ySzY%");

        actualPage = getInstance(LoginPage.class)
                .loginAs(userCredentials.get("username"))
                .withPassword(userCredentials.get("userpass"))
                .andRememberMe(true)
                .login()
                .goToDashboard();
    }

    public void verifySchoolTitle(){
        actualPage.as(MainPage.class).verifySchoolName();
    }

    public void navigateToCreateNewStudent(){
        actualPage = getInstance(NavPage.class);
        actualPage.as(NavPage.class).goToStudents().andCreateNew();
    }

    public void studentPersonalDetails(){
        actualPage.as(StudentsPage.class)
                .genderAs(studentPersonalDetails[0])
                .withFistName(studentPersonalDetails[1])
                .withLastName(studentPersonalDetails[2])
                .withDayOfBirth(studentPersonalDetails[3])
                .andCurrentAddress(studentPersonalDetails[4]);
    }

    public void studentAccountInformation(){
        studentAccountInfo.put("email","test1@email.com");
        studentAccountInfo.put("user","testuser");
        studentAccountInfo.put("password","test123");

        actualPage.as(StudentsPage.class)
                .userEmail(studentAccountInfo.get("email"))
                .withUserName(studentAccountInfo.get("user"))
                .withPassword(studentAccountInfo.get("password"))
                .andConfirmPass(studentAccountInfo.get("password"))
                .schoolDetails("088");
    }

    public void verifyUserIsAdded(){
        actualPage.as(StudentsPage.class).validateStudentIsAdded(studentPersonalDetails[1]);
    }
}
