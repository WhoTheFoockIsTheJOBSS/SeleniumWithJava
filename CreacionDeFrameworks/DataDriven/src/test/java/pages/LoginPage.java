package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static framework.utils.ui.SeleniumUtil.highLight;

public class LoginPage extends MainPage{
    @FindBy(id = "user_login")
    private WebElement txtUser;
    @FindBy(name = "pwd")
    private WebElement txtPassword;
    @FindBy(css = "#rememberme")
    private WebElement chkRememberMe;
    @FindBy(xpath = "//input[contains(@value, 'Log In')]")
    private WebElement btnLogin;

    @FindBy(id = "login_error")
    private WebElement lblError;

    public LoginPage loginAs(String userName){
        txtUser.clear();
        highLight(txtUser).sendKeys(userName);
        return this;
    }

    public LoginPage withPassword(String password){
        txtPassword.clear();
        highLight(txtPassword).sendKeys(password);
        return this;
    }

    public LoginPage andRememberMe(boolean checked){
        if (checked){
            highLight(chkRememberMe).click();
        }
        return this;
    }

    public LoginPage login(){
        highLight(btnLogin).click();
        return this;
    }

    private String getError(){
        return highLight(lblError).getText();
    }

    public void verifyErrorText(String error){
        verification.verifyTextAreEquals(getError(),error);
    }

    public LoginPage goToDashboard(){
        WebElement goToDashboard = new WebDriverWait(driver, Duration.ofSeconds(60))
                .until(ExpectedConditions.visibilityOf(driver.findElement(By.id("wp-admin-bar-dashboard"))));
        goToDashboard.click();
        return this;
    }
}
