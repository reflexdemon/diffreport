/**
 * 
 */
package diff.code.plugins.pmd.report;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Set;
import java.util.logging.Logger;

import javax.xml.bind.JAXBException;

import org.apache.commons.io.IOUtils;

import diff.code.plugins.pmd.PMDConstants;
import diff.code.plugins.pmd.PMDUtils;
import diff.code.report.Renderer;
import diff.code.util.CommonUtils;
import diff.code.util.Constants;
import diff.code.util.PluginUtils;
/**
 * The Class XMLRenderer.
 * 
 * @author Venkateswara
 */
public class XMLPMDRenderer implements Renderer {

	/**
	 * Logging Reference for TreeDiffReader
	 */
	private static final Logger logger = Logger.getLogger(XMLPMDRenderer.class
			.getName());

	/** The xml out file. */
	private String xmlOutFile;
	private String reportFileName = null;
	/*
	 * (non-Javadoc)
	 * 
	 * @see diff.code.report.Renderer#createReport(java.lang.String,
	 * java.util.Set)
	 */
	public void createReport(Set<String> files) {
		reportFileName = PluginUtils.getReportFile(PMDConstants.PMD, Constants.REPORT_XML);
		setXmlOutFile(reportFileName);
		createXmlReport(files);
	}

	/**
	 * Creates the xml report.
	 * 
	 * @param files
	 *            the files
	 */
	private void createXmlReport(Set<String> files) {
		FileWriter output = null;
		try {
			output = new FileWriter(xmlOutFile);
			StringBuilder data = PMDUtils.getStringXML(files);
			IOUtils.write(data.toString(), output);
		} catch (IOException io) {
			logger.throwing(XMLPMDRenderer.class.getName(), "createXmlReport",
					io);
		} catch (JAXBException e) {
			logger.throwing(XMLPMDRenderer.class.getName(), "createXmlReport",
					e);
		} finally {
			if (null != output) {
				try {
					output.close();
				} catch (IOException e) {
					// Ignored
				}
			}
		}
	}

	/**
	 * Gets the xml out file.
	 * 
	 * @return the xml out file
	 */
	public String getXmlOutFile() {
		return xmlOutFile;
	}

	/**
	 * Sets the xml out file.
	 * 
	 * @param xmlOutFile
	 *            the new xml out file
	 */
	public void setXmlOutFile(String xmlOutFile) {
		this.xmlOutFile = xmlOutFile;
	}


	public static void main(String args[]) {
		logger.finest(CommonUtils
				.getClassName("com/cbeyond/bsimple/action/Action.java"));
	}

}
