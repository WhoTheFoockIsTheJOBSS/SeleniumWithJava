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
    String personal = "";

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

    public void studentPersonalDetails(String... studentPersonalDetails){
        personal = studentPersonalDetails[1];
        actualPage.as(StudentsPage.class)
                .genderAs(studentPersonalDetails[0])
                .withFistName(studentPersonalDetails[1])
                .withLastName(studentPersonalDetails[2])
                .withDayOfBirth(studentPersonalDetails[3])
                .andCurrentAddress(studentPersonalDetails[4]);
    }

    public void studentAccountInformation(String... studentAccInfo){
        actualPage.as(StudentsPage.class)
                .userEmail(studentAccInfo[0])
                .withUserName(studentAccInfo[1])
                .withPassword(studentAccInfo[2])
                .andConfirmPass(studentAccInfo[2])
                .schoolDetails(studentAccInfo[3]);
    }

    public void verifyUserIsAdded(){
        actualPage.as(StudentsPage.class).validateStudentIsAdded(personal);
    }
}
