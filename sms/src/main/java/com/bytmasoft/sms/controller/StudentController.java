/**
 * @author Mahamat
 * Date 19.10.2020 : 13:38:32
 */
package com.bytmasoft.sms.controller;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import com.bytmasoft.StudentClient;
import com.bytmasoft.sms.view.FxmlView;
import com.bytmasoft.sms.view.StageManager;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import lombok.extern.log4j.Log4j2;

/**
 * @author Mahamat Date 19.10.2020 : 13:38:32
 */
@Log4j2
@Component
public class StudentController implements Initializable {

	@Autowired
	private ConfigurableApplicationContext applicationContext;

	@Value("${student.service.address}")
	private String surl;

	@Autowired
	ShowStudentDetailsController editStudentController;

	private StageManager stageManager;

	@Autowired
	private StudentClient studentClient;

	@FXML
	private MenuButton studentMenuButton;

	@FXML
	private MenuItem showStudentsMenuItem;

	@FXML
	private MenuItem addStudentsMenuItem;

	@FXML
	private AnchorPane rootContent;

	@FXML
	void showStudents(ActionEvent event) {
		System.out.println("show all Studentss");

		stageManager.addFileToMainContent(FxmlView.SHOWALLSTUDENTS.getFxmlFile());
	}

	@FXML
	void addNewStudent(ActionEvent event) {

		stageManager.addFileToMainContent(FxmlView.ADDSTUDENT.getFxmlFile());

	}

	/**
	 * @return the rootContent
	 */
	public AnchorPane getRootContent() {
		return rootContent;
	}

//	@FXML
//	private TableView<Student> studentTableview;
//
//	@FXML
//	private TextField filterField;
//
//	@FXML
//	private Accordion studentAccordion;
//
//	@FXML
//	private TitledPane showStudentsPane;
//	@FXML
//	private TitledPane addStudentPane;
//
//	@FXML
//	private TableColumn<Student, Long> idCol;
//	@FXML
//	private TableColumn<Student, String> firstnameCol;
//
//	@FXML
//	private TableColumn<Student, String> lastnameCol;
//
//	@FXML
//	private TableColumn<Student, String> emailCol;
//
//	@FXML
//	private TableColumn<Student, LocalDate> birthdayCol;
//
//	@FXML
//	private TableColumn<Student, Integer> ageCol;
//
//	@FXML
//	private TableColumn<Address, String> cityCol;
//
//	@FXML
//	private TableColumn<Address, String> streetCol;
//
//	@FXML
//	private TableColumn<Address, String> hausNrCol;
//
//	@FXML
//	private TableColumn<Student, String> statusCol;
//
//	@FXML
//	private Button refreshButton;
//
//	@FXML
//	private Button deleteButton;
//
//	
//	@FXML
//	private Button showStudentDetailsButton;
//
//	private final ObservableList<Student> dataList = FXCollections.observableArrayList();
//
//
//	private List<Student> studentlist;

//	/**
//	 * handle double Click
//	 */
//	private void onShowStudentDetails() {
//
//		Student student = studentTableview.getSelectionModel().getSelectedItem();
//		editStudentController.setStudent(student);
//		stageManager.switchAndWait(FxmlView.EDITSTUDENT);
//		
//	}
//
//	/**
//	 * 
//	 */
//	protected void refreshInhalt() {
//		studentTableview.refresh();
//		System.out.println("refresh table ...");
//	}

	/**
	 * 
	 * @throws JsonMappingException
	 * @throws JsonProcessingException
	 * @throws InterruptedException
	 * @throws ExecutionException
	 */

//	private void loadStudents()
//			throws JsonMappingException, JsonProcessingException, InterruptedException, ExecutionException {
//	
//		idCol.setCellValueFactory(new PropertyValueFactory<Student, Long>("id"));
//		firstnameCol.setCellValueFactory(new PropertyValueFactory<Student, String>("firstName"));
//		lastnameCol.setCellValueFactory(new PropertyValueFactory<Student, String>("lastName"));
//		emailCol.setCellValueFactory(new PropertyValueFactory<Student, String>("email"));
//		birthdayCol.setCellValueFactory(new PropertyValueFactory<Student, LocalDate>("birthday"));
//	
//		studentClient.getAllStudents("/students").subscribe(e-> {
//			
//			dataList.add(e);
//			refreshInhalt();
//		});
//
//		FilteredList<Student> filteredData = new FilteredList<Student>(dataList, b -> true);
//
//		filterField.textProperty().addListener((Observable, oldValue, newValue) -> {
//			filteredData.setPredicate(student -> {
//				if (newValue == null || newValue.isEmpty()) {
//					return true;
//				}
//
//				String lowerCaseFilter = newValue.toLowerCase();
//
//				if (student.getFirstName().toLowerCase().indexOf(lowerCaseFilter) != -1) {
//					return true;
//				} else if (student.getLastName().toLowerCase().indexOf(lowerCaseFilter) != -1) {
//					return true;
//
//				} else if (String.valueOf(student.getEmail()).indexOf(lowerCaseFilter) != -1)
//					return true;
//				else
//					return false;
//			});
//		});
//
//		SortedList<Student> sortedData = new SortedList<Student>(filteredData);
//		sortedData.comparatorProperty().bind(studentTableview.comparatorProperty());
//		studentTableview.setItems(sortedData);
//	}

//	private void addStudent() throws IOException {
//		Parent parent = stageManager.LoadViewNodeHierarchy(FxmlView.ADDSTUDENT.getFxmlFile());
//
//		AnchorPane pane = new AnchorPane();
//		pane.getChildren().add(parent);
//		this.addStudentPane.setContent(pane);
//	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		stageManager = applicationContext.getBean(StageManager.class);

		stageManager.setMainContent(rootContent);

		assert studentMenuButton != null
				: "fx:id=\"studentMenuButton\" was not injected: check your FXML file 'student.fxml'.";
		assert showStudentsMenuItem != null
				: "fx:id=\"showStudentsMenuItem\" was not injected: check your FXML file 'student.fxml'.";
		assert rootContent != null : "fx:id=\"rootContent\" was not injected: check your FXML file 'student.fxml'.";
		assert addStudentsMenuItem != null
				: "fx:id=\"addStudentsMenuItem\" was not injected: check your FXML file 'student.fxml'.";

//		assert studentAccordion != null
//				: "fx:id=\"studentAccordion\" was not injected: check your FXML file 'student.fxml'.";
//		assert showStudentsPane != null
//				: "fx:id=\"showStudentsPane\" was not injected: check your FXML file 'student.fxml'.";
//		assert addStudentPane != null
//				: "fx:id=\"addStudentPane\" was not injected: check your FXML file 'student.fxml'.";
//		assert filterField != null : "fx:id=\"filterField\" was not injected: check your FXML file 'student.fxml'.";
//		assert studentTableview != null
//				: "fx:id=\"studentTableview\" was not injected: check your FXML file 'student.fxml'.";
//		assert idCol != null : "fx:id=\"idCol\" was not injected: check your FXML file 'student.fxml'.";
//		assert firstnameCol != null : "fx:id=\"firstnameCol\" was not injected: check your FXML file 'student.fxml'.";
//		assert lastnameCol != null : "fx:id=\"lastnameCol\" was not injected: check your FXML file 'student.fxml'.";
//		assert emailCol != null : "fx:id=\"emailCol\" was not injected: check your FXML file 'student.fxml'.";
//		assert birthdayCol != null : "fx:id=\"birthdayCol\" was not injected: check your FXML file 'student.fxml'.";
//		assert ageCol != null : "fx:id=\"ageCol\" was not injected: check your FXML file 'student.fxml'.";
//		assert cityCol != null : "fx:id=\"cityCol\" was not injected: check your FXML file 'student.fxml'.";
//		assert streetCol != null : "fx:id=\"streetCol\" was not injected: check your FXML file 'student.fxml'.";
//		assert hausNrCol != null : "fx:id=\"hausNrCol\" was not injected: check your FXML file 'student.fxml'.";
//		assert statusCol != null : "fx:id=\"statusCol\" was not injected: check your FXML file 'student.fxml'.";
//		assert showStudentDetailsButton != null
//				: "fx:id=\"showStudentDetailsButton\" was not injected: check your FXML file 'student.fxml'.";
//		assert deleteButton != null : "fx:id=\"deleteButton\" was not injected: check your FXML file 'student.fxml'.";
//		assert refreshButton != null : "fx:id=\"refreshButton\" was not injected: check your FXML file 'student.fxml'.";

//		showStudentDetailsButton.setOnAction(e -> {
//
//			onShowStudentDetails();
//		});
//
//		refreshButton.setOnAction(e -> {
//			refreshInhalt();
//
//		});
//
//		try {
//			loadStudents();
//			addStudent();
//		} catch (JsonMappingException e) {
//			log.error(e.getMessage());
//			
//		} catch (JsonProcessingException e) {
//			log.error(e.getMessage());
//		} catch (InterruptedException e) {
//			log.error(e.getMessage());
//		} catch (ExecutionException e) {
//			log.error(e.getMessage());
//		} catch (IOException e) {
//			log.error(e.getMessage());
//		}
	}

}
