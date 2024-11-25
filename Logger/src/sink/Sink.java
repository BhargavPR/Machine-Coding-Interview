package sink;

import model.configuration.LogLevel;
import model.configuration.LoggerConfiguration;

public interface Sink {

    void write(String message, LogLevel logLevel, LoggerConfiguration loggerConfiguration);

}
