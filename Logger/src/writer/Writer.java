package writer;

import model.configuration.LogLevel;
import model.configuration.LoggerConfiguration;

public interface Writer {

    void write(String message, LogLevel logLevel, LoggerConfiguration loggerConfiguration);

}
