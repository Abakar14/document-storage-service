/**
 * 
 */
package com.bytmasoft.dss.service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.web.multipart.MultipartFile;

import com.bytmasoft.domain.models.EmployeeDocument;
import com.bytmasoft.domain.models.ManagerDocument;
import com.bytmasoft.domain.models.TeacherDocument;
import com.bytmasoft.domain.models.StudentDocument;

/**
 * @author Mahamat Abakar
 * created on 13.11.2020 at 09:37:14
 */
public interface DocumentService {
	
	StudentDocument storeStudentDocument(MultipartFile file, Long student_id) throws IOException;
	TeacherDocument storeTeacherDocument(MultipartFile file, Long teacher_id) throws IOException;
	ManagerDocument storeManagerDocument(MultipartFile file, Long manager_id) throws IOException;
	EmployeeDocument storeEmployeeDocument(MultipartFile file, Long employee_id) throws IOException;
	
	StudentDocument updateStudentDocument(MultipartFile file, Long id) throws IOException;
	TeacherDocument updateTeacherDocument(MultipartFile file, Long id) throws IOException;
	ManagerDocument updateManagerDocument(MultipartFile file, Long id) throws IOException;
	EmployeeDocument updateEmployeeDocument(MultipartFile file, Long id) throws IOException;
	
	StudentDocument getStudentDocumentByDocumentID(Long id);
	TeacherDocument getTeacherDocumentByDocumentID(Long id);
	ManagerDocument getManagerDocumentByDocumentID(Long id);
	EmployeeDocument getEmployeeDocumentByDocumentID(Long id);
	
	Stream <StudentDocument> getAllStudentDocumentsAsStream();
	List<StudentDocument> getAllStudentDocumentsAsList();
	
	Stream <TeacherDocument> getAllTeacherDocumentsAsStream();
	List<TeacherDocument> getAllTeacherDocumentsAsList();
	
	Stream <ManagerDocument> getAllManagerDocumentsAsStream();
	List<ManagerDocument> getAllManagerDocumentsAsList();
	
	Stream <EmployeeDocument> getAllEmployeeDocumentsAsStream();
	List<EmployeeDocument> getAllEmployeeDocumentsAsList();
	
	List<StudentDocument> getDocumentsByStudnetID(Long id);
	List<TeacherDocument> getDocumentsByTeacherID(Long id);
	List<ManagerDocument> getDocumentsByManagerID(Long id);
	List<EmployeeDocument> getDocumentsByEmployeeID(Long id);
	
	
}
