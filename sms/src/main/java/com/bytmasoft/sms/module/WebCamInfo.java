/**
 * 
 */
package com.bytmasoft.sms.module;

import lombok.Data;

/**
 * @author Mahamat Abakar created on 03.12.2020 at 10:12:47
 */
@Data
public class WebCamInfo {

	private String webCamName;
	private int webCamIndex;

	@Override
	public String toString() {

		return webCamName;
	}

}
