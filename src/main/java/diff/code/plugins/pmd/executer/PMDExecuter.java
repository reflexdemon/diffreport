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
package diff.code.plugins.pmd.executer;

import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;
import java.util.logging.Logger;

import net.sourceforge.pmd.DataSource;
import net.sourceforge.pmd.FileDataSource;
import net.sourceforge.pmd.PMD;
import net.sourceforge.pmd.PMDException;
import net.sourceforge.pmd.Report;
import net.sourceforge.pmd.Rule;
import net.sourceforge.pmd.RuleContext;
import net.sourceforge.pmd.RuleSet;
import net.sourceforge.pmd.RuleSetFactory;
import net.sourceforge.pmd.RuleSetNotFoundException;
import net.sourceforge.pmd.RuleSets;
import net.sourceforge.pmd.SourceType;
import net.sourceforge.pmd.renderers.Renderer;
import net.sourceforge.pmd.renderers.XMLRenderer;
import diff.code.config.Extractor;
import diff.code.config.xml.Plugin;
import diff.code.executer.Executer;
import diff.code.plugins.pmd.PMDConstants;
import diff.code.util.DataUtil;
import diff.code.util.DiffProperty;
import diff.code.util.PluginUtils;

/**
 * The Class PMDExecuter.
 */
public class PMDExecuter implements Executer {

	/**
	 * Logging Reference for PMDExecuter
	 */
	private static final Logger logger = Logger.getLogger(PMDExecuter.class.getName());
	

	/** The rule set files. */
	String ruleSetFiles = null;

	/** The report file. */
	String reportFile = null;

	/** The renderer. */
	Renderer renderer = null;

	/** The rs. */
	private RuleSets rs;

	/** The pmd. */
	private static PMD pmd = new PMD();

	/**
	 * Instantiates a new pMD executer.
	 */
	public PMDExecuter() {
		init();
	}

	private void init() {
		try {
			Plugin plugin = PluginUtils.getPluginbyType(PMDConstants.PMD);
			ruleSetFiles = plugin.getRuleSet().getValue();
			reportFile = plugin.getLeftOutput().getValue();
			renderer = new XMLRenderer();
			final ClassLoader classLoader = getClass().getClassLoader();

			RuleSetFactory ruleSetFactory = new RuleSetFactory() {
				public RuleSets createRuleSets(String ruleSetFileNames)
						throws RuleSetNotFoundException {
					return createRuleSets(ruleSetFiles, classLoader);
				}
			};
			// This is just used to validate and display rules. Each thread will
			// create its own ruleset
			ruleSetFactory.setMinimumPriority(Rule.LOWEST_PRIORITY);
			rs = ruleSetFactory.createRuleSets(ruleSetFiles, classLoader);
			logRulesUsed(rs);
		} catch (RuleSetNotFoundException e) {
			logger.throwing(PMDExecuter.class.getName(), "init", e);
		}
	}

	static {
		pmd.setJavaVersion(SourceType.JAVA_16);
	}

	/**
	 * Execute.
	 * 
	 * @param javaFile
	 *            the java file
	 * @throws Exception
	 *             the exception
	 */
	public void executePMD(final String javaFile) throws Exception {
		RuleContext ctx = new RuleContext();
		Report report = new Report();
		Writer w = null;
		ctx.setReport(report);
		if (reportFile != null) {
			w = new BufferedWriter(new FileWriter(reportFile));
		} else {
			w = new OutputStreamWriter(System.out);
		}
		renderer.setWriter(w);
		renderer.start();

		DataSource dataSource = new FileDataSource(new File(javaFile));
		String niceFileName = dataSource.getNiceFileName(false, javaFile);
		ctx.setSourceCodeFilename(niceFileName);
		ctx.setSourceCodeFile(new File(niceFileName));
		logger.info("Processing " + ctx.getSourceCodeFilename());
		rs.start(ctx);
		renderer.startFileAnalysis(dataSource);
		InputStream stream = new BufferedInputStream(
				dataSource.getInputStream());
		pmd.processFile(stream, DiffProperty.getValue("file.encoding"), rs, ctx);
		renderer.renderFileReport(report);
		w.write(EOL);

		renderer.end();
		w.flush();
		if (reportFile != null) {
			w.close();
			w = null;
		}

	}

	/**
	 * Execute pmd.
	 *
	 * @param files the files
	 * @param reportFileName the report file name
	 * @param baseDir the base dir
	 */
	public void executePMD(Set<String> files, String reportFileName,
			String baseDir) {
		RuleContext ctx = new RuleContext();
		Report report = new Report();
		Writer w = null;
		try {
			ctx.setReport(report);
			if (reportFileName != null) {
				w = new BufferedWriter(new FileWriter(reportFileName));
			} else {
				w = new OutputStreamWriter(System.out);
			}
			renderer.setWriter(w);
			renderer.start();

			rs.start(ctx);
			processFile(files, ctx, baseDir);
			renderer.renderFileReport(report);
			w.write(EOL);
			renderer.end();
			w.flush();
		} catch (IOException e) {
			logger.throwing(PMDExecuter.class.getName(), "executePMD", e);
		} catch (PMDException e) {
			logger.throwing(PMDExecuter.class.getName(), "executePMD", e);
		} finally {
			if (null != reportFile && null != w) {
				try {
					w.close();
				} catch (IOException e) {
					// Ignore
				}
				w = null;
			}
		}

	}

	/**
	 * Process file.
	 * 
	 * @param ctx
	 *            the ctx
	 * @param baseDir
	 *            the base dir
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 * @throws PMDException
	 *             the pMD exception
	 */
	private void processFile(Set<String> files, RuleContext ctx, String baseDir)
			throws IOException, PMDException {
		for (String javaFile : files) {

			File file = new File(baseDir + "/" + javaFile);
			if (file.exists() && file.getAbsolutePath().endsWith(DiffProperty.getValue("extention.java"))) {
				process(file, javaFile, ctx);
			}
		}

	}

	
	
	/**
	 * Process.
	 *
	 * @param file the file
	 * @param javaFile the java file
	 * @param ctx the ctx
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws PMDException the pMD exception
	 */
	private void process(File file, String javaFile, RuleContext ctx) throws IOException, PMDException {
		DataSource dataSource = new FileDataSource(file);
		String niceFileName = dataSource.getNiceFileName(false,
				javaFile);
		ctx.setSourceCodeFilename(niceFileName);
		ctx.setSourceCodeFile(new File(niceFileName));
		logger.fine("Processing " + ctx.getSourceCodeFilename());
		renderer.startFileAnalysis(dataSource);
		InputStream stream = new BufferedInputStream(
				dataSource.getInputStream());
		pmd.processFile(stream, DiffProperty.getValue("file.encoding"), rs, ctx);
	}

	/**
	 * Log rules used.
	 * 
	 * @param rules
	 *            the rules
	 */
	private void logRulesUsed(RuleSets rules) {
		logger.info("Using these rulesets: " + ruleSetFiles);

		RuleSet[] ruleSets = rules.getAllRuleSets();
		for (int j = 0; j < ruleSets.length; j++) {
			RuleSet ruleSet = ruleSets[j];

			for (Rule rule : ruleSet.getRules()) {
				logger.fine("Using rule " + rule.getName());
			}
		}
	}

	/**
	 * The main method.
	 * 
	 * @param strings
	 *            the arguments
	 */
	public static void main(String... strings) {
		try {
			// new PMDExecuter().executePMD("./src/diff/code/PMDExecuter.java");
			Plugin plugin = PluginUtils.getPluginbyType(PMDConstants.PMD);
			Set<String> files = new TreeSet<String>(
					Arrays.asList(DataUtil.fileArray));
			String reportFileName = plugin.getLeftOutput().getValue();
			String baseDir = Extractor.getInstance().getConfiguration()
					.getDirectories().getLeft().getValue();
			new PMDExecuter().executePMD(files, reportFileName, baseDir);
		} catch (Exception e) {
			logger.throwing(PMDExecuter.class.getName(), "main", e);
		}
	}

	/* (non-Javadoc)
	 * @see diff.code.executer.Executer#execute(java.util.Set, java.lang.String, java.lang.String)
	 */
	public void execute(Set<String> files, String reportFileName, String baseDir) {
		executePMD(files, reportFileName, baseDir);
		
	}

}
