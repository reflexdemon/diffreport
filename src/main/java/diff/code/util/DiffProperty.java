/**
 * 
 */
package diff.code.util;

import java.util.Properties;
import java.util.logging.Logger;

/**
 * The Class DiffProperty.
 * 
 * @author 272920
 */
public final class DiffProperty {

	

	/**
	 * Logging Reference for DiffProperty
	 */
	private static final Logger logger = Logger.getLogger(DiffProperty.class
			.getName());
	private static final String TRUE = "TRUE";

	private static final String ON = "ON";

	private static final String YES = "YES";

	private static final String ONE = "1";

	/** The instance. */
	private static DiffProperty instance = null;

	/** The properties. */
	private static Properties properties = null;

	/**
	 * Instantiates a new diff property.
	 */
	private DiffProperty() {
		// No implementation
	}

	/**
	 * Gets the value.
	 * 
	 * @param key
	 *            the key
	 * @return the value
	 */
	public static String getValue(String key) {
		_instanceChecker();
		return instance.getValueFromProperty(key);
	}

	
	/**
	 * Gets the boolean.
	 *
	 * @param key the key
	 * @return the boolean
	 */
	public static boolean getBoolean(String key) {
		String value = getValue(key);
		if ((null != value)) {
			value = value.trim();
			if (value.equalsIgnoreCase(TRUE) || value.equalsIgnoreCase(ON)
					|| value.equalsIgnoreCase(YES)
					|| value.equalsIgnoreCase(ONE)) {
				return true;
			}

		}
		return false;
	}

	private static void _instanceChecker() {
		if (null == instance) {
			instance = new DiffProperty();
			instance.loadDefauleProperties();
		}
	}

	/**
	 * Load defaule properties.
	 */
	private void loadDefauleProperties() {
		reload("diff.code.resources.resources");

	}

	/**
	 * Gets the value from property.
	 * 
	 * @param key
	 *            the key
	 * @return the value from property
	 */
	private String getValueFromProperty(String key) {
		return properties.getProperty(key);

	}

	/**
	 * Reload.
	 * 
	 * @param location
	 *            the location
	 */
	public static void reload(String location) {
		properties = PropertyLoader.loadProperties(location);
	}

	public static void main(String[] a) {
		logger.info(DiffProperty.getValue("app.name"));
	}
}
