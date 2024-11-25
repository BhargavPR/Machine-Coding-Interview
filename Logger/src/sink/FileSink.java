package sink;

import model.configuration.LogLevel;
import model.configuration.LoggerConfiguration;

public class FileSink implements Sink {

    @Override
    public void write(String message, LogLevel logLevel, LoggerConfiguration loggerConfiguration) {
        System.out.println("Not implemented");
    }

}
