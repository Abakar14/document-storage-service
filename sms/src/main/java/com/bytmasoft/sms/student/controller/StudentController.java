/**
 * @author Mahamat
 * Date 19.10.2020 : 13:38:32
 */
package com.bytmasoft.sms.student.controller;

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

	@Value("${school.name}")
	private String schoolName;

	@Value("${student.title}")
	private String title;

	@Autowired
	ShowStudentDetailsController editStudentController;

	private StageManager stageManager;

	@FXML
	private Label moduleNameLabel;

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
	private MenuButton classesMenuButton;

	@FXML
	private MenuItem showClassesMenuItem;

	@FXML
	private MenuItem addStudentToClassMenuItem;

	@FXML
	private MenuItem viewAllStudentsInClasseMenuItem;

	@FXML
	private AnchorPane rootContent;

	@FXML
	private ImageView schoolLogoImageView;

	@FXML
	private ImageView TitleImageView;

	@FXML
	void addStudentToClass(ActionEvent event) {
		System.out.println("add Student To Classe");
	}

	@FXML
	void viewAllStudentsForAClass(ActionEvent event) {
		System.out.println("view All Students For a Class");
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

		this.moduleNameLabel.setText(title);
	}

	/**
	 * @return the rootContent
	 */
	public AnchorPane getRootContent() {
		return rootContent;
	}

	@FXML
	void showClasses(ActionEvent event) {

		System.out.println("show Classes");
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		stageManager = applicationContext.getBean(StageManager.class);

		stageManager.setMainContent(rootContent);

		assert moduleNameLabel != null
				: "fx:id=\"pageTitleLabel\" was not injected: check your FXML file 'student.fxml'.";
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

		assert classesMenuButton != null
				: "fx:id=\"classesMenuButton\" was not injected: check your FXML file 'student.fxml'.";
		assert showClassesMenuItem != null
				: "fx:id=\"showClassesMenuItem\" was not injected: check your FXML file 'student.fxml'.";
		assert addStudentToClassMenuItem != null
				: "fx:id=\"addStudentToClassMenuItem\" was not injected: check your FXML file 'student.fxml'.";
		assert viewAllStudentsInClasseMenuItem != null
				: "fx:id=\"viewAllStudentsInClasseMenuItem\" was not injected: check your FXML file 'student.fxml'.";

		this.setTitlePage(title);

	}

}
