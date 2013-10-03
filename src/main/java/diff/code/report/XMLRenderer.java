/**
 * 
 */
package diff.code.report;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Set;
import java.util.logging.Logger;

import javax.xml.bind.JAXBException;

import org.apache.commons.io.IOUtils;

import diff.code.config.Extractor;
import diff.code.util.CommonUtils;
import diff.code.util.Constants;

/**
 * The Class XMLRenderer.
 * 
 * @author Venkateswara
 */
public class XMLRenderer implements Renderer {

	

	/**
	 * Logging Reference for XMLRenderer
	 */
	private static final Logger logger = Logger
			.getLogger(XMLRenderer.class.getName());
	
	/** The xml out file. */
	private String xmlOutFile;

	/* (non-Javadoc)
	 * @see diff.code.report.Renderer#createReport(java.lang.String, java.util.Set)
	 */
	public void createReport(Set<String> files) {
		setXmlOutFile(CommonUtils.getReportFile(Constants.REPORT_XML));
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
			StringBuffer data = getStringXML(files);
			IOUtils.write(data, output);
		} catch (IOException io) {
			logger.throwing(XMLRenderer.class.getName(), "createXmlReport", io);
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

	/**
	 * Gets the string xml.
	 * 
	 * @param files
	 *            the files
	 * @return the string xml
	 */
	private StringBuffer getStringXML(Set<String> files) {
		StringBuffer data = new StringBuffer();
		try {
			data.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
			data.append("<DiffReport>\n");
			data.append("<LeftBase value=\"")
					.append(Extractor.getInstance().getConfiguration()
							.getDirectories().getLeft().getValue())
					.append("\"/>\n");
			data.append("<RightBase value=\"")
					.append(Extractor.getInstance().getConfiguration()
							.getDirectories().getRight().getValue())
					.append("\"/>\n");
			data.append("<Files>\n");
			for (String file : files) {
				data.append("<File name=\"").append(file).append("\"/>\n");
			}
			data.append("</Files>\n");
		} catch (JAXBException e) {
			logger.throwing(XMLRenderer.class.getName(), "getStringXML", e);
		}
		data.append("</DiffReport>\n");
		return data;
	}

}
