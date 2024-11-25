package model.log;

import model.configuration.LogLevel;

public class Log {

    private String message;
    private Long timestamp;
    private LogLevel logLevel;

    public Log(String message, Long timestamp, LogLevel logLevel) {
        this.message = message;
        this.timestamp = timestamp;
        this.logLevel = logLevel;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public LogLevel getLogLevel() {
        return logLevel;
    }

    public void setLogLevel(LogLevel logLevel) {
        this.logLevel = logLevel;
    }
}
