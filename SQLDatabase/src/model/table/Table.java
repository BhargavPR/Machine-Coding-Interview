package model.table;

import model.column.Column;
import model.record.Record;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Table {

    private String name;
    private Set<Column> columns;
    private List<Record> records;

    public Table(String name) {
        this.name = name;
        this.columns = new HashSet<>();
        this.records = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Column> getColumns() {
        return columns;
    }

    public void setColumns(Set<Column> columns) {
        this.columns = columns;
    }

    public List<Record> getRecords() {
        return records;
    }

    public void setRecords(List<Record> records) {
        this.records = records;
    }

}
