package service;

import database.Database;
import model.column.Column;
import model.column.DataType;
import model.record.Record;
import model.record.RecordItem;
import model.table.Table;

import java.util.*;
import java.util.stream.Collectors;

public class DatabaseServiceImpl implements DatabaseService {

    private final Database database;

    public DatabaseServiceImpl(Database database) {
        this.database = database;
    }

    @Override
    public Table createTable(String name) {
        Table table = new Table(name);
        database.addTable(table);
        return table;
    }

    @Override
    public void addColumn(String tableName, String columnName, DataType dataType) {
        Optional<Table> tableOptional = database.getTable(tableName);
        if (tableOptional.isEmpty()) {
            System.out.println("Table not found");
            return;
        }

        Table table = tableOptional.get();
        Set<String> columnNames = table.getColumns()
                .stream().map(Column::getColumnName)
                .collect(Collectors.toSet());
        if (columnNames.contains(columnName)) {
            System.out.println("Column already exists");
            return;
        }
        table.getColumns().add(new Column(columnName, dataType));

    }

    @Override
    public void addRecord(String tableName, HashMap<String, Object> recordMap) {
        Optional<Table> tableOptional = database.getTable(tableName);
        if (tableOptional.isEmpty()) {
            System.out.println("Table not found");
            return;
        }


        Table table = tableOptional.get();
        if (table.getColumns().size() != recordMap.size()) {
            System.out.println("Record is invalid");
            return;
        }

        List<RecordItem> recordItems = new ArrayList<>();
        for (Column column : table.getColumns()) {
            if (!recordMap.containsKey(column.getColumnName())) {
                System.out.println("Record is invalid");
                return;
            }

            RecordItem recordItem = new RecordItem(column, recordMap.get(column.getColumnName()));
            recordItems.add(recordItem);
        }

        Record record = new Record(recordItems);
        table.getRecords().add(record);
    }

    @Override
    public void printRecords(String tableName) {
        Optional<Table> tableOptional = database.getTable(tableName);
        if (tableOptional.isEmpty()) {
            System.out.println("Table not found");
            return;
        }


        System.out.println("Records of table:- " + tableName);
        Table table = tableOptional.get();
        for (Record record : table.getRecords()) {
            List<RecordItem> recordItems = record.getItems();
            for (RecordItem recordItem : recordItems) {
                System.out.println(recordItem.getColumn().getColumnName() + " :- " + recordItem.getValue());
            }
            System.out.println();
        }
        System.out.println("-------");
    }

    @Override
    public void filterRecords(String tableName, String columnName, Object value) {
        Optional<Table> tableOptional = database.getTable(tableName);
        if (tableOptional.isEmpty()) {
            System.out.println("Table not found");
            return;
        }


        System.out.println("Filtering the records of table:- " + tableName);
        Table table = tableOptional.get();
        List<Record> records = new ArrayList<>();
        for (Record record : table.getRecords()) {
            Optional<RecordItem> recordItemOptional = record.getItems().stream()
                    .filter(recordItem -> recordItem.getColumn().getColumnName().equals(columnName))
                    .findFirst();
            if (recordItemOptional.isPresent() && Objects.equals(recordItemOptional.get().getValue(), value)) {
                records.add(record);
            }
        }


        for (Record record : records) {
            List<RecordItem> recordItems = record.getItems();
            for (RecordItem recordItem : recordItems) {
                System.out.println(recordItem.getColumn().getColumnName() + " :- " + recordItem.getValue());
            }
            System.out.println();
        }
        System.out.println("-------");
    }


}
