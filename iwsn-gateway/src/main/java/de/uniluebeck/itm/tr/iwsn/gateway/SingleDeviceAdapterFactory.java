package de.uniluebeck.itm.tr.iwsn.gateway;

import com.google.inject.Inject;
import de.uniluebeck.itm.nettyprotocols.HandlerFactory;
import de.uniluebeck.itm.tr.devicedb.DeviceConfig;
import de.uniluebeck.itm.tr.iwsn.nodeapi.NodeApiFactory;
import de.uniluebeck.itm.wsn.drivers.factories.DeviceFactory;
import de.uniluebeck.itm.wsn.drivers.factories.DeviceType;

import javax.annotation.Nullable;
import java.util.Map;
import java.util.Set;

public class SingleDeviceAdapterFactory implements DeviceAdapterFactory {

	private final NodeApiFactory nodeApiFactory;

	private final DeviceFactory deviceFactory;

	private final Set<HandlerFactory> handlerFactories;

	@Inject
	public SingleDeviceAdapterFactory(final DeviceFactory deviceFactory,
									  final NodeApiFactory nodeApiFactory,
									  final Set<HandlerFactory> handlerFactories) {
		this.deviceFactory = deviceFactory;
		this.nodeApiFactory = nodeApiFactory;
		this.handlerFactories = handlerFactories;
	}

	@Override
	public boolean canHandle(final String nodeType,
							 final String nodePort,
							 @Nullable final Map<String, String> nodeConfiguration,
							 @Nullable final DeviceConfig deviceConfig) {
		if (deviceConfig == null) {
			return false;
		}
		try {
			DeviceType.fromString(nodeType);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public DeviceAdapter create(final String deviceType,
								final String devicePort,
								@Nullable final Map<String, String> deviceConfiguration,
								@Nullable final DeviceConfig deviceConfig) {
		return new SingleDeviceAdapter(
				deviceType,
				devicePort,
				deviceConfiguration,
				deviceConfig,
				deviceFactory,
				nodeApiFactory,
				handlerFactories
		);
	}
}
