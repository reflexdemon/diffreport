/**
 * 
 */
package diff.code.util.scp;

import java.io.IOException;
import java.util.logging.Logger;

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.SCPClient;

/**
 * @author Venkateswara
 * 
 */
public class Transport {

	

	/**
	 * Logging Reference for Transport
	 */
	private static final Logger logger = Logger.getLogger(Transport.class.getName());
	// reflexdemon,diffreport@shell.sourceforge.net
	private String hostname = "shell.sourceforge.net";
	private String username = "reflexdemon,diffreport";
	private String password = "*******";

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Connection conn = null;
		String sourceFile;// "C:\\Venkateswara VP\\rd\\diffreport-code-0\\build\\dist\\diffreport1.0-bin.zip";
		String targetInfo;// "reflexdemon,diffreport@shell.sourceforge.net:/home/frs/project/diffreport/release/1.0";
		String password;// "********";
		if (args.length == 3) {
			sourceFile = args[0];
			targetInfo = args[1];
			password = args[2];
		} else {
			logger.finest("Usage:java Transport <sourcefile> <username>@<hostname>:<folder> <password>");
			return;
		}

		String username = targetInfo.substring(0, targetInfo.indexOf('@'));
		String hostname = targetInfo.substring(targetInfo.indexOf('@') + 1,
				targetInfo.indexOf(':'));
		String folder = targetInfo.substring(targetInfo.indexOf(':') + 1);

		try {
			Transport t = new Transport();

			t.setUsername(username);
			t.setHostname(hostname);
			t.setPassword(password);
			logger.info("Trying to get connection to " + hostname
					+ " for " + username);
			conn = t.getConnection();
			logger.info("Connected.");
			SCPClient client = conn.createSCPClient();
			logger.info("Sending " + sourceFile + " to " + folder
					+ " on " + hostname);
			client.put(sourceFile, folder);
			logger.info("Sending complete.");
		} catch (IOException e) {
			logger.throwing(Transport.class.getName(), "main", e);
		} finally {
			if (null != conn) {
				conn.close();
			}
		}
	}

	public Connection getConnection() throws IOException {
		/* Create a connection instance */

		Connection conn = new Connection(getHostname());
		/* Now connect */

		logger.finest("ID:" + Connection.identification);
		conn.connect();

		/*
		 * Authenticate. If you get an IOException saying something like
		 * "Authentication method password not supported by the server at this stage."
		 * then please check the FAQ.
		 */

		boolean isAuthenticated = conn.authenticateWithPassword(getUsername(),
				getPassword());

		if (isAuthenticated == false)
			throw new IOException("Authentication failed.");

		return conn;

	}

	/**
	 * @param hostname
	 *            the hostname to set
	 */
	public void setHostname(String hostname) {
		this.hostname = hostname;
	}

	/**
	 * @return the hostname
	 */
	public String getHostname() {
		return hostname;
	}

	/**
	 * @param username
	 *            the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
}
