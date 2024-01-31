package models;

import lombok.Data;

@Data
public class TestSteps extends Test{
    public String getPageName() {
        return pageName;
    }

    public void setPageName(String pageName) {
        this.pageName = pageName;
    }

    public String getPageObject() {
        return pageObject;
    }

    public void setPageObject(String pageObject) {
        this.pageObject = pageObject;
    }

    public String getActionKeyword() {
        return ActionKeyword;
    }

    public void setActionKeyword(String actionKeyword) {
        ActionKeyword = actionKeyword;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    private String pageName;
    private String pageObject;
    private String ActionKeyword;
    private String data;
}
