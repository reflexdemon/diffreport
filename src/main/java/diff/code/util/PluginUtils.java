package diff.code.util;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.xml.bind.JAXBException;

import diff.code.analyzer.Analyzer;
import diff.code.config.Extractor;
import diff.code.config.xml.Plugin;
import diff.code.config.xml.Report;
import diff.code.executer.Executer;
import diff.code.report.Renderer;

public final class PluginUtils {

	

	/**
	 * Logging Reference for PluginUtils
	 */
	private static final Logger logger = Logger
			.getLogger(PluginUtils.class.getName());
	private static final String PREFIX_EXECUTER = "executer.";
	private static final String PREFIX_ANALYZER = "analyzer.";
	private static final String PREFIX_RENDERER = "renderer.";

	/**
	 * Gets the plugins.
	 * 
	 * @return the plugins
	 */
	public static final List<Plugin> getPlugins() {
		List<Plugin> plugins = null;
		try {
			plugins = Extractor.getInstance().getConfiguration().getPlugins()
					.getPlugin();
		} catch (JAXBException e) {
			logger.throwing(PluginUtils.class.getName(), "getPlugins", e);
		}
		return plugins;
	}

	/**
	 * Gets the pluginby type.
	 * 
	 * @param type
	 *            the type
	 * @return the pluginby type;null if type does not match
	 */
	public static Plugin getPluginbyType(String type) {
		if (null != type) {
			for (Plugin plugin : PluginUtils.getPlugins()) {
				if (type.equalsIgnoreCase(plugin.getType())) {
					return plugin;
				}
			}
		}
		return null;
	}

	/**
	 * Gets the plugin types.
	 *
	 * @return the plugin types
	 */
	public static final List<String> getPluginTypes() {
		List<String> pluginTypes = new ArrayList<String>();
		for (Plugin plugin : getPlugins()) {
			pluginTypes.add(plugin.getType());
		}

		return pluginTypes;
	}
	
	/**
	 * Gets the executer for type.
	 *
	 * @param type the type
	 * @return the executer for type
	 */
	public static Executer getExecuterForType(String type) {
		Executer e = null;
		if (null != type) {
			try {
				e = (Executer) Class.forName(DiffProperty.getValue(PREFIX_EXECUTER + type.toLowerCase())).newInstance();
			} catch (InstantiationException e1) {
				logger.throwing(PluginUtils.class.getName(),
						"getExecuterForType", e1);
			} catch (IllegalAccessException e1) {
				logger.throwing(PluginUtils.class.getName(),
						"getExecuterForType", e1);
			} catch (ClassNotFoundException e1) {
				logger.throwing(PluginUtils.class.getName(),
						"getExecuterForType", e1);
			}
			
		}
		return e;
	}
	
	/**
	 * Gets the analyzer for type.
	 *
	 * @param type the type
	 * @return the analyzer for type
	 */
	public static Analyzer getAnalyzerForType(String type) {
		Analyzer a = null;
		if (null != type) {
			try {
				a = (Analyzer) Class.forName(DiffProperty.getValue(PREFIX_ANALYZER + type.toLowerCase())).newInstance();
			} catch (InstantiationException e1) {
				logger.throwing(PluginUtils.class.getName(),
						"getAnalyzerForType", e1);
			} catch (IllegalAccessException e1) {
				logger.throwing(PluginUtils.class.getName(),
						"getAnalyzerForType", e1);
			} catch (ClassNotFoundException e1) {
				logger.throwing(PluginUtils.class.getName(),
						"getAnalyzerForType", e1);
			}
			
		}
		return a;
	}
	
	
	/**
	 * Gets the renderer for type.
	 *
	 * @param pluginType the plugin type
	 * @param reportType the report type
	 * @return the renderer for type
	 */
	public static Renderer getRendererForType(String pluginType, String reportType) {
		Renderer a = null;
		if (null != pluginType && null != reportType) {
			try {
				a = (Renderer) Class.forName(DiffProperty.getValue(PREFIX_RENDERER + pluginType.toLowerCase() + "." + reportType.toLowerCase())).newInstance();
			} catch (InstantiationException e1) {
				logger.throwing(PluginUtils.class.getName(),
						"getRendererForType", e1);
			} catch (IllegalAccessException e1) {
				logger.throwing(PluginUtils.class.getName(),
						"getRendererForType", e1);
			} catch (ClassNotFoundException e1) {
				logger.throwing(PluginUtils.class.getName(),
						"getRendererForType", e1);
			}
			
		}
		return a;
	}

	/**
	 * Gets the report file.
	 *
	 * @param pluginType the plugin type
	 * @param reportType the report type
	 * @return the report file
	 */
	public static String getReportFile(String pluginType, String reportType) {
		if (null != pluginType && null != reportType) {
			for (Report r : getPluginbyType(pluginType).getReport()) {
				if (reportType.equalsIgnoreCase(r.getType())) {
					return r.getFile();
				}
			}			
		}
		return null;
	}
}
