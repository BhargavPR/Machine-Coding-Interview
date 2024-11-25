package database;

import model.table.Table;

import java.util.HashMap;
import java.util.Optional;

public class Database {

    private static Database INSTANCE = null;

    private final HashMap<String, Table> tableHashMap = new HashMap<>();

    public static Database getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Database();
        }
        return INSTANCE;
    }

    private Database() {

    }

    public void addTable(Table table) {
        tableHashMap.put(table.getName(), table);
    }

    public Optional<Table> getTable(String name) {
        if (!tableHashMap.containsKey(name)) {
            return Optional.empty();
        }
        return Optional.of(tableHashMap.get(name));
    }
}
