package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage extends MainPage{
    private By txtUser = By.id("user_login");
    private By txtPassword = By.name("pwd");
    private By chkRememberMe = By.cssSelector("#rememberme");
    private By btnLogin = By.xpath("//input[contains(@value, 'Log In')]");

    void setUserName(String userName){
        driver.findElement(txtUser).clear();
        driver.findElement(txtUser).sendKeys(userName);
    }

    void setPassword(String password){
        driver.findElement(txtPassword).clear();
        driver.findElement(txtPassword).sendKeys(password);
    }

    void checkRememberMe(){
        driver.findElement(chkRememberMe).click();
    }

    void clickLogin(){
        driver.findElement(btnLogin).click();
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
