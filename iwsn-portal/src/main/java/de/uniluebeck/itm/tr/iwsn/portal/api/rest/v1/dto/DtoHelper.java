package de.uniluebeck.itm.tr.iwsn.portal.api.rest.v1.dto;

import de.uniluebeck.itm.tr.iwsn.messages.MessageHeaderPair;

public interface DtoHelper {

	Iterable<Object> encodeToJsonPojo(MessageHeaderPair pair);

}
