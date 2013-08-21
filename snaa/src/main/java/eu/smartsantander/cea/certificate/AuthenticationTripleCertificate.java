/*******************************************************************************
 * Copyright (c) 2013 CEA LIST.
 * Contributor:
 *   ROUX Pierre
 *   Kim Thuat NGUYEN
 *
 * Java class for authenticating user based on the use of certificate
 *******************************************************************************/

package eu.smartsantander.cea.certificate;

import eu.wisebed.api.v3.common.NodeUrnPrefix;

import java.security.cert.X509Certificate;


public class AuthenticationTripleCertificate {
	private String username;
	private X509Certificate certificate;
	private NodeUrnPrefix urnPrefix;


	public AuthenticationTripleCertificate() {

	}


	/**
	 * Define new triple information for the authentication of certificate's approach
	 *
	 * @param username
	 * @param certificate
	 * @param urnPrefix
	 */
	public AuthenticationTripleCertificate(String username, X509Certificate certificate, NodeUrnPrefix urnPrefix) {
		this.username = username;
		this.certificate = certificate;
		this.urnPrefix = urnPrefix;
	}


	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public X509Certificate getCertificate() {
		return certificate;
	}

	public void setCertificate(X509Certificate certificate) {
		this.certificate = certificate;
	}

	public NodeUrnPrefix getUrnPrefix() {
		return urnPrefix;
	}

	public void setUrnPrefix(NodeUrnPrefix urnPrefix) {
		this.urnPrefix = urnPrefix;
	}

}
