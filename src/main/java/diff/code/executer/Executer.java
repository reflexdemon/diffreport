package diff.code.executer;

import java.util.Set;

/**
 * The Interface Executer.
 */
public interface Executer {

	/** The Constant EOL. */
	public static final String EOL = System.getProperty("line.separator", "\n");

	/**
	 * Execute pmd.
	 *
	 * @param files the files
	 * @param reportFileName the report file name
	 * @param baseDir the base dir
	 */
	public abstract void execute(Set<String> files, String reportFileName,
			String baseDir);

}