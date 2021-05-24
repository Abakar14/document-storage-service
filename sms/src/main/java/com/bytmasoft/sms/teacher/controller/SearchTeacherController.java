/**
 * 
 */
package com.bytmasoft.sms.teacher.controller;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.bytmasoft.StudentClient;
import com.bytmasoft.clientDomain.models.Student;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import lombok.extern.log4j.Log4j2;

/**
 * @author Mahamat Abakar created on 03.12.2020 at 20:05:37
 */
@Log4j2
@Component
public class SearchTeacherController implements Initializable {

	@Value("${serach.student.title}")
	private String title;

	@Autowired
	private StudentClient studentClient;

	@Autowired
	private TeacherController teacherController;

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

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
	private TableColumn<Student, String> registeredOncol;

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
	private JFXButton goButton;

	@FXML
	private JFXButton cancelButton;

	@FXML
	void cancel(ActionEvent event) {

	}

	@FXML
	void onSerach(ActionEvent event) {

	}

	@FXML
	void showDetails(ActionEvent event) {

	}

	@FXML
	void goSearch(ActionEvent event) {

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		teacherController.setTitlePage(title);

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

	}

}
