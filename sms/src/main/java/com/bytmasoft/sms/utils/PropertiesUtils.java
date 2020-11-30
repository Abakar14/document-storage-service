package com.bytmasoft.sms.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


public class PropertiesUtils {
	
	@Value("server.address")
	public String URL;

	public String getURL() {
		return URL;
	}
}
