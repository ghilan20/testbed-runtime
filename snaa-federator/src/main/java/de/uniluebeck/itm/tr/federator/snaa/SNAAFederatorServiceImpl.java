package de.uniluebeck.itm.tr.federator.snaa;

import com.google.common.util.concurrent.AbstractService;
import com.google.inject.Inject;
import com.google.inject.name.Named;
import de.uniluebeck.itm.servicepublisher.ServicePublisher;
import de.uniluebeck.itm.servicepublisher.ServicePublisherService;
import de.uniluebeck.itm.tr.federator.utils.FederationManager;
import eu.wisebed.api.v3.common.NodeUrnPrefix;
import eu.wisebed.api.v3.common.SecretAuthenticationKey;
import eu.wisebed.api.v3.common.UsernameNodeUrnsMap;
import eu.wisebed.api.v3.snaa.*;

import javax.jws.WebService;
import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Preconditions.checkState;
import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Maps.newHashMap;
import static com.google.common.collect.Sets.newHashSet;
import static de.uniluebeck.itm.tr.snaa.common.SNAAHelper.createSNAAFault;

@WebService(
		name = "SNAA",
		endpointInterface = "eu.wisebed.api.v3.snaa.SNAA",
		portName = "SNAAPort",
		serviceName = "SNAAService",
		targetNamespace = "http://wisebed.eu/api/v3/snaa"
)
public class SNAAFederatorServiceImpl extends AbstractService implements SNAAFederatorService {

	protected final SNAAFederatorServiceConfig config;

	protected final FederationManager<SNAA> federationManager;

	protected final ExecutorService executorService;

	protected final ServicePublisher servicePublisher;

	protected ServicePublisherService jaxWsService;

	@Inject
	public SNAAFederatorServiceImpl(@Named(SNAAFederatorService.SNAA_FEDERATOR_EXECUTOR_SERVICE) final ExecutorService executorService,
									final SNAAFederatorServiceConfig config,
									final FederationManager<SNAA> federationManager,
									final ServicePublisher servicePublisher) {
		this.config = checkNotNull(config);
		this.federationManager = checkNotNull(federationManager);
		this.servicePublisher = checkNotNull(servicePublisher);
		this.executorService = checkNotNull(executorService);
	}

	@Override
	protected void doStart() {
		try {
			jaxWsService = servicePublisher.createJaxWsService(config.getSnaaContextPath(), this);
			jaxWsService.startAndWait();
			notifyStarted();
		} catch (Exception e) {
			notifyFailed(e);
		}
	}

	@Override
	protected void doStop() {
		try {
			if (jaxWsService != null) {
				jaxWsService.stopAndWait();
			}
			notifyStopped();
		} catch (Exception e) {
			notifyFailed(e);
		}
	}

	@Override
	public List<SecretAuthenticationKey> authenticate(final List<AuthenticationTriple> authenticationData)
			throws AuthenticationFault, SNAAFault_Exception {

		checkState(isRunning());

		Map<SNAA, Set<AuthenticationTriple>> intersectionPrefixSet = getIntersectionPrefixSetAT(authenticationData);

		Set<Future<List<SecretAuthenticationKey>>> futures = new HashSet<Future<List<SecretAuthenticationKey>>>();

		for (SNAA snaa : intersectionPrefixSet.keySet()) {

			final ArrayList<AuthenticationTriple> atList = newArrayList(intersectionPrefixSet.get(snaa));
			final AuthenticationCallable callable = new AuthenticationCallable(snaa, atList);

			futures.add(executorService.submit(callable));
		}

		final List<SecretAuthenticationKey> resultSet = newArrayList();

		for (Future<List<SecretAuthenticationKey>> future : futures) {
			try {
				resultSet.addAll(future.get());
			} catch (Exception e) {
				throw createSNAAFault(e.getMessage(), e);
			}
		}

		return resultSet;

	}

	@Override
	public AuthorizationResponse isAuthorized(final List<UsernameNodeUrnsMap> usernameNodeUrnsMapList,
											  final Action action) throws SNAAFault_Exception {

		checkState(isRunning());

		if (usernameNodeUrnsMapList == null || action == null) {
			throw createSNAAFault("Arguments must not be null!");
		}

		AuthorizationResponse response = new AuthorizationResponse();
		response.setAuthorized(true);
		response.setMessage("");

		Set<Future<AuthorizationResponse>> futures = new HashSet<Future<AuthorizationResponse>>();

		for (UsernameNodeUrnsMap usernameNodeUrnsMap : usernameNodeUrnsMapList) {

			final NodeUrnPrefix urnPrefix = usernameNodeUrnsMap.getUrnPrefix();
			final SNAA snaa = federationManager.getEndpointByUrnPrefix(urnPrefix);

			IsAuthorizedCallable callable = new IsAuthorizedCallable(snaa, newArrayList(usernameNodeUrnsMap), action);

			futures.add(executorService.submit(callable));
		}

		for (Future<AuthorizationResponse> future : futures) {
			try {
				AuthorizationResponse authorizationResponse = future.get();
				response.setMessage(response.getMessage() + "; " + authorizationResponse.getMessage());
				response.setAuthorized(response.isAuthorized() && authorizationResponse.isAuthorized());
				response.getPerNodeUrnAuthorizationResponses().addAll(
						authorizationResponse.getPerNodeUrnAuthorizationResponses()
				);
			} catch (Exception e) {
				throw createSNAAFault(e.getMessage(),e);
			}
		}

		return response;
	}

	@Override
	public List<ValidationResult> isValid(final List<SecretAuthenticationKey> secretAuthenticationKeys)
			throws SNAAFault_Exception {

		checkState(isRunning());

		try {

			checkNotNull(secretAuthenticationKeys, "SecretAuthenticationKey list must not be null!");

			final Set<Future<List<ValidationResult>>> futures = newHashSet();

			for (SecretAuthenticationKey secretAuthenticationKey : secretAuthenticationKeys) {

				final SNAA snaa = federationManager.getEndpointByUrnPrefix(secretAuthenticationKey.getUrnPrefix());
				final IsValidCallable callable = new IsValidCallable(snaa, newArrayList(secretAuthenticationKey));

				futures.add(executorService.submit(callable));
			}

			final List<ValidationResult> result = newArrayList();

			for (Future<List<ValidationResult>> future : futures) {
				try {
					result.addAll(future.get());
				} catch (ExecutionException e) {
					throw createSNAAFault(e.getMessage(), e);
				}
			}

			return result;

		} catch (Exception e) {
			throw createSNAAFault(e.getMessage());
		}
	}

	protected Map<SNAA, Set<AuthenticationTriple>> getIntersectionPrefixSetAT(
			List<AuthenticationTriple> authenticationData) throws SNAAFault_Exception {

		checkNotNull(authenticationData, "Parameter authenticationData must not be null!");

		// SNAA Endpoint URL -> Set<URN Prefixes> for intersection of authenticationData
		Map<SNAA, Set<AuthenticationTriple>> intersectionPrefixSet = newHashMap();

		for (AuthenticationTriple at : authenticationData) {

			// check if federator federates the urn prefix found in the authentication triple
			if (!federationManager.servesUrnPrefix(at.getUrnPrefix())) {
				throw createSNAAFault("No endpoint known for URN prefix " + at.getUrnPrefix());
			}

			final SNAA endpoint = federationManager.getEndpointByUrnPrefix(at.getUrnPrefix());

			Set<AuthenticationTriple> set = intersectionPrefixSet.get(endpoint);
			if (set == null) {
				set = newHashSet();
				intersectionPrefixSet.put(endpoint, set);
			}
			set.add(at);
		}

		return intersectionPrefixSet;
	}
}
