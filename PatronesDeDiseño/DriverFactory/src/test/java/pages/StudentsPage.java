package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;
import java.util.Map;

public class StudentsPage extends MainPage{
    @FindBy(xpath = "(//span[contains(text(), 'Students')])[1]")
    private WebElement navStudents;
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

    public void clickStudents(){
        navStudents.click();
        preLoading();
    }

    public void selectGender(String gender){
        for (WebElement optGender: rdnGender){
            if (optGender.getAttribute("value").equals(gender)){
                optGender.click();
                break;
            }
        }
    }

    public void setFistName(String firstName){
        txtFirstName.clear();
        txtFirstName.sendKeys(firstName);
    }

    public void setLastName(String lastName){
        txtLastName.clear();
        txtLastName.sendKeys(lastName);
    }

    private void setDayOfBirth(String selectedDay){
        dtpDateOfBirth.click();
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfAllElements(tdSelectDayOfBirth));
        for (WebElement day:tdSelectDayOfBirth){
            if (day.getText().equals(selectedDay)){
                day.click();
                break;
            }
        }
    }

    private void setCurrentAddress(String currentAddress){
        txtCurrentAddress.clear();
        txtCurrentAddress.sendKeys(currentAddress);
    }

    public void studentPersonalDetails(String ... details){
        selectGender(details[0]);
        setFistName(details[1]);
        setLastName(details[2]);
        setDayOfBirth(details[3]);
        setCurrentAddress(details[4]);
    }

    private void setEmailAddress(String email){
        txtEmailAdress.clear();
        txtEmailAdress.sendKeys(email);
    }

    private void setUserName(String userName){
        txtUserName.clear();
        txtUserName.sendKeys(userName);
    }

    private void setPassword(String password){
        txtPassword.clear();
        txtPassword.sendKeys(password);
    }

    private void setConfirmPass(String password){
        txtConfirmPass.clear();
        txtConfirmPass.sendKeys(password);
    }

    public void accountInformation(Map<String,String> accountInfo){
        setEmailAddress(accountInfo.get("email"));
        setUserName(accountInfo.get("user"));
        setPassword(accountInfo.get("password"));
        setConfirmPass(accountInfo.get("password"));
    }

    public void schoolDetails(String rollNumber){
        txtRollNuber.clear();
        txtRollNuber.sendKeys(rollNumber);
        txtRollNuber.submit();
        preLoading();
    }

    public void validateStudentIsAdded(String name){
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
    }

    public void deleteStudent(){
        deleteRow();
        confirmationWindow();
    }
}
