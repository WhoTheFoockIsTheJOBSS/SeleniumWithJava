package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static framework.utils.ui.SeleniumUtil.highLight;

public class NavPage extends MainPage{
    @FindBy(xpath = "(//span[contains(text(), 'Students')])[1]")
    private WebElement navStudents;
    public StudentsPage goToStudents(){
        highLight(navStudents).click();
        return (StudentsPage) (actualPage = getInstance(StudentsPage.class));
    }
}
