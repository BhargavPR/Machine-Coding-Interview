package model.record;

import model.column.Column;

public class RecordItem {

    private Column column;
    private Object value;

    public RecordItem(Column column, Object value) {
        this.column = column;
        this.value = value;
    }

    public Column getColumn() {
        return column;
    }

    public void setColumn(Column column) {
        this.column = column;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

}
