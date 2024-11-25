package sink;

import database.LogDatabase;
import model.configuration.LogLevel;
import model.configuration.LoggerConfiguration;
import model.log.Log;
import utils.OutputUtils;

import java.util.Calendar;
import java.util.List;

public class DatabaseSink implements Sink {

    private final LogDatabase logDatabase;

    public DatabaseSink(LogDatabase logDatabase) {
        this.logDatabase = logDatabase;
    }

    @Override
    public void write(String message, LogLevel logLevel, LoggerConfiguration loggerConfiguration) {
        Long currentTime = Calendar.getInstance().getTimeInMillis();
        logDatabase.addLog(new Log(message, currentTime, logLevel));
        printDatabaseLogs(loggerConfiguration);
    }

    private void printDatabaseLogs(LoggerConfiguration loggerConfiguration) {
        List<Log> logs = logDatabase.getLogs();
        System.out.println("---------- DATABASE SNAPSHOT ----------");
        for (Log log : logs) {
            OutputUtils.printLog(log, loggerConfiguration.getTimestampFormat());
        }
    }

}
