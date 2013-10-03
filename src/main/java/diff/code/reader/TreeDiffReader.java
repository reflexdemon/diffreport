/**
 *Created Date : Aug 18, 2012
 *
 * 
 * @author Cognizant
 * 
 */
package diff.code.reader;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeSet;
import java.util.logging.Logger;

import javax.xml.bind.JAXBException;

import diff.code.analyzer.Analyzer;
import diff.code.config.Extractor;
import diff.code.config.xml.DiffReport;
import diff.code.config.xml.Report;
import diff.code.report.Renderer;
import diff.code.util.CommonUtils;
import diff.code.util.LoggingUtils;
import diff.code.util.PluginUtils;
import diff.code.walker.CodeWalker;

/**
 * The Class TreeDiff.
 */
public class TreeDiffReader {


	/**
	 * Logging Reference for TreeDiffReader
	 */
	private static final Logger logger = Logger.getLogger(TreeDiffReader.class
			.getName());

	/** The left folder. */
	private static String leftFolder = null;

	/** The right folder. */
	private static String rightFolder = null;

	/** The left. */
	private Map<String, Long> left = null;

	/** The right. */
	private Map<String, Long> right = null;

	/** The result. */
	private Map<String, Long> result = new HashMap<String, Long>();

	/** The out file. */
	private static boolean processPlugins = false;

	/** The basic renderers. */
	private static List<Renderer> basicRenderers = new ArrayList<Renderer>();

	/** The plugin renderers. */
	private static List<Renderer> pluginRenderers = new ArrayList<Renderer>();

	/**
	 * Scan files.
	 */
	public void scanFiles() {
		CodeWalker walker = new CodeWalker();
		left = walker.find(new File(leftFolder));
		right = walker.find(new File(rightFolder));
	}

	/**
	 * Diffrence.
	 * 
	 * @return the sets the
	 */
	public Set<String> diff() {
		Set<String> list = new TreeSet<String>();
		// Parse
		for (Entry<String, Long> rightEntry : right.entrySet()) {
			Long value = left.get(rightEntry.getKey());
			// log("Left:" + value + "\tRight:"
			// + rightEntry.getValue());
			if (null == value
					|| value.longValue() != rightEntry.getValue().longValue()) {
				result.put(rightEntry.getKey(), rightEntry.getValue());
				list.add(rightEntry.getKey());
			}
		}
		for (Entry<String, Long> leftEntry : left.entrySet()) {
			Long value = right.get(leftEntry.getKey());
			// log("Left:" + value + "\tRight:"
			// + rightEntry.getValue());
			if (null == value
					|| value.longValue() != leftEntry.getValue().longValue()) {
				result.put(leftEntry.getKey(), leftEntry.getValue());
				list.add(leftEntry.getKey());
			}
		}
		return list;
	}

	/**
	 * Tested with: D:\272920\eclipse_workspace\12.3\CBO_12.3\src
	 * D:\272920\eclipse_workspace\12.4\CBO_12.4\src output.txt The main method.
	 * 
	 * @param args
	 *            the arguments
	 */
	public static void main(String... args) {

		try {
			LoggingUtils.initDefaultApplicationLogging();
		} catch (IOException e) {
			e.printStackTrace();
		}
		logger.info("Parsing input");
		if (!handleArguments(args)) {
			return;
		}

		TreeDiffReader reader = new TreeDiffReader();
		logger.info("Scaning files");
		reader.scanFiles();
		logger.info("Obtaining Diff.");
		Set<String> files = reader.diff();
		//Set<String> files = DataUtil.files;
		logger.fine("Following files has conflicts while processing...");
		for (String file : files) {
			logger.fine(file);
		}

		renderReport(basicRenderers, files);

		if (processPlugins) {
			logger.info("Processing Plugins...");
			for (String pType : PluginUtils.getPluginTypes()) {
				for (Report r : PluginUtils.getPluginbyType(pType).getReport()) {
					String rType = r.getType();
					if (null != rType) {
						pluginRenderers.add(PluginUtils.getRendererForType(
								pType, rType));
					}
				}
			}
			performAnalysis(files);
			renderReport(pluginRenderers, files);
		}

		logger.info("Total conflicts:" + files.size());
	}

	/**
	 * Render report.
	 * 
	 * @param renderers
	 *            the renderers
	 * @param files
	 *            the files
	 */
	private static void renderReport(List<Renderer> renderers, Set<String> files) {
		for (Renderer output : renderers) {
			output.createReport(files);
		}
	}

	/**
	 * Perform analysis.
	 * 
	 * @param files
	 *            the files
	 */
	private static void performAnalysis(Set<String> files) {
		for (String pluginType : PluginUtils.getPluginTypes()) {
			Analyzer analysis = PluginUtils.getAnalyzerForType(pluginType);
			analysis.leftAnalyze(files);
			analysis.rightAnalyze(files);
		}
	}

	/**
	 * Handle arguments.
	 * 
	 * @param args
	 *            the args
	 */
	private static boolean handleArguments(String[] args) {
		logger.config("Arguments " + Arrays.asList(args).toString());
		if (null == args || args.length == 0 || args.length > 3) {
			printUsage();
			return false;
		} else if (args.length == 1) {
			try {
				DiffReport config = Extractor.getInstance(args[0])
						.getConfiguration();
				leftFolder = config.getDirectories().getLeft().getValue();
				rightFolder = config.getDirectories().getRight().getValue();
				for (Report r : CommonUtils.getReports().getReport()) {
					String type = r.getType();
					Renderer render = CommonUtils.getBasicRenderer(type);
					basicRenderers.add(render);
				}
				processPlugins = null != config.getPlugins();
			} catch (JAXBException e) {
				logger.throwing(TreeDiffReader.class.getName(),
						"handleArguments", e);
			}
		} else if (args.length == 2) {
			leftFolder = args[0];
			rightFolder = args[1];
		} else if (args.length == 3) {
			leftFolder = args[0];
			rightFolder = args[1];
		}

		return true;
	}

	/**
	 * Prints the usage.
	 */
	private static void printUsage() {
		
		System.out.println("Usage:\n\tjava -jar diffreport<version>.jar dir1 dir2 <optional-outputFile>");
		System.out.println("(or)\tjava -jar diffreport<version>.jar <configFileName>");

	}
}
