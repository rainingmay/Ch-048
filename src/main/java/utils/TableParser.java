package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Evgen on 19.04.2017.
 */
public class TableParser {

    private WebElement table;
    private List<String> titleList;


    public TableParser(WebElement table) {
        this.table = table;
        getTitleFromTable();
    }


    private void getTitleFromTable() {
        WebElement thead = table.findElement(By.cssSelector("thead"));
        List<WebElement> elementsList = thead.findElements(By.cssSelector("th"));
        titleList = new LinkedList<>();
        elementsList.forEach(title -> titleList.add(title.getText().toLowerCase()));
    }

    private List<WebElement> getCellsFromTableRow(int rowNumber) {
        WebElement tbody = table.findElement(By.cssSelector("tbody"));
        List<WebElement> cellsFromRow = tbody.findElements(By.cssSelector("tr:nth-child(" + rowNumber + ") td"));
        return cellsFromRow;
    }

    private List<String> getDataFromTableRow(int rowNumber) {
        List<WebElement> cells = getCellsFromTableRow(rowNumber);
        List<String> result = new LinkedList<>();
        cells.forEach(text -> result.add(text.getText()));
        return result;
    }

    public String getFieldFromTableRow(int rowNumber, String fieldName) {
        List<String> allInfo = getDataFromTableRow(rowNumber);
        int indexOfField = titleList.indexOf(fieldName);
        return allInfo.get(indexOfField);
    }

    public WebElement getButtonFromTableRow(int rowNumber, String buttonName) {
        List<WebElement> webElements = getCellsFromTableRow(rowNumber);
        WebElement button = null;
        for (WebElement cell : webElements) {
            if (cell.findElements(By.cssSelector("span[title=\"" + buttonName + "\"]")).size() > 0)
                button = cell.findElement(By.cssSelector("span[title=\"" + buttonName + "\"]"));
        }
        return button;
    }


}
