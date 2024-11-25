package sink;

import model.configuration.LogLevel;
import model.configuration.LoggerConfiguration;
import utils.OutputUtils;

import java.util.Calendar;

public class STDOUTSink implements Sink {

    @Override
    public void write(String message, LogLevel logLevel, LoggerConfiguration loggerConfiguration) {
        Long currentTime = Calendar.getInstance().getTimeInMillis();
        OutputUtils.printLog(message, currentTime, logLevel, loggerConfiguration.getTimestampFormat());
    }

}
