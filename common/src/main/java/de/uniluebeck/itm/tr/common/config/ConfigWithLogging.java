package de.uniluebeck.itm.tr.common.config;

import de.uniluebeck.itm.util.logging.LogLevel;
import org.kohsuke.args4j.Option;

public class ConfigWithLogging extends Config {

	@Option(name = "--logLevel",
			usage = "Logging level (valid values: TRACE, DEBUG, INFO, WARN, ERROR).",
			handler = LogLevelOptionHandler.class
	)
	public LogLevel logLevel = null;

	@Option(name = "--logLibs",
			usage = "If set to true, also library outputs are logged with the same level"
	)
	public boolean logLibs = false;

	@Option(
			name = "--verbose",
			usage = "Verbose (DEBUG) logging output (default: INFO)."
	)
	public boolean verbose = false;

}
