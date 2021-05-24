/**
 * @author Mahamat
 * Date 19.10.2020 : 14:03:54
 */
package com.bytmasoft.sms.teacher.controller;

import java.net.URL;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import com.bytmasoft.sms.view.FxmlView;
import com.bytmasoft.sms.view.StageManager;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * @author Mahamat Date 19.10.2020 : 14:03:54
 */
@Component
public class TeacherController implements Initializable {

	@Autowired
	private ConfigurableApplicationContext applicationContext;

	@FXML
	private AnchorPane teacherAnchorPane;

	public void close() {
		Stage stage = (Stage) this.teacherAnchorPane.getScene().getWindow();
		stage.close();
	}

	@Value("${student.service.address}")
	private String surl;

	@Value("${school.name}")
	private String schoolName;

	@Value("${student.title}")
	private String title;

	@Autowired
	ShowTeacherDetailsController editStudentController;

	private StageManager stageManager;

	@FXML
	private Label pageTitleLabel;

	@FXML
	private Label schoolNameLabel;
	@FXML
	private MenuButton studentMenuButton;

	@FXML
	private MenuItem showStudentsMenuItem;

	@FXML
	private MenuItem searchStudent;

	@FXML
	private MenuItem addStudentsMenuItem;

	@FXML
	private MenuItem addStudentToClass;

	@FXML
	private MenuItem viewAllStudentsInClass;

	@FXML
	private AnchorPane rootContent;

	@FXML
	private ImageView schoolLogoImageView;

	@FXML
	private ImageView TitleImageView;

	@FXML
	void addStudentToClass(ActionEvent event) {

	}

	@FXML
	void viewAllStudentsForAClass(ActionEvent event) {

	}

	@FXML
	void showStudents(ActionEvent event) {
		System.out.println("show all Studentss");

		stageManager.addFileToMainContent(FxmlView.SHOWALLSTUDENTS.getFxmlFile());
	}

	@FXML
	void addNewStudent(ActionEvent event) {

		stageManager.addFileToMainContent(FxmlView.ADDSTUDENT.getFxmlFile());

	}

	@FXML
	void searchStudent(ActionEvent event) {

		stageManager.addFileToMainContent(FxmlView.SEARCHSTUDENT.getFxmlFile());
	}

	protected void setTitlePage(String title) {

		this.pageTitleLabel.setText(title);
	}

	/**
	 * @return the rootContent
	 */
	public AnchorPane getRootContent() {
		return rootContent;
	}

	@FXML
	private MenuItem showAllTeachersMenuItem;

	@FXML
	private MenuItem addTeachersMenuItem;

	@FXML
	void addTeacher(ActionEvent event) {

	}

	@FXML
	void showAllTeachers(ActionEvent event) {

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		assert teacherAnchorPane != null
				: "fx:id=\"teacherAnchorPane\" was not injected: check your FXML file 'teacher.fxml'.";

		stageManager = applicationContext.getBean(StageManager.class);
		stageManager.addTeacherController(this);

		stageManager.setMainContent(rootContent);

		assert teacherAnchorPane != null
				: "fx:id=\"teacherAnchorPane\" was not injected: check your FXML file 'teacher.fxml'.";
		assert showAllTeachersMenuItem != null
				: "fx:id=\"showAllTeachersMenuItem\" was not injected: check your FXML file 'teacher.fxml'.";
		assert addTeachersMenuItem != null
				: "fx:id=\"addTeachersMenuItem\" was not injected: check your FXML file 'teacher.fxml'.";

		assert pageTitleLabel != null
				: "fx:id=\"pageTitleLabel\" was not injected: check your FXML file 'student.fxml'.";
		assert schoolNameLabel != null
				: "fx:id=\"schoolNameLabel\" was not injected: check your FXML file 'student.fxml'.";
		assert schoolLogoImageView != null
				: "fx:id=\"schoolLogoImageView\" was not injected: check your FXML file 'student.fxml'.";
		assert TitleImageView != null
				: "fx:id=\"TitleImageView\" was not injected: check your FXML file 'student.fxml'.";
		assert studentMenuButton != null
				: "fx:id=\"studentMenuButton\" was not injected: check your FXML file 'student.fxml'.";
		assert showStudentsMenuItem != null
				: "fx:id=\"showStudentsMenuItem\" was not injected: check your FXML file 'student.fxml'.";
		assert addStudentsMenuItem != null
				: "fx:id=\"addStudentsMenuItem\" was not injected: check your FXML file 'student.fxml'.";
		assert addStudentToClass != null
				: "fx:id=\"addStudentToClass\" was not injected: check your FXML file 'student.fxml'.";
		assert viewAllStudentsInClass != null
				: "fx:id=\"viewAllStudentsInClass\" was not injected: check your FXML file 'student.fxml'.";
		assert searchStudent != null : "fx:id=\"searchStudent\" was not injected: check your FXML file 'student.fxml'.";
		assert rootContent != null : "fx:id=\"rootContent\" was not injected: check your FXML file 'student.fxml'.";

		this.schoolNameLabel.setText(schoolName);
		this.setTitlePage(title);

	}

}
