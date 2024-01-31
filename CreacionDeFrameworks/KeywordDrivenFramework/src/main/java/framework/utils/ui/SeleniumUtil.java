package framework.utils.ui;

import framework.base.DriverFactory;
import framework.base.FrameworkException;
import framework.utils.logs.Log;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;

import java.io.File;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;

import static framework.config.Constants.SCREENSHOT_FOLDER;
import static framework.config.CreateFolder.createFolder;

public class SeleniumUtil {
    private static WebDriver driver = DriverFactory.getInstance().getDriver();
    protected static WebElement element;

    protected static WebElement findElement(String locator) {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
        try{
            return driver.findElement(By.id(locator));
        }catch (NoSuchElementException ne){
            return returnElementName(locator);
        }
    }

    private static WebElement returnElementName(String name){
        try{
            return driver.findElement(By.name(name));
        }catch (NoSuchElementException ne){
            return returnElementXpath(name);
        }
    }

    private static WebElement returnElementXpath(String xpath){
        try{
            return driver.findElement(By.xpath(xpath));
        }catch (NoSuchElementException ne){
            return returnElementCssSelector(xpath);
        }
    }

    private static WebElement returnElementCssSelector(String cssSelector){
        try{
            return driver.findElement(By.cssSelector(cssSelector));
        }catch (NoSuchElementException ne){
            return returnElementClassName(cssSelector);
        }
    }

    private static WebElement returnElementClassName(String className){
        try{
            return driver.findElement(By.className(className));
        }catch (NoSuchElementException ne){
            return returnElementLinkText(className);
        }
    }

    private static WebElement returnElementLinkText(String linkText){
        try{
            return driver.findElement(By.linkText(linkText));
        }catch (NoSuchElementException ne){
            return returnElementTagName(linkText);
        }
    }

    private static WebElement returnElementTagName(String tag) {
        try{
            return driver.findElement(By.tagName(tag));
        }catch (NoSuchElementException ne){
            new FrameworkException("Class SeleniumUtil | Method findElement | Exception desc: Can not find the element: "+tag);
            return null;
        }
    }

    public static WebElement highLight(WebElement element){
        for (int i = 0; i < 3; i++){
            JavascriptExecutor js = (JavascriptExecutor)driver;
            for (int iCnt=0; iCnt<3; iCnt++){
                js.executeScript("arguments[0].setAttribute('style', 'background: yellow')", element);
                try{
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                js.executeScript("arguments[0].setAttribute('style', 'background: ')", element);
            }
        }
        return element;
    }

    public static String getScreenshot(WebElement element) {
        String ssDateTime = new SimpleDateFormat("yyMMddHHmmss").format(Calendar.getInstance().getTime());
        String file = null;
        try{
            file = createFolder(SCREENSHOT_FOLDER) + "/" + ssDateTime + ".png";
        }catch (FrameworkException fe){
            fe.printStackTrace();
        }
        try{
            File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(
                    (element == null ? srcFile : element.getScreenshotAs(OutputType.FILE)),
                    new File(file));
        }catch (Exception e){
            Log.error("Class SeleniumUtils | Method takeSnapshot | Exception desc: "+e.getMessage());
        }
        return file;
    }

    public static void scrollToElement(WebElement element) throws FrameworkException {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        try{
            js.executeScript("arguments[0].scrollIntoView(true)", element);
        }catch (JavascriptException jse){
            throw new FrameworkException(jse.getMessage());
        }
    }

    public static void refresh(){
        driver.navigate().refresh();
    }
}
