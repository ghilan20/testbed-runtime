package de.uniluebeck.itm.tr.iwsn.portal.api.rest.v1;

import com.google.inject.AbstractModule;
import com.google.inject.assistedinject.FactoryModuleBuilder;
import com.google.inject.name.Names;
import de.uniluebeck.itm.servicepublisher.ServicePublisher;
import de.uniluebeck.itm.tr.common.IdProvider;
import de.uniluebeck.itm.tr.common.WisemlProvider;
import de.uniluebeck.itm.tr.iwsn.common.ResponseTrackerFactory;
import de.uniluebeck.itm.tr.iwsn.portal.PortalEventBus;
import de.uniluebeck.itm.tr.iwsn.portal.ReservationManager;
import de.uniluebeck.itm.tr.iwsn.portal.api.rest.v1.dto.DtoHelper;
import de.uniluebeck.itm.tr.iwsn.portal.api.rest.v1.dto.DtoHelperFactory;
import de.uniluebeck.itm.tr.iwsn.portal.api.rest.v1.dto.DtoHelperImpl;
import de.uniluebeck.itm.tr.iwsn.portal.api.rest.v1.resources.*;
import de.uniluebeck.itm.tr.iwsn.portal.api.rest.v1.ws.EventWebSocketFactory;
import de.uniluebeck.itm.tr.iwsn.portal.api.rest.v1.ws.WsnWebSocketFactory;
import eu.wisebed.api.v3.rs.RS;
import eu.wisebed.api.v3.snaa.SNAA;

public class RestApiModule extends AbstractModule {

	public static final String IS_FEDERATOR = "RestApiModule.IS_FEDERATOR";

	public static final String API_VERSION = "RestApiModule.API_VERSION";

	private final boolean federator;

	public RestApiModule(final boolean federator) {
		this.federator = federator;
	}

	@Override
	protected void configure() {

		// services
		requireBinding(RS.class);
		requireBinding(SNAA.class);
		requireBinding(WisemlProvider.class);

		// internals
		requireBinding(PortalEventBus.class);
		requireBinding(ReservationManager.class);

		// helpers
		requireBinding(ResponseTrackerFactory.class);
		requireBinding(IdProvider.class);
		requireBinding(ServicePublisher.class);

		bind(RestApiService.class).to(RestApiServiceImpl.class);
		bind(ExperimentResource.class).to(ExperimentResourceImpl.class);
		bind(CookieResource.class).to(CookieResourceImpl.class);

		bind(RemoteExperimentConfigurationResource.class).to(RemoteExperimentConfigurationResourceImpl.class);
		bind(RsResource.class).to(RsResourceImpl.class);
		bind(SnaaResource.class).to(SnaaResourceImpl.class);
		bind(RootResource.class).to(RootResourceImpl.class);
		bind(UserRegistrationResource.class).to(UserRegistrationResourceImpl.class);

		install(new FactoryModuleBuilder().implement(DtoHelper.class, DtoHelperImpl.class).build(DtoHelperFactory.class));

		if (federator) {
			bind(NodeStatusTrackerResource.class).to(NotImplementedNodeStatusTrackerResource.class);
			bind(EventStoreResource.class).to(NotImplementedEventStoreResource.class);
		} else {
			bind(NodeStatusTrackerResource.class).to(NodeStatusTrackerResourceImpl.class);
			bind(EventStoreResource.class).to(EventStoreResourceImpl.class);
		}

		bindConstant().annotatedWith(Names.named(IS_FEDERATOR)).to(federator);
		bindConstant().annotatedWith(Names.named(API_VERSION)).to("1.0");

		install(new FactoryModuleBuilder().build(WsnWebSocketFactory.class));
		install(new FactoryModuleBuilder().build(EventWebSocketFactory.class));
	}
}
