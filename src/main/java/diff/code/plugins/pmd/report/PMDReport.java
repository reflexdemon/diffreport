/**
 * 
 */
package diff.code.plugins.pmd.report;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.logging.Logger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import diff.code.config.Extractor;
import diff.code.plugins.pmd.xml.Pmd;
import diff.code.plugins.pmd.xml.Violation;
import diff.code.util.DiffProperty;
/**
 * The Class PMDReport.
 *
 * @author Venkateswara VP
 */
final public class PMDReport {

	/**
	 * Logging Reference for TreeDiffReader
	 */
	private static final Logger logger = Logger.getLogger(PMDReport.class
			.getName());
	
	/** The jaxb context. */
	JAXBContext jaxbContext = null;
	
	/** The unmarshaller. */
	Unmarshaller unmarshaller = null;
	
	/** The file name. */
	private String fileName = null;
	/** The instance. */
	private static Map<String, PMDReport> instance = new HashMap<String, PMDReport>();
	
	/** The violations. */
	private Map<String, List<Violation>> violations = new TreeMap<String, List<Violation>>();

	/**
	 * Gets the violations.
	 *
	 * @return the violations
	 */
	public Map<String, List<Violation>> getViolations() {
		return violations;
	}

	/**
	 * Sets the violations.
	 *
	 * @param violations the violations to set
	 */
	public void setViolations(Map<String, List<Violation>> violations) {
		this.violations = violations;
	}

	/**
	 * Gets the file name.
	 *
	 * @return the fileName
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 * Sets the file name.
	 *
	 * @param fileName the fileName to set
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	/**
	 * Instantiates a new extractor.
	 */
	private PMDReport() {
		// Controlled
	}

	/**
	 * Gets the single instance of Extracter.
	 *
	 * @param fileName the file name
	 * @return single instance of Extracter
	 */
	public static PMDReport getInstance(String fileName) {
		if (null == instance.get(fileName)) {
			PMDReport report = new PMDReport();
			report.setFileName(fileName);
			report.init();
			instance.put(fileName, report);
		}
		return instance.get(fileName);
	}

	/**
	 * Inits the.
	 */
	protected void init() {
		try {
			jaxbContext = JAXBContext.newInstance(DiffProperty.getValue("jaxb.pmd.package"));
			unmarshaller = jaxbContext.createUnmarshaller();

		} catch (JAXBException e) {
			logger.throwing(PMDReport.class.getName(), "init", e);
		}
	}

	/**
	 * Gets the configuration.
	 * 
	 * @return the configuration
	 * @throws JAXBException
	 *             the jAXB exception
	 */
	public Pmd getPMDReport() throws JAXBException {
		Pmd configuration = (Pmd) unmarshaller.unmarshal(new File(fileName));
		return configuration;
	}

	/**
	 * The main method.
	 * 
	 * @param args
	 *            the arguments
	 */
	public static void main(String[] args) {
		try {
			PMDReport leftReport = PMDReport
					.getInstance("./report/pmd/left-pmd.xml");
			leftReport.processReport(Extractor.getInstance().getConfiguration()
					.getDirectories().getLeft().getValue());
			Map<String, List<Violation>> left = leftReport.getViolations();
			displayOut(left);
			PMDReport rightReport = PMDReport
					.getInstance("./report/pmd/right-pmd.xml");
			rightReport.processReport(Extractor.getInstance()
					.getConfiguration().getDirectories().getRight().getValue());
			Map<String, List<Violation>> right = rightReport.getViolations();
			displayOut(right);
		} catch (JAXBException e) {
			logger.throwing(PMDReport.class.getName(), "main", e);
		}
	}

	/**
	 * Display out.
	 * 
	 * @param left
	 *            the left
	 */
	private static void displayOut(Map<String, List<Violation>> left) {
		for (Entry<String, List<Violation>> entry : left.entrySet()) {
			for (Violation v : entry.getValue()) {
				logger.finest(entry.getKey());
				logger.finest("\t" + v.getRule());
				logger.finest("\t" + v.getRuleset());
				logger.finest("\t" + v.getPriority());
			}
		}
	}

	/**
	 * Process report.
	 *
	 * @param baseDir the base dir
	 * @throws JAXBException the jAXB exception
	 */
	public void processReport(String baseDir) throws JAXBException {
		PMDReport reporter = instance.get(this.fileName);
		Pmd report = reporter.getPMDReport();
		for (diff.code.plugins.pmd.xml.File file : report.getFile()) {
			for (Violation v : file.getViolation()) {
				reporter.addViolation(v, file.getName(), baseDir);
			}
		}
	}

	/**
	 * Adds the violation.
	 *
	 * @param v the v
	 * @param fileName the file name
	 * @param baseDir the base dir
	 */
	public void addViolation(Violation v, String fileName, String baseDir) {
		int index = new File(baseDir).getAbsolutePath().length()  + 1;
		String key = getValue(fileName.substring(index).replace('\\', '/'));
		if (key.indexOf('$') != -1) {
			key = key.substring(0, key.indexOf('$'));
		}
		List<Violation> value = null;
		if (violations.containsKey(key)) {
			value = violations.get(key);
		} else {
			logger.fine("New Key::" + key);
			value = new ArrayList<Violation>();
		}
		value.add(v);
		violations.put(key, value);
	}

	/**
	 * Gets the value.
	 * 
	 * @param string
	 *            the string
	 * @return the value
	 */
	private String getValue(String string) {
		return null == string ? "" : string;
	}

}
