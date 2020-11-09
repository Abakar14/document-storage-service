 /**
 * 
 */
package com.bytmasoft.sms.model;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

/**
 * 
 * @author Mahamat Abakar Date 23.12.2019
 */

public class Privilege {

	private static final long serialVersionUID = 1L;

	
	public Privilege() {
		// TODO Auto-generated constructor stub
	}
	
	@DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
	@JsonFormat(pattern = "dd.MM.yyyy HH:mm:ss")
	private LocalDateTime createdOn;

	@DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
	@JsonFormat(pattern = "dd.MM.yyyy HH:mm:ss")
	private LocalDateTime updatedOn;

	private String status;
	
	private String name;

	
	private Boolean deletestatus = false;

	
	private String insertedBy;

	
	private String insertedProg;

	
	private String updatedBy;

	
	private String updatedProg;

	private Long id;


	public LocalDateTime getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(LocalDateTime createdOn) {
		this.createdOn = createdOn;
	}

	public LocalDateTime getUpdatedOn() {
		return updatedOn;
	}

	public void setUpdatedOn(LocalDateTime updatedOn) {
		this.updatedOn = updatedOn;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Boolean getDeletestatus() {
		return deletestatus;
	}

	public void setDeletestatus(Boolean deletestatus) {
		this.deletestatus = deletestatus;
	}

	public String getInsertedBy() {
		return insertedBy;
	}

	public void setInsertedBy(String insertedBy) {
		this.insertedBy = insertedBy;
	}

	public String getInsertedProg() {
		return insertedProg;
	}

	public void setInsertedProg(String insertedProg) {
		this.insertedProg = insertedProg;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public String getUpdatedProg() {
		return updatedProg;
	}

	public void setUpdatedProg(String updatedProg) {
		this.updatedProg = updatedProg;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	
	public void setName(String name) {
		this.name = name;
	}  
	
	public String getName() {
		return name;
	}
	
	

}
