
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
 *         &lt;element ref="{}LogFile" minOccurs="0"/>
 *         &lt;element ref="{}Directories"/>
 *         &lt;element ref="{}Reports" minOccurs="0"/>
 *         &lt;element ref="{}Plugins" minOccurs="0"/>
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
    "logFile",
    "directories",
    "reports",
    "plugins"
})
@XmlRootElement(name = "DiffReport")
public class DiffReport {

    @XmlElement(name = "LogFile")
    protected LogFile logFile;
    @XmlElement(name = "Directories", required = true)
    protected Directories directories;
    @XmlElement(name = "Reports")
    protected Reports reports;
    @XmlElement(name = "Plugins")
    protected Plugins plugins;

    /**
     * Gets the value of the logFile property.
     * 
     * @return
     *     possible object is
     *     {@link LogFile }
     *     
     */
    public LogFile getLogFile() {
        return logFile;
    }

    /**
     * Sets the value of the logFile property.
     * 
     * @param value
     *     allowed object is
     *     {@link LogFile }
     *     
     */
    public void setLogFile(LogFile value) {
        this.logFile = value;
    }

    /**
     * Gets the value of the directories property.
     * 
     * @return
     *     possible object is
     *     {@link Directories }
     *     
     */
    public Directories getDirectories() {
        return directories;
    }

    /**
     * Sets the value of the directories property.
     * 
     * @param value
     *     allowed object is
     *     {@link Directories }
     *     
     */
    public void setDirectories(Directories value) {
        this.directories = value;
    }

    /**
     * Gets the value of the reports property.
     * 
     * @return
     *     possible object is
     *     {@link Reports }
     *     
     */
    public Reports getReports() {
        return reports;
    }

    /**
     * Sets the value of the reports property.
     * 
     * @param value
     *     allowed object is
     *     {@link Reports }
     *     
     */
    public void setReports(Reports value) {
        this.reports = value;
    }

    /**
     * Gets the value of the plugins property.
     * 
     * @return
     *     possible object is
     *     {@link Plugins }
     *     
     */
    public Plugins getPlugins() {
        return plugins;
    }

    /**
     * Sets the value of the plugins property.
     * 
     * @param value
     *     allowed object is
     *     {@link Plugins }
     *     
     */
    public void setPlugins(Plugins value) {
        this.plugins = value;
    }

}
