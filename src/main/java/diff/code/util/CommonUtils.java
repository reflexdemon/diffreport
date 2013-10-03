/**
 * 
 */
package diff.code.util;

import java.util.logging.Logger;

import javax.xml.bind.JAXBException;

import diff.code.config.Extractor;
import diff.code.config.xml.Report;
import diff.code.config.xml.Reports;
import diff.code.report.Renderer;

/**
 * @author Venkateswara
 * 
 */
public final class CommonUtils {

	

	/**
	 * Logging Reference for CommonUtils
	 */
	private static final Logger logger = Logger
			.getLogger(CommonUtils.class.getName());
	public static final String NEW_LINE = "\n";
	public static final String PREFIX_RENDERER = "renderer.";

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	/**
	 * Gets the class name.
	 * 
	 * @param file
	 *            the file
	 * @return the class name
	 */
	public static String getClassName(String file) {
		// com/cbeyond/bsimple/action/Action.java
		if (null != file && file.trim().length() != 0) {
			// return file.substring(0, file.indexOf('.')).replace('/', '.');
			return file;
		}
		return "";
	}


	/**
	 * Gets the reports.
	 * 
	 * @return the reports
	 */
	public static Reports getReports() {
		Reports r = null;
		try {
			r = Extractor.getInstance().getConfiguration().getReports();
		} catch (JAXBException e) {
			logger.throwing(CommonUtils.class.getName(), "getReports", e);
		}
		return r;
	}

	/**
	 * Gets the basic renderer.
	 * 
	 * @param type
	 *            the type
	 * @return the basic renderer
	 */
	public static Renderer getBasicRenderer(String type) {
		Renderer a = null;
		if (null != type) {
			try {
				a = (Renderer) Class.forName(
						DiffProperty.getValue(PREFIX_RENDERER
								+ type.toLowerCase())).newInstance();
			} catch (InstantiationException e1) {
				logger.throwing(CommonUtils.class.getName(),
						"getBasicRenderer", e1);
			} catch (IllegalAccessException e1) {
				logger.throwing(CommonUtils.class.getName(),
						"getBasicRenderer", e1);
			} catch (ClassNotFoundException e1) {
				logger.throwing(CommonUtils.class.getName(),
						"getBasicRenderer", e1);
			}

		}
		return a;

	}

	/**
	 * Gets the report file.
	 * 
	 * @param reportType
	 *            the report type
	 * @return the report file
	 */
	public static String getReportFile(String reportType) {
		if (null != reportType) {
			for (Report r : getReports().getReport()) {
				if (reportType.equalsIgnoreCase(r.getType())) {
					return r.getFile();
				}
			}
		}
		return null;
	}
}
