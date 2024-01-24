package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage extends MainPage{
    @FindBy(id = "user_login")
    private WebElement txtUser;
    @FindBy(name = "pwd")
    private WebElement txtPassword;
    @FindBy(css = "#rememberme")
    private WebElement chkRememberMe;
    @FindBy(xpath = "//input[contains(@value, 'Log In')]")
    private WebElement btnLogin;

    void setUserName(String userName){
        txtUser.clear();
        txtUser.sendKeys(userName);
    }

    void setPassword(String password){
        txtPassword.clear();
        txtPassword.sendKeys(password);
    }

    void checkRememberMe(){
        chkRememberMe.click();
    }

    void clickLogin(){
        btnLogin.click();
    }

    void goToDashboard(){
        WebElement goToDashboard = new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(ExpectedConditions.visibilityOf(driver.findElement(By.id("wp-admin-bar-dashboard"))));
        goToDashboard.click();
    }

    public void login(String userName, String password){
        setUserName(userName);
        setPassword(password);
        checkRememberMe();
        clickLogin();
        goToDashboard();
        preLoading();
    }
}
