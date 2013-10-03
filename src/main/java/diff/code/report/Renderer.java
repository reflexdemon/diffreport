/**
 * 
 */
package diff.code.report;

import java.util.Set;

/**
 * @author Venkateswara
 * 
 */
public interface Renderer {

	/**
	 * Creates the report.
	 * 
	 * @param files
	 *            the files
	 */
	public void createReport(Set<String> files);
}
