
package diff.code.config.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


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
 *       &lt;/sequence>
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
    "rightOutput"
})
@XmlRootElement(name = "PMD")
public class PMD {

    @XmlElement(name = "RuleSet", required = true)
    protected RuleSet ruleSet;
    @XmlElement(name = "LeftOutput", required = true)
    protected LeftOutput leftOutput;
    @XmlElement(name = "RightOutput", required = true)
    protected RightOutput rightOutput;

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

}
