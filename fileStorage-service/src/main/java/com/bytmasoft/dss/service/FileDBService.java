package com.bytmasoft.dss.service;

import java.io.IOException;
import java.util.stream.Stream;

import org.springframework.web.multipart.MultipartFile;

import com.bytmasoft.domain.models.FileDB;

public interface FileDBService {

	FileDB storeFile(MultipartFile file) throws IOException;
	FileDB storeStudentFile(MultipartFile file, Long id) throws IOException;
	FileDB storeTeacherFile(MultipartFile file, Long id) throws IOException;
	FileDB storeManagerFile(MultipartFile file, Long id) throws IOException;
	FileDB storeEmployeeFile(MultipartFile file, Long id) throws IOException;
	
	FileDB updateFile(MultipartFile file, Long id) throws IOException;
	
	FileDB getFileByID(Long id);
	
	Stream <FileDB> getAllFiles();
	
	

	
}
