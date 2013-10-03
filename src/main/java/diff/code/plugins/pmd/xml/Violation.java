
package diff.code.plugins.pmd.xml;

import java.math.BigInteger;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute name="begincolumn" use="required" type="{http://www.w3.org/2001/XMLSchema}integer" />
 *       &lt;attribute name="beginline" use="required" type="{http://www.w3.org/2001/XMLSchema}integer" />
 *       &lt;attribute name="class" type="{http://www.w3.org/2001/XMLSchema}NCName" />
 *       &lt;attribute name="endcolumn" use="required" type="{http://www.w3.org/2001/XMLSchema}integer" />
 *       &lt;attribute name="endline" use="required" type="{http://www.w3.org/2001/XMLSchema}integer" />
 *       &lt;attribute name="externalInfoUrl" use="required" type="{http://www.w3.org/2001/XMLSchema}anyURI" />
 *       &lt;attribute name="method" type="{http://www.w3.org/2001/XMLSchema}NCName" />
 *       &lt;attribute name="package" type="{http://www.w3.org/2001/XMLSchema}NCName" />
 *       &lt;attribute name="priority" use="required" type="{http://www.w3.org/2001/XMLSchema}integer" />
 *       &lt;attribute name="rule" use="required" type="{http://www.w3.org/2001/XMLSchema}NCName" />
 *       &lt;attribute name="ruleset" use="required" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" />
 *       &lt;attribute name="variable" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "content"
})
@XmlRootElement(name = "violation")
public class Violation {

    @XmlValue
    protected String content;
    @XmlAttribute(required = true)
    protected BigInteger begincolumn;
    @XmlAttribute(required = true)
    protected BigInteger beginline;
    @XmlAttribute(name = "class")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "NCName")
    protected String clazz;
    @XmlAttribute(required = true)
    protected BigInteger endcolumn;
    @XmlAttribute(required = true)
    protected BigInteger endline;
    @XmlAttribute(required = true)
    @XmlSchemaType(name = "anyURI")
    protected String externalInfoUrl;
    @XmlAttribute
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "NCName")
    protected String method;
    @XmlAttribute(name = "package")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "NCName")
    protected String _package;
    @XmlAttribute(required = true)
    protected BigInteger priority;
    @XmlAttribute(required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "NCName")
    protected String rule;
    @XmlAttribute(required = true)
    @XmlSchemaType(name = "anySimpleType")
    protected String ruleset;
    @XmlAttribute
    @XmlSchemaType(name = "anySimpleType")
    protected String variable;

    /**
     * Gets the value of the content property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContent() {
        return content;
    }

    /**
     * Sets the value of the content property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContent(String value) {
        this.content = value;
    }

    /**
     * Gets the value of the begincolumn property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getBegincolumn() {
        return begincolumn;
    }

    /**
     * Sets the value of the begincolumn property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setBegincolumn(BigInteger value) {
        this.begincolumn = value;
    }

    /**
     * Gets the value of the beginline property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getBeginline() {
        return beginline;
    }

    /**
     * Sets the value of the beginline property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setBeginline(BigInteger value) {
        this.beginline = value;
    }

    /**
     * Gets the value of the clazz property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getClazz() {
        return clazz;
    }

    /**
     * Sets the value of the clazz property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setClazz(String value) {
        this.clazz = value;
    }

    /**
     * Gets the value of the endcolumn property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getEndcolumn() {
        return endcolumn;
    }

    /**
     * Sets the value of the endcolumn property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setEndcolumn(BigInteger value) {
        this.endcolumn = value;
    }

    /**
     * Gets the value of the endline property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getEndline() {
        return endline;
    }

    /**
     * Sets the value of the endline property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setEndline(BigInteger value) {
        this.endline = value;
    }

    /**
     * Gets the value of the externalInfoUrl property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExternalInfoUrl() {
        return externalInfoUrl;
    }

    /**
     * Sets the value of the externalInfoUrl property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExternalInfoUrl(String value) {
        this.externalInfoUrl = value;
    }

    /**
     * Gets the value of the method property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMethod() {
        return method;
    }

    /**
     * Sets the value of the method property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMethod(String value) {
        this.method = value;
    }

    /**
     * Gets the value of the package property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPackage() {
        return _package;
    }

    /**
     * Sets the value of the package property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPackage(String value) {
        this._package = value;
    }

    /**
     * Gets the value of the priority property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getPriority() {
        return priority;
    }

    /**
     * Sets the value of the priority property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setPriority(BigInteger value) {
        this.priority = value;
    }

    /**
     * Gets the value of the rule property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRule() {
        return rule;
    }

    /**
     * Sets the value of the rule property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRule(String value) {
        this.rule = value;
    }

    /**
     * Gets the value of the ruleset property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRuleset() {
        return ruleset;
    }

    /**
     * Sets the value of the ruleset property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRuleset(String value) {
        this.ruleset = value;
    }

    /**
     * Gets the value of the variable property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVariable() {
        return variable;
    }

    /**
     * Sets the value of the variable property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVariable(String value) {
        this.variable = value;
    }

}
