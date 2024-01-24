package pages;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;

public class StudentsPage extends MainPage{
    @FindBy(name = "s_gender")
    private List<WebElement> rdnGender;
    @FindBy(id = "firstname")
    private WebElement txtFirstName;
    @FindBy(id = "lastname")
    private WebElement txtLastName;
    @FindBy(id = "Dob")
    private WebElement dtpDateOfBirth;
    @FindBy(xpath = "//td[contains(@class, 'day')]")
    private List<WebElement> tdSelectDayOfBirth;
    @FindBy(id = "current_address")
    private WebElement txtCurrentAddress;
    @FindBy(id = "Email")
    private WebElement txtEmailAdress;
    @FindBy(name = "Username")
    private WebElement txtUserName;
    @FindBy(name = "Password")
    private WebElement txtPassword;
    @FindBy(id = "ConfirmPassword")
    private WebElement txtConfirmPass;
    @FindBy(id = "Rollno")
    private WebElement txtRollNuber;
    @FindBy(css = "tr[role='row']")
    private List<WebElement> trStudentsRows;

    public StudentsPage genderAs(String gender){
        for (WebElement optGender: rdnGender){
            if (optGender.getAttribute("value").equals(gender)){
                optGender.click();
                break;
            }
        }
        return this;
    }

    public StudentsPage withFistName(String firstName){
        txtFirstName.clear();
        txtFirstName.sendKeys(firstName);
        return this;
    }

    public StudentsPage withLastName(String lastName){
        txtLastName.clear();
        txtLastName.sendKeys(lastName);
        return this;
    }

    public StudentsPage withDayOfBirth(String selectedDay){
        dtpDateOfBirth.click();
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfAllElements(tdSelectDayOfBirth));
        for (WebElement day:tdSelectDayOfBirth){
            if (day.getText().equals(selectedDay)){
                day.click();
                break;
            }
        }
        return this;
    }

    public StudentsPage andCurrentAddress(String currentAddress){
        txtCurrentAddress.clear();
        txtCurrentAddress.sendKeys(currentAddress);
        return this;
    }

    public StudentsPage userEmail(String email){
        txtEmailAdress.clear();
        txtEmailAdress.sendKeys(email);
        return this;
    }

    public StudentsPage withUserName(String userName){
        txtUserName.clear();
        txtUserName.sendKeys(userName);
        return this;
    }

    public StudentsPage withPassword(String password){
        txtPassword.clear();
        txtPassword.sendKeys(password);
        return this;
    }

    public StudentsPage andConfirmPass(String password){
        txtConfirmPass.clear();
        txtConfirmPass.sendKeys(password);
        return this;
    }

    public StudentsPage schoolDetails(String rollNumber){
        txtRollNuber.clear();
        txtRollNuber.sendKeys(rollNumber);
        txtRollNuber.submit();
        preLoading();
        return this;
    }

    public StudentsPage validateStudentIsAdded(String name){
        try{
            new WebDriverWait(driver, Duration.ofSeconds(10))
                    .until(ExpectedConditions.visibilityOfAllElements(trStudentsRows));
        } catch (TimeoutException te){
            preLoading();
            new WebDriverWait(driver, Duration.ofSeconds(10))
                    .until(ExpectedConditions.visibilityOfAllElements(trStudentsRows));
        }
        WebElement newStudentRow = trStudentsRows.getLast();
        Assert.assertTrue(newStudentRow.getText().contains(name));
        return this;
    }

    public StudentsPage deleteStudent(){
        deleteRow();
        confirmationWindow();
        return this;
    }
}
