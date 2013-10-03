/**
 * 
 */
package diff.code.util;

import java.io.IOException;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

/**
 * @author Venkateswara VP
 * 
 */
public final class LoggingUtils {

	/**
	 * Logging Reference for LoggingUtils
	 */
	private static final Logger logger = Logger.getLogger(LoggingUtils.class
			.getName());

	/**
	 * Log finest.
	 */
	public static void logFinest() {
		logger.warning("logFinest invoked");
		// The root logger's handlers default to INFO. We have to
		// crank them up. We could crank up only some of them
		// if we wanted, but we will turn them all up.
		for (Handler handler : Logger.getLogger("").getHandlers()) {
			handler.setLevel(Level.FINE);
		}
	}

	/**
	 * Inits the logging from property.
	 * 
	 * @param logPropertyFile
	 *            the log property file
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public static void initLoggingFromProperty(String logPropertyFile)
			throws IOException {
		LogManager.getLogManager().readConfiguration(Thread.currentThread()
				.getContextClassLoader().getResourceAsStream(logPropertyFile));
	}

	/**
	 * Inits the default application logging.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static void initDefaultApplicationLogging() throws IOException {
		initLoggingFromProperty(DiffProperty.getValue("logging.config"));
	}


}
