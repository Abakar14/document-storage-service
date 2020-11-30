/**
 * @author Mahamat
 * Date 19.10.2020 : 14:03:54
 */
package com.bytmasoft.sms.controller;

import java.net.URL;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import com.bytmasoft.sms.view.StageManager;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * @author Mahamat
 * Date 19.10.2020 : 14:03:54
 */
@Component
public class TeacherController implements Initializable{

	@Autowired
	private ConfigurableApplicationContext applicationContext;
	
    @FXML
    private AnchorPane teacherAnchorPane;

	private StageManager stageManager;



	public void close() {
		Stage stage = (Stage) this.teacherAnchorPane.getScene().getWindow();
		stage.close();
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		  assert teacherAnchorPane != null : "fx:id=\"teacherAnchorPane\" was not injected: check your FXML file 'teacher.fxml'.";

			stageManager = applicationContext.getBean(StageManager.class);
			
			stageManager.addTeacherController(this);
		
	}
	
}
