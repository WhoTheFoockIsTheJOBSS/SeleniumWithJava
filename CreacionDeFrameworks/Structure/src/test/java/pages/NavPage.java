package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class NavPage extends MainPage{
    @FindBy(xpath = "(//span[contains(text(), 'Students')])[1]")
    private WebElement navStudents;
    public StudentsPage goToStudents(){
        navStudents.click();
        preLoading();
        return new StudentsPage();
    }
}
