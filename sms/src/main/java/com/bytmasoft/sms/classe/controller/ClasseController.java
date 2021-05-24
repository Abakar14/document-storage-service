/**
 * @author Mahamat
 * Date 19.10.2020 : 14:04:07
 */
package com.bytmasoft.sms.classe.controller;

import java.net.URL;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;

/**
 * @author Mahamat Date 19.10.2020 : 14:04:07
 */
@Component
public class ClasseController {

	@Autowired
	private ConfigurableApplicationContext applicationContext;

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private MenuButton classesMenuButton;

	@FXML
	private MenuItem showClassesMenuItem;

	@FXML
	private MenuItem addNewClasseMenuItem;

	@FXML
	private MenuItem showClasseReportMenuItem;

	@FXML
	void addSNewClasse(ActionEvent event) {
		System.out.println("Add new Class...");
	}

	@FXML
	void showClasses(ActionEvent event) {
		System.out.println("show classes...");
	}

	@FXML
	void viewClasseReport(ActionEvent event) {

		System.out.println("view ClasseReport...");
	}

	@FXML
	void initialize() {

		assert classesMenuButton != null
				: "fx:id=\"classesMenuButton\" was not injected: check your FXML file 'class.fxml'.";
		assert showClassesMenuItem != null
				: "fx:id=\"showClassesMenuItem\" was not injected: check your FXML file 'class.fxml'.";
		assert addNewClasseMenuItem != null
				: "fx:id=\"addNewClasseMenuItem\" was not injected: check your FXML file 'class.fxml'.";
		assert showClasseReportMenuItem != null
				: "fx:id=\"showClasseReportMenuItem\" was not injected: check your FXML file 'class.fxml'.";

	}

}
