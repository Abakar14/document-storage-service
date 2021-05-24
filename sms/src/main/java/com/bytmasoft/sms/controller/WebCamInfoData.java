/**
 * 
 */
package com.bytmasoft.sms.controller;

import lombok.Data;

/**
 * @author Mahamat Abakar created on 03.12.2020 at 10:04:06
 */
@Data
public class WebCamInfoData {
	/**
	 * 
	 */
	private WebCamInfoData data;

	{
		data = new WebCamInfoData();
	}

	public String getWebCamName() {
		return data.getWebCamName();
	}

	public void setWebCamName(String webCamName) {
		this.data.setWebCamName(webCamName);
	}

	public int getWebCamIndex() {
		return data.getWebCamIndex();
	}

	public void setWebCamIndex(int webCamIndex) {
		this.data.setWebCamIndex(webCamIndex);
	}

}