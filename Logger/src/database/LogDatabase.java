package database;

import model.log.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class LogDatabase {

    private static LogDatabase INSTANCE;
    private final List<Log> logs = new ArrayList<>();

    public static LogDatabase getInstance() {
        if (Objects.isNull(INSTANCE)) {
            INSTANCE = new LogDatabase();
        }
        return INSTANCE;
    }

    private LogDatabase() {
    }

    public void addLog(Log log) {
        logs.add(log);
    }

    public List<Log> getLogs() {
        return Collections.unmodifiableList(logs);
    }

}
