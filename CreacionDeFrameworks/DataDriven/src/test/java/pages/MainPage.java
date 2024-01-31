package pages;

import framework.base.BasePage;
import framework.base.DriverFactory;
import framework.base.FrameworkException;
import framework.utils.verifications.VerificationUtil;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

import static framework.utils.ui.SeleniumUtil.highLight;
import static framework.utils.ui.SeleniumUtil.refresh;
import static framework.utils.ui.WaitUtil.*;

public class MainPage extends BasePage {
    WebDriver driver = DriverFactory.getInstance().getDriver();

    VerificationUtil verification = new VerificationUtil();

    //forma Page Factory
    @FindBy(className = "wpsp-preLoading")
    private WebElement dvPreloading;
    // forma POM: private By dvPreloading = By.className("wpsp-preLoading");
    @FindBy(className = "wpsp-schoolname")
    private WebElement spnTI;
    @FindBy(xpath = "//a[contains(text(), ' Create New')]")
    private WebElement btnCreateNew;
    @FindBy(id = "d_teacher")
    private List<WebElement> deleteIcon;
    @FindBy(xpath = "//a[contains(text(),'Ok')]")
    private WebElement btnOkDelete;

    public MainPage preLoading(){
        try{
            sync();
            elementNotVisible(highLight(dvPreloading));
        } catch (TimeoutException | FrameworkException te){
            refresh();
        }
        return this;
    }

    private String getSchoolName(){
        return highLight(spnTI).getText();
    }

    public MainPage verifySchoolName(){
        verification.verifyTextAreEquals(getSchoolName(), "Titanium School");
        return this;
    }

    public MainPage andCreateNew(){
        elementClickable(highLight(btnCreateNew));
        preLoading();
        return this;
    }

    public MainPage deleteRow(){
        allElementVisible(deleteIcon);
        highLight(deleteIcon.getLast()).click();
        return this;
    }

    public MainPage confirmationWindow(){
        elementClickable(highLight(btnOkDelete));
        elementNotVisible(btnOkDelete);
        return this;
    }
}
