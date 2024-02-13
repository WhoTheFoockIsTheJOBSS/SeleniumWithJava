package htmlable;

import models.ColumnSpecialValue;
import models.TableDataCollection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
public class ReadHtmlTable {

    private static List<TableDataCollection> tableDataCollections;

    public static void readTable(WebElement table, String... compareText) {
        //Inicializar la Tabla
        tableDataCollections = new ArrayList<>();

        //Obtener todas las columnas de la tabla
        List<WebElement> columns = table.findElements(By.tagName("th"));

        //Obtener todas las fila (rows)
        List<WebElement> rows = table.findElements(By.tagName("tr"));

        //Create row index
        int rowIndex = 0;
        for (WebElement row : rows) {
            int colIndex = 0;

            List<WebElement> colDatas = row.findElements(By.tagName("td"));

            //Almacenar la data sólo si tiene valor en row
            if (colDatas.size() != 0) {
                for (WebElement colValue : colDatas) {
                    tableDataCollections.add(new TableDataCollection(
                            rowIndex,
                            !columns.get(colIndex).getText().equals("") ? columns.get(colIndex).getText()
                                    : String.valueOf(colIndex),
                            colValue.getText(),
                            getControl(colValue)
                    ));

                    //Moverse a la siguiente Columna
                    colIndex++;
                }
                rowIndex++;
            }
        }
    }

    public static void compareTableText(String... strCompare) {
        for (String text : strCompare) {
            tableDataCollections.forEach(tblc -> {
                if (tblc.getColVal().equals(text)) {
                    System.out.println(text + " Test Pass!!!");
                }
            });
        }
    }

    private static ColumnSpecialValue getControl(WebElement colValue) {
        ColumnSpecialValue columnSpecialValue = new ColumnSpecialValue(null, ControlType.Text);

        //Revisar si el control tiene tags específicos como input, hyperlink, etc
        if (colValue.findElements(By.tagName("a")).size() > 0) {
            columnSpecialValue = new ColumnSpecialValue(
                    colValue.findElements(By.tagName("a")),
                    ControlType.Hyperlink
            );
        }

        if (colValue.findElements(By.tagName("input")).size() > 0) {
            columnSpecialValue = new ColumnSpecialValue(
                    colValue.findElements(By.tagName("input")),
                    ControlType.Input
            );
        }

        return columnSpecialValue;
    }

    public static void performActionCell(String colIndex, String refColName, String refColValue,
                                         String controlToOperate) {
        for (int rowNumber : getDynamicRowNumber(refColName, refColValue)) {

            //checar si hay que realizar una acción sobre un control
            final WebElement[] tableElement = new WebElement[rowNumber];

      /*for (TableDataCollection tblc : tableDataCollections) {
        if (tblc.getColSpecialVal().getControlType().equals(ControlType.Hyperlink) &&
            tblc.getColName().equals(colIndex) && tblc.getRowNumber() == rowNumber){
          for (var subItem : tblc.getColSpecialVal().getElementIterable()) {
            if (controlToOperate != null && subItem.getAttribute("title").equals(controlToOperate)){
              tableElement[0] = subItem;
            }
          }
        }
      }*/

            tableDataCollections.forEach(tblc -> {
                if (tblc.getColSpecialVal().getControlType().equals(ControlType.Hyperlink) && tblc.getColName()
                        .equals(colIndex) && tblc.getRowNumber() == rowNumber) {
                    tblc
                            .getColSpecialVal()
                            .getElementIterable()
                            .forEach(e -> {
                                if (controlToOperate != null && e.getAttribute("title").equals(controlToOperate)) {
                                    tableElement[0] = e;
                                }
                            });
                }
            });

            try {
                Arrays.stream(tableElement).findFirst().get().click();
            }catch (NullPointerException ne){
                var cell = tableDataCollections.stream()
                        .filter(e -> e.getColName().equals(colIndex) && e.getRowNumber() == rowNumber)
                        .map(e -> e.getColSpecialVal()).findFirst();
                cell.stream().findFirst().get().getElementIterable().iterator().next().click();
            }
        }
    }

    private static int[] getDynamicRowNumber(String refColName, String refColValue) {
        for (var table : tableDataCollections) {
            if (table.getColName().equals(refColName) && table.getColVal().equals(refColValue)) {
                return new int[]{table.getRowNumber()};
            }
        }
        return new int[0];
    }
}
