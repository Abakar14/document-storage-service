package com.bytmasoft.dss.messages;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 
 * @author Mahamat
 * conatains name, url, type, and size
 */
@Getter
@Setter
@NoArgsConstructor
public class FileUploadResponse {
	
	private String name;
	private String url;
	private String type;
	private long size;
	
	public FileUploadResponse(String name, String url, String type, long size) {
		this.name = name;
		this.url = url;
		this.type = type;
		this.size = size;
	}
	

}
