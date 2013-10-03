package diff.code.util;

import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;

/**
 * The Class DataUtil.
 * 
 * @author Venkateswara VP
 */
public final class DataUtil {

	public static String fileArray[] = {
	    "diff/code/config/Extractor.java",
	    "diff/code/config/xml/DiffReport.java",
	    "diff/code/config/xml/Directories.java",
	    "diff/code/config/xml/Left.java",
	    "diff/code/config/xml/LeftOutput.java",
	    "diff/code/config/xml/LogFile.java",
	    "diff/code/config/xml/ObjectFactory.java",
	    "diff/code/config/xml/PMD.java",
	    "diff/code/config/xml/Plugin.java",
	    "diff/code/config/xml/Plugins.java",
	    "diff/code/config/xml/Report.java",
	    "diff/code/config/xml/Reports.java",
	    "diff/code/config/xml/Right.java",
	    "diff/code/config/xml/RightOutput.java",
	    "diff/code/config/xml/RuleSet.java",
	    "diff/code/plugins/PluginManager.java",
	    "diff/code/plugins/pmd/PMDConstants.java",
	    "diff/code/plugins/pmd/PMDUtils.java",
	    "diff/code/plugins/pmd/analyze/PMDAnalyzer.java",
	    "diff/code/plugins/pmd/executer/PMDExecuter.java",
	    "diff/code/plugins/pmd/report/HTMLPMDRenderer.java",
	    "diff/code/plugins/pmd/report/PMDReport.java",
	    "diff/code/plugins/pmd/report/XMLPMDRenderer.java",
	    "diff/code/reader/TreeDiffReader.java",
	    "diff/code/report/TextRenderer.java",
	    "diff/code/report/XMLRenderer.java",
	    "diff/code/resources/resources.properties",
	    "diff/code/scp/Transport.java",
	    "diff/code/util/CommonUtils.java",
	    "diff/code/util/Constants.java",
	    "diff/code/util/DataUtil.java",
	    "diff/code/util/DiffLogFormatter.java",
	    "diff/code/util/DiffProperty.java",
	    "diff/code/util/LoggingUtils.java",
	    "diff/code/util/PluginUtils.java",
	    "diff/code/util/PropertyLoader.java",
	    "diff/code/util/scp/Transport.java",
	    "diff/code/vo/FileInfo.java",
	    "diff/code/vo/Violation.java",
	    "diff/code/walker/CodeWalker.java",
	    "diff/code/xml/conf/DiffReport.java",
	    "diff/code/xml/conf/Directories.java",
	    "diff/code/xml/conf/Left.java",
	    "diff/code/xml/conf/LeftOutput.java",
	    "diff/code/xml/conf/LogFile.java",
	    "diff/code/xml/conf/ObjectFactory.java",
	    "diff/code/xml/conf/PMD.java",
	    "diff/code/xml/conf/Plugin.java",
	    "diff/code/xml/conf/Plugins.java",
	    "diff/code/xml/conf/Report.java",
	    "diff/code/xml/conf/Reports.java",
	    "diff/code/xml/conf/Right.java",
	    "diff/code/xml/conf/RightOutput.java",
	    "diff/code/xml/conf/RuleSet.java" };
	// new PMDExecuter().executePMD("./src/diff/code/PMDExecuter.java");
	public static Set<String> files = new TreeSet<String>(
			Arrays.asList(fileArray));

	public static final String HTML_HEADER = "<head>\r\n"
			+ "<META http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\r\n"
			+ "<title>\r\n"
			+ "					PMD\r\n"
			+ "					\r\n"
			+ "					Report\r\n"
			+ "				</title>\r\n"
			+ "<style type=\"text/css\">\r\n"
			+ "					body { margin-left: 2%; margin-right: 2%;\r\n"
			+ "					font:normal\r\n"
			+ "					verdana,arial,helvetica; color:#000000; }\r\n"
			+ "					th{ font-weight: bold; text-align:left; background:#a6caf0; }\r\n"
			+ "		</style>\r\n" + "</head>\r\n";

	/**
	 * The main method.
	 * 
	 * @param args
	 *            the arguments
	 */
	public static void main(String[] args) {

	}

}
