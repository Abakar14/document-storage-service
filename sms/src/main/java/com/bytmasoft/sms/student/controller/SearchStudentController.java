/**
 * 
 */
package com.bytmasoft.sms.student.controller;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import com.bytmasoft.StudentClient;
import com.bytmasoft.clientDomain.models.Student;
import com.bytmasoft.sms.utils.DateTimePicker;
import com.bytmasoft.sms.view.FxmlView;
import com.bytmasoft.sms.view.StageManager;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lombok.extern.log4j.Log4j2;

/**
 * @author Mahamat Abakar created on 03.12.2020 at 20:05:37
 */
@Log4j2
@Component
public class SearchStudentController implements Initializable {

	@Autowired
	private ConfigurableApplicationContext applicationContext;

	@Value("${serach.student.title}")
	private String title;

	@Value("${student.service.address}")
	private String surl;

	@Autowired
	private StudentClient studentClient;

	@Autowired
	private StudentController studentController;

	private StageManager stageManager;

	@Autowired
	ShowStudentDetailsController showStudentDetailsController;

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	private final ObservableList<Student> dataList = FXCollections.observableArrayList();

	private DateTimePicker registeredOnTimePicker = new DateTimePicker();

	@Value("${dss.dateTime.format}")
	private String dateTimeFormat;

	@FXML
	private TableColumn<Student, Long> idCol;

	@FXML
	private TableColumn<Student, String> rollNoCol;

	@FXML
	private TableColumn<Student, String> classCol;

	@FXML
	private TableColumn<Student, Long> familyIdCol;

	@FXML
	private TableColumn<Student, String> phoneCol;

	@FXML
	private TableColumn<Student, LocalDateTime> registeredOncol;

	@FXML
	private TableColumn<Student, LocalDate> birthdayCol;

	@FXML
	private TableColumn<Student, String> firstnameCol;

	@FXML
	private TableColumn<Student, String> lastnameCol;

	@FXML
	private JFXTextField searchTextField;

	@FXML
	private JFXButton searchButton;

	@FXML
	private JFXTextField rollNoTextField;

	@FXML
	private JFXButton showDetailsButton;

	@FXML
	private JFXComboBox<?> classComboBox;

	@FXML
	private JFXComboBox<?> sectionComboBox;

	@FXML
	private JFXTextField familyIdTextField;

	@FXML
	private JFXComboBox<?> DisplayOnlyComboBox;

	@FXML
	private TableView<Student> studentTableView;

	@FXML
	private JFXButton goButton;

	@FXML
	private JFXButton cancelButton;

	@FXML
	void cancel(ActionEvent event) {

		System.out.println("Cancel");
	}

	@FXML
	void onSerach(ActionEvent event) {

		dataList.clear();

		findByLastname();

	}

	@FXML
	void goSearch(ActionEvent event) {

	}

	@FXML
	void showDetails(ActionEvent event) {

		System.out.println("ShowDetails");

		System.out.println("show Details ...");
		Student student = studentTableView.getSelectionModel().getSelectedItem();
		showStudentDetailsController.setStudent(student);
		stageManager.addFileToMainContent(FxmlView.SHOWSTUDENTDETAILS.getFxmlFile());
	}

	private void findByFirstname() {
		studentClient.findStudentByName("/students/student?firstname=" + searchTextField.getText()).subscribe(s -> {
			dataList.add(s);
		});

	}

	private void findByLastname() {

		studentClient.findStudentByName(
				"/students/student?lastname=" + searchTextField.getText() + "&firstname=" + searchTextField.getText())
				.subscribe(s -> {
					dataList.add(s);

				});

	}

	private void initTable() {

		idCol.setCellValueFactory(new PropertyValueFactory<Student, Long>("id"));
		firstnameCol.setCellValueFactory(new PropertyValueFactory<Student, String>("firstName"));
		lastnameCol.setCellValueFactory(new PropertyValueFactory<Student, String>("lastName"));

		birthdayCol.setCellValueFactory(new PropertyValueFactory<Student, LocalDate>("birthday"));
		phoneCol.setCellValueFactory(new PropertyValueFactory<Student, String>("mobilePhoneNr"));

		registeredOncol.setCellValueFactory(new PropertyValueFactory<Student, LocalDateTime>("createdOn"));

		/**
		 * if (!DSSValidation.isObjectNull(student.getCreatedOn())) {
		 * 
		 * registerdOnTimePicker.setDateTimeValue(student.getCreatedOn()); }
		 * 
		 * registerdOnTimePicker.setEditable(false);
		 * registerdOnTimePicker.setPrefWidth(134);
		 * registerdOnTextField.setText(DSSFormat
		 * .formateLocalDateTimeToString(registerdOnTimePicker.getDateTimeValue(),
		 * dateTimeFormat));
		 */

		FilteredList<Student> filteredData = new FilteredList<Student>(dataList, b -> true);

//		rollNoCol.textProperty().addListener((Observable, oldValue, newValue) -> {
//			filteredData.setPredicate(student -> {
//				if (newValue == null || newValue.isEmpty()) {
//					return true;
//				}
//
//				String lowerCaseFilter = newValue.toLowerCase();
//
////				if (student.getRollNumber.toLowerCase().indexOf(lowerCaseFilter) != -1) {
////					return true;
////				}  else
//				if (String.valueOf(student.getEmail()).indexOf(lowerCaseFilter) != -1)
//					return true;
//				else
//					return false;
//
//			});
//		});

		SortedList<Student> sortedData = new SortedList<Student>(filteredData);
		sortedData.comparatorProperty().bind(studentTableView.comparatorProperty());
		studentTableView.setItems(sortedData);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		stageManager = applicationContext.getBean(StageManager.class);
		studentController.setTitlePage(title);
		initTable();

		assert idCol != null : "fx:id=\"idCol\" was not injected: check your FXML file 'search-student.fxml'.";
		assert firstnameCol != null
				: "fx:id=\"firstnameCol\" was not injected: check your FXML file 'search-student.fxml'.";
		assert lastnameCol != null
				: "fx:id=\"lastnameCol\" was not injected: check your FXML file 'search-student.fxml'.";
		assert rollNoCol != null : "fx:id=\"rollNoCol\" was not injected: check your FXML file 'search-student.fxml'.";
		assert classCol != null : "fx:id=\"classCol\" was not injected: check your FXML file 'search-student.fxml'.";
		assert familyIdCol != null
				: "fx:id=\"familyIdCol\" was not injected: check your FXML file 'search-student.fxml'.";
		assert phoneCol != null : "fx:id=\"phoneCol\" was not injected: check your FXML file 'search-student.fxml'.";
		assert registeredOncol != null
				: "fx:id=\"registeredOncol\" was not injected: check your FXML file 'search-student.fxml'.";
		assert birthdayCol != null
				: "fx:id=\"birthdayCol\" was not injected: check your FXML file 'search-student.fxml'.";
		assert searchTextField != null
				: "fx:id=\"searchTextField\" was not injected: check your FXML file 'search-student.fxml'.";
		assert searchButton != null
				: "fx:id=\"searchButton\" was not injected: check your FXML file 'search-student.fxml'.";
		assert rollNoTextField != null
				: "fx:id=\"rollNoTextField\" was not injected: check your FXML file 'search-student.fxml'.";
		assert classComboBox != null
				: "fx:id=\"classComboBox\" was not injected: check your FXML file 'search-student.fxml'.";
		assert sectionComboBox != null
				: "fx:id=\"sectionComboBox\" was not injected: check your FXML file 'search-student.fxml'.";
		assert familyIdTextField != null
				: "fx:id=\"familyIdTextField\" was not injected: check your FXML file 'search-student.fxml'.";
		assert DisplayOnlyComboBox != null
				: "fx:id=\"DisplayOnlyComboBox\" was not injected: check your FXML file 'search-student.fxml'.";
		assert goButton != null : "fx:id=\"goButton\" was not injected: check your FXML file 'search-student.fxml'.";
		assert showDetailsButton != null
				: "fx:id=\"showDetailsButton\" was not injected: check your FXML file 'search-student.fxml'.";
		assert cancelButton != null
				: "fx:id=\"cancelButton\" was not injected: check your FXML file 'search-student.fxml'.";
		assert studentTableView != null
				: "fx:id=\"studentTableView\" was not injected: check your FXML file 'search-student.fxml'.";

	}

}
