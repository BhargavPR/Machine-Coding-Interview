package validator;

import model.configuration.LogLevel;
import model.configuration.LoggerConfiguration;

public interface LogValidator {

    void validate(String message, LogLevel logLevel, LoggerConfiguration loggerConfiguration);

}
