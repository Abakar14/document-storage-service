package com.bytmasoft.dss.service.impl;

import java.io.IOException;
import java.util.stream.Stream;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.bytmasoft.domain.models.Employee;
import com.bytmasoft.domain.models.FileDB;
import com.bytmasoft.domain.models.Manager;
import com.bytmasoft.domain.models.Student;
import com.bytmasoft.domain.models.Teacher;
import com.bytmasoft.dss.repositories.FileDBRepository;
import com.bytmasoft.dss.service.FileDBService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class FileDBServiceImpl implements FileDBService {

	@Autowired
	FileDBRepository fileDBRepository;
	
	@Override
	public FileDB storeFile(MultipartFile file) throws IOException {
		String filename = StringUtils.cleanPath(file.getOriginalFilename());
		FileDB fileDB = new FileDB(filename, file.getContentType(), file.getBytes());
		return fileDBRepository.save(fileDB);
	}


	@Override
	public FileDB getFileByID(Long id) {
		return this.fileDBRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("There is no file with this id:" + id));
	}

	@Override
	public Stream<FileDB> getAllFiles() {	
		return this.fileDBRepository.findAll().stream();
	}


	@Override
	public FileDB updateFile(MultipartFile file, Long id) throws IOException {
		
		FileDB oldFile = getFileByID(id);
		
		String filename = StringUtils.cleanPath(file.getOriginalFilename());
		
		oldFile.setFilename(filename);
		oldFile.setType(file.getContentType());
		oldFile.setData(file.getBytes());
		
		return fileDBRepository.save(oldFile);
		
	}


	@Override
	public FileDB storeStudentFile(MultipartFile file, Long id) throws IOException {

		Student student = new Student();
		student.setId(id);
		student.setFirstName("Mahamat");
		student.setLastName("Abka");
		student.setEmail("aa@web.de");
		String filename = StringUtils.cleanPath(file.getOriginalFilename());
		FileDB fileDB = new FileDB(filename, file.getContentType(), file.getBytes());
		fileDB.setStudent(student);
		return fileDBRepository.save(fileDB);
	}


	@Override
	public FileDB storeTeacherFile(MultipartFile file, Long id) throws IOException {
		Teacher  teacher = new Teacher();
		teacher.setId(id);
		String filename = StringUtils.cleanPath(file.getOriginalFilename());
		FileDB fileDB = new FileDB(filename, file.getContentType(), file.getBytes());
		fileDB.setTeacher(teacher);
		return fileDBRepository.save(fileDB);
	}


	@Override
	public FileDB storeManagerFile(MultipartFile file, Long id) throws IOException {
		Manager manager = new Manager();
		manager.setId(id);
		String filename = StringUtils.cleanPath(file.getOriginalFilename());
		FileDB fileDB = new FileDB(filename, file.getContentType(), file.getBytes());
		fileDB.setManager(manager);
		return fileDBRepository.save(fileDB);
	}


	@Override
	public FileDB storeEmployeeFile(MultipartFile file, Long id) throws IOException {
		Employee employee = new Employee();
		employee.setId(id);
		String filename = StringUtils.cleanPath(file.getOriginalFilename());
		FileDB fileDB = new FileDB(filename, file.getContentType(), file.getBytes());
		fileDB.setEmployee(employee);
		return fileDBRepository.save(fileDB);
	}

	

}
