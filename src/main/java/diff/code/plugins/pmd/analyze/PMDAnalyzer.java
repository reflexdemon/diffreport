/**
 *Created Date : Aug 18, 2012
 *
 * Version CR/Defect# Modified Date Reason for Change
 * ------- ---------- ------------- ------------------
 * 
 *
 * TODO:: Please add info on how to use the class.
 * 
 * @author Cognizant
 * 
 */
package diff.code.plugins.pmd.analyze;

import java.util.Set;
import java.util.logging.Logger;

import javax.xml.bind.JAXBException;

import diff.code.analyzer.Analyzer;
import diff.code.config.Extractor;
import diff.code.executer.Executer;
import diff.code.plugins.pmd.PMDConstants;
import diff.code.plugins.pmd.executer.PMDExecuter;
import diff.code.util.PluginUtils;

public class PMDAnalyzer implements Analyzer {
	/**
	 * Logging Reference for TreeDiffReader
	 */
	private static final Logger logger = Logger.getLogger(PMDAnalyzer.class
			.getName());
	/** The pmd. */
	Executer pmd = new PMDExecuter();

	/* (non-Javadoc)
	 * @see diff.code.analyzer.Analyzer#leftAnalyze(java.util.Set)
	 */
	public void leftAnalyze(Set<String> files) {
		try {
			String leftOut = PluginUtils.getPluginbyType(PMDConstants.PMD).getLeftOutput().getValue();
			String left = Extractor.getInstance().getConfiguration()
					.getDirectories().getLeft().getValue();
			pmd.execute(files, leftOut, left);
		} catch (JAXBException e) {
			logger.throwing(PMDAnalyzer.class.getName(), "leftAnalyze", e);
		}
	}

	/* (non-Javadoc)
	 * @see diff.code.analyzer.Analyzer#rightAnalyze(java.util.Set)
	 */
	public void rightAnalyze(Set<String> files) {
		try {
			String rightOut = PluginUtils.getPluginbyType(PMDConstants.PMD).getRightOutput().getValue();
			String right = Extractor.getInstance().getConfiguration()
					.getDirectories().getRight().getValue();
			pmd.execute(files, rightOut, right);
		} catch (JAXBException e) {
			logger.throwing(PMDAnalyzer.class.getName(), "rightAnalyze", e);
		}
	}

}
