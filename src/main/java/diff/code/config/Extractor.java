/**
 * 
 */
package diff.code.config;

import java.io.File;
import java.util.logging.Logger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import diff.code.config.xml.DiffReport;
import diff.code.util.DiffProperty;

/**
 * @author Venkateswara VP
 * 
 */
final public class Extractor {
	/**
	 * Logging Reference for Extractor
	 */
	private static final Logger logger = Logger.getLogger(Extractor.class.getName());

	JAXBContext jaxbContext = null;
	Unmarshaller unmarshaller = null;
	private String fileName = DiffProperty.getValue("default.config.file");

	/**
	 * @return the fileName
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 * @param fileName
	 *            the fileName to set
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	private static Extractor instance = null;

	/**
	 * Instantiates a new extractor.
	 */
	private Extractor() {
		// Singletone
	}

	/**
	 * Gets the single instance of Extractor.
	 * 
	 * @return single instance of Extracter
	 */
	public static synchronized Extractor getInstance() {
		if (null == instance) {
			logger.info("Creating Instance of Extractor");
			instance = new Extractor();
			instance.init();
		}
		return instance;
	}

	/**
	 * Gets the single instance of Extracter.
	 * 
	 * @return single instance of Extracter
	 */
	public static synchronized Extractor getInstance(String fileName) {
		if (null == instance) {
			instance = new Extractor();
			instance.setFileName(fileName);
			instance.init();
		}
		return instance;
	}

	/**
	 * Inits the.
	 */
	protected void init() {
		try {
			jaxbContext = JAXBContext.newInstance(DiffProperty
					.getValue("jaxb.package"));
			unmarshaller = jaxbContext.createUnmarshaller();

		} catch (JAXBException e) {
			logger.throwing(Extractor.class.getName(), "init", e);
		}
	}

	/**
	 * Gets the configuration.
	 * 
	 * @return the configuration
	 * @throws JAXBException
	 *             the jAXB exception
	 */
	public DiffReport getConfiguration() throws JAXBException {
		DiffReport configuration = (DiffReport) unmarshaller
				.unmarshal(new File(fileName));
		return configuration;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			DiffReport config = Extractor.getInstance().getConfiguration();
			logger.finest("LEFT:::"
					+ config.getDirectories().getLeft().getValue());
			logger.finest("RIGHT:::"
					+ config.getDirectories().getRight().getValue());
			logger.finest("OUTPUT FILE:::"
					+ config.getReports().getReport());
		} catch (JAXBException e) {
			logger.throwing(Extractor.class.getName(), "main", e);
		}
	}

}
