import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class App {
    public static void main(String[] args) {
        System.setProperty("log4j.configurationFile", "D:\\_work\\idea_ws\\Logging\\src\\resources\\log4j2.xml");

        Logger logger = LogManager.getRootLogger();
        logger.info("Information message");
        logger.debug("Debug message");
        logger.trace("Trace message");
        logger.error("Error message");
        logger.fatal("Fatal message");
        logger.warn("Warning message");

        Logger logger2 = LogManager.getLogger(App.class);
        logger2.info("2 Information message");
        logger2.debug("2 Debug message");
        logger2.trace("2 Trace message");
        logger2.error("2 Error message");
        logger2.fatal("2 Fatal message");
        logger2.warn("2 Warning message");
    }
}