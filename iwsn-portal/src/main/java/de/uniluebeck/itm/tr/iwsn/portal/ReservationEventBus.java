package de.uniluebeck.itm.tr.iwsn.portal;

import de.uniluebeck.itm.tr.common.EventBusService;

public interface ReservationEventBus extends EventBusService {

	void enableVirtualization();

	void disableVirtualization();

	boolean isVirtualizationEnabled();
}
