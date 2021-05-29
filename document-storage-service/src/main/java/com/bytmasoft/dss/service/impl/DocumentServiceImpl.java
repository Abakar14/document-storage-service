/**
 * 
 */
package com.bytmasoft.dss.service.impl;

import java.io.IOException;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.reactive.function.client.WebClient;

import com.bytmasoft.domain.models.EmployeeDocument;
import com.bytmasoft.domain.models.ManagerDocument;
import com.bytmasoft.domain.models.Student;
import com.bytmasoft.domain.models.StudentDocument;
import com.bytmasoft.domain.models.TeacherDocument;
import com.bytmasoft.dss.repositories.EmployeeDocumentRepository;
import com.bytmasoft.dss.repositories.ManagerDocumentRepository;
import com.bytmasoft.dss.repositories.StudentDocumentRepository;
import com.bytmasoft.dss.repositories.TeacherDocumentRepository;
import com.bytmasoft.dss.service.DocumentService;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

/**
 * @author Mahamat Abakar created on 13.11.2020 at 09:37:30
 */
@RequiredArgsConstructor
@Service
public class DocumentServiceImpl implements DocumentService {

	@Autowired
	StudentDocumentRepository studentDocumentRepository;

	@Autowired
	TeacherDocumentRepository teacherDocumentRepository;

	@Autowired
	ManagerDocumentRepository managerDocumentRepository;

	@Autowired
	EmployeeDocumentRepository employeeDocumentRepository;

	// for get all instance client in eurka server
//	@Autowired
//	DiscoveryClient  discoveryClient; 

	@Value("${spring.application.name}")
	private String appName;

	@Value("${student.service.address}")
	private String studentUrl;

	@Value("${teacher.service.address}")
	private String teacherUrl;

	@Value("${manager.service.address}")
	private String managerUrl;

	@Value("${employee.service.address}")
	private String employeeUrl;

	@Autowired
	private final WebClient.Builder webClientBuilder;

	private WebClient webClient;

	/**
	 * 
	 */
	public WebClient studentWebClient() {

		return webClientBuilder.baseUrl(studentUrl)
				.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
				.defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE).build();
	}

	@Override
	public StudentDocument storeStudentDocument(MultipartFile file, Long student_id) throws IOException {

		Student student = this.getStudent(student_id).block();

		StudentDocument document = this.getStudentDocument(file, student);
		return studentDocumentRepository.save(document);
	}

	private Mono<Student> getStudent(Long student_id) {

		this.webClient = studentWebClient();

		return webClient.get().uri("/" + student_id).retrieve().bodyToMono(Student.class);
	}

	@Override
	public TeacherDocument storeTeacherDocument(MultipartFile file, Long teacher_id) throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ManagerDocument storeManagerDocument(MultipartFile file, Long manager_id) throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EmployeeDocument storeEmployeeDocument(MultipartFile file, Long employee_id) throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public StudentDocument updateStudentDocument(MultipartFile file, Long id) throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TeacherDocument updateTeacherDocument(MultipartFile file, Long id) throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ManagerDocument updateManagerDocument(MultipartFile file, Long id) throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EmployeeDocument updateEmployeeDocument(MultipartFile file, Long id) throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public StudentDocument getStudentDocumentByDocumentID(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TeacherDocument getTeacherDocumentByDocumentID(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ManagerDocument getManagerDocumentByDocumentID(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EmployeeDocument getEmployeeDocumentByDocumentID(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Stream<StudentDocument> getAllStudentDocumentsAsStream() {

		return studentDocumentRepository.findAll().stream();
	}

	@Override
	public List<StudentDocument> getAllStudentDocumentsAsList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Stream<TeacherDocument> getAllTeacherDocumentsAsStream() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TeacherDocument> getAllTeacherDocumentsAsList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Stream<ManagerDocument> getAllManagerDocumentsAsStream() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ManagerDocument> getAllManagerDocumentsAsList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Stream<EmployeeDocument> getAllEmployeeDocumentsAsStream() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<EmployeeDocument> getAllEmployeeDocumentsAsList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<StudentDocument> getDocumentsByStudnetID(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TeacherDocument> getDocumentsByTeacherID(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ManagerDocument> getDocumentsByManagerID(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<EmployeeDocument> getDocumentsByEmployeeID(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

//	private void updateStudentDocument(StudentDocument document, MultipartFile file) throws IOException {
//
//		document.setStatus("A");
//		document.setUpdatedProg(appName);
//		document.setUpdatedOn(LocalDateTime.now());
//		document.setName(this.getFilename(file));
//		document.setType(file.getContentType());
//		document.setContent(file.getBytes());
//		
//	}

	private StudentDocument getStudentDocument(MultipartFile file, Student student) throws IOException {

		StudentDocument document = new StudentDocument();
		document.setStatus("A");
		document.setInsertedProg(appName);
		document.setName(this.getFilename(file));
		document.setType(file.getContentType());
		document.setContent(file.getBytes());
		document.setStudent(student);

		return document;
	}

	private String getFilename(MultipartFile file) {
		return StringUtils.cleanPath(file.getOriginalFilename());
	}

//	@Override
//	public StudentDocument storeDocument(MultipartFile file) throws IOException {
//
//		StudentDocument document = this.getUserDocument(file);
//		return documentRepository.save(document);
//	}

//	@Override
//	public StudentDocument storeStudentDocument(MultipartFile file, Student student) throws IOException {
//		StudentDocument document = this.getUserDocument(file);
//		document.setStudent(student);
//		return documentRepository.save(document);
//	}
//
//	@Override
//	public StudentDocument storeTeacherDocument(MultipartFile file, Teacher teacher) throws IOException {
//		StudentDocument document = this.getUserDocument(file);
//		document.setTeacher(teacher);
//		return documentRepository.save(document);
//	}
//
//	@Override
//	public StudentDocument storeManagerDocument(MultipartFile file, Manager manager) throws IOException {
//		StudentDocument document = this.getUserDocument(file);
//		document.setManager(manager);
//		return documentRepository.save(document);
//	}
//
//	@Override
//	public StudentDocument storeEmployeeDocument(MultipartFile file, Employee employee) throws IOException {
//		StudentDocument document = this.getUserDocument(file);
//		document.setEmployee(employee);
//		return documentRepository.save(document);
//	}
//
//	@Override
//	public StudentDocument updateFile(MultipartFile file, Long id) throws IOException {
//		StudentDocument document = documentRepository.getOne(id);
//		updateDocument(document, file);
//		return this.documentRepository.save(document);
//	}
//
//	@Override
//	public StudentDocument getDocumentByDocumentID(Long id) {
//		
//		return this.documentRepository.getOne(id);
//	}

//	@Override
//	public Stream<StudentDocument> getAllDocumentsAsStream() {
//		
//		return this.documentRepository.findAll().stream();
//	}
//
//	@Override
//	public List<StudentDocument> getAllDocumentsAsList() {
//		
//		return this.documentRepository.findAll();
//	}

}
