/**
 * @author Mahamat
 * Date 19.10.2020 : 13:38:32
 */
package com.bytmasoft.sms.controller;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import com.bytmasoft.StudentClient;
import com.bytmasoft.clientDomain.models.Address;
import com.bytmasoft.clientDomain.models.BaseUser;
import com.bytmasoft.clientDomain.models.Student;
import com.bytmasoft.sms.utils.DSSValidation;
import com.bytmasoft.sms.view.FxmlView;
import com.bytmasoft.sms.view.StageManager;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Accordion;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TitledPane;
import javafx.scene.control.cell.PropertyValueFactory;
import lombok.extern.log4j.Log4j2;

/**
 * @author Mahamat Date 19.10.2020 : 13:38:32
 */
@Log4j2
@Component
public class ShowAllStudentsController implements Initializable {

	@Autowired
	private ConfigurableApplicationContext applicationContext;

	private StageManager stageManager;

	DSSValidation dssValidation;

	@Value("${student.service.address}")
	private String surl;

	@Autowired
	private StudentClient studentClient;

	@Autowired
	ShowStudentDetailsController showStudentDetailsController;

	private final ObservableList<Student> dataList = FXCollections.observableArrayList();

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private TableView<Student> studentTableview;

	@FXML
	private Accordion studentAccordion;

	@FXML
	private TitledPane showStudentsPane;
	@FXML
	private TitledPane addStudentPane;

	@FXML
	private TableColumn<Student, Long> idCol;
	@FXML
	private TableColumn<Student, String> firstnameCol;

	@FXML
	private TableColumn<Student, String> lastnameCol;

	@FXML
	private TableColumn<Student, String> emailCol;

	@FXML
	private TableColumn<Student, LocalDate> birthdayCol;

	@FXML
	private TableColumn<Student, Integer> ageCol;

	@FXML
	private TableColumn<Address, String> cityCol;

	@FXML
	private TableColumn<Address, String> streetCol;

	@FXML
	private TableColumn<Address, String> hausNrCol;

	@FXML
	private TableColumn<Student, String> statusCol;

	@FXML
	private JFXButton showStudentDetailsButton;

	@FXML
	private JFXTextField filterField;

	@FXML
	private JFXButton deleteStudentButton;

	@FXML
	void deleteStudent(ActionEvent event) {

	}

	@FXML
	void showStudentDetails(ActionEvent event) {

		System.out.println("show Details ...");
		Student student = studentTableview.getSelectionModel().getSelectedItem();
		showStudentDetailsController.setStudent(student);
//		showStudentDetailsButton.getScene().getWindow().hide();

		stageManager.addFileToMainContent(FxmlView.SHOWSTUDENTDETAILS.getFxmlFile());

	}

	/**
	 * @return the showStudentDetailsButton
	 */
	public JFXButton getShowStudentDetailsButton() {
		return showStudentDetailsButton;
	}

	/**
	 * 
	 * @throws JsonMappingException
	 * @throws JsonProcessingException
	 * @throws InterruptedException
	 * @throws ExecutionException
	 */

	private void loadStudents()
			throws JsonMappingException, JsonProcessingException, InterruptedException, ExecutionException {

		idCol.setCellValueFactory(new PropertyValueFactory<Student, Long>("id"));
		firstnameCol.setCellValueFactory(new PropertyValueFactory<Student, String>("firstName"));
		lastnameCol.setCellValueFactory(new PropertyValueFactory<Student, String>("lastName"));
		emailCol.setCellValueFactory(new PropertyValueFactory<Student, String>("email"));
		birthdayCol.setCellValueFactory(new PropertyValueFactory<Student, LocalDate>("birthday"));
		ageCol.setCellValueFactory(new PropertyValueFactory<Student, Integer>("age"));

		// TODO muss ein Meldung geben wenn keine Antwort kommt
		// auch die antwort zeit muss bekannt sein
		// dann soll meldung geben .Abakar Mahamat 25.11.2020 10:17:47

		if (dataList.isEmpty()) {

			studentClient.getAllStudents("/students").subscribe(e -> {

				dataList.add(e);

			});
		} else {

			List<BaseUser> currentList = new ArrayList<BaseUser>();
			List<BaseUser> oldList = new ArrayList<BaseUser>();

			oldList.addAll(dataList);

			currentList.addAll(studentClient.getAllStudents("/students").collectList().block());

			if (!dssValidation.isListsEqual(currentList, oldList)) {

				dataList.clear();
				currentList.forEach(u -> {
					dataList.add((Student) u);

				});
			}

		}

		FilteredList<Student> filteredData = new FilteredList<Student>(dataList, b -> true);

		filterField.textProperty().addListener((Observable, oldValue, newValue) -> {
			filteredData.setPredicate(student -> {
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}

				String lowerCaseFilter = newValue.toLowerCase();

				if (student.getFirstName().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true;
				} else if (student.getLastName().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true;

				} else if (String.valueOf(student.getEmail()).indexOf(lowerCaseFilter) != -1)
					return true;
				else
					return false;
			});
		});

		SortedList<Student> sortedData = new SortedList<Student>(filteredData);
		sortedData.comparatorProperty().bind(studentTableview.comparatorProperty());
		studentTableview.setItems(sortedData);
	}

	protected void refreshStudentsTableView() {
		studentTableview.refresh();
		System.out.println("refresh table ...");
	}

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
		dssValidation = new DSSValidation();

		assert studentTableview != null
				: "fx:id=\"studentTableview\" was not injected: check your FXML file 'showStudents.fxml'.";
		assert idCol != null : "fx:id=\"idCol\" was not injected: check your FXML file 'showStudents.fxml'.";
		assert firstnameCol != null
				: "fx:id=\"firstnameCol\" was not injected: check your FXML file 'showStudents.fxml'.";
		assert lastnameCol != null
				: "fx:id=\"lastnameCol\" was not injected: check your FXML file 'showStudents.fxml'.";
		assert emailCol != null : "fx:id=\"emailCol\" was not injected: check your FXML file 'showStudents.fxml'.";
		assert birthdayCol != null
				: "fx:id=\"birthdayCol\" was not injected: check your FXML file 'showStudents.fxml'.";
		assert ageCol != null : "fx:id=\"ageCol\" was not injected: check your FXML file 'showStudents.fxml'.";
		assert cityCol != null : "fx:id=\"cityCol\" was not injected: check your FXML file 'showStudents.fxml'.";
		assert streetCol != null : "fx:id=\"streetCol\" was not injected: check your FXML file 'showStudents.fxml'.";
		assert showStudentDetailsButton != null
				: "fx:id=\"showStudentDetailsButton\" was not injected: check your FXML file 'showStudents.fxml'.";
		assert filterField != null
				: "fx:id=\"filterField\" was not injected: check your FXML file 'showStudents.fxml'.";
		assert deleteStudentButton != null
				: "fx:id=\"deleteStudentButton\" was not injected: check your FXML file 'showStudents.fxml'.";

		try {
//			dataList.clear();
			loadStudents();
		} catch (JsonProcessingException | InterruptedException | ExecutionException e) {
//			JOptionPane.showInternalMessageDialog(null, "Can't load students from Service", "Student-Service",
//					JOptionPane.ERROR_MESSAGE);
			System.out.println("Fehler beim load users ....");
			log.error(e.getMessage());
		}
//
//		assert studentMenuButton != null
//				: "fx:id=\"studentMenuButton\" was not injected: check your FXML file 'student.fxml'.";
//		assert showStudentsMenuItem != null
//				: "fx:id=\"showStudentsMenuItem\" was not injected: check your FXML file 'student.fxml'.";
//		assert mainContent != null : "fx:id=\"mainContent\" was not injected: check your FXML file 'student.fxml'.";

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
