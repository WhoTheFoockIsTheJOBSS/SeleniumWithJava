package framework.base;

public interface DriverFactory {
    Object getDriver();
    void setDriver(BrowserType browserType);
    void removeDriver();
}
