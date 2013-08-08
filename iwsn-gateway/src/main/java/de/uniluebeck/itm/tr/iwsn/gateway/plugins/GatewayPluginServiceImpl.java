package de.uniluebeck.itm.tr.iwsn.gateway.plugins;

import com.google.common.io.Files;
import com.google.common.util.concurrent.AbstractService;
import com.google.inject.Inject;
import de.uniluebeck.itm.tr.common.plugins.PluginContainer;
import de.uniluebeck.itm.tr.common.plugins.PluginContainerFactory;
import de.uniluebeck.itm.tr.devicedb.DeviceDBService;
import de.uniluebeck.itm.tr.iwsn.gateway.DeviceAdapterRegistry;
import de.uniluebeck.itm.tr.iwsn.gateway.GatewayConfig;
import de.uniluebeck.itm.tr.iwsn.gateway.GatewayEventBus;
import eu.wisebed.api.v3.rs.RS;
import eu.wisebed.api.v3.sm.SessionManagement;
import eu.wisebed.api.v3.snaa.SNAA;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.io.Files.copy;
import static com.google.common.io.Resources.newInputStreamSupplier;

class GatewayPluginServiceImpl extends AbstractService implements GatewayPluginService {

	private static final Logger log = LoggerFactory.getLogger(GatewayPluginServiceImpl.class);

	private final PluginContainerFactory pluginContainerFactory;

	private final GatewayConfig gatewayConfig;

	private final RS rs;

	private final SNAA snaa;

	private final SessionManagement sessionManagement;

	private final GatewayEventBus gatewayEventBus;

	private final DeviceDBService deviceDBService;

	private final DeviceAdapterRegistry deviceAdapterRegistry;

	private PluginContainer pluginContainer;

	@Inject
	public GatewayPluginServiceImpl(final PluginContainerFactory pluginContainerFactory,
									final GatewayConfig gatewayConfig,
									final RS rs,
									final SNAA snaa,
									final SessionManagement sessionManagement,
									final DeviceDBService deviceDBService,
									final GatewayEventBus gatewayEventBus,
									final DeviceAdapterRegistry deviceAdapterRegistry) {

		this.pluginContainerFactory = checkNotNull(pluginContainerFactory);
		this.gatewayConfig = checkNotNull(gatewayConfig);

		this.rs = checkNotNull(rs);
		this.snaa = checkNotNull(snaa);
		this.sessionManagement = checkNotNull(sessionManagement);
		this.deviceDBService = checkNotNull(deviceDBService);
		this.gatewayEventBus = checkNotNull(gatewayEventBus);
		this.deviceAdapterRegistry = checkNotNull(deviceAdapterRegistry);
	}

	@Override
	protected void doStart() {
		try {

			log.trace("GatewayPluginServiceImpl.doStart()");

			if (gatewayConfig.getPluginDirectory() != null && !"".equals(gatewayConfig.getPluginDirectory())) {

				final File pluginDirectory = new File(gatewayConfig.getPluginDirectory());

				if (!pluginDirectory.isDirectory()) {
					throw new IllegalArgumentException(pluginDirectory.getAbsolutePath() + " is not a directory!");
				}

				if (!pluginDirectory.canRead()) {
					throw new IllegalArgumentException(pluginDirectory.getAbsolutePath() + " is not readable!");
				}

				final String extenderBundle = copyBundleToTmpFile(
						"tr.plugins.framework-extender-gateway-0.9-SNAPSHOT.jar"
				);

				pluginContainer = pluginContainerFactory.create(pluginDirectory.getAbsolutePath(), extenderBundle);

				pluginContainer.startAndWait();

				pluginContainer.registerService(PluginContainer.class, pluginContainer);
				pluginContainer.registerService(RS.class, rs);
				pluginContainer.registerService(SNAA.class, snaa);
				pluginContainer.registerService(SessionManagement.class, sessionManagement);
				pluginContainer.registerService(GatewayEventBus.class, gatewayEventBus);
				pluginContainer.registerService(DeviceDBService.class, deviceDBService);
				pluginContainer.registerService(DeviceAdapterRegistry.class, deviceAdapterRegistry);
			}

			notifyStarted();

		} catch (Exception e) {
			notifyFailed(e);
		}
	}

	@Override
	protected void doStop() {
		try {
			log.trace("GatewayPluginServiceImpl.doStop()");
			pluginContainer.stopAndWait();
			notifyStopped();
		} catch (Exception e) {
			notifyFailed(e);
		}
	}

	private String copyBundleToTmpFile(final String fileName) throws IOException {
		final URL resource = GatewayPluginServiceImpl.class.getResource(fileName);
		final File tempDir = Files.createTempDir();
		final File tempFile = new File(tempDir, fileName);
		copy(newInputStreamSupplier(resource), tempFile);
		return tempFile.getAbsolutePath();
	}
}
