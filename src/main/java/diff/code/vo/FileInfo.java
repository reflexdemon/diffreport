package diff.code.vo;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

import org.apache.commons.io.FileUtils;


/**
 * The Class FileInfo.
 */
public class FileInfo implements java.io.Serializable {
	/**
	 * Logging Reference for TreeDiffReader
	 */
	private static final Logger logger = Logger.getLogger(FileInfo.class
			.getName());
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 779678530914676392L;

	/** The file. */
	private File file;
	
	/** The class Name. */
	private String className;

	/**
	 * Gets the class name.
	 *
	 * @return the class name
	 */
	public String getClassName() {
		return className;
	}

	/**
	 * Sets the class name.
	 *
	 * @param className the new class name
	 */
	public void setClassName(String className) {
		this.className = className;
	}

	/** The left priority. */
	private Priority leftPriority;

	/** The right priority. */
	private Priority rightPriority;
	
	/** The violations. */
	private List<Violation> violations = null;

	/**
	 * Instantiates a new file info.
	 * 
	 * @param file
	 *            the file
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public FileInfo(File file) throws IOException {
		super();
		this.file = file;
	}

	/**
	 * Instantiates a new file info.
	 */
	public FileInfo() {
	}

	/**
	 * Gets the file.
	 * 
	 * @return the file
	 */
	public File getFile() {
		return file;
	}

	/**
	 * Sets the file.
	 * 
	 * @param file
	 *            the new file
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public void setFile(File file) throws IOException {
		this.file = file;
	}

	/**
	 * Gets the checksum.
	 * 
	 * @return the checksum
	 */
	public long getChecksum() {
		try {
			return FileUtils.checksumCRC32(file);
		} catch (IOException e) {
			logger.throwing(FileInfo.class.getName(), "getChecksum", e);
		}
		return 0;
	}

	/**
	 * @return the leftPriority
	 */
	public Priority getLeftPriority() {
		return leftPriority;
	}

	/**
	 * @param leftPriority
	 *            the leftPriority to set
	 */
	public void setLeftPriority(Priority leftPriority) {
		this.leftPriority = leftPriority;
	}

	/**
	 * @return the rightPriority
	 */
	public Priority getRightPriority() {
		return rightPriority;
	}

	/**
	 * @param rightPriority
	 *            the rightPriority to set
	 */
	public void setRightPriority(Priority rightPriority) {
		this.rightPriority = rightPriority;
	}



	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "FileInfo [file=" + file + ", className=" + className
				+ ", leftPriority=" + leftPriority + ", rightPriority="
				+ rightPriority + "]";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof FileInfo) {
			FileInfo other = (FileInfo) obj;
			return this.hashCode() == other.hashCode();
		}
		return false;
	}

	@Override
	public int hashCode() {
		return (int) this.getChecksum();
	}

	/**
	 * @param violations the violations to set
	 */
	public void setViolations(List<Violation> violations) {
		this.violations = violations;
	}

	/**
	 * @return the violations
	 */
	public List<Violation> getViolations() {
		return violations;
	}

}
