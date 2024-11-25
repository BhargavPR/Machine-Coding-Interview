package model.configuration;

public class LoggerConfiguration {

    private String timestampFormat;
    private LogLevel logLevel;
    private LoggerType loggerType;
    private int bufferSize;
    private SinkType sinkType;

    public LoggerConfiguration(String timestampFormat,
                               LogLevel logLevel,
                               LoggerType loggerType,
                               int bufferSize,
                               SinkType sinkType) {
        this.timestampFormat = timestampFormat;
        this.logLevel = logLevel;
        this.loggerType = loggerType;
        this.bufferSize = bufferSize;
        this.sinkType = sinkType;
    }

    public LoggerConfiguration() {
    }

    public String getTimestampFormat() {
        return timestampFormat;
    }

    public void setTimestampFormat(String timestampFormat) {
        this.timestampFormat = timestampFormat;
    }

    public LogLevel getLogLevel() {
        return logLevel;
    }

    public void setLogLevel(LogLevel logLevel) {
        this.logLevel = logLevel;
    }

    public LoggerType getLoggerType() {
        return loggerType;
    }

    public void setLoggerType(LoggerType loggerType) {
        this.loggerType = loggerType;
    }

    public int getBufferSize() {
        return bufferSize;
    }

    public void setBufferSize(int bufferSize) {
        this.bufferSize = bufferSize;
    }

    public SinkType getSinkType() {
        return sinkType;
    }

    public void setSinkType(SinkType sinkType) {
        this.sinkType = sinkType;
    }

    public static class Builder {

        private final LoggerConfiguration loggerConfiguration;

        public Builder() {
            this.loggerConfiguration = new LoggerConfiguration();
        }

        public Builder setTimestampFormat(String timestampFormat) {
            this.loggerConfiguration.setTimestampFormat(timestampFormat);
            return this;
        }


        public Builder setLogLevel(LogLevel logLevel) {
            this.loggerConfiguration.setLogLevel(logLevel);
            return this;
        }

        public Builder setLoggerType(LoggerType loggerType) {
            this.loggerConfiguration.setLoggerType(loggerType);
            return this;
        }

        public Builder setBufferSize(int bufferSize) {
            this.loggerConfiguration.setBufferSize(bufferSize);
            return this;
        }

        public Builder setSinkType(SinkType sinkType) {
            this.loggerConfiguration.setSinkType(sinkType);
            return this;
        }

        public LoggerConfiguration build() {
            return this.loggerConfiguration;
        }

    }
}
