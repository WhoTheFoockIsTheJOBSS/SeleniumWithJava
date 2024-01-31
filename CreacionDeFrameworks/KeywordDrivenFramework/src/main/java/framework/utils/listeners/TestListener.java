package framework.utils.listeners;

import com.aventstack.extentreports.Status;
import framework.base.DriverFactory;
import framework.utils.extentreports.ExtentManager;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import static framework.utils.extentreports.ExtentTestManager.getTest;
import static framework.utils.logs.Log.info;

public class TestListener extends DriverFactory implements ITestListener {

    private static String getTestMethodName(ITestResult iTestResult){
        return iTestResult.getMethod().getConstructorOrMethod().getName();
    }

    @Override
    public void onFinish(ITestContext iTestContext){
        info("Finishing method "+iTestContext.getName());
        info("XXXXXXXXXXXXXXXXXXXXXXXXXXX     E--N--D    XXXXXXXXXXXXXXXXXXXXXXXXXXX");
        ExtentManager.extentReports.flush();
    }

    @Override
    public void onTestFailure(ITestResult iTestResult){
        info(getTestMethodName(iTestResult) + " test is failed");
        getTest().log(Status.FAIL, "Test Failed");
        //Generate Screenshot
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult){
        info(getTestMethodName(iTestResult) + " test is skipped");
        getTest().log(Status.SKIP, "Test Skipped");
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult){
        info(getTestMethodName(iTestResult) + " test is succed");
        getTest().log(Status.PASS, "Test Passed");
    }

    @Override
    public void onTestStart(ITestResult iTestResult){
        info("**********************************************************************");
        info(getTestMethodName(iTestResult) + " test is starting");
        info("**********************************************************************");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult){
        info("Test failed but it is in defined success ratio "+getTestMethodName(iTestResult));
    }
}
