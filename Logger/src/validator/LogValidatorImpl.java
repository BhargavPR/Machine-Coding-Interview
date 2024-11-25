package validator;

import model.configuration.LogLevel;
import model.configuration.LoggerConfiguration;

public class LogValidatorImpl implements LogValidator {

    @Override
    public void validate(String message, LogLevel logLevel, LoggerConfiguration loggerConfiguration) {
        if (logLevel.getValue() < loggerConfiguration.getLogLevel().getValue()) {
            throw new RuntimeException("For message:- " + message + " Log level " + logLevel + " is lower then configured level " + loggerConfiguration.getLogLevel());
        }
    }

}
