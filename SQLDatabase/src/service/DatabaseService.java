package service;

import model.column.DataType;
import model.table.Table;

import java.util.HashMap;

public interface DatabaseService {

    Table createTable(String name);

    void addColumn(String tableName, String columnName, DataType dataType);

    void addRecord(String tableName, HashMap<String, Object> recordMap);

    void printRecords(String tableName);

    void filterRecords(String tableName, String columnName, Object value);

}
