
package diff.code.config.xml;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the diff.code.xml.conf package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: diff.code.xml.conf
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link RightOutput }
     * 
     */
    public RightOutput createRightOutput() {
        return new RightOutput();
    }

    /**
     * Create an instance of {@link LeftOutput }
     * 
     */
    public LeftOutput createLeftOutput() {
        return new LeftOutput();
    }

    /**
     * Create an instance of {@link RuleSet }
     * 
     */
    public RuleSet createRuleSet() {
        return new RuleSet();
    }

    /**
     * Create an instance of {@link Directories }
     * 
     */
    public Directories createDirectories() {
        return new Directories();
    }

    /**
     * Create an instance of {@link Right }
     * 
     */
    public Right createRight() {
        return new Right();
    }

    /**
     * Create an instance of {@link Plugins }
     * 
     */
    public Plugins createPlugins() {
        return new Plugins();
    }

    /**
     * Create an instance of {@link DiffReport }
     * 
     */
    public DiffReport createDiffReport() {
        return new DiffReport();
    }

    /**
     * Create an instance of {@link Left }
     * 
     */
    public Left createLeft() {
        return new Left();
    }

    /**
     * Create an instance of {@link LogFile }
     * 
     */
    public LogFile createLogFile() {
        return new LogFile();
    }

    /**
     * Create an instance of {@link Report }
     * 
     */
    public Report createReport() {
        return new Report();
    }

    /**
     * Create an instance of {@link Reports }
     * 
     */
    public Reports createReports() {
        return new Reports();
    }

    /**
     * Create an instance of {@link Plugin }
     * 
     */
    public Plugin createPlugin() {
        return new Plugin();
    }

}
