package pages;

import base.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class MainPage {
    WebDriver driver = DriverFactory.getInstance().getDriver();
    private By dvPreloading = By.className("wpsp-preLoading");
    private By spnTI = By.className("wpsp-schoolname");
    private By btnCreateNew = By.xpath("//a[contains(text(), ' Create New')]");
    private By deleteIcon = By.id("d_teacher");
    private By btnOkDelete = By.xpath("//a[contains(text(),'Ok')]");

    public void preLoading(){
        try{
            new WebDriverWait(driver, Duration.ofSeconds(10))
                    .until(ExpectedConditions.invisibilityOf(driver.findElement(dvPreloading)));
        } catch (TimeoutException te){
            driver.navigate().refresh();
            preLoading();
        }
    }

    private String getSchoolName(){
        return driver.findElement(spnTI).getText();
    }

    public void verifySchoolName(){
        Assert.assertEquals(getSchoolName(), "Titanium School");
    }

    public void clickCreateNew(){
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(driver.findElement(btnCreateNew)));
        driver.findElement(btnCreateNew).click();
        preLoading();
    }

    public void deleteRow(){
        new WebDriverWait(driver, Duration.ofSeconds(8))
                .until(ExpectedConditions.visibilityOfAllElements(driver.findElements(deleteIcon)));
        WebElement delete = driver.findElements(deleteIcon).getLast();
        delete.click();
    }

    protected void confirmationWindow(){
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(driver.findElement(btnOkDelete)));
        driver.findElement(btnOkDelete).click();
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.invisibilityOf(driver.findElement(btnOkDelete)));
    }
}
