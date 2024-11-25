package model.record;

import java.util.List;

public class Record {

    private List<RecordItem> items;

    public Record(List<RecordItem> items) {
        this.items = items;
    }

    public List<RecordItem> getItems() {
        return items;
    }

    public void setItems(List<RecordItem> items) {
        this.items = items;
    }
}
