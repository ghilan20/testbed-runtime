package de.uniluebeck.itm.tr.devicedb;

import com.google.common.base.Function;
import com.google.common.base.Strings;
import com.google.common.collect.Maps;
import com.google.common.util.concurrent.AbstractService;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.persist.Transactional;
import de.uniluebeck.itm.tr.iwsn.common.NodeUrnHelper;
import eu.wisebed.api.v3.common.NodeUrn;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Map;

import static com.google.common.collect.Iterables.transform;
import static com.google.common.collect.Lists.newArrayList;

public class DeviceDBJpa extends AbstractService implements DeviceDB {

	private final Provider<EntityManager> entityManager;

	@Inject
	public DeviceDBJpa(final Provider<EntityManager> entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	protected void doStart() {
		notifyStarted();
	}

	@Override
	protected void doStop() {
		notifyStopped();
	}

	@Override
	@Transactional
	public Map<NodeUrn, DeviceConfig> getConfigsByNodeUrns(Iterable<NodeUrn> nodeUrns) {
		// TODO simplify this as soon as NodeUrn is serializable
		// prepare list of IDs
		List<String> nodeUrnStrings = newArrayList(transform(nodeUrns, NodeUrnHelper.NODE_URN_TO_STRING));

		List<DeviceConfig> configs = entityManager.get()
				.createQuery("SELECT d FROM DeviceConfig d WHERE d.nodeUrn IN :urns", DeviceConfig.class)
				.setParameter("urns", nodeUrnStrings).getResultList();

		// create map for final result
		return Maps.uniqueIndex(configs, new Function<DeviceConfig, NodeUrn>() {

			@Override
			public NodeUrn apply(DeviceConfig config) {
				return config.getNodeUrn();
			}

		}
		);
	}

	@Override
	@Transactional
	public DeviceConfig getConfigByUsbChipId(String usbChipId) {
		return entityManager.get()
				.createQuery("SELECT d FROM DeviceConfig d WHERE d.nodeUSBChipID = :usbChipId", DeviceConfig.class)
				.setParameter("usbChipId", usbChipId).getSingleResult();
	}

	@Override
	@Transactional
	public DeviceConfig getConfigByNodeUrn(NodeUrn nodeUrn) {
		// TODO remove toString() as soon as NodeUrn is Serializable
		return entityManager.get()
				.createQuery("SELECT d FROM DeviceConfig d WHERE d.nodeUrn = :nodeUrn", DeviceConfig.class)
				.setParameter("nodeUrn", nodeUrn.toString()).getSingleResult();
	}

	@Override
	@Transactional
	public DeviceConfig getConfigByMacAddress(long macAddress) {
		String macHex = "0x" + Strings.padStart(Long.toHexString(macAddress), 4, '0');
		return entityManager.get()
				.createQuery("SELECT d FROM DeviceConfig d WHERE d.nodeUrn LIKE :macAddress", DeviceConfig.class)
				.setParameter("macAddress", "%" + macHex).getSingleResult();
	}

	@Override
	@Transactional
	public Iterable<DeviceConfig> getAll() {
		return entityManager.get().createQuery("SELECT d FROM DeviceConfig d", DeviceConfig.class).getResultList();
	}

	@Override
	@Transactional
	public void add(final DeviceConfig deviceConfig) {
		entityManager.get().persist(deviceConfig);
	}

	@Override
	@Transactional
	public boolean removeByNodeUrn(final NodeUrn nodeUrn) {

		final int entriesRemoved = entityManager.get()
				.createQuery("DELETE FROM DeviceConfig d WHERE d.nodeUrn = :nodeUrn")
				.setParameter("nodeUrn", nodeUrn.toString())
				.executeUpdate();

		if (entriesRemoved > 1) {
			throw new RuntimeException(
					"More than one entry (" + entriesRemoved + ") removed while trying to remove entry for one Node URN!"
			);
		}

		return entriesRemoved == 1;
	}

	@Override
	@Transactional
	public void removeAll() {
		entityManager.get().createQuery("DELETE FROM DeviceConfig d").executeUpdate();
	}
}