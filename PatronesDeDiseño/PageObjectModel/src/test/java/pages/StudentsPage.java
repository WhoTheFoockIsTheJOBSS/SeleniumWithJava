package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.Map;

public class StudentsPage extends MainPage{
    private By navStudents = By.xpath("(//span[contains(text(), 'Students')])[1]");
    private By rdnGender = By.name("s_gender");
    private By txtFirstName = By.id("firstname");
    private By txtLastName = By.id("lastname");
    private By dtpDateOfBirth = By.id("Dob");
    private By tdSelectDayOfBirth = By.xpath("//td[contains(@class, 'day')]");
    private By txtCurrentAddress = By.id("current_address");
    private By txtEmailAdress = By.id("Email");
    private By txtUserName = By.name("Username");
    private By txtPassword = By.name("Password");
    private By txtConfirmPass = By.id("ConfirmPassword");
    private By txtRollNuber = By.id("Rollno");
    private By trStudentsRows = By.cssSelector("tr[role='row']");

    public void clickStudents(){
        driver.findElement(navStudents).click();
        preLoading();
    }

    public void selectGender(String gender){
        for (WebElement optGender: driver.findElements(rdnGender)){
            if (optGender.getAttribute("value").equals(gender)){
                optGender.click();
                break;
            }
        }
    }

    public void setFistName(String firstName){
        driver.findElement(txtFirstName).clear();
        driver.findElement(txtFirstName).sendKeys(firstName);
    }

    public void setLastName(String lastName){
        driver.findElement(txtLastName).clear();
        driver.findElement(txtLastName).sendKeys(lastName);
    }

    private void setDayOfBirth(String selectedDay){
        driver.findElement(dtpDateOfBirth).click();
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfAllElements(driver.findElements(tdSelectDayOfBirth)));
        for (WebElement day:driver.findElements(tdSelectDayOfBirth)){
            if (day.getText().equals(selectedDay)){
                day.click();
                break;
            }
        }
    }

    private void setCurrentAddress(String currentAddress){
        driver.findElement(txtCurrentAddress).clear();
        driver.findElement(txtCurrentAddress).sendKeys(currentAddress);
    }

    public void studentPersonalDetails(String ... details){
        selectGender(details[0]);
        setFistName(details[1]);
        setLastName(details[2]);
        setDayOfBirth(details[3]);
        setCurrentAddress(details[4]);
    }

    private void setEmailAddress(String email){
        driver.findElement(txtEmailAdress).clear();
        driver.findElement(txtEmailAdress).sendKeys(email);
    }

    private void setUserName(String userName){
        driver.findElement(txtUserName).clear();
        driver.findElement(txtUserName).sendKeys(userName);
    }

    private void setPassword(String password){
        driver.findElement(txtPassword).clear();
        driver.findElement(txtPassword).sendKeys(password);
    }

    private void setConfirmPass(String password){
        driver.findElement(txtConfirmPass).clear();
        driver.findElement(txtConfirmPass).sendKeys(password);
    }

    public void accountInformation(Map<String,String> accountInfo){
        setEmailAddress(accountInfo.get("email"));
        setUserName(accountInfo.get("user"));
        setPassword(accountInfo.get("password"));
        setConfirmPass(accountInfo.get("password"));
    }

    public void schoolDetails(String rollNumber){
        driver.findElement(txtRollNuber).clear();
        driver.findElement(txtRollNuber).sendKeys(rollNumber);
        driver.findElement(txtRollNuber).submit();
        preLoading();
    }

    public void validateStudentIsAdded(String name){
        try{
            new WebDriverWait(driver, Duration.ofSeconds(10))
                    .until(ExpectedConditions.visibilityOfAllElements(driver.findElements(trStudentsRows)));
        } catch (TimeoutException te){
            preLoading();
            new WebDriverWait(driver, Duration.ofSeconds(10))
                    .until(ExpectedConditions.visibilityOfAllElements(driver.findElements(trStudentsRows)));
        }
        WebElement newStudentRow = driver.findElements(trStudentsRows).getLast();
        Assert.assertTrue(newStudentRow.getText().contains(name));
    }

    public void deleteStudent(){
        deleteRow();
        confirmationWindow();
    }
}
