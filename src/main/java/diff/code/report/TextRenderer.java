/**
 * 
 */
package diff.code.report;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Set;
import java.util.logging.Logger;

import diff.code.util.CommonUtils;
import diff.code.util.Constants;

/**
 * @author Venkateswara
 *
 */
public class TextRenderer implements Renderer {

	

	/**
	 * Logging Reference for TextRenderer
	 */
	private static final Logger logger = Logger.getLogger(TextRenderer.class
			.getName());
	/* (non-Javadoc)
	 * @see diff.code.report.Renderer#createReport(java.lang.String, java.util.Set)
	 */
	public void createReport(Set<String> files) {
		FileWriter out = null;
		try {
			out = new FileWriter(CommonUtils.getReportFile(Constants.REPORT_TEXT));
			for(String string : files) {
				out.write(string + "\n");
			}

		} catch (IOException e) {
			logger.throwing(TextRenderer.class.getName(), "createReport", e);
		} finally {
			if (null != out) {
				try {
					out.close();
				} catch (IOException e) {
					// Ignore
				}
			}
		}
				
	}

}
