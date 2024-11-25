package writer;

import model.configuration.LogLevel;
import model.configuration.LoggerConfiguration;
import model.configuration.LoggerType;
import sink.SinkContext;
import validator.LogValidator;
import validator.LogValidatorImpl;

public class WriterContext {

    private final LogValidator logValidator;
    private final Writer syncWriter;
    private final Writer asyncWriter;

    public WriterContext(SinkContext sinkContext) {
        this.logValidator = new LogValidatorImpl();
        this.syncWriter = new SyncWriter(sinkContext);
        this.asyncWriter = new AsyncWriter(sinkContext);
    }

    public void write(String message, LogLevel logLevel, LoggerConfiguration loggerConfiguration) {
        validate(message, logLevel, loggerConfiguration);
        String truncateMessage = truncateMessage(message, loggerConfiguration);
        if (LoggerType.SYNC.equals(loggerConfiguration.getLoggerType())) {
            syncWriter.write(truncateMessage, logLevel, loggerConfiguration);
        } else {
            asyncWriter.write(truncateMessage, logLevel, loggerConfiguration);
        }
    }

    private void validate(String message, LogLevel logLevel, LoggerConfiguration loggerConfiguration) {
        logValidator.validate(message, logLevel, loggerConfiguration);
    }

    private String truncateMessage(String message, LoggerConfiguration loggerConfiguration) {
        if (message.length() > loggerConfiguration.getBufferSize()) {
            return message.substring(0, loggerConfiguration.getBufferSize());
        }
        return message;
    }
}
