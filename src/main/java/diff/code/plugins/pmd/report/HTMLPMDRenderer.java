/**
 * 
 */
package diff.code.plugins.pmd.report;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

import javax.xml.bind.JAXBException;

import org.apache.commons.io.IOUtils;

import diff.code.plugins.pmd.PMDConstants;
import diff.code.plugins.pmd.PMDUtils;
import diff.code.report.Renderer;
import diff.code.util.Constants;
import diff.code.util.DataUtil;
import diff.code.util.PluginUtils;
import diff.code.vo.FileInfo;

/**
 * The Class XMLRenderer.
 * 
 * @author Venkateswara
 */
public class HTMLPMDRenderer implements Renderer {

	/**
	 * Logging Reference for TreeDiffReader
	 */
	private static final Logger logger = Logger.getLogger(HTMLPMDRenderer.class
			.getName());
	/** The xml out file. */
	private String htmlOutFile;

	/*
	 * (non-Javadoc)
	 * 
	 * @see diff.code.report.Renderer#createReport(java.lang.String,
	 * java.util.Set)
	 */
	public void createReport( Set<String> files) {
		setHtmlOutFile(PluginUtils.getReportFile(PMDConstants.PMD, Constants.REPORT_HTML));
		FileWriter output = null;
		try {
			output = new FileWriter(htmlOutFile);
			StringBuilder html = prepareReport(files);
			if (null != html) {
				IOUtils.write(html.toString(), output);
			}
		} catch (IOException io) {
			logger.throwing(HTMLPMDRenderer.class.getName(), "createReport", io);
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
	 * Prepare report.
	 *
	 * @param files the files
	 * @return the string builder
	 */
	private StringBuilder prepareReport(Set<String> files) {
		StringBuilder data = null;

		try {
			List<FileInfo> report = PMDUtils.getFileData(PMDUtils.getLeft(files), PMDUtils.getRight(files));
			data = PMDUtils.getStringHTML(report);
		} catch (JAXBException e) {
			logger.throwing(HTMLPMDRenderer.class.getName(), "prepareReport",
					e);
		}
		return data;
	}


//	/**
//	 * Transform xml.
//	 *
//	 * @param xmlString the xml string
//	 * @return the string
//	 */
//	private String transformXML(String xmlString) {
//		String formattedOutput = "";
//		try {
//
//			TransformerFactory tFactory = TransformerFactory.newInstance();
//			Transformer transformer = tFactory.newTransformer(new StreamSource(
//					this.getClass().getClassLoader().getResource(XSL_FILE_NAME)
//							.openStream()));
//
//			StreamSource xmlSource = new StreamSource(new StringReader(
//					xmlString));
//			ByteArrayOutputStream baos = new ByteArrayOutputStream();
//			transformer.transform(xmlSource, new StreamResult(baos));
//
//			formattedOutput = baos.toString();
//
//		} catch (IOException e) {
//			logger.throwing(HTMLPMDRenderer.class.getName(), "getHtmlOutFile", e);
//		} catch (TransformerConfigurationException e) {
//			logger.throwing(HTMLPMDRenderer.class.getName(), "getHtmlOutFile", e);
//		} catch (TransformerException e) {
//			logger.throwing(HTMLPMDRenderer.class.getName(), "getHtmlOutFile", e);
//		}
//
//		return formattedOutput;
//	}

	/**
	 * Gets the xml out file.
	 * 
	 * @return the xml out file
	 */
	public String getHtmlOutFile() {
		return htmlOutFile;
	}

	/**
	 * Sets the xml out file.
	 * 
	 * @param htmlOutFile
	 *            the new xml out file
	 */
	public void setHtmlOutFile(String htmlOutFile) {
		this.htmlOutFile = htmlOutFile;
	}

	/**
	 * The main method.
	 * 
	 * @param args
	 *            the arguments
	 */
	public static void main(String args[]) {
		Renderer r = new HTMLPMDRenderer();
		r.createReport(DataUtil.files);
	}

}
