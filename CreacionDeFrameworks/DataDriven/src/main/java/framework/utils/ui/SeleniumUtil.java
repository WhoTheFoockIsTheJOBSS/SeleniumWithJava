package framework.utils.ui;

import framework.base.DriverFactory;
import framework.base.FrameworkException;
import framework.utils.logs.Log;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import static framework.config.Constants.SCREENSHOT_FOLDER;
import static framework.config.CreateFolder.createFolder;

public class SeleniumUtil {
    private static WebDriver driver = DriverFactory.getInstance().getDriver();

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
