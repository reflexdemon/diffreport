/**
 * 
 */
package diff.code.plugins.pmd;

import static diff.code.util.CommonUtils.NEW_LINE;
import static diff.code.util.CommonUtils.getClassName;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.logging.Logger;

import javax.xml.bind.JAXBException;

import diff.code.config.Extractor;
import diff.code.plugins.pmd.report.PMDReport;
import diff.code.plugins.pmd.xml.Violation;
import diff.code.util.CommonUtils;
import diff.code.util.DataUtil;
import diff.code.util.PluginUtils;
import diff.code.vo.FileInfo;
import diff.code.vo.Priority;

/**
 * @author Venkateswara VP
 * 
 */
public final class PMDUtils {

	/**
	 * Logging Reference for Transport
	 */
	private static final Logger logger = Logger.getLogger(PMDUtils.class.getName());

	/**
	 * Gets the violations.
	 * 
	 * @param outputValue
	 *            the value
	 * @param reportFile
	 *            the report file
	 * @return the violations
	 * @throws JAXBException
	 *             the jAXB exception
	 */
	public static Map<String, List<Violation>> getViolations(
			String outputValue, String reportFile) throws JAXBException {
		PMDReport report = PMDReport.getInstance(outputValue);
		report.processReport(reportFile);
		return report.getViolations();
	}

	/**
	 * Gets the string xml.
	 * 
	 * @param files
	 *            the files
	 * @return the string xml
	 * @throws JAXBException
	 */
	public static StringBuilder getStringXML(Set<String> files)
			throws JAXBException {

		Map<String, List<Violation>> left = getLeft(files);
		Map<String, List<Violation>> right = getRight(files);

		StringBuilder data = new StringBuilder();
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
				String className = getClassName(file);
				data.append("<File name=\"").append(file).append("\">\n");
				data.append("<Left>\n");
				data.append(appendViolations(className, left));
				data.append("</Left>\n");
				data.append("<Right>\n");
				data.append(appendViolations(className, right));
				data.append("</Right>\n");
				data.append("</File>\n");
			}
			data.append("</Files>\n");
		} catch (JAXBException e) {
			logger.throwing(CommonUtils.class.getName(), "getStringXML", e);
		}
		data.append("</DiffReport>\n");
		return data;
	}

	/**
	 * Gets the right.
	 * 
	 * @param files
	 *            the files
	 * @return the right
	 * @throws JAXBException
	 *             the jAXB exception
	 */
	public static Map<String, List<Violation>> getRight(Set<String> files)
			throws JAXBException {
		return getViolations(PluginUtils.getPluginbyType(PMDConstants.PMD)
				.getRightOutput().getValue(), Extractor.getInstance()
				.getConfiguration().getDirectories().getRight().getValue());
	}

	/**
	 * Gets the left.
	 * 
	 * @param files
	 *            the files
	 * @return the left
	 * @throws JAXBException
	 *             the jAXB exception
	 */
	public static Map<String, List<Violation>> getLeft(Set<String> files)
			throws JAXBException {
		return getViolations(PluginUtils.getPluginbyType(PMDConstants.PMD)
				.getLeftOutput().getValue(), Extractor.getInstance()
				.getConfiguration().getDirectories().getLeft().getValue());
	}

	/**
	 * Gets the file data.
	 * 
	 * @param left
	 *            the left
	 * @param right
	 *            the right
	 * @return the file data
	 */
	public static List<FileInfo> getFileData(Map<String, List<Violation>> left,
			Map<String, List<Violation>> right) {
		List<FileInfo> output = new ArrayList<FileInfo>();
		Set<Entry<String, List<Violation>>> files = right.entrySet();
		for (Entry<String, List<Violation>> entry : files) {
			FileInfo info = new FileInfo();
			String className = entry.getKey();
			info.setClassName(className);
			final List<Violation> rightList = entry.getValue();
			Priority rPriority = getPriority(rightList), lPriority = null;
			info.setRightPriority(rPriority);
			if (left.containsKey(className)) {
				lPriority = getPriority(left.get(className));
				info.setLeftPriority(lPriority);
			}
			List<Violation> leftList = left.get(className);
			info.setViolations(removeDuplicateEntries(rightList, leftList));
			output.add(info);
		}
		return output;
	}

	/**
	 * Removes the duplicate entries.
	 * 
	 * @param violations
	 *            the violations
	 * @param list
	 *            the list
	 * @return the list
	 */
	private static List<diff.code.vo.Violation> removeDuplicateEntries(
			List<Violation> violations, List<Violation> list) {
		List<diff.code.vo.Violation> result = new ArrayList<diff.code.vo.Violation>();
		if (null == list) {
			return getViolations(violations);
		}
		List<diff.code.vo.Violation> left = getViolations(list);
		List<diff.code.vo.Violation> right = getViolations(violations);
		for (diff.code.vo.Violation r : right) {
			boolean found = false;
			for (diff.code.vo.Violation l : left) {
				if (l.equals(r)) {
					found = true;
					break;
				}
			}
			if (!found) {
				result.add(r);
			}
		}
		logger.finest("Added " + result.size());
		return result;

	}

	/**
	 * Gets the violations.
	 * 
	 * @param violations
	 *            the violations
	 * @return the violations
	 */
	private static List<diff.code.vo.Violation> getViolations(
			List<Violation> violations) {
		List<diff.code.vo.Violation> result = new ArrayList<diff.code.vo.Violation>();
		for (Violation v : violations) {
			diff.code.vo.Violation newobj = new diff.code.vo.Violation();
			newobj.setBegincolumn(v.getBegincolumn());
			newobj.setBeginline(v.getBeginline());
			newobj.setEndcolumn(v.getEndcolumn());
			newobj.setEndline(v.getEndline());
			newobj.setPriority(v.getPriority());
			newobj.setPackage(v.getPackage());
			newobj.setClazz(v.getClazz());
			newobj.setContent(v.getContent());
			newobj.setExternalInfoUrl(v.getExternalInfoUrl());
			newobj.setMethod(v.getMethod());
			newobj.setRule(v.getRule());
			newobj.setRuleset(v.getRuleset());
			newobj.setVariable(v.getVariable());
			result.add(newobj);
		}

		return result;
	}

	/**
	 * Gets the priority.
	 * 
	 * @param files
	 *            the file collection
	 * @return the priority
	 */
	public static Priority getPriority(final List<Violation> violations) {
		Priority rPriority = new Priority();

		for (Violation volation : violations) {
			BigInteger p = volation.getPriority();
			if (p.intValue() == 1) {
				rPriority.addPriority1Count();
			} else if (p.intValue() == 2) {
				rPriority.addPriority2Count();
			} else if (p.intValue() == 3) {
				rPriority.addPriority3Count();
			} else if (p.intValue() == 4) {
				rPriority.addPriority4Count();
			} else if (p.intValue() == 5) {
				rPriority.addPriority5Count();
			}
		}

		return rPriority;
	}

	/**
	 * Append violations.
	 * 
	 * @param className
	 *            the class name
	 * @param violations
	 *            the violations
	 * @return the string
	 */
	public static String appendViolations(String className,
			Map<String, List<Violation>> violations) {
		StringBuilder data = new StringBuilder();
		long p1 = 0, p2 = 0, p3 = 0, p4 = 0, p5 = 0;
		if (violations.containsKey(className)) {

			for (Violation volation : violations.get(className)) {
				BigInteger p = volation.getPriority();

				if (p.intValue() == 1) {
					++p1;
				} else if (p.intValue() == 2) {
					++p2;
				} else if (p.intValue() == 3) {
					++p3;
				} else if (p.intValue() == 4) {
					++p4;
				} else if (p.intValue() == 5) {
					++p5;
				}
			}
			data.append("<P1>").append(p1).append("</P1>");
			data.append("<P2>").append(p2).append("</P2>");
			data.append("<P3>").append(p3).append("</P3>");
			data.append("<P4>").append(p4).append("</P4>");
			data.append("<P5>").append(p5).append("</P5>");
		}
		return data.toString();
	}

	/**
	 * Gets the string html.
	 * 
	 * @param report
	 *            the report
	 * @return the string html
	 * @throws JAXBException
	 */
	public static StringBuilder getStringHTML(List<FileInfo> report)
			throws JAXBException {
		StringBuilder data = new StringBuilder();
		StringBuilder details = new StringBuilder();
		data.append("<html>").append(NEW_LINE);
		data.append(DataUtil.HTML_HEADER).append(NEW_LINE);
		data.append("<body>").append(NEW_LINE);
		data.append("<h1>").append(NEW_LINE);
		data.append("PMD Delta Report").append(NEW_LINE);
		data.append("</h1>").append(NEW_LINE);
		data.append("<table border=\"1\" class=\"sortable\">").append(NEW_LINE);
		data.append("<tr>").append(NEW_LINE);
		data.append("<th>").append("Left").append("</th>").append(NEW_LINE);
		data.append("<td>")
				.append(Extractor.getInstance().getConfiguration()
						.getDirectories().getLeft().getValue()).append("</td>")
				.append(NEW_LINE);
		data.append("</tr>").append(NEW_LINE);
		data.append("<tr>").append(NEW_LINE);
		data.append("<th>").append("Right").append("</th>").append(NEW_LINE);
		data.append("<td>")
				.append(Extractor.getInstance().getConfiguration()
						.getDirectories().getRight().getValue())
				.append("</td>").append(NEW_LINE);
		data.append("</tr>").append(NEW_LINE);
		data.append("</table>").append(NEW_LINE);

		data.append("<table border=\"1\" class=\"sortable\" ").append("cellpadding=\"2\" cellspacing=\"0\" width=\"80%\"").append(">").append(NEW_LINE);
		data.append(
				"<tr><th>FileName</th><th>P1</th><th>P2</th><th>P3</th><th>P4</th><th>P5</th></tr>")
				.append(NEW_LINE);
		details.append("<table border=\"1\" class=\"sortable\">").append(
				NEW_LINE);
		details.append("<tr>").append(NEW_LINE);
		details.append(
				"<tr><th>Priority</th><th>Rule</th><th>Ruleset</th><th>Comments</th><th>LineNumber</th></tr>")
				.append(NEW_LINE);
		details.append("</tr>").append(NEW_LINE);
		for (FileInfo info : report) {
			data.append("<tr>").append(NEW_LINE);
			data.append("<td>").append(info.getClassName()).append("</td>")
					.append(NEW_LINE);
			for (int p = 1; p <= 5; p++) {
				long right = getPriority(info.getRightPriority(), p);
				long left = getPriority(info.getLeftPriority(), p);
				//This div by 2 is irritating bug - I had no other option
				long violations = (right - left)/2;
				data.append("<td ");
				data.append("title=\"").append("R(").append(right)
						.append(") - L(").append(left).append(") = ")
						.append(violations).append("\"");
				if (violations > 0) {
					data.append(" bgcolor=\"red\" ");
				} else if (violations < 0) {
					data.append(" bgcolor=\"green\" ");
				}
				data.append(">");
				if (violations == 0) {
				    data.append("&nbsp;");
				} else {
				    data.append(violations);				    
				}
				data.append("</td>").append(NEW_LINE);
			}

			// Get detailed list of violations
			if (null != info.getViolations()
					&& info.getViolations().size() != 0) {
				details.append("<tr>").append(NEW_LINE);
				details.append("<th colspan=\"5\">").append(NEW_LINE);
				details.append(info.getClassName());
				details.append("</th>").append(NEW_LINE);
				details.append("</tr>").append(NEW_LINE);
				Set<String> duplicateIndexer = new HashSet<String>();
				for (diff.code.vo.Violation v : info.getViolations()) {
				    	String superKey = getSuperKey(v);
				    	if (!duplicateIndexer.contains(superKey)) {
				    	    	duplicateIndexer.add(superKey);
						details.append("<tr>").append(NEW_LINE);

						details.append("<td>");
						details.append(v.getPriority());
						details.append("</td>").append(NEW_LINE);

						details.append("<td>");
						details.append(v.getRule());
						details.append("</td>").append(NEW_LINE);

						details.append("<td>");
						details.append(v.getRuleset());
						details.append("</td>").append(NEW_LINE);

						details.append("<td>");
						details.append(v.getContent());
						details.append("</td>").append(NEW_LINE);

						details.append("<td>");
						if (v.getEndline().intValue() == v.getBeginline().intValue()) {
							details.append(v.getBeginline());
						} else {
							details.append(v.getBeginline()).append("-")
									.append(v.getEndline());
						}
						details.append("</td>").append(NEW_LINE);

						details.append("</tr>").append(NEW_LINE);
				    	    
				    	}
				}
			}

			data.append("</tr>").append(NEW_LINE);
		}
		details.append("</table>").append(NEW_LINE);
		data.append("</table>").append(NEW_LINE);
		data.append("<br>").append("<br>").append("<br>");
		data.append(details.toString());
		data.append("</body>").append(NEW_LINE);
		data.append("</html>").append(NEW_LINE);
		return data;
	}

	/**
	 * Get super key.
	 * The sole intent behind this method is to avoid duplicates
	 *
	 * @param v the v
	 * @return the string
	 */
	private static String getSuperKey(diff.code.vo.Violation v) {
	    return v.getRule() + v.getBeginline() + v.getPriority();
	}

	/**
	 * Gets the priority.
	 * 
	 * @param priority
	 *            the priority
	 * @param p
	 *            the p
	 * @return the priority
	 */
	private static long getPriority(Priority priority, int p) {
		if (null != priority) {
			switch (p) {
			case 1:
				return priority.getPriority1Count();
			case 2:
				return priority.getPriority2Count();
			case 3:
				return priority.getPriority3Count();
			case 4:
				return priority.getPriority4Count();
			case 5:
				return priority.getPriority5Count();
			}
		}

		return 0;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
