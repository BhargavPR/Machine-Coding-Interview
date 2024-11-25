package writer;

import model.configuration.LogLevel;
import model.configuration.LoggerConfiguration;
import sink.SinkContext;

public class SyncWriter implements Writer {

    private final SinkContext sinkContext;

    public SyncWriter(SinkContext sinkContext) {
        this.sinkContext = sinkContext;
    }

    @Override
    public void write(String message, LogLevel logLevel, LoggerConfiguration loggerConfiguration) {
        sinkContext.write(message, logLevel, loggerConfiguration);
    }

}
