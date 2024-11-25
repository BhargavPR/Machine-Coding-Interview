package logger;

import database.LogDatabase;
import model.configuration.LogLevel;
import model.configuration.LoggerConfiguration;
import sink.SinkContext;
import writer.WriterContext;

public class Logger {

    private final LoggerConfiguration loggerConfiguration;
    private final WriterContext writerContext;

    public Logger(LoggerConfiguration loggerConfiguration,
                  WriterContext writerContext) {
        this.loggerConfiguration = loggerConfiguration;
        this.writerContext = writerContext;
    }

    public static Logger getInstance(LoggerConfiguration loggerConfiguration) {
        SinkContext sinkContext = new SinkContext(LogDatabase.getInstance());
        return new Logger(loggerConfiguration,
                new WriterContext(sinkContext));
    }

    public void debug(String message) {
        writeMessage(message, LogLevel.DEBUG);
    }

    public void info(String message) {
        writeMessage(message, LogLevel.INFO);
    }

    public void warn(String message) {
        writeMessage(message, LogLevel.WARN);
    }

    public void error(String message) {
        writeMessage(message, LogLevel.ERROR);
    }

    public void fatal(String message) {
        writeMessage(message, LogLevel.FATAL);
    }

    private void writeMessage(String message, LogLevel logLevel) {
        try {
            writerContext.write(message, logLevel, getLoggerConfiguration());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public LoggerConfiguration getLoggerConfiguration() {
        return loggerConfiguration;
    }

}
