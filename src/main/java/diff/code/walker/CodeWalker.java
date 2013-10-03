package diff.code.walker;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Logger;

import org.apache.commons.io.DirectoryWalker;
import org.apache.commons.io.filefilter.CanReadFileFilter;
import org.apache.commons.io.filefilter.FileFilterUtils;
import org.apache.commons.io.filefilter.IOFileFilter;

import diff.code.util.DiffProperty;
import diff.code.vo.FileInfo;

/**
 * The Class CodeWalker.
 */
public class CodeWalker extends DirectoryWalker {

	/**
	 * Logging Reference for CodeWalker
	 */
	private static final Logger logger = Logger.getLogger(CodeWalker.class.getName());
	/** The Constant VERBOSE. */
	private static final boolean VERBOSE = DiffProperty.getBoolean("verbose");

	/** The Constant INFINITY. */
	private static final int INFINITY = -1;

	/** The default filter. */
	static IOFileFilter defaultFilter = applySCMAwareness(CanReadFileFilter.CAN_READ);;

	/**
	 * Instantiates a new code walker.
	 */
	public CodeWalker() {
		super(defaultFilter, INFINITY);
	}

	/**
	 * Apply scm awareness.
	 * 
	 * @param defaultFilter2
	 *            the default filter2
	 * @return the iO file filter
	 */
	private static IOFileFilter applySCMAwareness(IOFileFilter filter) {
		filter = FileFilterUtils.makeCVSAware(filter);
		filter = FileFilterUtils.makeSVNAware(filter);
		return filter;
	}

	public CodeWalker(IOFileFilter dirFilter, IOFileFilter fileFilter) {
		super(dirFilter, fileFilter, -1);
	}

	/**
	 * find files.
	 * 
	 * @param startDirectory
	 *            the start directory
	 * @return the list
	 */
	public Map<String, Long> find(File startDirectory) {
		logger.info("startDirectory: " + startDirectory);
		List<FileInfo> fileDetails = search(startDirectory);
		return toMap(fileDetails, startDirectory.getAbsolutePath().length());
	}

	/**
	 * To map.
	 * 
	 * @param fileDetails
	 *            the file details
	 * @param beginIndex
	 *            the length
	 * @return the map
	 */
	private Map<String, Long> toMap(List<FileInfo> fileDetails, int beginIndex) {
		Map<String, Long> result = new LinkedHashMap<String, Long>();
		for (FileInfo info : fileDetails) {
			result.put(
					info.getFile().getAbsolutePath().substring(beginIndex + 1)
							.replace('\\', '/'), info.getChecksum());
		}
		return result;
	}

	/**
	 * Search.
	 * 
	 * @param startDirectory
	 *            the start directory
	 * @return
	 */
	private List<FileInfo> search(File startDirectory) {
		List<FileInfo> fileDetails = new ArrayList<FileInfo>();
		try {
			walk(startDirectory, fileDetails);
		} catch (IOException e) {
			logger.throwing(CodeWalker.class.getName(), "search", e);
		}
		return fileDetails;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.commons.io.DirectoryWalker#handleFile(java.io.File, int,
	 * java.util.Collection)
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void handleFile(File file, int depth, Collection results) {
		if (VERBOSE) {
			logger.finest("Adding " + file);
		}

		try {
			FileInfo info = new FileInfo(file);
			results.add(info);
		} catch (IOException e) {
			logger.throwing(CodeWalker.class.getName(), "handleFile", e);
		}
	}

	/**
	 * The main method.
	 * 
	 * @param strings
	 *            the arguments
	 */
	public static void main(String... strings) {
		CodeWalker walker = new CodeWalker();
		File startDirectory;
		startDirectory = new File(".");
		Map<String, Long> files = walker.find(startDirectory);
		logger.info("Added " + files.size() + " files.");
		for (Entry<String, Long> entry : files.entrySet()) {
			logger.finest(entry.getKey() + "\t" + entry.getValue());
		}

	}
}
