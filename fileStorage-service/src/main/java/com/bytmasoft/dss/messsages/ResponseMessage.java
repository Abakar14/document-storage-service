package com.bytmasoft.dss.messsages;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 
 * @author Mahamat used from controller for sending message via Http response
 */
@Getter
@Setter
@NoArgsConstructor
public class ResponseMessage {

	private String message;

	public ResponseMessage(String message) {
		this.message = message;
	}

}
