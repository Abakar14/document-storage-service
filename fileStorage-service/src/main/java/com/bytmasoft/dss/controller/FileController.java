package com.bytmasoft.dss.controller;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.bytmasoft.domain.models.FileDB;
import com.bytmasoft.domain.utils.DSSMapping;
import com.bytmasoft.dss.messsages.FileUploadResponse;
import com.bytmasoft.dss.messsages.ResponseMessage;
import com.bytmasoft.dss.service.impl.FileDBServiceImpl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
//@RequestMapping(produces = {"application/xml", "application/json" }, consumes = {
//		"application/xml", "application/json"})
@CrossOrigin
@RequestMapping(path = DSSMapping.FILES)
public class FileController {
	
	private final FileDBServiceImpl fileDBService;
	
	@PostMapping
	public ResponseMessage uploadFile(@RequestParam("file") MultipartFile file) {
		
		String message = "";
		
		try {
		
			fileDBService.storeFile(file);
			message = "Uploaded the file successfully: "+file.getOriginalFilename();
			return new ResponseMessage(message);
			
			
		} catch (IOException e) {
			message = "could not upload the file: "+file.getOriginalFilename() +"!" ;
			return new ResponseMessage(message);
		}
		
		
	}
	
	@PostMapping("/student/{studentId}")
	public ResponseMessage uploadFileStudent(@RequestParam("file") MultipartFile file, @PathVariable Long studentId) {
		
		String message = "";
		
		try {
		
			fileDBService.storeStudentFile(file, studentId);
			message = "Uploaded the file: "+ file.getOriginalFilename() +" for student ID: " +studentId +" successfully";
			return new ResponseMessage(message);
			
			
		} catch (IOException e) {
			message = "could not upload the file: "+file.getOriginalFilename() +"!" ;
			return new ResponseMessage(message);
		}
		
		
	}
	
	
	@PutMapping("/{id}")
	public ResponseMessage uploadFile(@RequestParam("file") MultipartFile file, @PathVariable Long id) {
		
		String message = "";
		
		try {
		
			fileDBService.updateFile(file, id);
			
			message = "Update the file successfully: "+file.getOriginalFilename();
			return new ResponseMessage(message);
			
			
		} catch (IOException e) {
			message = "could not update the file: "+file.getOriginalFilename() +"!" ;
			return new ResponseMessage(message);
		}
		
		
	}
	
	@GetMapping
	public List<FileUploadResponse> getFiles() {
	 
		  List<FileUploadResponse> files = fileDBService.getAllFiles().map(dbFile -> {
			  
		      String fileDownloadUri = ServletUriComponentsBuilder
		          .fromCurrentContextPath()
		          .path("/"+dbFile.getId())
		          .toUriString();

		     
		      return new FileUploadResponse(
		          dbFile.getFilename(),
		          fileDownloadUri,
		          dbFile.getType(),
		          dbFile.getData().length);
		    }).collect(Collectors.toList());

		    return files;
	}
	
	
	
	
	@GetMapping("/{id}")
	public byte[] getFile(@PathVariable Long id) {
		
		FileDB fileDb = fileDBService.getFileByID(id);
		return fileDb.getData();
	}
	
	

}
