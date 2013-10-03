
package diff.code.config.xml;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
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
 *       &lt;sequence>
 *         &lt;element ref="{}RuleSet"/>
 *         &lt;element ref="{}LeftOutput"/>
 *         &lt;element ref="{}RightOutput"/>
 *         &lt;element ref="{}Report" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *       &lt;attribute name="name" use="required" type="{http://www.w3.org/2001/XMLSchema}NCName" />
 *       &lt;attribute name="type" type="{http://www.w3.org/2001/XMLSchema}NCName" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "ruleSet",
    "leftOutput",
    "rightOutput",
    "report"
})
@XmlRootElement(name = "Plugin")
public class Plugin {

    @XmlElement(name = "RuleSet", required = true)
    protected RuleSet ruleSet;
    @XmlElement(name = "LeftOutput", required = true)
    protected LeftOutput leftOutput;
    @XmlElement(name = "RightOutput", required = true)
    protected RightOutput rightOutput;
    @XmlElement(name = "Report", required = true)
    protected List<Report> report;
    @XmlAttribute(required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "NCName")
    protected String name;
    @XmlAttribute
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "NCName")
    protected String type;

    /**
     * Gets the value of the ruleSet property.
     * 
     * @return
     *     possible object is
     *     {@link RuleSet }
     *     
     */
    public RuleSet getRuleSet() {
        return ruleSet;
    }

    /**
     * Sets the value of the ruleSet property.
     * 
     * @param value
     *     allowed object is
     *     {@link RuleSet }
     *     
     */
    public void setRuleSet(RuleSet value) {
        this.ruleSet = value;
    }

    /**
     * Gets the value of the leftOutput property.
     * 
     * @return
     *     possible object is
     *     {@link LeftOutput }
     *     
     */
    public LeftOutput getLeftOutput() {
        return leftOutput;
    }

    /**
     * Sets the value of the leftOutput property.
     * 
     * @param value
     *     allowed object is
     *     {@link LeftOutput }
     *     
     */
    public void setLeftOutput(LeftOutput value) {
        this.leftOutput = value;
    }

    /**
     * Gets the value of the rightOutput property.
     * 
     * @return
     *     possible object is
     *     {@link RightOutput }
     *     
     */
    public RightOutput getRightOutput() {
        return rightOutput;
    }

    /**
     * Sets the value of the rightOutput property.
     * 
     * @param value
     *     allowed object is
     *     {@link RightOutput }
     *     
     */
    public void setRightOutput(RightOutput value) {
        this.rightOutput = value;
    }

    /**
     * Gets the value of the report property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the report property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getReport().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Report }
     * 
     * 
     */
    public List<Report> getReport() {
        if (report == null) {
            report = new ArrayList<Report>();
        }
        return this.report;
    }

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Gets the value of the type property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getType() {
        return type;
    }

    /**
     * Sets the value of the type property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setType(String value) {
        this.type = value;
    }

}
