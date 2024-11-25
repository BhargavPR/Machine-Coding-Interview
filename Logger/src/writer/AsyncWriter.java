package writer;

import model.configuration.LogLevel;
import model.configuration.LoggerConfiguration;
import sink.SinkContext;

public class AsyncWriter implements Writer {

    private final SinkContext sinkContext;

    public AsyncWriter(SinkContext sinkContext) {
        this.sinkContext = sinkContext;
    }

    @Override
    public void write(String message, LogLevel logLevel, LoggerConfiguration loggerConfiguration) {
        new Thread(() -> sinkContext.write(message, logLevel, loggerConfiguration))
                .start();
    }

}
