package diff.code.vo;

import java.math.BigInteger;

/**
 * The Class Violation.
 */
public class Violation {

	/** The beginline. */
	protected BigInteger beginline;
	
	/** The endcolumn. */
	protected BigInteger endcolumn;
	
	/** The endline. */
	protected BigInteger endline;
	
	/** The priority. */
	protected BigInteger priority;
	
	/** The _package. */
	protected String _package;
	
	/** The clazz. */
	protected String clazz;
	
	/** The content. */
	protected String content;
	
	/** The external info url. */
	protected String externalInfoUrl;
	
	/** The method. */
	protected String method;
	
	/** The rule. */
	protected String rule;
	
	/** The ruleset. */
	protected String ruleset;
	
	/** The variable. */
	protected String variable;
	
	/** The begincolumn. */
	protected BigInteger begincolumn;
	
	/** The linenumber. */
	protected BigInteger linenumber;

	
	/**
	 * @return the linenumber
	 */
	public BigInteger getLinenumber() {
	    return linenumber;
	}

	/**
	 * @param linenumber the linenumber to set
	 */
	public void setLinenumber(BigInteger linenumber) {
	    this.linenumber = linenumber;
	}

	/**
	 * Gets the begincolumn.
	 *
	 * @return the begincolumn
	 */
	public BigInteger getBegincolumn() {
		return begincolumn;
	}
	
	/**
	 * Sets the begincolumn.
	 *
	 * @param begincolumn the begincolumn to set
	 */
	public void setBegincolumn(BigInteger begincolumn) {
		this.begincolumn = begincolumn;
	}
	
	/**
	 * Gets the beginline.
	 *
	 * @return the beginline
	 */
	public BigInteger getBeginline() {
		return beginline;
	}
	
	/**
	 * Sets the beginline.
	 *
	 * @param beginline the beginline to set
	 */
	public void setBeginline(BigInteger beginline) {
		this.beginline = beginline;
	}
	
	/**
	 * Gets the endcolumn.
	 *
	 * @return the endcolumn
	 */
	public BigInteger getEndcolumn() {
		return endcolumn;
	}
	
	/**
	 * Sets the endcolumn.
	 *
	 * @param endcolumn the endcolumn to set
	 */
	public void setEndcolumn(BigInteger endcolumn) {
		this.endcolumn = endcolumn;
	}
	
	/**
	 * Gets the endline.
	 *
	 * @return the endline
	 */
	public BigInteger getEndline() {
		return endline;
	}
	
	/**
	 * Sets the endline.
	 *
	 * @param endline the endline to set
	 */
	public void setEndline(BigInteger endline) {
		this.endline = endline;
	}
	
	/**
	 * Gets the priority.
	 *
	 * @return the priority
	 */
	public BigInteger getPriority() {
		return priority;
	}
	
	/**
	 * Sets the priority.
	 *
	 * @param priority the priority to set
	 */
	public void setPriority(BigInteger priority) {
		this.priority = priority;
	}
	
	/**
	 * Gets the _package.
	 *
	 * @return the _package
	 */
	public String getPackage() {
		return _package;
	}
	
	/**
	 * Sets the _package.
	 *
	 * @param _package the _package to set
	 */
	public void setPackage(String _package) {
		this._package = _package;
	}
	
	/**
	 * Gets the clazz.
	 *
	 * @return the clazz
	 */
	public String getClazz() {
		return clazz;
	}
	
	/**
	 * Sets the clazz.
	 *
	 * @param clazz the clazz to set
	 */
	public void setClazz(String clazz) {
		this.clazz = clazz;
	}
	
	/**
	 * Gets the content.
	 *
	 * @return the content
	 */
	public String getContent() {
		return content;
	}
	
	/**
	 * Sets the content.
	 *
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}
	
	/**
	 * Gets the external info url.
	 *
	 * @return the externalInfoUrl
	 */
	public String getExternalInfoUrl() {
		return externalInfoUrl;
	}
	
	/**
	 * Sets the external info url.
	 *
	 * @param externalInfoUrl the externalInfoUrl to set
	 */
	public void setExternalInfoUrl(String externalInfoUrl) {
		this.externalInfoUrl = externalInfoUrl;
	}
	
	/**
	 * Gets the method.
	 *
	 * @return the method
	 */
	public String getMethod() {
		return method;
	}
	
	/**
	 * Sets the method.
	 *
	 * @param method the method to set
	 */
	public void setMethod(String method) {
		this.method = method;
	}
	
	/**
	 * Gets the rule.
	 *
	 * @return the rule
	 */
	public String getRule() {
		return rule;
	}
	
	/**
	 * Sets the rule.
	 *
	 * @param rule the rule to set
	 */
	public void setRule(String rule) {
		this.rule = rule;
	}
	
	/**
	 * Gets the ruleset.
	 *
	 * @return the ruleset
	 */
	public String getRuleset() {
		return ruleset;
	}
	
	/**
	 * Sets the ruleset.
	 *
	 * @param ruleset the ruleset to set
	 */
	public void setRuleset(String ruleset) {
		this.ruleset = ruleset;
	}
	
	/**
	 * Gets the variable.
	 *
	 * @return the variable
	 */
	public String getVariable() {
		return variable;
	}
	
	/**
	 * Sets the variable.
	 *
	 * @param variable the variable to set
	 */
	public void setVariable(String variable) {
		this.variable = variable;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "diff.code.vo.Violation ["
				+ (beginline != null ? "beginline=" + beginline + ", " : "")
				+ (endcolumn != null ? "endcolumn=" + endcolumn + ", " : "")
				+ (endline != null ? "endline=" + endline + ", " : "")
				+ (priority != null ? "priority=" + priority + ", " : "")
				+ (_package != null ? "_package=" + _package + ", " : "")
				+ (clazz != null ? "clazz=" + clazz + ", " : "")
				+ (content != null ? "content=" + content + ", " : "")
				+ (externalInfoUrl != null ? "externalInfoUrl="
						+ externalInfoUrl + ", " : "")
				+ (method != null ? "method=" + method + ", " : "")
				+ (rule != null ? "rule=" + rule + ", " : "")
				+ (ruleset != null ? "ruleset=" + ruleset + ", " : "")
				+ (variable != null ? "variable=" + variable + ", " : "")
				+ (begincolumn != null ? "begincolumn=" + begincolumn : "")
				+ "]";
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((_package == null) ? 0 : _package.hashCode());
		result = prime * result
				+ ((begincolumn == null) ? 0 : begincolumn.intValue());
		result = prime * result + ((clazz == null) ? 0 : clazz.hashCode());
		result = prime * result + ((content == null) ? 0 : content.hashCode());
		result = prime * result
				+ ((endcolumn == null) ? 0 : endcolumn.intValue());
		result = prime * result
				+ ((externalInfoUrl == null) ? 0 : externalInfoUrl.hashCode());
		result = prime * result + ((method == null) ? 0 : method.hashCode());
		result = prime * result
				+ ((priority == null) ? 0 : priority.intValue());
		result = prime * result + ((rule == null) ? 0 : rule.hashCode());
		result = prime * result + ((ruleset == null) ? 0 : ruleset.hashCode());
		result = prime * result
				+ ((variable == null) ? 0 : variable.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Violation)) {
			return false;
		}
		Violation other = (Violation) obj;
		if (priority == null) {
			if (other.priority != null) {
				return false;
			}
		} else if (!priority.equals(other.priority)) {
			return false;
		}
		if (rule == null) {
			if (other.rule != null) {
				return false;
			}
		} else if (!rule.equals(other.rule)) {
			return false;
		}
		if (ruleset == null) {
			if (other.ruleset != null) {
				return false;
			}
		} else if (!ruleset.equals(other.ruleset)) {
			return false;
		}
		if (linenumber == null) {
			if (other.linenumber != null) {
				return false;
			}
		} else if (!linenumber.equals(other.linenumber)) {
			return false;
		}

		return true;
	}

}
