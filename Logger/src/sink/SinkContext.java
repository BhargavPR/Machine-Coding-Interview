package sink;

import database.LogDatabase;
import model.configuration.LogLevel;
import model.configuration.LoggerConfiguration;
import model.configuration.SinkType;

public class SinkContext {

    private final LogDatabase logDatabase;
    private final Sink stdoutSink;
    private final Sink fileSink;
    private final Sink databaseSink;

    public SinkContext(LogDatabase logDatabase) {
        this.logDatabase = logDatabase;
        this.stdoutSink = new STDOUTSink();
        this.fileSink = new FileSink();
        this.databaseSink = new DatabaseSink(logDatabase);
    }

    public void write(String message, LogLevel logLevel, LoggerConfiguration loggerConfiguration) {
        if (SinkType.STDOUT.equals(loggerConfiguration.getSinkType())) {
            stdoutSink.write(message, logLevel, loggerConfiguration);
        } else if (SinkType.FILE.equals(loggerConfiguration.getSinkType())) {
            fileSink.write(message, logLevel, loggerConfiguration);
        } else if (SinkType.DATABASE.equals(loggerConfiguration.getSinkType())) {
            databaseSink.write(message, logLevel, loggerConfiguration);
        }
    }

}
