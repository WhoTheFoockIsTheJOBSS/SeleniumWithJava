package models;

import lombok.Data;

@Data
public class TestCase extends Test{
    public String getRunMode() {
        return runMode;
    }

    public void setRunMode(String runMode) {
        this.runMode = runMode;
    }

    public int getTcKey() {
        return tcKey;
    }

    public void setTcKey(int tcKey) {
        this.tcKey = tcKey;
    }

    private String runMode;
    private int tcKey;
}
