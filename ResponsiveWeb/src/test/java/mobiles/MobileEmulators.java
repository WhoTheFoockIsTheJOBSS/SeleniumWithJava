package mobiles;

import org.testng.annotations.DataProvider;

public class MobileEmulators {
    @DataProvider
    public Object[][] mobileEmulations(){
        return new Object[][]{
                {"iPhone SE"},
                {"Pixel 5"},
                {"Samsung Galaxy S20 Ultra"},
                {"iPad Mini"},
                {"Surface Pro 7"}
        };
    }
}
