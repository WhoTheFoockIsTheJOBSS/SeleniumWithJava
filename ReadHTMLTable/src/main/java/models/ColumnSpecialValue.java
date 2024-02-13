package models;

import htmlable.ControlType;
import lombok.Data;
import org.openqa.selenium.WebElement;

@Data
public class ColumnSpecialValue {
    private Iterable<WebElement> elementIterable;
    private ControlType controlType;

    public ColumnSpecialValue(Iterable<WebElement> elementIterable, ControlType controlType) {
        this.elementIterable = elementIterable;
        this.controlType = controlType;
    }
}
