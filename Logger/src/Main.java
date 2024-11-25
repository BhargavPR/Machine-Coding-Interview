import logger.Logger;
import model.configuration.LogLevel;
import model.configuration.LoggerConfiguration;
import model.configuration.LoggerType;
import model.configuration.SinkType;

public class Main {
    public static void main(String[] args) {
        test1();
    }

    private static void test1() {
        LoggerConfiguration loggerConfiguration = new LoggerConfiguration.Builder()
                .setTimestampFormat("dd-MM-yyyy-HH-mm-ss")
                .setLogLevel(LogLevel.INFO)
                .setLoggerType(LoggerType.SYNC)
                .setBufferSize(25)
                .setSinkType(SinkType.DATABASE)
                .build();
        Logger logger = Logger.getInstance(loggerConfiguration);

        logger.info("This is test message 1");
        logger.info("This is test message 2");
        logger.error("This is test message 3");

    }

    private static void test2() {
        LoggerConfiguration loggerConfiguration = new LoggerConfiguration.Builder()
                .setTimestampFormat("dd-MM-yyyy-HH-mm-ss")
                .setLogLevel(LogLevel.INFO)
                .setLoggerType(LoggerType.ASYNC)
                .setBufferSize(25)
                .setSinkType(SinkType.STDOUT)
                .build();
        Logger logger = Logger.getInstance(loggerConfiguration);

        logger.info("This is test message 1");
        logger.info("This is test message 2");
        logger.info("This is test message 3");
        logger.info("This is test message 4");
        logger.info("This is test message 5");
        logger.error("This is test message 6");
    }

    private static void test3() {
        LoggerConfiguration loggerConfiguration = new LoggerConfiguration.Builder()
                .setTimestampFormat("dd-MM-yyyy-HH-mm-ss")
                .setLogLevel(LogLevel.INFO)
                .setLoggerType(LoggerType.SYNC)
                .setBufferSize(25)
                .setSinkType(SinkType.DATABASE)
                .build();
        Logger logger = Logger.getInstance(loggerConfiguration);

        logger.info("This is test message 1");
        logger.info("This is test message 2");
        logger.info("This is test message 3");
        logger.info("This is test message 4");
        logger.info("This is test message 5");
        logger.error("This is test message 6");
    }

}