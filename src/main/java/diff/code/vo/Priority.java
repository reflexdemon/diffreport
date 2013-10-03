/**
 * 
 */
package diff.code.vo;

import java.io.Serializable;

/**
 * @author Venkateswara
 *
 */
public class Priority implements Serializable {

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Priority [priority1Count=" + priority1Count
				+ ", priority2Count=" + priority2Count + ", priority3Count="
				+ priority3Count + ", priority4Count=" + priority4Count
				+ ", priority5Count=" + priority5Count + "]";
	}
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -8126768163226987714L;

	/** The priority1 count. */
	private long priority1Count = 0;
	
	/** The priority2 count. */
	private long priority2Count = 0;
	
	/** The priority3 count. */
	private long priority3Count = 0;
	
	/** The priority4 count. */
	private long priority4Count = 0;
	
	/** The priority5 count. */
	private long priority5Count = 0;



	
	/**
	 * @return the priority1Count
	 */
	public long getPriority1Count() {
		return priority1Count;
	}
	/**
	 * @param priority1Count the priority1Count to set
	 */
	public void setPriority1Count(long priority1Count) {
		this.priority1Count = priority1Count;
	}
	/**
	 * @return the priority2Count
	 */
	public long getPriority2Count() {
		return priority2Count;
	}
	/**
	 * @param priority2Count the priority2Count to set
	 */
	public void setPriority2Count(long priority2Count) {
		this.priority2Count = priority2Count;
	}
	/**
	 * @return the priority31Count
	 */
	public long getPriority3Count() {
		return priority3Count;
	}
	/**
	 * @param priority31Count the priority31Count to set
	 */
	public void setPriority31Count(long priority3Count) {
		this.priority3Count = priority3Count;
	}
	/**
	 * @return the priority4Count
	 */
	public long getPriority4Count() {
		return priority4Count;
	}
	/**
	 * @param priority4Count the priority4Count to set
	 */
	public void setPriority4Count(long priority4Count) {
		this.priority4Count = priority4Count;
	}
	/**
	 * @return the priority5Count
	 */
	public long getPriority5Count() {
		return priority5Count;
	}
	/**
	 * @param priority5Count the priority5Count to set
	 */
	public void setPriority5Count(long priority5Count) {
		this.priority5Count = priority5Count;
	}
	
	
	/**
	 * Adds the priority1 count.
	 */
	public void addPriority1Count() {
		++priority1Count;
	}
	
	/**
	 * Adds the priority2 count.
	 */
	public void addPriority2Count() {
		++priority2Count;
	}
	/**
	 * Adds the priority3 count.
	 */
	public void addPriority3Count() {
		++priority3Count;
	}
	/**
	 * Adds the priority4 count.
	 */
	public void addPriority4Count() {
		++priority4Count;
	}
	/**
	 * Adds the priority5 count.
	 */
	public void addPriority5Count() {
		++priority5Count;
	}

}
