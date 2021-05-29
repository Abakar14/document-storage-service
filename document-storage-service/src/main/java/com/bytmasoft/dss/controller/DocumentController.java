/**
 * 
 */
package com.bytmasoft.dss.controller;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.bytmasoft.domain.utils.DSSMapping;
import com.bytmasoft.dss.messages.FileUploadResponse;
import com.bytmasoft.dss.messages.ResponseMessage;
import com.bytmasoft.dss.service.impl.DocumentServiceImpl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Mahamat Abakar created on 13.11.2020 at 09:36:39
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@CrossOrigin
@RequestMapping(path = DSSMapping.DOCUMENTS)
public class DocumentController {

	@Autowired
	DocumentServiceImpl documentService;

	@PostMapping("/students/{student_id}")
	public ResponseMessage uploadStudentDocument(@RequestParam("file") MultipartFile file,
			@PathVariable Long student_id) {

		String message = "";

		try {

			documentService.storeStudentDocument(file, student_id);

			message = "Uploaded the file successfully: " + file.getOriginalFilename();
			return new ResponseMessage(message);

		} catch (IOException e) {
			message = "could not upload the file: " + file.getOriginalFilename() + "!";
			return new ResponseMessage(message);
		}

	}

//	@PostMapping("/student/{studentId}")
//	public ResponseMessage uploadFileStudent(@RequestParam("file") MultipartFile file, @PathVariable Long studentId) {
//		//TODO call student service by id to have student object
//		String message = "";
//		Student student = new Student();
//		
//		
////		try {
////		
//////			documentService.storeStudentDocument(file, student);
////			
////			message = "Uploaded the file: "+ file.getOriginalFilename() +" for student ID: " +studentId +" successfully";
////			return new ResponseMessage(message);
////			
////			
////		} catch (IOException e) {
////			message = "could not upload the file: "+file.getOriginalFilename() +"!" ;
////			return new ResponseMessage(message);
////		}
//		return null;
//		
//	}

	@PutMapping("/{id}")
	public ResponseMessage uploadFile(@RequestParam("file") MultipartFile file, @PathVariable Long id) {

		String message = "";

//		try {
//		
//			this.documentService.updateFile(file, id);
//				
//			message = "Update the file successfully: "+file.getOriginalFilename();
//			return new ResponseMessage(message);
//			
//			
//		} catch (IOException e) {
//			message = "could not update the file: "+file.getOriginalFilename() +"!" ;
//			return new ResponseMessage(message);
//		}

		return null;

	}

//	@PostMapping
//	public ResponseMessage uploadFile(@RequestParam("file") MultipartFile file) {
//
//		String message = "";
//
//		try {
//
//			fileDBService.storeFile(file);
//			message = "Uploaded the file successfully: " + file.getOriginalFilename();
//			return new ResponseMessage(message);
//
//		} catch (IOException e) {
//			message = "could not upload the file: " + file.getOriginalFilename() + "!";
//			return new ResponseMessage(message);
//		}
//
//	}
//
//	@PostMapping("/student/{studentId}")
//	public ResponseMessage uploadFileStudent(@RequestParam("file") MultipartFile file, @PathVariable Long studentId) {
//
//		String message = "";
//
//		try {
//
//			fileDBService.storeStudentFile(file, studentId);
//			message = "Uploaded the file: " + file.getOriginalFilename() + " for student ID: " + studentId
//					+ " successfully";
//			return new ResponseMessage(message);
//
//		} catch (IOException e) {
//			message = "could not upload the file: " + file.getOriginalFilename() + "!";
//			return new ResponseMessage(message);
//		}
//
//	}

	@GetMapping("/students")
	public List<FileUploadResponse> getFiles() {

		List<FileUploadResponse> files = documentService.getAllStudentDocumentsAsStream().map(dbFile -> {

			String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("").toUriString();

			return new FileUploadResponse(dbFile.getName(), fileDownloadUri, dbFile.getType(),
					dbFile.getContent().length);
		}).collect(Collectors.toList());

		return files;
	}

//	@GetMapping("/{id}")
//	public byte[] getFile(@PathVariable Long id) {
//		
//		StudentDocument document = documentService.getDocumentByDocumentID(id);
//		return document.getContent();
//	}

}
