/**
 * 
 */
package diff.code.analyzer;

import java.util.Set;

/**
 * @author Venkateswara VP
 * 
 */
public interface Analyzer {

	/**
	 * Left analyze.
	 * 
	 * @param files
	 *            the files
	 */
	public void leftAnalyze(Set<String> files);

	/**
	 * Right analyze.
	 * 
	 * @param files
	 *            the files
	 */
	public void rightAnalyze(Set<String> files);
}
